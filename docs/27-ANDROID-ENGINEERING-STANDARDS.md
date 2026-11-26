# 27 — ANDROID ENGINEERING STANDARDS

## Stack
- Kotlin.
- Jetpack Compose + Material 3.
- Room/SQLite.
- ViewModel + StateFlow.
- Coroutines.
- Navigation Compose.
- Kotlin Serialization atau parser terstruktur untuk payload versioned.
- JUnit dan AndroidX test.
- Build berbasis Gradle Kotlin DSL dan version catalog.

## Layer
`presentation → domain ← data`
- Presentation: Compose screen, UI state, event handler.
- Domain: entity/value object/use case/calculation engine; Kotlin murni.
- Data: Room, DAO, repository, mapper, backup adapters.
- Domain tidak boleh mengimpor Android, Compose, Room, atau API platform.

## State
- UI state immutable.
- StateFlow untuk observable state.
- One-shot effects untuk navigasi/snackbar.
- Tidak ada mutable singleton untuk state bisnis.
- ViewModel tidak melakukan rumus bisnis sendiri.

## Concurrency
- Satu command transaksi menggunakan database transaction.
- Cegah double-submit dengan command ID/idempotency.
- Mutex hanya bila dibutuhkan; database constraint tetap sumber proteksi.
- Operasi file/crypto dijalankan off main thread.
- Cancellation tidak boleh meninggalkan transaksi parsial.

## Uang dan tanggal
- Long Rupiah integer dengan checked arithmetic.
- Jangan gunakan Float/Double untuk uang.
- Tidak memakai implicit device locale untuk perhitungan periode.
- Business date selalu Asia/Jakarta.
- Simpan instant dan effective date secara eksplisit.

## Logging
- Log teknis tidak memuat nama pelanggan, nominal transaksi sensitif, passphrase, atau isi backup.
- Crash report pihak ketiga/telemetry tidak digunakan pada baseline offline.
- Debug log dinonaktifkan pada release.

## Dependency governance
- Gunakan dependency stabil yang saling kompatibel.
- Pin versi melalui version catalog.
- Hindari dependency yang tidak terawat.
- Tidak menambahkan library hanya untuk fungsi kecil yang dapat dibuat dengan API platform.
