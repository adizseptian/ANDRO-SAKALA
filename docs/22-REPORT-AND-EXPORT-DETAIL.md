# 22 — REPORT & EXPORT DETAIL

## Laporan utama
### Ringkasan periode
- Nama toko dan periode.
- Penjualan kotor, diskon, penjualan bersih.
- Tunai dan non-tunai dipisahkan.
- Pengeluaran berdasarkan sumber dana.
- Uang siap setoran, setoran fisik, saldo catatan.
- Status dan daftar ketidakpastian.

### Laba rugi
Urutan tetap:
1. Penjualan Kotor
2. Diskon
3. Penjualan Bersih
4. Modal Barang Terjual (Moving Average, known cost only)
5. Untung Kotor
6. Pendapatan Lain
7. Kerugian Barang Rusak/Dipakai/Selisih
8. Biaya Operasional
9. Untung Bersih

Jika modal unknown berdampak, angka laba bersih harus diberi status belum final/perlu diperiksa. Jangan mengganti unknown menjadi nol.

### Kartu stok
- Produk dan unit dasar.
- Stok awal.
- Pembelian.
- Barang terjual.
- Barang rusak/dipakai/selisih.
- Stok akhir catatan.
- Hasil hitung fisik terakhir.
- Known-cost quantity/value dan unknown-cost quantity.

### Laporan setoran
- Tanggal setiap penyerahan.
- Nominal.
- Penerima.
- Total penyerahan.
- Ekspektasi uang fisik.
- Selisih dan status.

## Filter
Tanggal awal/akhir, periode, produk, kategori, tipe event, status rekonsiliasi. Filter harus menampilkan rentang aktif dengan jelas.

## Export
- PDF: layout cetak, judul, identitas toko, periode, timestamp ekspor, total, status, footer halaman.
- CSV: UTF-8, header stabil, escaping separator/quote/newline, angka disimpan tanpa pemisah ribuan agar bisa diolah spreadsheet.
- File export tidak mengubah data.
- Pengguna memilih lokasi melalui Android Storage Access Framework.
- Nama file aman, misalnya `TOKO-SAKALA_Laporan-2026-11-01_2026-11-07.pdf`.
- Preview sebelum ekspor; hasil menampilkan lokasi/aksi buka file bila tersedia.
- Laporan harus menampilkan status ketidakpastian, bukan hanya angka.
