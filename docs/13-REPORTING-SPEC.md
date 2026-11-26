# 13 — REPORTING & EXPORT SPEC

## Periode
- Default mingguan Senin–Minggu WIB.
- Pengguna dapat memilih rentang tanggal untuk laporan historis.
- Filter tidak boleh mengubah data sumber.

## Laporan ringkasan
Menampilkan penjualan kotor, diskon, penjualan bersih, penerimaan tunai, penerimaan non-tunai, pengeluaran, pendapatan lain, setoran fisik, saldo catatan, dan indikator ketidakpastian.

## Laporan laba rugi
Urutan:
1. Penjualan Kotor
2. Diskon
3. Penjualan Bersih
4. Modal Barang Terjual (Moving Average, known cost only)
5. Untung Kotor
6. Pendapatan Lain
7. Kerugian Barang Rusak/Dipakai/Selisih
8. Biaya Operasional
9. Untung Bersih

Jika modal unknown memengaruhi periode, tampilkan “Hasil belum sepenuhnya dapat dipastikan” dan pisahkan known-cost subtotal.

## Laporan stok
Saldo awal, pembelian, barang terjual, rusak, dipakai, selisih, stok akhir catatan, hasil hitung fisik terakhir, komposisi known/unknown cost.

## Laporan setoran
Tanggal penyerahan, nominal, penerima, total setoran, ekspektasi kas, sisa/selisih, status.

## Ekspor
- PDF untuk laporan siap cetak.
- CSV untuk analisis spreadsheet.
- Nama file memuat nama laporan dan rentang tanggal.
- Export preview menunjukkan rentang dan jumlah data.
- Data ekspor mengikuti filter yang terlihat.
- Jangan memasukkan secret atau metadata perangkat yang tidak perlu.
- Ekspor tidak mengubah data sumber.

## Print layout
A4 portrait baseline; margin aman; hierarki angka jelas; tanggal dan zona waktu eksplisit; footer versi aplikasi dan waktu ekspor. Tabel tidak boleh memotong nama produk/nilai.
