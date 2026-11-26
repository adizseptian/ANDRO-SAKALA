# 34 — UX DETAIL PER SCREEN

## Beranda
Urutan: top bar nama toko/periode; status periode; ringkasan penjualan bersih; non-tunai; uang siap setoran; setoran fisik; panel perlu perhatian; aktivitas terakhir. Angka utama diberi konteks periode. Tap kartu membuka rincian sumber. CTA Catat Aktivitas membuka action hub.

## Stok
Search selalu terlihat; filter sebagai chips; list padat namun nyaman. Sort default nama. Produk archived tidak muncul kecuali filter riwayat. Item menampilkan stok dan status modal unknown. FAB Tambah Produk tidak menutupi bottom navigation.

## Detail Produk
Header nama dan status; angka total stok; breakdown known/unknown; harga aktif; aksi Pembelian, Hitung Fisik, Rusak/Dipakai; tab Pergerakan dan Riwayat Harga/Kemasan. Tap event membuka rincian sumber.

## Catat
Action hub berisi Penjualan, Pembelian, Pengeluaran, Pendapatan Lain, Hitung Fisik, Non-tunai. Deskripsi singkat di bawah setiap aksi. Aksi tidak langsung membuat event sebelum form dikonfirmasi.

## Setoran
Tiga angka dipisahkan: uang siap setoran, total setoran fisik, saldo catatan. Daftar setoran berdasarkan waktu terbaru. Tambah setoran menampilkan form ringkas dan preview saldo sesudah setoran.

## Lainnya
Kelompok: Laporan, Riwayat Aktivitas, Tutup Periode, Backup & Restore, Profil Toko, Preferensi, Tentang. Aksi berisiko tidak ditempatkan berdekatan tanpa pemisahan.

## Laporan
Pilih periode, jenis laporan, filter. Header laporan tetap memperlihatkan rentang tanggal. Setiap total dapat dibuka untuk melihat komponennya. Export selalu menggunakan filter aktif yang ditampilkan sebelum konfirmasi.

## Riwayat
Filter event, tanggal, produk. Event koreksi memperlihatkan hubungan dengan event asal. Event void tetap terlihat sebagai riwayat dengan status void.

## Dialog/sheet
- Sheet aksi: satu tujuan utama.
- Confirmation: konsekuensi spesifik.
- Error: fokus ke tindakan pemulihan.
- Preview: tampilkan angka sebelum simpan.
- Loading: tombol disabled, progress tidak menghilangkan konteks.
