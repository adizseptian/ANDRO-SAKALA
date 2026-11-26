# 16 — END-TO-END USER JOURNEYS

Dokumen ini menetapkan alur dari kondisi awal sampai hasil akhir. Setiap alur harus dapat diselesaikan tanpa pengguna menghitung manual.

## 1. First launch dan setup toko
1. Tampilkan intro singkat: data disimpan di perangkat; backup harus dibuat sendiri.
2. Pengguna mengisi nama toko (wajib), nama pemilik (opsional), preferensi tema.
3. Aplikasi menampilkan ringkasan zona waktu WIB dan periode Senin–Minggu.
4. Pengguna memilih mulai dari pencatatan stok awal atau menyiapkan katalog produk terlebih dahulu.
5. Setup tidak membuat produk contoh, stok contoh, atau saldo uang contoh.
6. Selesai setup: Beranda menampilkan checklist konfigurasi awal yang dapat ditutup setelah selesai.

## 2. Membuat katalog produk
1. Beranda > Stok > Tambah Produk.
2. Isi nama, satuan dasar, harga jual awal; SKU/kategori/catatan opsional.
3. Tambahkan definisi kemasan jika diperlukan.
4. Preview konversi ditampilkan langsung.
5. Validasi nama, harga, dan konversi.
6. Simpan atomik: product + price history + package definition.
7. Buka Detail Produk dan tampilkan status “Belum ada stok awal”.

## 3. Mencatat stok awal
1. Pilih produk dan tanggal efektif.
2. Masukkan kuantitas total.
3. Pecah kuantitas ke pool modal diketahui dan belum diketahui.
4. Untuk modal diketahui, masukkan total biaya per kelompok, bukan modal nol sebagai default.
5. Preview komposisi stok dan rata-rata biaya pool diketahui.
6. Konfirmasi; buat opening-stock event.
7. Detail produk memperlihatkan dua pool secara terpisah.

## 4. Restock di tengah periode
1. Detail Produk > Pembelian.
2. Pilih kemasan atau unit dasar.
3. Sistem mengonversi ke unit dasar menggunakan definisi kemasan saat transaksi.
4. Masukkan total biaya barang yang benar-benar dibayar.
5. Preview moving average pool diketahui; unknown pool tidak dicampur.
6. Konfirmasi dan simpan pembelian.
7. Stok bertambah pada periode berjalan. Pembelian tidak muncul sebagai biaya operasional.

## 5. Mencatat penjualan
1. Catat > Penjualan.
2. Pilih produk, jumlah, harga transaksi dan diskon.
3. Harga aktif diusulkan otomatis; perubahan harga transaksi harus terlihat jelas.
4. Pilih penerimaan: tunai atau kanal non-tunai sesuai skema pencatatan.
5. Preview gross, diskon, net, pengurangan stok, serta kepastian HPP.
6. Jika stok kurang, blokir dan tampilkan jumlah yang tersedia.
7. Simpan transaksi dan pergerakan stok dalam satu transaksi database.
8. Aktivitas, stok, ringkasan penjualan, dan kas diperbarui dari event tersimpan.

## 6. Mencatat pengeluaran
1. Catat > Pengeluaran.
2. Pilih kategori, nominal, sumber dana, tanggal, dan deskripsi.
3. Pilih sumber dana: Uang Toko / Uang Pribadi / Belum Tahu.
4. Preview dampak laba dan dampak kas ditampilkan terpisah.
5. Uang Pribadi tetap menjadi biaya usaha jika benar untuk operasional; tidak mengurangi kas toko.
6. Belum Tahu memberi penanda ketidakpastian pada rekonsiliasi.
7. Simpan event dan tampilkan dampaknya pada ringkasan.

## 7. Menghitung stok fisik
1. Pilih produk; sistem menampilkan saldo buku.
2. Masukkan jumlah fisik aktual dan tanggal pemeriksaan.
3. Sistem menghitung selisih.
4. Selisih lebih: klasifikasikan modal diketahui/belum diketahui.
5. Selisih kurang: pilih rusak, dipakai, atau selisih belum diketahui.
6. Preview dampak stok dan laba.
7. Jika ketidakpastian masih ada, jangan tampilkan tindakan “Catat sebagai selisih” sebagai penyelesaian final.
8. Konfirmasi; event stock-check dan event penyesuaian terkait disimpan tertaut.

## 8. Setoran berkali-kali
1. Buka Setoran.
2. Lihat uang siap setoran, setoran yang telah tercatat, dan saldo catatan tersisa.
3. Tambah setoran dengan nominal positif, tanggal, penerima, dan catatan.
4. Preview sisa setelah setoran.
5. Simpan satu event untuk satu penyerahan fisik.
6. Penyerahan berikutnya dicatat sebagai event baru, bukan mengedit setoran lama.

## 9. Menutup periode
1. Aplikasi hanya menawarkan periode Senin–Minggu yang telah berakhir.
2. Tampilkan checklist kelengkapan: stok, transaksi, non-tunai, pengeluaran, setoran, unresolved.
3. Tampilkan preview snapshot dan seluruh peringatan.
4. Pengguna meninjau ringkasan; sistem tidak menyembunyikan nilai unknown.
5. Konfirmasi eksplisit.
6. Simpan snapshot immutable dan closure event atomik.
7. Periode tertutup masuk riwayat; data lama tidak dapat diedit langsung.

## 10. Koreksi setelah periode ditutup
1. Riwayat > pilih event > Koreksi.
2. Tampilkan data sumber yang tidak dapat diubah.
3. Pengguna memasukkan koreksi dan alasan wajib.
4. Preview menunjukkan periode asal, periode penerima dampak, serta nilai sebelum/sesudah.
5. Buat correction event yang menunjuk event sumber.
6. Snapshot lama tetap sama; dampak koreksi dicatat pada periode berjalan sesuai aturan.

## 11. Backup dan restore
Lihat dokumen 12. Restore selalu membuat backup kondisi saat ini sebelum mengganti database dan harus atomik.

## 12. Prinsip lintas alur
- Setiap aksi memiliki preview dampak yang relevan.
- Tombol simpan hanya aktif jika input valid dan tidak ada hard blocker.
- Loading tidak boleh memungkinkan double submit.
- Setelah berhasil, tampilkan hasil yang konkret, bukan toast “Sukses” saja.
- Jika gagal, data form dipertahankan dan pengguna diberi tindakan pemulihan.
