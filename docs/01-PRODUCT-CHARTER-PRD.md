# 01 — PRODUCT CHARTER & PRODUCT REQUIREMENTS DOCUMENT

## 1. Ringkasan produk
TOKO SAKALA adalah aplikasi pencatatan operasional dan keuangan toko berbasis Android Native. Aplikasi membantu pemilik memahami hubungan stok, penjualan, uang diterima, biaya, setoran pegawai, dan hasil usaha per periode.

Produk tidak meminta pengguna menyusun laporan akuntansi. Pengguna mencatat aktivitas sebagaimana terjadi; aplikasi membentuk gambaran usaha secara konsisten.

## 2. Masalah yang diselesaikan
- Pemilik sulit mencocokkan stok awal, pembelian, stok sisa, dan barang terjual.
- Pembelian ulang dapat terjadi di tengah minggu.
- Setoran pegawai tidak selalu sama dengan penjualan karena ada non-tunai, pengeluaran, dan uang yang belum diserahkan.
- Modal sebagian barang mungkin belum diketahui.
- Catatan manual tidak selalu membedakan uang toko dan uang pribadi.
- Perubahan setelah periode ditutup dapat merusak histori jika data lama diedit.

## 3. Sasaran
1. Catatan aktivitas usaha mudah dibuat.
2. Stok dan pergerakannya dapat ditelusuri.
3. Penjualan, uang diterima, dan uang siap disetor tidak tercampur.
4. Selisih dijelaskan dengan bahasa netral.
5. HPP dan laba tidak menampilkan kepastian palsu.
6. Riwayat periode tetap dapat diaudit.
7. Data dapat dicadangkan secara manual dan aman.

## 4. Bukan sasaran MVP
- Multi-cabang atau multi-toko.
- Multi-user online.
- Payroll, hutang-piutang kompleks, perpajakan, POS kasir terintegrasi.
- Integrasi bank/payment gateway.
- Prediksi permintaan, rekomendasi AI, OCR struk.
- Sinkronisasi cloud atau akses lintas perangkat.
- Pengelolaan supplier kompleks.

## 5. Pengguna dan konteks
**Pemilik toko:** menyiapkan produk, melihat ringkasan, memeriksa stok, laporan, dan menutup periode.  
**Petugas pencatat:** memasukkan transaksi/aktivitas harian dan penyerahan uang. Pada MVP tidak ada role/login terpisah; perangkat berada dalam penguasaan pemilik.

## 6. Prinsip pengalaman
- Bahasa Indonesia sehari-hari; istilah teknis diberi penjelasan.
- Default aman; jangan mengarang modal, kuantitas, tanggal, atau nilai uang.
- Satu tindakan utama per layar.
- Preview dampak sebelum tindakan yang mengubah data secara besar.
- Kesalahan menjelaskan cara memperbaiki, bukan hanya “gagal”.
- Riwayat tidak dihapus diam-diam.
- Pengguna dapat membatalkan sebelum simpan; setelah simpan gunakan koreksi sesuai aturan.

## 7. Platform dan constraint
- Android Native, Kotlin, Jetpack Compose, Material 3.
- Minimum SDK 26 (Android 8.0) sebagai baseline proyek; verifikasi perangkat target sebelum rilis.
- Database Room/SQLite lokal.
- Zona waktu bisnis `Asia/Jakarta`.
- Tidak memerlukan permission INTERNET.
- Tidak ada server-side dependency.

## 8. Functional requirements
### FR-001 Profil toko
Simpan nama toko, nama pemilik opsional, alamat opsional, mata uang IDR, zona waktu bisnis WIB. Perubahan profil tidak mengubah histori transaksi.

### FR-002 Produk
Tambah/edit metadata produk, unit dasar, kemasan, isi kemasan, harga jual aktif, status aktif/nonaktif. Riwayat harga dan ukuran kemasan harus tersimpan sebagai event efektif tanggal.

### FR-003 Stok awal
Catat jumlah stok awal. Setiap unit harus masuk kelompok modal diketahui atau modal belum diketahui. Nilai modal kosong bukan nol.

