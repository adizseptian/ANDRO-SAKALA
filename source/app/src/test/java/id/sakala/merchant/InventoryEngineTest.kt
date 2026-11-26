package id.sakala.merchant

import id.sakala.merchant.domain.InventoryEngine
import id.sakala.merchant.domain.InventoryPool
import id.sakala.merchant.domain.ReconciliationEngine
import id.sakala.merchant.domain.ReconciliationInput
import id.sakala.merchant.domain.ReconciliationStatus
import org.junit.Assert.*
import org.junit.Test

class InventoryEngineTest {
    @Test fun unknownUnitsAreConsumedBeforeKnownCostUnits() {
        val pool = InventoryPool(knownUnits = 10, knownTotalCost = 100_000, unknownUnits = 3)
        val result = InventoryEngine.consume(pool, 5)
        assertEquals(3, result.consumedUnknownUnits)
        assertEquals(2, result.consumedKnownUnits)
        assertTrue(result.needsReview)
        assertEquals(8, result.remaining.knownUnits)
        assertEquals(0, result.remaining.unknownUnits)
    }

    @Test fun knownCostPoolCanBeConsumedWithoutReview() {
        val pool = InventoryPool(knownUnits = 10, knownTotalCost = 100_000)
        val result = InventoryEngine.consume(pool, 2)
        assertFalse(result.needsReview)
        assertEquals(20_000, result.knownCostOfConsumedUnits)
    }

    @Test fun equalCashWithUnknownInputsNeedsReview() {
        assertEquals(
            ReconciliationStatus.PERLU_DIPERIKSA,
            ReconciliationEngine.evaluate(ReconciliationInput(100_000, 100_000, hasUnknownFunding = true))
        )
    }

    @Test fun exactCashWithoutUncertaintyMatches() {
        assertEquals(
            ReconciliationStatus.COCOK,
            ReconciliationEngine.evaluate(ReconciliationInput(100_000, 100_000))
        )
    }
}
