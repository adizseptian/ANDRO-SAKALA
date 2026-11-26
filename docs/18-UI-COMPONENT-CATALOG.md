# 18 — UI COMPONENT CATALOG

## Prinsip visual
SAKALA ATELIER — Merchant Pro. Warm Editorial Fintech, Quiet Confidence, Operational Clarity, Tactile Precision. Fungsi dan keterbacaan mengalahkan dekorasi.

## Komponen fondasi
- `SakalaScaffold`: safe area, top app bar, content host, bottom navigation.
- `PageHeader`: judul, konteks periode, aksi utama.
- `PeriodSelector`: rentang Senin–Minggu, label WIB.
- `MoneyText`: format IDR, tabular numerals, semantic content description.
- `QuantityText`: kuantitas dan unit dasar.
- `StatusPill`: COCOK, PERLU DIPERIKSA, BELUM COCOK, MENUNGGU SETORAN; selalu disertai teks, bukan warna saja.
- `MetricRow`: label, nilai, supporting explanation.
- `SectionHeader`: judul dan optional action.
- `ProductListItem`: nama, saldo, harga, unknown-cost cue.
- `EventTimelineItem`: tanggal, tipe, deskripsi, nilai, status koreksi.
- `EmptyState`: alasan kosong + tindakan berikutnya.
- `ErrorState`: apa yang gagal + opsi retry/keluar aman.
- `ReadOnlyBanner`: alasan data tidak dapat diubah.
- `InlineValidation`: pesan tepat di dekat field.
- `ImpactPreview`: perubahan sebelum/sesudah.
- `ConfirmSheet`: ringkasan konsekuensi dan tombol eksplisit.
- `UncertaintyNotice`: jelaskan data yang belum diketahui dan dampaknya.
- `SourceBreakdown`: angka beserta asal event.

## Ukuran dan bentuk
- Grid spacing: 4, 8, 12, 16, 20, 24, 32, 40 dp.
- Horizontal content margin: 20 dp pada ponsel.
- Minimum touch target: 48×48 dp.
- Input: tinggi target 52–56 dp.
- Primary action: tinggi 54 dp, radius 14 dp.
- Card: radius 16 dp; border halus, elevasi minimal.
- Dialog/sheet: radius atas 24 dp.
- Icon aksi: 20–24 dp; dekoratif tidak boleh mendominasi.
- Angka uang: gunakan tabular figures dan alignment konsisten.

## Warna
- Porcelain #F7F5F0
- Surface #FFFFFF
- Soft #EEEDE7
- Ink #191D1B
- Muted #68716B
- Line #E3E2DB
- Evergreen #173F36
- Evergreen Soft #E8F0EC
- Champagne #B18B60 (aksen dekoratif terbatas)
- Success #246B4A
- Warning #8B5A18
- Error #9C3A36
- Info #315D7A
Tidak menggunakan violet, Mega Mendung, neon, glassmorphism, atau gradient sebagai pengganti hierarki.

## Tipografi
Plus Jakarta Sans atau Inter. Hierarki: display angka utama, title layar, section title, body, supporting, label. Jangan memakai micro-label yang sulit dibaca. Font scale Android harus dihormati.

## Bottom navigation
Lima item: Beranda, Stok, Catat, Setoran, Lainnya. Ikon dan label selalu terlihat. Navigasi Catat membuka action hub, bukan langsung menyimpan transaksi. State tiap tab dipertahankan sewajarnya.

## Motion dan feedback
- Animasi transisi 150–250 ms untuk perpindahan ringan.
- Hindari animasi berulang pada angka keuangan.
- Haptic hanya untuk konfirmasi penting, tidak setiap tap.
- Snackbar untuk hasil singkat; hasil finansial penting tetap terlihat pada layar.
- Reduce motion dan system animation scale dihormati.
