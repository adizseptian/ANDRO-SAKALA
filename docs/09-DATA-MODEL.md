# 09 — DATA MODEL & EVENT LEDGER

## Prinsip
Event adalah sumber kebenaran. Tabel saldo/snapshot merupakan projection/derived state yang dapat dibangun ulang dan harus memiliki mekanisme rekonsiliasi.

## Entitas inti
### store_profile
`id`, `store_name`, `owner_label`, `address`, `currency_code`, `timezone`, `created_at`, `updated_at`.

### product
`id`, `name`, `sku`, `category_id`, `base_unit`, `active`, `created_at`, `updated_at`.

### package_definition
`id`, `product_id`, `label`, `base_units_per_package`, `effective_from`, `effective_to`, `is_default`.

### product_price_history
`id`, `product_id`, `unit_price`, `effective_from`, `effective_to`, `reason`, `created_at`.

### business_period
`period_start`, `period_end`, `status`, `closed_at`, `snapshot_id`.

### business_event
`id`, `event_type`, `occurred_at`, `business_date`, `period_start`, `note`, `created_at`, `schema_version`, `idempotency_key`, `correction_of_event_id`, `voids_event_id`.

### event_line
`id`, `event_id`, `product_id`, `quantity_base_units`, `unit_price_snapshot`, `discount_amount`, `known_cost_amount`, `unknown_cost_units`, `package_definition_snapshot`.

### inventory_cost_pool
Projection: `product_id`, `known_units`, `known_cost_numerator`, `unknown_units`, `as_of_event_id`, `projection_version`.

### expense_detail
`event_id`, `category_id`, `amount`, `funding_source`.

### receipt_detail
`event_id`, `channel`, `amount`, `reference`.

### deposit_detail
`event_id`, `amount`, `recipient_label`.

### period_closure
`period_start`, `closed_at`, `snapshot_payload`, `digest`, `engine_version`, `schema_version`.

### audit_event
`id`, `action`, `target_id`, `before_digest`, `after_digest`, `reason`, `created_at`.

### app_meta
`key`, `value`, `updated_at`; menyimpan versi engine/schema dan metadata non-rahasia.

## Event type minimum
`OPENING_STOCK`, `PURCHASE`, `PHYSICAL_COUNT`, `SALE`, `DISCOUNT`, `NON_CASH_RECEIPT`, `EXPENSE`, `OTHER_INCOME`, `DAMAGED`, `USED`, `UNKNOWN_STOCK_DIFFERENCE`, `DEPOSIT`, `PRICE_CHANGE`, `PACKAGE_CHANGE`, `CORRECTION`, `VOID`.

## Constraints
- Primary key UUID.
- Foreign key dengan kebijakan yang tidak menghapus histori.
- Unique idempotency key per command.
- Nominal non-negatif; setoran > 0.
- Kuantitas positif untuk event masuk/keluar; arah ditentukan event type.
- `correction_of_event_id` harus merujuk event yang ada.
- Closed period tidak boleh update/delete event sumber atau snapshot.
- Indeks pada business_date, period_start, event_type, product_id.
- Transaksi multi-tabel wajib atomik.

## Catatan implementasi
Jangan menjadikan JSON snapshot satu-satunya sumber kebenaran. Simpan struktur yang dapat divalidasi dan versioned serialization. Room migration harus diuji dari setiap versi yang pernah dirilis.
