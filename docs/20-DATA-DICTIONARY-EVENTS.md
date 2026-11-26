# 20 — DATA DICTIONARY & EVENT TAXONOMY

## Aturan event
Event adalah sumber kebenaran. Event bersifat append-only. Koreksi menghasilkan event baru yang merujuk event asal. Tidak menyimpan hanya saldo akhir tanpa jejak pembentuknya.

## Envelope event
- `event_id`: UUID.
- `event_type`: enum stabil.
- `effective_date`: tanggal bisnis lokal.
- `recorded_at`: instant UTC.
- `timezone_id`: `Asia/Jakarta`.
- `period_id`: periode bisnis terkait.
- `payload_version`: versi struktur payload.
- `source`: MANUAL / CORRECTION / RESTORE_IMPORT / SYSTEM.
- `corrects_event_id`: nullable, referensi event asal.
- `voids_event_id`: nullable, hanya untuk void yang diizinkan.
- `note`: nullable.
- `created_by_label`: label operator lokal opsional.
- `integrity_hash`: opsional untuk pemeriksaan file backup, bukan pengganti enkripsi.

## Event type
1. `PRODUCT_CREATED`
2. `PRODUCT_ARCHIVED`
3. `SELLING_PRICE_CHANGED`
4. `PACKAGE_DEFINITION_CHANGED`
5. `OPENING_STOCK_RECORDED`
6. `PURCHASE_RECORDED`
7. `SALE_RECORDED`
8. `NON_CASH_RECEIPT_RECORDED`
9. `EXPENSE_RECORDED`
10. `OTHER_INCOME_RECORDED`
11. `STOCK_COUNT_RECORDED`
12. `STOCK_DAMAGED_RECORDED`
13. `STOCK_USED_RECORDED`
14. `STOCK_VARIANCE_RECORDED`
15. `DEPOSIT_RECORDED`
16. `PERIOD_CLOSED`
17. `CORRECTION_RECORDED`
18. `EVENT_VOIDED`

## Payload invariants
- Semua nominal IDR berupa integer Rupiah.
- Semua quantity menyimpan unit dasar dan faktor konversi snapshot.
- Event penjualan menyimpan harga transaksi dan diskon aktual.
- Event pembelian menyimpan kuantitas dasar, total biaya known atau flag unknown.
- Event setoran menyimpan nominal fisik positif.
- Event expense menyimpan funding source.
- Event koreksi selalu menyimpan referensi sumber dan alasan.
- Event closure menyimpan snapshot version dan period boundaries.

## Identitas dan deduplikasi
- UUID dibuat sekali saat draft dikonfirmasi.
- Idempotency key lokal mencegah double tap membuat dua event.
- Jangan menggunakan nama produk sebagai primary key.
- Foreign key dan unique index wajib pada relasi kritis.
