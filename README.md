# TOKO SAKALA — ANDROID NATIVE PRODUCT PACKAGE

Paket ini memuat spesifikasi produk dan desain mendalam serta fondasi source project Android Native.

## Cakupan
- Product requirements dan functional scope.
- Arsitektur informasi dan inventaris layar.
- UX flow, interaksi, field-level validation, state machine, dan microcopy.
- Design system dan katalog komponen.
- Business rules, domain calculations, event ledger, dan data dictionary.
- Room schema, migration, arsitektur Kotlin/Compose.
- Keamanan lokal, backup/restore terenkripsi.
- Laporan, export, QA, acceptance, roadmap, dan release.

## Mulai dari sini
Buka `docs/00-MASTER-INDEX.md`. Untuk keputusan bisnis, baca `docs/07-BUSINESS-RULES-LOCK.md` dan `docs/31-OWNER-DECISION-REGISTER.md`. Untuk perhitungan, baca `docs/08-DOMAIN-CALCULATION-SPEC.md`.

## Prinsip produk
**Jangan membuat pengguna memahami sistem. Buat sistem yang memahami pengguna.**

Pengguna mencatat kejadian sehari-hari. Aplikasi menghitung stok, uang, HPP, hasil usaha, rekonsiliasi, dan menjelaskan bila ada informasi yang belum pasti.

## Baseline platform
Kotlin, Jetpack Compose, Material 3, Room/SQLite, ViewModel, StateFlow, Coroutines. Aplikasi beroperasi lokal/offline dan tidak memerlukan akun atau backend.

## Ketentuan
Dokumen adalah baseline spesifikasi. Source project adalah fondasi implementasi yang dikembangkan mengikuti dokumen. Contoh data dan nominal di dokumen bukan data awal toko. Tidak ada matriks kekurangan dalam paket ini; rincian implementasi dapat ditinjau langsung dari source dan hasil build.
