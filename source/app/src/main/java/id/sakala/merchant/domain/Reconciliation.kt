package id.sakala.merchant.domain

enum class ReconciliationStatus {
    COCOK, PERLU_DIPERIKSA, BELUM_COCOK, MENUNGGU_SETORAN
}

enum class ExpenseFunding { UANG_TOKO, UANG_PRIBADI, BELUM_TAHU }

data class ReconciliationInput(
    val expectedCash: Long,
    val handedOverCash: Long,
    val hasUnknownFunding: Boolean = false,
    val hasUnknownCostImpact: Boolean = false,
    val hasUnresolvedStockDifference: Boolean = false
)

object ReconciliationEngine {
    fun evaluate(input: ReconciliationInput): ReconciliationStatus {
        if (input.handedOverCash == 0L) return ReconciliationStatus.MENUNGGU_SETORAN
        if (input.expectedCash != input.handedOverCash) return ReconciliationStatus.BELUM_COCOK
        if (input.hasUnknownFunding || input.hasUnknownCostImpact || input.hasUnresolvedStockDifference)
            return ReconciliationStatus.PERLU_DIPERIKSA
        return ReconciliationStatus.COCOK
    }
}
