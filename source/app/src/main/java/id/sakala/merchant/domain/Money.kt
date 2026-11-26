package id.sakala.merchant.domain

@JvmInline
value class Rupiah(val amount: Long) {
    init { require(amount >= 0L) { "Nilai uang tidak boleh negatif." } }
    operator fun plus(other: Rupiah) = Rupiah(Math.addExact(amount, other.amount))
    operator fun minus(other: Rupiah): SignedRupiah =
        SignedRupiah(Math.subtractExact(amount, other.amount))
}

@JvmInline
value class SignedRupiah(val amount: Long)

fun formatRupiah(value: Long): String {
    val raw = kotlin.math.abs(value).toString().reversed().chunked(3).joinToString(".").reversed()
    return (if (value < 0) "-Rp" else "Rp") + raw
}
