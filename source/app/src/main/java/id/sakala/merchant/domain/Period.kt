package id.sakala.merchant.domain

import java.time.LocalDate
import java.time.ZoneId

object PeriodRules {
    val zone: ZoneId = ZoneId.of("Asia/Jakarta")

    /** Monday-Sunday business period, represented by its Monday date. */
    fun periodStart(date: LocalDate): LocalDate =
        date.minusDays((date.dayOfWeek.value - 1).toLong())

    fun periodEnd(date: LocalDate): LocalDate = periodStart(date).plusDays(6)
}
