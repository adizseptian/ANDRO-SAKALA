# Android Native Source

Fondasi project TOKO SAKALA Native Android.

Baca spesifikasi di folder `../docs/` sebelum mengembangkan fitur. Semua perhitungan bisnis harus berada pada domain layer Kotlin murni dan memiliki unit test. Seluruh mutasi data harus melalui repository/database transaction dan event ledger.

Baseline:
- Kotlin + Jetpack Compose + Material 3
- Room/SQLite lokal
- ViewModel + StateFlow + Coroutines
- Offline, tanpa backend/login/cloud sync
- Tidak meminta INTERNET permission

Aturan bisnis yang wajib dijaga: `../docs/07-BUSINESS-RULES-LOCK.md`.