### FR-004 Pembelian
Catat tanggal, produk, jumlah, unit pembelian, konversi ke unit dasar, total biaya barang, biaya tambahan yang dialokasikan bila fitur tersebut diaktifkan, dan catatan. Pembelian adalah penambahan persediaan, bukan biaya operasional.

### FR-005 Pemeriksaan stok
Catat hasil hitung fisik, waktu, produk, jumlah fisik, dan alasan/penjelasan. Sistem menghitung selisih dari saldo catatan.

### FR-006 Pergerakan non-penjualan
Catat barang rusak, dipakai untuk kebutuhan toko/pribadi, dan selisih stok yang belum diketahui penyebabnya. Masing-masing jenis harus dibedakan.

### FR-007 Penjualan
Catat produk, kuantitas, harga yang berlaku saat transaksi, diskon, dan metode penerimaan. Harga transaksi historis tidak boleh ikut berubah ketika harga produk diperbarui.

### FR-008 Non-tunai
Catat penerimaan non-tunai per kanal dan periode. Nilai non-tunai tidak boleh dihitung sebagai uang tunai yang harus diserahkan.

### FR-009 Pengeluaran
Catat nominal, kategori, tanggal, keterangan, dan sumber dana: UANG_TOKO / UANG_PRIBADI / BELUM_TAHU.

### FR-010 Pendapatan lain
Catat pendapatan selain penjualan barang, misalnya jasa seduh. Pendapatan ini dipisahkan dari penjualan barang.

### FR-011 Setoran
Catat beberapa penyerahan uang fisik dalam satu periode. Nominal setoran harus positif. Setoran tidak sama dengan omzet, laba, atau saldo teoritis.

### FR-012 Rekonsiliasi
Sediakan status COCOK / PERLU DIPERIKSA / BELUM COCOK / MENUNGGU SETORAN beserta rincian pembentuk nilai.

### FR-013 Laporan
Laporan periode, penjualan, non-tunai, pengeluaran, stok, pembelian, HPP, laba, setoran, koreksi, dan aktivitas.

### FR-014 Penutupan periode
Preview → impact → apply. Simpan snapshot immutable dan digest. Tidak ada penutupan dini; periode Senin–Minggu.

### FR-015 Koreksi
Perubahan setelah simpan harus menjadi event koreksi tertaut. Periode tertutup tidak diedit.

### FR-016 Backup/restore
Ekspor manual terenkripsi, verifikasi file, passphrase, backup kondisi saat ini sebelum restore, dan restore atomik.

## 9. Non-functional requirements
- NFR-001: Perhitungan bisnis deterministik.
- NFR-002: Uang disimpan sebagai integer Rupiah (Long) atau representasi aman; tidak memakai Float/Double.
- NFR-003: Tidak ada operasi jaringan untuk fungsi inti.
- NFR-004: Semua aksi simpan memiliki state loading/success/error.
- NFR-005: Data lokal tidak dikirim ke pihak lain.
- NFR-006: Ukuran font sistem dihormati.
- NFR-007: Target sentuh minimum 48dp.
- NFR-008: UI mendukung lebar 320dp sampai tablet.
- NFR-009: Crash tidak boleh meninggalkan transaksi setengah tersimpan.
- NFR-010: Migrasi database diuji dan tidak menghapus data.
- NFR-011: Backup restore memiliki uji korupsi, passphrase salah, dan pemulihan.
- NFR-012: Laporan dapat ditelusuri kembali ke event sumber.

## 10. Acceptance criteria tingkat produk
- Semua uang memiliki asal perhitungan yang dapat dibuka.
- Modal belum diketahui tidak pernah ditampilkan sebagai Rp0.
- Setoran hanya mencatat uang fisik yang benar-benar diserahkan.
- Periode tertutup tidak berubah saat event baru/koreksi dibuat.
- Data dapat dipulihkan dari backup terenkripsi yang valid.
- Tidak ada koneksi jaringan yang diperlukan untuk alur inti.
- Semua golden test domain lulus.
- Semua modul pada matriks release berstatus PASS sebelum penggunaan produksi.
