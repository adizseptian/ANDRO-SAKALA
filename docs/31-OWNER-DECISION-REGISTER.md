# 31 — OWNER DECISION REGISTER

Register ini mencatat keputusan bisnis yang sudah menjadi baseline. Perubahan harus memiliki tanggal, alasan, dampak, dan versi dokumen.

| Area | Keputusan terkunci |
|---|---|
| Platform | Android Native |
| UI | Kotlin + Jetpack Compose |
| Penyimpanan | Room/SQLite lokal |
| Operasi | Offline-first, tanpa backend/cloud |
| Akun | Tidak ada login/autentikasi |
| Navigasi | Beranda, Stok, Catat, Setoran, Lainnya |
| Costing | Moving Average |
| Unknown cost | Pool terpisah; tidak dianggap nol |
| Depletion | Unknown-cost-first |
| Period | Senin–Minggu, Asia/Jakarta |
| Closure | Snapshot immutable |
| Correction | Event tertaut; tidak mengubah snapshot lama |
| Deposit | Penyerahan uang fisik aktual |
| Backup | Manual, terenkripsi, passphrase pengguna |
| OCR/AI runtime | Tidak termasuk baseline |
| Telemetry/ads | Tidak ada |

## Change control
Setiap proposal perubahan mencantumkan:
1. Keputusan yang diminta.
2. Masalah yang diselesaikan.
3. Dampak pada data lama.
4. Dampak pada laporan dan perhitungan.
5. Dampak migrasi database.
6. Dampak UI dan kebiasaan operator.
7. Test yang wajib ditambahkan.
8. Persetujuan Owner sebelum implementasi.
