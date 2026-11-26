# 17 — FIELD-LEVEL FORM SPECIFICATION

## Standar umum
Setiap field memiliki label yang jelas, contoh format, helper text bila diperlukan, validasi inline, dan pesan kesalahan yang menyebut cara memperbaiki. Jangan mengandalkan placeholder sebagai label. Nilai uang memakai format Rupiah lokal; penyimpanan tetap integer Rupiah.

## Form Produk
| Field | Tipe | Wajib | Aturan |
|---|---|---:|---|
| Nama produk | Teks | Ya | Trim; 1–80 karakter; tidak boleh kosong |
| Satuan dasar | Pilihan/teks | Ya | Contoh pcs, botol, sachet; konsisten untuk produk |
| Harga jual awal | Rupiah | Ya | Integer >= 0; tidak menerima pecahan |
| SKU | Teks | Tidak | Maks. 40 karakter; harus unik jika diisi |
| Kategori | Pilihan | Tidak | Kategori buatan pengguna |
| Catatan | Teks | Tidak | Maks. 500 karakter |
| Nama kemasan | Teks | Tidak | Contoh dus/pak/toples |
| Isi kemasan | Integer | Jika kemasan dibuat | > 0; tidak boleh overflow |

## Form stok awal
- Produk: wajib, satu produk per transaksi stok awal.
- Tanggal efektif: wajib; tanggal lokal, tanpa jam palsu.
- Kuantitas modal diketahui: integer >= 0.
- Total modal diketahui: Rupiah >= 0; diwajibkan bila kuantitas known > 0.
- Kuantitas modal belum diketahui: integer >= 0.
- Jumlah kedua pool harus sama dengan jumlah stok awal.
- Jangan prefill biaya unknown dengan Rp0.
- Catatan sumber informasi dianjurkan.

## Form pembelian
- Produk dan tanggal wajib.
- Mode kuantitas: kemasan atau unit dasar.
- Jumlah harus integer > 0.
- Snapshot konversi kemasan wajib disimpan pada baris transaksi.
- Total biaya barang wajib untuk masuk known-cost pool; bila tidak diketahui, pengguna harus secara eksplisit memilih “Modal belum diketahui”.
- Nilai tambahan seperti ongkos angkut tidak boleh dialokasikan ke modal sebelum kebijakan alokasi disahkan; untuk sementara catat terpisah sebagai biaya bila substansinya memang biaya usaha.
- Tombol simpan memerlukan preview yang konsisten.

## Form penjualan
- Produk wajib; jumlah integer > 0.
- Harga transaksi diusulkan dari harga aktif.
- Diskon total per baris tidak boleh negatif dan tidak boleh melebihi gross baris.
- Penerimaan wajib dipilih.
- Jika transaksi campuran tunai/non-tunai dibutuhkan, input pecahan penerimaan harus memenuhi total net sales; jangan membuat dua transaksi penjualan untuk satu penjualan fisik.
- Stok tidak cukup = hard blocker.
- Tampilkan HPP diketahui dan indikator unknown cost secara terpisah.

## Form pengeluaran
- Kategori, tanggal, nominal, sumber dana wajib.
- Nominal integer > 0.
- Sumber dana wajib dipilih eksplisit; tidak ada default diam-diam.
- Deskripsi wajib untuk kategori “Lainnya”.
- Sumber dana BELUM_TAHU menandai uncertainty.
- Tampilkan “Mengurangi kas toko” atau “Tidak mengurangi kas toko” sebelum simpan.

## Form pendapatan lain
- Jenis, tanggal, nominal, metode penerimaan wajib.
- Nominal > 0.
- Tidak digabung ke penjualan barang.
- Contoh jasa seduh hanya contoh kategori, bukan data bawaan.

## Form setoran
- Tanggal wajib.
- Nominal integer > 0.
- Penerima opsional.
- Catatan opsional.
- Nominal lebih besar dari saldo teoritis harus memicu konfirmasi dan status saldo catatan minus; tidak boleh mengubah nominal setoran menjadi negatif atau memblokir fakta penyerahan fisik yang benar-benar terjadi.

## Form hitung fisik
- Produk, tanggal, jumlah fisik wajib.
- Jumlah fisik integer >= 0.
- Selisih dihitung sistem.
- Alasan wajib bila selisih tidak nol.
- Klasifikasi unknown tidak boleh disamarkan sebagai known.
