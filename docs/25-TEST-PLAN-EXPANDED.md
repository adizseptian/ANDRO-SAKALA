# 25 — EXPANDED TEST PLAN

## Unit tests domain
- Rupiah: parse, format, overflow, nilai nol.
- Package conversion: faktor 1, faktor besar, invalid, overflow.
- Moving Average: pembelian berulang, pool known, pool unknown, konsumsi parsial, remainder.
- Unknown-cost-first: konsumsi unknown sebelum known.
- Penjualan: diskon nol, diskon penuh, diskon melebihi gross, stok cukup/tidak cukup.
- Expense: tiga sumber dana dan pengaruh kas/laba.
- Other income: tunai/non-tunai.
- Deposit: positif, nol, lebih besar dari saldo catatan.
- Period: batas Senin/Minggu WIB, pergantian bulan/tahun, tahun kabisat.
- Reconciliation: cocok, selisih, unknown, belum ada setoran.
- Correction: periode terbuka/tertutup, linked event, snapshot tidak berubah.

## Database tests
- Insert event atomik.
- Foreign key.
- Unique constraints.
- Rollback jika salah satu baris gagal.
- DAO tidak menyediakan hard delete event.
- Migration dari seluruh schema version yang didukung.
- Snapshot closure immutable.
- Rebuild projection dari ledger.

## UI tests
- First launch.
- Setup toko.
- Tambah produk dan kemasan.
- Stok awal dua pool.
- Restock.
- Penjualan.
- Pengeluaran tiga sumber dana.
- Setoran berulang.
- Hitung fisik dan unresolved.
- Penutupan periode.
- Koreksi.
- Backup/restore berhasil dan gagal.
- Font besar, TalkBack, keyboard, dark mode, tablet.

## Property-based / invariant tests
- Total stok = opening + purchases − sales − non-sale outflows ± adjustments.
- Known cost remaining + known cost consumed = known cost available.
- Unknown pool tidak pernah berubah menjadi known tanpa event klasifikasi eksplisit.
- Jumlah setoran = penjumlahan event deposit.
- Closed snapshot digest tetap sama setelah koreksi.
- Setiap correction memiliki sumber yang valid.
- No negative deposit event.
- Tidak ada nominal uang disimpan sebagai Float/Double.

## Acceptance criteria
Fitur diterima hanya bila:
1. Preview sama dengan hasil setelah simpan.
2. Data tetap benar setelah aplikasi ditutup/dibuka.
3. Error tidak merusak draft atau ledger.
4. Semua perubahan dapat ditelusuri.
5. Laporan konsisten dengan event sumber.
6. Tidak ada angka unknown yang disulap menjadi nol.
