# 04 — SCREEN INVENTORY: SPESIFIKASI SELURUH LAYAR

## Konvensi state
Setiap layar wajib memiliki: Loading, Content, Empty, Error, Read-only bila relevan, dan Confirmation untuk aksi destruktif/berdampak besar.

## S01 — Beranda
**Header:** sapaan netral, nama toko, pemilih periode Senin–Minggu.  
**Hero:** status periode, indikator catatan lengkap/perlu diperiksa.  
**Ringkasan:** Penjualan Bersih, Non-tunai, Uang Siap Setoran, Setoran Fisik.  
**Panel perhatian:** produk yang stoknya belum diverifikasi, transaksi yang belum lengkap, input yang memengaruhi kepastian.  
**Aktivitas terbaru:** tanggal, jenis, ringkasan, nilai.  
**Aksi:** Catat aktivitas, Lihat laporan, Periksa setoran.  
**Empty:** “Belum ada aktivitas pada periode ini. Mulai dengan mencatat stok atau transaksi.”  
**Error:** “Ringkasan belum dapat dihitung. Data tersimpan tetap aman. Coba muat ulang.”

## S02 — Daftar Stok
Header pencarian, filter kategori/status, tombol Tambah Produk.  
Item produk: nama, satuan dasar, saldo unit, badge “modal belum diketahui” bila ada, harga jual.  
Tap item membuka Detail Produk. Long press tidak menjadi satu-satunya cara untuk aksi.  
Empty: jelaskan cara menambah produk.  
Filter kosong: tombol Hapus filter.

## S03 — Detail Produk
Header nama + status aktif.  
Blok saldo stok: total unit, unit bermodal diketahui, unit bermodal belum diketahui.  
Blok harga: harga jual aktif dan mulai berlaku.  
Aksi utama: Pembelian, Hitung Fisik, Catat Rusak/Dipakai.  
Tab/segmen: Ringkasan, Pergerakan, Riwayat Harga/Kemasan.  
Setiap pergerakan menampilkan event sumber dan dampak saldo. Tidak menampilkan HPP pasti jika pool tidak cukup diketahui.

## S04 — Tambah Produk
Field wajib: nama, satuan dasar, harga jual awal.  
Field opsional: SKU/kode internal, kategori, catatan.  
Definisi kemasan: nama kemasan dan jumlah unit dasar per kemasan.  
Preview: “1 dus = N pcs”.  
Validasi: nama tidak kosong; harga integer ≥ 0; isi kemasan integer > 0; duplikasi nama diberi peringatan, bukan otomatis digabung.  
Simpan membuat produk dan event harga/kemasan awal secara atomik.

## S05 — Stok Awal
Pilih produk, tanggal efektif, kuantitas, kelompok modal:
- Modal diketahui: jumlah dan total modal.
- Modal belum diketahui: jumlah.
Jumlah dua kelompok harus sama dengan total stok awal. Jangan mengisi modal default nol.  
Preview dampak stok dan komposisi modal sebelum simpan.

## S06 — Pembelian/Restock
Pilih produk, tanggal, jumlah kemasan/unit, definisi konversi, total biaya barang, biaya tambahan opsional bila kebijakan alokasi telah disetujui, catatan.  
Tampilkan konversi ke unit dasar dan estimasi rata-rata modal baru.  
Simpan menambah stok dan event pembelian atomik. Pembelian bukan beban operasional.

## S07 — Hitung Fisik
Pilih produk/lokasi opsional, tanggal dan jumlah fisik.  
Tampilkan saldo sistem saat ini dan selisih.  
Jika fisik lebih besar: tambahan stok perlu klasifikasi modal diketahui/belum diketahui.  
Jika fisik lebih kecil: pengguna memilih alasan diketahui (rusak/dipakai) atau selisih belum diketahui.  
Tidak ada auto-adjust tanpa review.

## S08 — Penjualan
Tanggal, produk, kuantitas, harga transaksi, diskon, jenis penerimaan.  
Harga default berasal dari harga berlaku; pengguna dapat mengubah dengan alasan bila diizinkan.  
Preview: penjualan kotor, diskon, penjualan bersih, pengurangan stok, dampak HPP dengan indikator kepastian.  
Jika stok tidak cukup, simpan diblokir kecuali kebijakan stok minus disetujui Owner (default: blokir).

