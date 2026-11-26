# 21 — ROOM SCHEMA & MIGRATION DESIGN

## Entitas inti
`StoreProfile`, `Product`, `SellingPriceHistory`, `PackageDefinition`, `BusinessPeriod`, `BusinessEvent`, `EventLine`, `PeriodClosure`, `PeriodSnapshot`, `AppPreference`, `SchemaMetadata`.

## Kolom minimal
### Product
`productId TEXT PK`, `name TEXT NOT NULL`, `baseUnit TEXT NOT NULL`, `sku TEXT NULL`, `categoryId TEXT NULL`, `isArchived INTEGER NOT NULL`, `createdAt TEXT NOT NULL`.

### BusinessEvent
`eventId TEXT PK`, `eventType TEXT NOT NULL`, `effectiveDate TEXT NOT NULL`, `recordedAtEpochMillis INTEGER NOT NULL`, `periodId TEXT NOT NULL`, `payloadJson TEXT NOT NULL`, `payloadVersion INTEGER NOT NULL`, `correctsEventId TEXT NULL`, `voidsEventId TEXT NULL`, `note TEXT NULL`.

### EventLine
`lineId TEXT PK`, `eventId TEXT FK`, `productId TEXT FK`, `quantityBase INTEGER NOT NULL`, `unitPriceRupiah INTEGER NULL`, `discountRupiah INTEGER NULL`, `knownCostRupiah INTEGER NULL`, `unknownCostQuantity INTEGER NOT NULL`, `conversionFactorSnapshot INTEGER NULL`.

### PeriodClosure
`periodId TEXT PK`, `closedAtEpochMillis INTEGER NOT NULL`, `snapshotVersion INTEGER NOT NULL`, `snapshotDigest TEXT NOT NULL`.

### PeriodSnapshot
`periodId TEXT PK/FK`, `snapshotJson TEXT NOT NULL`, `createdAtEpochMillis INTEGER NOT NULL`.

## Index
- BusinessEvent `(periodId, effectiveDate)`
- BusinessEvent `(eventType, effectiveDate)`
- BusinessEvent `(correctsEventId)`
- EventLine `(productId, eventId)`
- SellingPriceHistory `(productId, effectiveFrom)`
- PackageDefinition `(productId, isActive)`

## Constraints
- IDR nonnegative pada field yang memang tidak menerima nilai negatif.
- Quantity transaksi positif; stock balance dapat dihitung sebagai signed movement.
- Foreign keys ON.
- Jangan cascade-delete event ledger.
- Produk yang pernah dipakai event tidak dihapus fisik.
- Snapshot closure tidak dapat di-update melalui DAO umum.

## Migration
1. Setiap perubahan schema menaikkan database version.
2. Migration harus eksplisit dan deterministik.
3. Tidak memakai destructive migration untuk database pengguna.
4. Migration diuji dari setiap versi rilis yang didukung.
5. Backup otomatis sebelum migration besar bila memungkinkan.
6. Jika migration gagal, transaksi dibatalkan dan database lama dipertahankan.
7. Migration menambah metadata versi payload secara terpisah dari versi schema Room.

## Query dan performa
- Jangan memuat seluruh event ledger untuk layar daftar.
- Gunakan paging atau query rentang tanggal.
- Ringkasan memakai projection/cache yang dapat dibangun ulang dari event.
- Cache bukan sumber kebenaran dan harus dapat direkonstruksi.
