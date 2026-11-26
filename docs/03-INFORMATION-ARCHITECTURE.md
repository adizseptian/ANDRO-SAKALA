# 03 — INFORMATION ARCHITECTURE & NAVIGATION

## Navigasi primer
1. Beranda
2. Stok
3. Catat
4. Setoran
5. Lainnya

Navigasi bawah selalu konsisten pada lima tujuan primer. Detail dan form dibuka sebagai layar dalam stack atau bottom sheet sesuai kompleksitas. Tombol Back kembali ke konteks sebelumnya tanpa membuang draft tanpa konfirmasi.

## Peta layar
- Beranda
  - Ringkasan periode aktif
  - Status kelengkapan
  - Aktivitas terbaru
  - Rincian angka
- Stok
  - Daftar produk
  - Cari/filter produk
  - Detail produk
    - Ringkasan stok
    - Komposisi modal
    - Pergerakan
    - Riwayat harga
  - Tambah produk
  - Edit produk
  - Stok awal
  - Pembelian
  - Hitung fisik
  - Rusak/dipakai
  - Selisih stok
- Catat
  - Pilih jenis catatan
  - Form penjualan
  - Form diskon
  - Form non-tunai
  - Form pengeluaran
  - Form pendapatan lain
  - Review sebelum simpan
  - Hasil simpan
- Setoran
  - Ringkasan rekonsiliasi
  - Daftar penyerahan
  - Tambah setoran
  - Detail setoran
  - Rincian selisih
- Laporan
  - Pilih periode/rentang
  - Ringkasan
  - Detail kategori
  - Ekspor
- Lainnya
  - Profil
  - Preferensi
  - Backup
  - Restore
  - Bantuan
  - Tentang

## Pola navigasi
- Tab utama mempertahankan state scroll saat berpindah tab.
- Form yang belum disimpan menampilkan konfirmasi keluar.
- Dialog hanya untuk keputusan singkat.
- Bottom sheet untuk pilihan kontekstual.
- Layar penuh untuk proses multi-langkah seperti restore dan penutupan periode.
- Tidak ada deep link eksternal yang diperlukan untuk fungsi inti.
