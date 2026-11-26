package id.sakala.merchant.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val baseUnit: String,
    val unitsPerPackage: Long,
    val sellingPrice: Long,
    val knownUnits: Long = 0,
    val knownTotalCost: Long = 0,
    val unknownUnits: Long = 0,
    val createdAt: Long
)

@Entity(tableName = "ledger_events")
data class LedgerEventEntity(
    @PrimaryKey val id: String,
    val periodStart: String,
    val type: String,
    val productId: String?,
    val quantity: Long?,
    val amount: Long?,
    val funding: String?,
    val note: String,
    val occurredAt: Long,
    val isCorrection: Boolean = false,
    val correctsEventId: String? = null
)

@Entity(tableName = "period_closures")
data class PeriodClosureEntity(
    @PrimaryKey val periodStart: String,
    val closedAt: Long,
    val snapshotJson: String,
    val digest: String
)
