# 29 — LOCALIZATION & MICROCOPY GUIDE

## Bahasa
Bahasa Indonesia yang lugas, hangat, netral, tidak menggurui. Hindari istilah teknis bila ada ungkapan yang mudah dipahami.

## Prinsip microcopy
- Sebut keadaan, bukan menyalahkan orang.
- Jelaskan dampak dan langkah berikutnya.
- Jangan menyembunyikan ketidakpastian.
- Gunakan satu istilah konsisten untuk satu konsep.
- Hindari “data invalid” tanpa penjelasan.

## Pilihan istilah
| Konsep internal | Istilah UI |
|---|---|
| Unknown cost | Modal belum diketahui |
| Reconciliation | Pemeriksaan catatan |
| Period closure | Tutup periode |
| Correction event | Koreksi catatan |
| Cash expected | Uang siap setoran |
| Physical deposit | Setoran fisik |
| Stock variance | Selisih stok |
| Known cost pool | Stok dengan modal diketahui |

## Contoh pesan
- Empty stock: “Belum ada produk. Tambahkan produk untuk mulai mencatat stok.”
- No transactions: “Belum ada transaksi pada periode ini.”
- Unknown cost: “Modal sebagian stok belum diketahui. Aplikasi tidak akan mengira-ngira nilainya.”
- Reconciliation difference: “Ada perbedaan antara catatan dan setoran. Periksa rincian berikut.”
- Closed period: “Periode sudah ditutup. Buat koreksi tertaut agar riwayat tetap terjaga.”
- Restore success: “Data cadangan berhasil dipulihkan. Periksa ringkasan sebelum melanjutkan.”
- Backup reminder: “Simpan salinan cadangan di tempat aman agar data tetap tersedia jika perangkat bermasalah.”

## Format
- IDR: `Rp1.250.000` untuk tampilan.
- Tanggal: `7 Okt 2026` atau format lokal yang konsisten.
- Rentang: `Senin, 5 Okt – Minggu, 11 Okt 2026`.
- Kuantitas selalu disertai unit.