## S09 — Non-tunai
Tanggal/periode, kanal, nominal, referensi opsional, catatan.  
Non-tunai tidak masuk kas fisik. Hindari duplikasi bila satu transaksi penjualan telah dicatat sebagai non-tunai; UI harus meminta pengguna memilih metode penerimaan pada transaksi atau membuat penyesuaian yang jelas.

## S10 — Pengeluaran
Tanggal, kategori, nominal, sumber dana (Uang Toko/Uang Pribadi/Belum Tahu), keterangan.  
Preview memisahkan dampak laba dan dampak kas.  
Uang Pribadi tetap biaya usaha bila pengeluaran memang untuk usaha, tetapi tidak mengurangi uang toko yang diserahkan. Belum Tahu membuat rekonsiliasi kas perlu diperiksa.

## S11 — Pendapatan Lain
Tanggal, kategori/jenis, nominal, metode penerimaan, keterangan.  
Contoh jasa seduh. Dipisahkan dari penjualan barang. Metode penerimaan menentukan dampak kas/non-tunai.

## S12 — Setoran
Ringkasan ekspektasi uang fisik, total setoran tercatat, sisa yang belum diserahkan, status.  
Tambah setoran: tanggal, nominal positif, penerima, catatan.  
Setiap penyerahan adalah event terpisah. Tidak mengubah angka penjualan.

## S13 — Rekonsiliasi
Tampilkan perhitungan berurutan dan sumber tiap nilai.  
Status COCOK bila nilai cocok dan tidak ada ketidakpastian; PERLU DIPERIKSA bila nominal cocok tetapi ada input tidak pasti; BELUM COCOK bila berbeda; MENUNGGU SETORAN bila belum ada penyerahan.  
Tampilkan selisih bertanda dan uraian netral. Tidak menyebut pegawai “kurang” atau “menggelapkan”.

## S14 — Laporan Laba Rugi
Urutan wajib:
1. Penjualan Kotor
2. Diskon
3. Penjualan Bersih
4. Modal Barang Terjual (Moving Average, known cost only)
5. Untung Kotor
6. Pendapatan Lain
7. Kerugian Barang Rusak/Dipakai/Selisih
8. Biaya Operasional
9. Untung Bersih
Nilai yang belum dapat dipastikan diberi penanda dan penjelasan; jangan menghasilkan angka final palsu.

## S15 — Penutupan Periode
Langkah 1 pemeriksaan kelengkapan. Langkah 2 preview snapshot. Langkah 3 dampak: data yang akan dikunci, unresolved items, warning. Langkah 4 konfirmasi. Setelah ditutup, snapshot immutable. Penutupan dini tidak tersedia.

## S16 — Koreksi
Pilih event asal, tampilkan data lama, masukkan nilai koreksi, alasan wajib, preview dampak pada periode berjalan/riwayat. Simpan event baru dengan `corrects_event_id`. Jika periode asal tertutup, snapshot lama tidak berubah; koreksi tampil sebagai penyesuaian tertaut.

## S17 — Backup
Pilih lokasi penyimpanan, masukkan dan konfirmasi passphrase, ringkasan data yang dicadangkan, progress, hasil file. Beri peringatan bahwa passphrase tidak dapat dipulihkan.

## S18 — Restore
Pilih file, masukkan passphrase, validasi header/authentication/schema/version, tampilkan preview isi dan tanggal backup, buat backup kondisi sekarang, konfirmasi restore, jalankan restore atomik, tampilkan hasil. Gagal di tahap mana pun tidak boleh mengubah database aktif.

## S19 — Profil & Preferensi
Nama toko, zona waktu (WIB terkunci pada baseline), format angka, tema, pengingat lokal opsional. Perubahan preferensi tidak mengubah catatan transaksi.

## S20 — Riwayat Aktivitas
Filter tanggal/jenis/produk, detail event, tautan koreksi, status periode. Tidak ada tombol hapus permanen.
