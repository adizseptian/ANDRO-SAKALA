package id.sakala.merchant.data

import androidx.room.withTransaction
import id.sakala.merchant.domain.InventoryEngine
import id.sakala.merchant.domain.InventoryPool
import id.sakala.merchant.domain.PeriodRules
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

class SakalaRepository(private val db: AppDatabase) {
    val products: Flow<List<ProductEntity>> = db.products().observeAll()
    val events: Flow<List<LedgerEventEntity>> = db.ledger().observeAll()

    suspend fun addProduct(name: String, unit: String, unitsPerPackage: Long, sellingPrice: Long) {
        require(name.isNotBlank())
        require(unitsPerPackage > 0 && sellingPrice >= 0)
        db.products().insert(
            ProductEntity(
                id = UUID.randomUUID().toString(),
                name = name.trim(),
                baseUnit = unit.trim().ifBlank { "pcs" },
                unitsPerPackage = unitsPerPackage,
                sellingPrice = sellingPrice,
                createdAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordPurchase(productId: String, units: Long, totalCost: Long, note: String = "") {
        require(units > 0 && totalCost >= 0)
        db.withTransaction {
            val product = db.products().findById(productId) ?: error("Produk tidak ditemukan.")
            val pool = InventoryPool(product.knownUnits, product.knownTotalCost, product.unknownUnits)
            val next = InventoryEngine.addKnownPurchase(pool, units, totalCost)
            db.products().updateStock(productId, next.knownUnits, next.knownTotalCost, next.unknownUnits)
            db.ledger().insert(event("PURCHASE", productId, units, totalCost, note))
        }
    }

    suspend fun recordUnknownOpeningStock(productId: String, units: Long, note: String = "") {
        require(units > 0)
        db.withTransaction {
            val product = db.products().findById(productId) ?: error("Produk tidak ditemukan.")
            val pool = InventoryPool(product.knownUnits, product.knownTotalCost, product.unknownUnits)
            val next = InventoryEngine.addUnknownStock(pool, units)
            db.products().updateStock(productId, next.knownUnits, next.knownTotalCost, next.unknownUnits)
            db.ledger().insert(event("UNKNOWN_OPENING_STOCK", productId, units, null, note))
        }
    }

    suspend fun recordExpense(amount: Long, funding: String, note: String) {
        require(amount > 0)
        require(funding in setOf("UANG_TOKO", "UANG_PRIBADI", "BELUM_TAHU"))
        db.ledger().insert(event("EXPENSE", null, null, amount, note, funding))
    }

    suspend fun recordDeposit(amount: Long, note: String = "") {
        require(amount > 0) { "Setoran harus lebih dari nol." }
        db.ledger().insert(event("DEPOSIT", null, null, amount, note))
    }

    suspend fun closePeriod(date: LocalDate) {
        val start = PeriodRules.periodStart(date).toString()
        db.withTransaction {
            check(db.ledger().closure(start) == null) { "Periode sudah ditutup." }
            val rows = db.ledger().eventsForPeriod(start)
            val snapshot = rows.joinToString("|") { "${it.id}:${it.type}:${it.amount}:${it.quantity}" }
            val digest = java.security.MessageDigest.getInstance("SHA-256")
                .digest(snapshot.toByteArray())
                .joinToString("") { "%02x".format(it) }
            db.ledger().closePeriod(
                PeriodClosureEntity(start, System.currentTimeMillis(), snapshot, digest)
            )
        }
    }

    private fun event(
        type: String,
        productId: String?,
        quantity: Long?,
        amount: Long?,
        note: String,
        funding: String? = null
    ): LedgerEventEntity {
        val date = LocalDate.now(PeriodRules.zone)
        val period = PeriodRules.periodStart(date).toString()
        return LedgerEventEntity(
            id = UUID.randomUUID().toString(),
            periodStart = period,
            type = type,
            productId = productId,
            quantity = quantity,
            amount = amount,
            funding = funding,
            note = note,
            occurredAt = System.currentTimeMillis()
        )
    }
}
