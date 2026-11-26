# 30 — OPERATIONAL EDGE CASES

## Produk dan kemasan
- Nama produk sama: beri peringatan, tetap izinkan bila memang barang berbeda.
- Kemasan berubah: buat definisi baru efektif ke depan; transaksi lampau memakai snapshot konversi.
- Produk diarsipkan: tetap muncul di riwayat; tidak dapat dipakai untuk transaksi baru kecuali diaktifkan kembali.

## Stok
- Stok awal nol: boleh, tetapi tidak menghasilkan pool modal.
- Modal unknown: tetap unknown, bukan Rp0.
- Hitung fisik lebih tinggi: perlu klasifikasi modal.
- Hitung fisik lebih rendah: perlu alasan dan dampak.
- Stok negatif: diblokir pada penjualan baseline.
- Produk tanpa stok awal: tampilkan belum diinisialisasi, bukan stok nol yang dianggap pasti.

## Penjualan dan penerimaan
- Diskon sama dengan gross: net nol, transaksi valid jika kebijakan mengizinkan.
- Diskon lebih besar dari gross: blokir.
- Transaksi tunai dan non-tunai campuran: total alokasi harus sama dengan net sales.
- Pembayaran non-tunai belum cair: jangan dianggap uang fisik.
- Pembatalan setelah transaksi: event koreksi/void sesuai status dependensi.

## Pengeluaran dan pendapatan
- Expense pribadi untuk usaha: biaya usaha, bukan pengurang kas toko.
- Expense sumber belum tahu: tandai uncertainty.
- Pengeluaran non-operasional pribadi: jangan dicatat sebagai biaya usaha.
- Pendapatan jasa: other income, tidak dicampur penjualan produk.
- Refund: event koreksi dengan hubungan ke transaksi asal.

## Setoran
- Beberapa setoran sehari: event terpisah.
- Setoran lebih besar dari saldo teoritis: simpan fakta fisik, tampilkan saldo catatan minus.
- Tidak ada setoran: status menunggu setoran; bukan otomatis selisih nol.
- Koreksi setoran: linked correction, bukan edit diam-diam.

## Waktu dan periode
- Input terlambat untuk periode tertutup: correction event dan dampak pada periode berjalan sesuai kebijakan.
- Perubahan tanggal perangkat: business period tetap dihitung berdasarkan tanggal yang dipilih dan zona WIB.
- Pergantian tahun: ID periode tidak boleh ambigu.
- Tidak ada penutupan dini.

## Restore dan perangkat
- Ruang penyimpanan habis: batalkan tanpa merusak database.
- File backup parsial: tolak sebelum database aktif berubah.
- Passphrase salah: tidak ada perubahan database.
- Restore ke versi lebih lama: migrasi hanya bila format didukung.
