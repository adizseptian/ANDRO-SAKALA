package id.sakala.merchant.domain

/**
 * Moving Average valuation with separate known-cost and unknown-cost pools.
 * Unknown-cost units are depleted first. Unknown cost is never converted to zero.
 */
data class InventoryPool(
    val knownUnits: Long = 0,
    val knownTotalCost: Long = 0,
    val unknownUnits: Long = 0
) {
    init {
        require(knownUnits >= 0 && knownTotalCost >= 0 && unknownUnits >= 0)
        require((knownUnits == 0L) == (knownTotalCost == 0L))
    }
    val averageUnitCost: Long?
        get() = if (knownUnits == 0L) null else knownTotalCost / knownUnits
    val totalUnits: Long get() = Math.addExact(knownUnits, unknownUnits)
}

data class ConsumptionResult(
    val remaining: InventoryPool,
    val consumedUnknownUnits: Long,
    val consumedKnownUnits: Long,
    val knownCostOfConsumedUnits: Long,
    val needsReview: Boolean
)

object InventoryEngine {
    fun addKnownPurchase(pool: InventoryPool, units: Long, totalCost: Long): InventoryPool {
        require(units > 0 && totalCost >= 0)
        return InventoryPool(
            knownUnits = Math.addExact(pool.knownUnits, units),
            knownTotalCost = Math.addExact(pool.knownTotalCost, totalCost),
            unknownUnits = pool.unknownUnits
        )
    }

    fun addUnknownStock(pool: InventoryPool, units: Long): InventoryPool {
        require(units > 0)
        return pool.copy(unknownUnits = Math.addExact(pool.unknownUnits, units))
    }

    fun consume(pool: InventoryPool, units: Long): ConsumptionResult {
        require(units > 0)
        require(units <= pool.totalUnits) { "Stok tidak mencukupi." }

        val fromUnknown = minOf(units, pool.unknownUnits)
        val fromKnown = units - fromUnknown
        val cost = if (fromKnown == 0L) 0L else {
            // Integer arithmetic; remainder is retained in remaining pool.
            val costPerUnit = pool.knownTotalCost / pool.knownUnits
            Math.multiplyExact(costPerUnit, fromKnown)
        }
        val remainingKnown = pool.knownUnits - fromKnown
        val remainingCost = pool.knownTotalCost - cost
        return ConsumptionResult(
            remaining = InventoryPool(remainingKnown, remainingCost, pool.unknownUnits - fromUnknown),
            consumedUnknownUnits = fromUnknown,
            consumedKnownUnits = fromKnown,
            knownCostOfConsumedUnits = cost,
            needsReview = fromUnknown > 0
        )
    }
}
