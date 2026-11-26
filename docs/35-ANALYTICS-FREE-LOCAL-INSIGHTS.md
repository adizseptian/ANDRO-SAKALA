# 35 — LOCAL INSIGHTS WITHOUT AI

Insight dihitung secara lokal dengan aturan deterministik dan hanya dari data yang tersedia. Tidak ada model AI, prediksi stok otomatis, atau klaim sebab-akibat.

## Insight yang diizinkan
- Produk dengan stok di bawah batas minimum yang ditentukan pengguna.
- Produk yang belum pernah dihitung fisik.
- Pengeluaran berdasarkan kategori dan periode.
- Perbandingan penjualan antarperiode yang sama panjang.
- Produk dengan pergerakan tertinggi berdasarkan unit terjual.
- Catatan yang masih memiliki uncertainty.
- Periode yang belum ditutup.

## Guardrails
- Jangan menyebut “akan habis” tanpa parameter dan asumsi yang jelas.
- Jangan menyarankan jumlah pembelian seolah pasti.
- Jangan menyimpulkan pegawai melakukan kesalahan.
- Jika periode tidak sebanding, tampilkan peringatan.
- Unknown data dikecualikan dari rasio yang membutuhkan nilai pasti dan disebutkan.
- Pengguna dapat membuka sumber setiap insight.
