# 26 — IMPLEMENTATION ROADMAP

Urutan kerja mengunci fondasi sebelum menambah fitur visual kompleks.

## Stage 0 — Repository & build foundation
- Android project dapat dibuka Android Studio.
- Gradle wrapper dan version catalog konsisten.
- Manifest minimal; tidak meminta INTERNET.
- CI menjalankan compile dan unit test.

## Stage 1 — Design system & navigation
- Theme light/dark.
- Typography, spacing, color, shapes.
- Scaffold lima navigasi.
- Component catalog diimplementasikan.
- Preview Compose untuk komponen utama.

## Stage 2 — Domain core
- Money dan quantity value objects.
- Period calculator WIB.
- Package conversion.
- Inventory ledger.
- Moving Average known pool dan unknown pool.
- Reconciliation engine.
- Unit tests dan invariant tests.

## Stage 3 — Persistence
- Room entities, DAO, database.
- Repository transaction boundary.
- Schema migration.
- Projection rebuild.
- Event append-only.

## Stage 4 — Product & stock
- Product list/detail/create.
- Opening stock.
- Package definitions.
- Purchase/restock.
- Physical count, damaged, used, variance.

## Stage 5 — Sales & money
- Sales entry.
- Cash/non-cash split.
- Expense.
- Other income.
- Deposit multi-event.
- Reconciliation.

## Stage 6 — Period and reports
- Dashboard.
- Period review.
- Closure snapshot.
- Corrections.
- Profit/loss, stock, deposit reports.
- PDF/CSV export.

## Stage 7 — Backup and restore
- Encrypted backup format.
- SAF integration.
- Staging restore.
- Atomic swap and recovery testing.

## Stage 8 — Hardening
- Accessibility, responsive, performance.
- Database migration tests.
- Device matrix.
- Regression and acceptance tests.

## Stage 9 — Release
- Versioning.
- Signed release build.
- Privacy and data-loss disclosures.
- Backup reminder.
- Internal testing then production rollout.

Setiap stage memiliki definition of done dan tidak boleh ditandai selesai hanya karena layar sudah terlihat.
