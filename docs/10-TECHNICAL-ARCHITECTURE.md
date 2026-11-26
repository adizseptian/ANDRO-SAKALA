# 10 — TECHNICAL ARCHITECTURE

## Stack
- Kotlin.
- Jetpack Compose + Material 3.
- AndroidX Lifecycle / ViewModel.
- Coroutines + StateFlow.
- Room / SQLite.
- Android Storage Access Framework untuk file backup/ekspor.
- JDK 17 baseline; versi Gradle/AGP/Kotlin dikunci di version catalog.

## Layer
1. Presentation: Compose screen, component, navigation, UI state.
2. Application: ViewModel, use case, command orchestration.
3. Domain: pure Kotlin model, validation, inventory engine, reconciliation engine, report engine.
4. Data: Room DAO, entities, repository, migrations, backup codec.
5. Platform adapter: SAF, clipboard/share, file metadata.

## Dependency direction
Presentation → Application → Domain.  
Data implements repository ports used by Application.  
Domain tidak bergantung pada Android, Compose, Room, filesystem, atau current wall clock.

## Command pattern
Semua perubahan data melalui command:
- Validate input.
- Resolve current state.
- Calculate preview/impact.
- Verify period state and dependencies.
- Persist event(s) and projection update in one DB transaction.
- Return structured result.
Command harus idempotent untuk mencegah double submit.

## State management
UI state immutable. ViewModel mengubah StateFlow. UI tidak memanggil DAO langsung. Semua error dipetakan menjadi pesan yang dapat dipahami pengguna tanpa membocorkan detail internal.

## Database
Room single database file. WAL dapat digunakan setelah diuji. Foreign key aktif. Migrations wajib eksplisit; destructive migration dilarang pada production.

## Time
Business date dan timestamp dipisahkan. `Asia/Jakarta` untuk interpretasi periode. Simpan timestamp sebagai epoch/Instant yang konsisten dan business date sebagai ISO date.

## No network
Manifest tidak memuat INTERNET. Tidak ada HTTP client, cloud SDK, telemetry, atau remote config. CI/build tool dapat mengakses dependency repository saat build, tetapi aplikasi runtime tidak membutuhkan jaringan.

## Build baseline
- Android Gradle Plugin 8.13.x.
- Kotlin 2.2.x.
- Compose compiler plugin selaras Kotlin.
- compileSdk 36.
- minSdk 26.
- JDK 17.
Versi final harus diverifikasi terhadap Android Studio/SDK yang dipakai sebelum release; jangan menganggap nomor versi di dokumen sebagai hasil build.

## Test architecture
Domain unit tests, repository/database tests, migration tests, Compose UI tests, backup codec tests, restore fault-injection tests, device QA.
