# 14 — QA PLAN & GOLDEN TEST MATRIX

## Test layers
1. Domain unit tests.
2. Property/invariant tests.
3. Room repository tests.
4. Migration tests.
5. Compose UI tests.
6. Backup/restore cryptographic tests.
7. Device tests: Android 8+ target range.
8. Regression tests after every engine/schema change.

## Core invariants
- Uang tidak pernah memakai floating point.
- Unknown cost tidak pernah otomatis menjadi nol.
- Total units = known units + unknown units.
- Known cost numerator tidak negatif.
- Tidak ada konsumsi stok melebihi saldo pada default policy.
- Event yang sudah committed tidak diedit langsung.
- Closed snapshot tidak berubah.
- Deposit amount > 0.
- Period boundaries follow Monday–Sunday Asia/Jakarta.
- Rebuild projection from event ledger matches stored projection.

## Golden scenarios
### G01 — Package conversion
1 dus × 24 pcs = 24 pcs. 3 dus = 72 pcs. Transaksi menyimpan conversion snapshot.

### G02 — Midweek restock
Pembelian di Rabu tetap masuk periode Senin–Minggu yang sama dan mengubah saldo setelah event pembelian.

### G03 — Unknown opening stock
Stok awal 10 unit modal belum diketahui. Sistem menyimpan 10 unknown units, known cost tetap kosong, bukan Rp0.

### G04 — Unknown-cost-first depletion
Pool known 10 units + unknown 3 units; konsumsi 5 → unknown 3, known 2; uncertainty flag true.

### G05 — Known Moving Average
Pool known 10 units dengan total modal Rp100.000; pembelian 10 unit Rp120.000; pool 20 unit Rp220.000; average rational Rp11.000/unit.

### G06 — Fractional average
Pool 3 unit Rp10.000; konsumsi 1 unit. Uji kebijakan remainder agar nilai cost tersisa + cost consumed = Rp10.000 persis. Tidak boleh kehilangan rupiah akibat pembulatan berulang.

### G07 — Expense funding
Expense Rp10.000 UANG_TOKO mengurangi kas dan laba. UANG_PRIBADI mengurangi laba tetapi tidak kas toko. BELUM_TAHU membuat rekonsiliasi uncertain.

### G08 — Sales/non-cash separation
Penjualan bersih Rp100.000 dengan non-tunai Rp30.000 tidak menghasilkan kas tunai Rp100.000. Non-tunai tidak boleh masuk setoran fisik.

### G09 — Multiple deposits
Dua setoran Rp20.000 dan Rp30.000 dihitung sebagai penyerahan total Rp50.000, bukan dua transaksi penjualan.

### G10 — Status matching with uncertainty
Ekspektasi dan setoran sama, tetapi funding unknown → PERLU_DIPERIKSA, bukan COCOK.

### G11 — Difference
Ekspektasi Rp100.000, setoran Rp90.000 → BELUM COCOK dengan selisih Rp10.000 dan breakdown.

### G12 — No deposit
Belum ada setoran pada periode → MENUNGGU SETORAN.

### G13 — Closed period immutability
Setelah close, event lama/snapshot tidak dapat di-update/delete. Koreksi membuat event tertaut baru.

### G14 — Restore wrong passphrase
Restore gagal, database aktif identik dengan sebelum restore.

### G15 — Corrupt backup
GCM authentication failure/file truncation/schema mismatch ditolak tanpa mutasi database.

### G16 — Duplicate submit
Double tap dengan idempotency key sama menghasilkan satu event.

### G17 — Date-only event
Tanggal tanpa jam disimpan sebagai DATE_ONLY; tidak mengarang timestamp jam transaksi.

### G18 — Negative theoretical balance
UI menampilkan saldo catatan minus dengan rincian; tidak membuat nominal setoran negatif.

## UI acceptance
- Semua CTA dapat dijangkau keyboard/TalkBack.
- Form tidak kehilangan draft saat error.
- Loading mencegah double submit.
- Empty state memberi satu langkah berikutnya.
- Font scale 200% tidak memotong nominal penting.
- 320dp tidak menyebabkan horizontal overflow.
- Dark mode kontras AA.
