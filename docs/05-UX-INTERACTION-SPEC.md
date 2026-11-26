# 05 — UX INTERACTION SPEC: TOMBOL, FORM, VALIDASI, DAN HASIL AKSI

## Pola umum tombol
- Primary: menyimpan/menjalankan satu tindakan utama.
- Secondary: batal/kembali atau tindakan alternatif.
- Tertiary: bantuan, rincian, filter.
- Destructive: hanya untuk tindakan yang benar-benar dapat dibatalkan; minta konfirmasi dan jelaskan dampak.
- Disabled: tampilkan alasan singkat jika tidak aktif.

## Siklus form standar
1. Pengguna membuka form.
2. Field wajib ditandai secara jelas.
3. Input divalidasi saat pengguna selesai mengisi atau menekan lanjut.
4. Preview memperlihatkan konsekuensi.
5. Tombol Simpan aktif hanya jika valid.
6. Saat simpan, tombol menampilkan loading dan mencegah double tap.
7. Database transaction menyimpan semua perubahan terkait.
8. Berhasil: snackbar singkat + ringkasan + tautan “Lihat detail”.
9. Gagal: draft tetap ada; pesan menyebut field/masalah; tidak ada setengah transaksi.

## Tombol per layar
### Beranda
- Pemilih periode → membuka date/week picker; pilihan hanya periode valid.
- “Lihat rincian” → laporan periode terkait.
- “Catat aktivitas” → layar pilih jenis catatan.
- “Periksa” → layar terkait item yang membutuhkan perhatian.

### Stok
- “Tambah produk” → S04.
- Search → hasil difilter tanpa mengubah data.
- Filter → bottom sheet; Terapkan/Hapus.
- Item produk → S03.
- “Pembelian” → S06 dengan produk terpilih.
- “Hitung fisik” → S07.
- “Rusak/Dipakai” → form klasifikasi pergerakan.
- “Nonaktifkan” → konfirmasi; histori tetap tersedia, produk tidak muncul pada input transaksi baru.

### Form produk
- “Simpan” → validasi, preview singkat, simpan product + definisi awal.
- “Batal” → jika draft berubah, dialog “Buang perubahan?”.
- “Tambah kemasan” → baris kemasan baru; isi > 0.
- “Jadikan utama” → satu kemasan default; histori konversi sebelumnya tetap disimpan.

### Form pembelian
- Pemilih produk → daftar produk aktif + pencarian.
- Pemilih kemasan → mengisi konversi dari definisi yang dipilih.
- Kuantitas → angka bulat positif.
- Total biaya → Rupiah integer ≥ 0; nol hanya jika pembelian memang tanpa biaya dan alasan eksplisit dicatat.
- Preview → unit dasar yang ditambahkan, modal rata-rata baru, komposisi stok.
- “Simpan pembelian” → event pembelian + perubahan saldo atomik.

### Form penjualan
- “Tambah item” → baris item baru.
- Kuantitas → > 0 dan tidak melebihi stok tersedia pada baseline.
- Harga → snapshot harga pada saat transaksi.
- Diskon → nominal atau persentase; jangan izinkan hasil negatif.
- “Review” → ringkasan total dan dampak stok.
- “Simpan penjualan” → simpan penjualan dan pergerakan stok dalam satu transaksi database.

### Pengeluaran
- Pilihan sumber dana wajib.
- “Uang Toko” → memengaruhi kas toko dan laba.
- “Uang Pribadi” → memengaruhi laba usaha, tidak mengurangi kas toko.
- “Belum Tahu” → biaya tetap tercatat; rekonsiliasi berstatus perlu diperiksa.
- “Simpan” → satu event pengeluaran.

### Setoran
- Nominal harus > 0.
- Tidak boleh melebihi sisa ekspektasi tanpa konfirmasi/klasifikasi kelebihan.
- Bila ada kelebihan, jangan tolak diam-diam; minta pengguna menentukan apakah itu setoran untuk periode lain atau koreksi catatan.
- “Catat penyerahan” → event setoran baru, tidak mengubah penjualan.
- “Lihat rincian” → komponen pembentuk uang siap setoran.

### Tutup periode
- “Periksa kelengkapan” → menjalankan validator.
- “Lihat preview” → snapshot yang akan dibekukan.
- “Tutup periode” → hanya aktif pada akhir periode dan setelah konfirmasi.
- Setelah berhasil: halaman periode tertutup, badge “Ditutup”, tombol koreksi tertaut.

### Backup/restore
- “Buat backup” → SAF create document; enkripsi; verifikasi file tertulis.
- “Pilih file” → SAF open document; tidak membaca file dari jalur hardcoded.
- “Validasi backup” → dekripsi dan validasi tanpa mengubah DB.
- “Pulihkan” → backup kondisi sekarang → transaksi restore atomik.
- “Batal” → tidak mengubah DB.

## Copywriting standar
- Success: “Catatan berhasil disimpan.”
- Error: “Belum dapat disimpan. Periksa kembali jumlah dan keterangan.”
- Unknown cost: “Modal barang ini belum diketahui. Sistem tidak akan menganggapnya nol.”
- Reconciliation: “Ada bagian catatan yang perlu diperiksa. Berikut rinciannya.”
- Closed period: “Periode ini sudah ditutup. Buat koreksi tertaut agar riwayat tetap terjaga.”
- No stock: “Stok tercatat belum mencukupi. Periksa stok fisik atau catat pembelian terlebih dahulu.”

## Micro-interaction
- Tap feedback ringan; tidak ada animasi yang menunda pekerjaan.
- Motion 120–220ms untuk transisi sederhana.
- Loading tidak boleh menghilangkan konteks.
- Snackbar tidak menjadi satu-satunya bukti untuk tindakan penting.
- Haptic hanya untuk konfirmasi penting dan dapat mengikuti pengaturan perangkat.
