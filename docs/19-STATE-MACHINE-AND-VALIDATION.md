# 19 — STATE MACHINE, VALIDATION & ERROR CONTRACT

## State form
`Idle → Editing → Validating → PreviewReady → Saving → Saved`
Cabang:
- `Editing → Invalid`: field error; tetap di form.
- `Validating → Blocked`: jelaskan blocker.
- `Saving → Failed`: pertahankan input dan tampilkan retry.
- `Saving → Saved`: cegah double submit, navigasi ke hasil.

## State periode
`OPEN → REVIEW_REQUIRED → READY_TO_CLOSE → CLOSED`
- OPEN menerima event baru.
- REVIEW_REQUIRED memiliki unresolved atau uncertainty.
- READY_TO_CLOSE berarti semua pemeriksaan yang diwajibkan selesai; warning yang diizinkan tetap ditampilkan.
- CLOSED bersifat immutable.
Tidak ada transisi CLOSED → OPEN. Koreksi melalui event tertaut.

## State stok produk
- NOT_INITIALIZED: produk ada, stok awal belum dicatat.
- AVAILABLE: saldo diketahui dan dapat dipakai.
- MIXED_COST: ada pool known dan unknown.
- UNKNOWN_COST_ONLY: kuantitas ada tetapi modal tidak diketahui.
- ZERO_STOCK: saldo nol.
- REVIEW_REQUIRED: ada selisih/ketidakpastian yang belum diselesaikan.
State dihitung, bukan disimpan sebagai sumber kebenaran.

## Error contract
Setiap error domain memiliki `code`, `userMessage`, `field`, `recoverability`, `sourceEventId?`.
Contoh:
- `PRODUCT_NAME_REQUIRED`: “Masukkan nama produk.”
- `INVALID_QUANTITY`: “Jumlah harus lebih dari 0.”
- `PACKAGE_FACTOR_INVALID`: “Isi kemasan harus bilangan bulat lebih dari 0.”
- `INSUFFICIENT_STOCK`: “Stok tercatat tidak mencukupi. Tersedia {qty} {unit}.”
- `DISCOUNT_EXCEEDS_GROSS`: “Diskon tidak boleh lebih besar dari nilai penjualan.”
- `UNKNOWN_COST_IMPACT`: “Sebagian barang yang digunakan belum memiliki modal yang diketahui. Hasil laba perlu diperiksa.”
- `PERIOD_CLOSED`: “Periode ini sudah ditutup. Buat koreksi tertaut untuk memperbaiki catatan.”
- `DUPLICATE_SUBMIT`: “Catatan sedang diproses. Tunggu sampai selesai.”
- `BACKUP_AUTH_FAILED`: “Passphrase tidak cocok atau file telah berubah.”
- `BACKUP_SCHEMA_UNSUPPORTED`: “Versi file cadangan tidak didukung aplikasi ini.”

## Konfirmasi
Konfirmasi wajib untuk:
- menutup periode,
- restore database,
- perubahan harga/kemasan yang berlaku ke depan,
- koreksi yang berdampak ke laporan,
- void event yang masih memenuhi syarat.
Konfirmasi menyebut objek, nilai, dan dampak; bukan sekadar “Yakin?”.
