# 08 — DOMAIN & CALCULATION SPECIFICATION

## 1. Unit uang dan kuantitas
- IDR disimpan sebagai integer Rupiah.
- Tidak menggunakan Float/Double untuk uang.
- Kuantitas menggunakan integer untuk barang satuan; pecahan hanya jika Owner mengizinkan unit pecahan secara eksplisit.
- Semua operasi harus memeriksa overflow.
- Pembulatan tidak boleh implisit. Kebijakan pembulatan harus ditetapkan per operasi.

## 2. Konversi kemasan
Contoh definisi: 1 dus = 24 pcs. Jika pembelian 3 dus, kuantitas dasar = 72 pcs. Konversi yang dipakai harus disimpan pada transaksi sebagai snapshot; perubahan definisi kemasan tidak mengubah transaksi lampau.

## 3. Moving Average
Untuk pool modal diketahui:
- `qty_baru = qty_lama + qty_masuk`
- `cost_baru = cost_lama + total_cost_masuk`
- `average_cost = cost_baru / qty_baru`
Simpan numerator total cost dan quantity; jangan hanya menyimpan hasil average yang telah dibulatkan.
Saat konsumsi:
- `known_cost_consumed` dihitung dengan kebijakan alokasi integer deterministik.
- Sisa cost pool harus konsisten: `remaining_cost = prior_cost - consumed_cost`.
- Unknown-cost pool tetap terpisah.
**Open design decision:** bagaimana pembagian sisa Rupiah ketika rata-rata tidak bulat. Rekomendasi teknis: gunakan rational numerator/denominator atau alokasi remainder deterministik agar total biaya tidak hilang. Owner harus menyetujui sebelum production.

## 4. Unknown-cost-first
Jika stok terdiri dari unknown units dan known units, konsumsi terlebih dahulu unknown units. Jika konsumsi menyentuh unknown units, hasil HPP/laba tidak sepenuhnya pasti. Known-cost portion masih dapat dilaporkan, tetapi total HPP/laba diberi status PERLU DIPERIKSA.

## 5. Penjualan
- Gross sales = Σ(quantity × transaction_unit_price).
- Discount = total diskon yang sah dan tidak melebihi gross sales.
- Net sales = gross sales − discount.
- Setiap baris menyimpan harga saat transaksi.
- Stok berkurang sesuai kuantitas dasar.
- COGS hanya diketahui untuk porsi known-cost.
- Jika stok tidak cukup, default blokir transaksi; tidak membuat stok negatif secara diam-diam.

## 6. Pembelian
- Pembelian menambah stok.
- Nilai pembelian masuk ke pool modal diketahui hanya bila biaya dapat dipastikan.
- Pembelian tanpa modal diketahui masuk unknown-cost pool dengan alasan.
- Pembelian tidak dicatat sebagai biaya operasional.

## 7. Pengeluaran barang non-penjualan
Barang rusak, dipakai, dan selisih dicatat sebagai tipe event berbeda. Pengaruh ke kerugian barang mengikuti known/unknown cost pool. Unknown cost yang terpakai membuat hasil perlu diperiksa.

## 8. Pengeluaran kas
Setiap expense memiliki:
- nominal,
- kategori,
- sumber dana,
- tanggal,
- keterangan.
UANG_TOKO memengaruhi kas fisik; UANG_PRIBADI tidak mengurangi kas toko; BELUM_TAHU menciptakan uncertainty flag. Ketiganya tetap dapat menjadi biaya usaha sesuai substansi pengeluaran.

## 9. Pendapatan lain
Dibukukan terpisah dari penjualan barang. Contoh jasa seduh. Penerimaan tunai/non-tunai dipisahkan untuk rekonsiliasi kas.

## 10. Rekonsiliasi uang siap setoran
Engine wajib menghitung secara eksplisit:
- penerimaan tunai dari transaksi,
- penerimaan tunai dari pendapatan lain,
- non-tunai yang harus dikecualikan dari uang fisik,
- pengeluaran yang dibayar dari uang toko,
- koreksi/penyesuaian kas yang sah,
- total setoran fisik,
- sisa kas teoritis.
Jangan mengasumsikan rumus final hanya dari penjualan bersih. Setiap komponen harus menjadi event dan ditampilkan pada breakdown. Setoran tidak boleh dihitung sebagai pengeluaran usaha.

## 11. Periode dan waktu
- `period_start` = Senin lokal.
- `period_end` = Minggu lokal.
- Timestamp disimpan dengan instant yang jelas; transaksi tanggal saja perlu penanda DATE_ONLY agar tidak dibuat-buat seolah memiliki jam.
- Seluruh batas periode dihitung di Asia/Jakarta.

## 12. Status
Status dihitung dari selisih nominal dan uncertainty flags. Jika nilai cocok namun ada uncertainty, PERLU_DIPERIKSA. Jika tidak ada setoran, MENUNGGU_SETORAN. Jika ada perbedaan, BELUM_COCOK. Urutan prioritas persis harus diuji dengan matriks kombinasi.

## 13. Contoh ilustratif (bukan data awal)
- 1 dus = 24 pcs.
- Harga jual mie Rp7.000/pcs.
- Pendapatan jasa seduh Rp2.000.
Angka tersebut hanya contoh penjelasan; aplikasi tidak boleh menanamnya sebagai data toko.
