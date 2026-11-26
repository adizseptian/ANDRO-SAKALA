# MASTER INDEX — TOKO SAKALA ANDROID NATIVE

Paket ini adalah baseline produk, bisnis, UX/UI, arsitektur, data, keamanan, pengujian, dan roadmap implementasi TOKO SAKALA Native Android.

## Urutan membaca

1. `01-PRODUCT-CHARTER-PRD.md` — tujuan, pengguna, prinsip produk, hasil yang diharapkan.
2. `02-FUNCTIONAL-SCOPE.md` — batas fungsi dan fitur.
3. `03-INFORMATION-ARCHITECTURE.md` — struktur navigasi dan hubungan modul.
4. `04-SCREEN-INVENTORY.md` — inventaris layar dan elemen utama.
5. `05-UX-INTERACTION-SPEC.md` — perilaku interaksi dan state.
6. `06-DESIGN-SYSTEM.md` — identitas visual dan token.
7. `07-BUSINESS-RULES-LOCK.md` — aturan bisnis yang tidak boleh berubah tanpa persetujuan Owner.
8. `08-DOMAIN-CALCULATION-SPEC.md` — rumus dan invariant domain.
9. `09-DATA-MODEL.md` — model data konseptual.
10. `10-TECHNICAL-ARCHITECTURE.md` — struktur teknis Android.
11. `11-SECURITY-PRIVACY.md` — privasi dan batas keamanan.
12. `12-BACKUP-RESTORE-SPEC.md` — alur backup/restore.
13. `13-REPORTING-SPEC.md` — laporan.
14. `14-QA-GOLDEN-TESTS.md` — skenario uji utama.
15. `15-BUILD-RELEASE-PLAN.md` — build dan distribusi.

## Spesifikasi pendalaman

16. `16-END-TO-END-USER-JOURNEYS.md` — alur pengguna dari setup sampai koreksi dan pemulihan.
17. `17-FIELD-LEVEL-FORM-SPEC.md` — field, tipe, kewajiban, validasi, dan preview tiap form.
18. `18-UI-COMPONENT-CATALOG.md` — katalog komponen, ukuran, warna, tipografi, motion.
19. `19-STATE-MACHINE-AND-VALIDATION.md` — state form/periode/stok dan kontrak error.
20. `20-DATA-DICTIONARY-EVENTS.md` — event envelope, taxonomy, payload invariant.
21. `21-ROOM-SCHEMA-AND-MIGRATION.md` — skema Room, index, constraint, migration.
22. `22-REPORT-AND-EXPORT-DETAIL.md` — isi laporan, filter, PDF/CSV.
23. `23-BACKUP-FILE-FORMAT.md` — container, enkripsi, validasi, restore aman.
24. `24-ACCESSIBILITY-RESPONSIVE-QUALITY.md` — aksesibilitas, ukuran layar, keyboard.
25. `25-TEST-PLAN-EXPANDED.md` — unit, database, UI, invariant, acceptance.
26. `26-IMPLEMENTATION-ROADMAP.md` — tahapan implementasi dan definition of done.
27. `27-ANDROID-ENGINEERING-STANDARDS.md` — standar Kotlin, Compose, Room, state, concurrency.
28. `28-SECURITY-THREAT-MODEL.md` — aset, ancaman, mitigasi, disclosure.
29. `29-LOCALIZATION-COPY-GUIDE.md` — istilah dan microcopy Bahasa Indonesia.
30. `30-OPERATIONAL-EDGE-CASES.md` — kondisi khusus operasional dan pemulihan.
31. `31-OWNER-DECISION-REGISTER.md` — keputusan Owner yang menjadi baseline.
32. `32-GLOSSARY.md` — kamus istilah.
33. `33-RELEASE-AND-DEVICE-MATRIX.md` — validasi perangkat dan release.
34. `34-UX-DETAIL-PER-SCREEN.md` — urutan konten dan perilaku setiap layar utama.
35. `35-ANALYTICS-FREE-LOCAL-INSIGHTS.md` — insight lokal deterministik tanpa AI.

## Source Android

Folder `source/` berisi fondasi project Android Native. Dokumen produk dan aturan bisnis menjadi rujukan saat mengembangkan modul. Implementasi harus mengikuti keputusan Owner dan seluruh invariant pada dokumen 07–08.

## Aturan kendali perubahan

- Dokumen 07 dan 31 adalah baseline keputusan bisnis.
- Perubahan rumus, perlakuan unknown-cost, periode, setoran, penutupan, atau koreksi memerlukan persetujuan Owner.
- Setiap perubahan domain harus memperbarui test golden dan test invariant.
- Contoh angka dalam dokumen adalah ilustrasi, bukan data awal aplikasi.
- Jangan menambahkan backend, login, cloud, sinkronisasi, AI runtime, OCR, iklan, atau telemetry tanpa keputusan baru.

## Prinsip utama

**Pengguna mencatat kejadian. Sistem menghitung, memeriksa, dan menjelaskan dampaknya.**
