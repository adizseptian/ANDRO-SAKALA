# 07 — BUSINESS RULES LOCK (OWNER BASELINE)

Dokumen ini adalah baseline aturan bisnis. Perubahan hanya melalui keputusan Owner dan revisi bernomor.

## 1. Prinsip pencatatan
Pengguna mencatat kejadian; sistem menghitung dan menjelaskan. Jangan meminta pengguna memahami rumus akuntansi.

## 2. Tiga lapisan uang
1. **Penjualan:** nilai barang/jasa yang dijual.
2. **Uang diterima:** uang tunai dan non-tunai yang benar-benar diterima.
3. **Uang siap setoran:** kas fisik yang secara catatan tersedia untuk diserahkan.
Ketiganya tidak boleh digabung menjadi satu angka.

## 3. Penjualan dan non-tunai
- Penjualan bersih = penjualan kotor − diskon.
- Non-tunai dicatat terpisah menurut kanal/periode.
- Kas dari penjualan tidak boleh memasukkan penerimaan non-tunai.
- Jangan menghitung penerimaan non-tunai dua kali.

## 4. Pengeluaran dan sumber dana
- UANG_TOKO: mengurangi kas toko dan laba usaha.
- UANG_PRIBADI: tidak mengurangi kas toko, tetapi biaya usaha tetap mengurangi hasil usaha jika benar untuk kepentingan usaha.
- BELUM_TAHU: biaya tetap tercatat; kas menjadi tidak pasti.
- Pembelian barang dagang adalah persediaan, bukan biaya operasional.

## 5. Persediaan dan modal
- Moving Average adalah satu-satunya metode HPP.
- Stok dengan modal diketahui dan stok dengan modal belum diketahui disimpan terpisah.
- Modal tidak diketahui bukan nol.
- Tidak boleh menciptakan angka modal.
- Pengeluaran stok yang tidak diketahui modalnya diprioritaskan lebih dahulu.
- Jika stok modal belum diketahui memengaruhi barang terjual/rusak/dipakai/selisih, HPP/laba ditandai PERLU DIPERIKSA.
- Rata-rata modal hanya dihitung dari unit dan total modal yang diketahui.

## 6. Periode
- Senin sampai Minggu.
- Zona waktu Asia/Jakarta (WIB).
- Tidak ada penutupan dini.
- Restock di tengah minggu masuk periode yang sama.

## 7. Setoran
- Setoran adalah uang fisik yang benar-benar diserahkan.
- Boleh beberapa kali dalam satu minggu.
- Setoran tidak sama dengan penjualan, omzet, atau keuntungan.
- Nominal setoran harus positif.
- Saldo teoritis minus ditampilkan sebagai penjelasan saldo catatan minus; jangan menghasilkan setoran negatif.

## 8. Status rekonsiliasi
- COCOK: angka cocok dan tidak ada ketidakpastian yang relevan.
- PERLU DIPERIKSA: angka mungkin cocok tetapi ada data tidak pasti atau dampak unknown.
- BELUM COCOK: ada selisih nominal.
- MENUNGGU SETORAN: belum ada penyerahan yang tercatat.
Urutan prioritas status harus didokumentasikan pada engine dan diuji.

## 9. Selisih stok
“Catat sebagai selisih” hanya tersedia bila produk terkait berstatus CUKUP dan tidak ada ketidakpastian relevan. Jika tidak, masalah tetap unresolved dan harus dijelaskan.

## 10. Laporan laba rugi
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

Jika HPP tidak sepenuhnya diketahui, tampilkan nilai known-cost dan status ketidakpastian; jangan tampilkan laba bersih seolah final.

## 11. Penutupan dan koreksi
- Snapshot periode tertutup immutable.
- Tidak ada hard delete.
- VOID hanya pada periode terbuka dan jika tidak ada dependensi.
- Jika ada dependensi, gunakan event koreksi tertaut.
- Periode tertutup hanya dapat diperbaiki melalui koreksi tertaut.
- Tampilkan “Koreksi dari periode sebelumnya” pada periode yang menerima dampaknya.

## 12. Bahasa
Netral dan faktual. Hindari “pegawai kurang”, “pegawai salah”, “uang hilang” sebelum bukti. Gunakan “terdapat selisih catatan”, “perlu pemeriksaan”, “belum ada penyerahan tercatat”.
