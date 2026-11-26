package id.sakala.merchant.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name COLLATE NOCASE")
    fun observeAll(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(product: ProductEntity)

    @Query("UPDATE products SET knownUnits=:knownUnits, knownTotalCost=:knownCost, unknownUnits=:unknownUnits WHERE id=:id")
    suspend fun updateStock(id: String, knownUnits: Long, knownCost: Long, unknownUnits: Long)

    @Query("SELECT * FROM products WHERE id=:id LIMIT 1")
    suspend fun findById(id: String): ProductEntity?
}

@Dao
interface LedgerDao {
    @Query("SELECT * FROM ledger_events ORDER BY occurredAt DESC")
    fun observeAll(): Flow<List<LedgerEventEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(event: LedgerEventEntity)

    @Query("SELECT * FROM ledger_events WHERE periodStart=:period ORDER BY occurredAt")
    suspend fun eventsForPeriod(period: String): List<LedgerEventEntity>

    @Query("SELECT * FROM period_closures WHERE periodStart=:period LIMIT 1")
    suspend fun closure(period: String): PeriodClosureEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun closePeriod(closure: PeriodClosureEntity)
}
