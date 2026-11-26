# 02 — FUNCTIONAL SCOPE & MODULE SPECIFICATION

## Modul 1 — Beranda
Tujuan: memberi gambaran singkat, bukan menggantikan laporan.
Konten: periode aktif, status kelengkapan catatan, ringkasan penjualan bersih, penerimaan non-tunai, kas teoritis, setoran fisik, produk perlu diperiksa, dan aktivitas terakhir.
Aturan: angka tidak tersedia ditampilkan sebagai “Belum tercatat” atau “Belum diketahui”, bukan nol.
Aksi: pilih periode, buka rincian, tambah catatan, lanjutkan pemeriksaan.

## Modul 2 — Stok
Submodul:
1. Daftar produk.
2. Detail produk.
3. Tambah/edit produk.
4. Definisi kemasan.
5. Stok awal.
6. Pembelian/restock.
7. Hitung fisik.
8. Barang rusak/dipakai.
9. Selisih stok.
10. Riwayat pergerakan.
11. Riwayat harga dan kemasan.

Detail produk menampilkan stok tercatat, kelompok modal diketahui, kelompok modal belum diketahui, harga jual aktif, unit, riwayat, dan peringatan data.

## Modul 3 — Catat
Menu pencatatan cepat:
- Penjualan barang.
- Diskon.
- Penerimaan non-tunai.
- Pengeluaran.
- Pendapatan lain.
- Barang rusak/dipakai.
- Koreksi catatan.
Pilih tindakan dulu, kemudian formulir kontekstual. Jangan menampilkan semua kolom sekaligus.

## Modul 4 — Setoran
- Ringkasan uang siap disetor.
- Daftar setoran yang telah diserahkan.
- Tambah penyerahan.
- Rekonsiliasi nominal.
- Rincian faktor pembentuk.
- Status dan tindakan tindak lanjut.
Beberapa setoran dalam satu periode diperbolehkan.

## Modul 5 — Laporan
- Ringkasan periode.
- Laporan laba rugi.
- Laporan stok.
- Laporan pembelian.
- Laporan pengeluaran.
- Laporan pendapatan lain.
- Laporan non-tunai.
- Laporan setoran.
- Riwayat koreksi.
- Ekspor PDF/CSV pada fase laporan.

## Modul 6 — Lainnya
- Profil toko.
- Preferensi tampilan.
- Pengelolaan kategori.
- Backup.
- Restore.
- Informasi penyimpanan lokal.
- Panduan istilah.
- Tentang aplikasi.
- Pemeriksaan integritas data.

## Aturan lintas modul
- Semua tanggal transaksi memakai tanggal lokal WIB.
- Tanggal transaksi lama tidak boleh otomatis diganti tanggal hari ini.
- Perubahan harga berlaku ke transaksi baru saja.
- Konversi kemasan disimpan sebagai snapshot pada transaksi.
- Semua simpan harus atomik.
- Data historis tidak dihapus permanen.
- Koreksi menyimpan alasan dan tautan event asal.
