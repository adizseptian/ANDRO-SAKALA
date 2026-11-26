# 06 — DESIGN SYSTEM: SAKALA ATELIER / MERCHANT PRO

## 1. Konsep
**SAKALA ATELIER — Merchant Pro**  
Warm Editorial Fintech · Quiet Confidence · Operational Clarity · Tactile Precision.

Visual harus terasa matang, tenang, presisi, dan mudah dibaca. Fokus utama adalah angka dan aktivitas, bukan dekorasi.

## 2. Palet warna
| Token | Hex | Peran |
|---|---|---|
| Porcelain | #F7F5F0 | Background utama |
| Surface | #FFFFFF | Permukaan kartu/form |
| Soft | #EEEDE7 | Surface sekunder |
| Ink | #191D1B | Teks utama |
| Muted | #68716B | Teks sekunder |
| Line | #E3E2DB | Divider/border |
| Evergreen | #173F36 | Aksi utama/identitas |
| Evergreen Soft | #E8F0EC | Surface informasi lembut |
| Champagne | #B18B60 | Aksen dekoratif terbatas |
| Success | #246B4A | Status berhasil |
| Warning | #8B5A18 | Perlu diperiksa |
| Error | #9C3A36 | Kesalahan |
| Info | #315D7A | Informasi |

Tidak menggunakan violet. Tidak menggunakan motif Mega Mendung. Champagne bukan warna teks kecil di atas background putih jika kontras tidak cukup.

## 3. Tipografi
- Pilihan utama: Plus Jakarta Sans; fallback Inter/Roboto.
- Judul halaman 26–30sp, semibold/bold.
- Judul section 16–18sp, semibold.
- Body 14–16sp.
- Supporting 12–13sp, tidak untuk informasi kritis.
- Angka uang memakai tabular numerals.
- Hindari uppercase panjang dan tracking berlebihan.

## 4. Spacing dan geometri
Spacing scale: 4, 8, 12, 16, 20, 24, 32, 40dp.  
Margin layar 20dp (adaptif).  
Input 52–56dp.  
Primary button 54dp, radius 14dp.  
Card radius 16dp.  
Minimum target sentuh 48×48dp.  
Grid harus konsisten; jangan menyusun kartu hanya untuk memenuhi ruang.

## 5. Komponen
- AppTopBar: nama layar, back, action contextual.
- BottomNavigation: lima tujuan primer.
- PeriodSelector: periode Senin–Minggu.
- MoneyValue: nilai Rupiah + status kepastian.
- MetricRow: label, nilai, sumber/rincian.
- StatusPill: COCOK, PERLU DIPERIKSA, BELUM COCOK, MENUNGGU SETORAN.
- ProductListItem: nama, stok, satuan, status modal.
- EmptyState: judul, penjelasan, satu CTA.
- ErrorState: masalah + langkah perbaikan.
- FormField: label, nilai, helper, error.
- MoneyInput: angka Rupiah, keyboard numerik, pemformatan yang aman.
- QuantityInput: angka kuantitas + satuan.
- PreviewPanel: perubahan sebelum simpan.
- ConfirmationDialog: konsekuensi + tombol jelas.
- EventTimelineItem: waktu, jenis, nilai, tautan.
- BottomSheetPicker: pilihan kontekstual.
- Snackbar: hasil singkat dengan action jika relevan.

## 6. Dark theme
Dark theme harus memakai token semantik, bukan membalik warna secara otomatis. Kontras teks minimum WCAG AA. Surface berlapis menggunakan perbedaan luminance halus. Status tetap dibedakan dengan teks/ikon, bukan warna saja.

## 7. Aksesibilitas
- TalkBack label untuk ikon dan tombol.
- Urutan fokus logis.
- Font scaling hingga 200% tanpa clipping.
- Jangan memakai warna sebagai satu-satunya pembeda.
- Touch target 48dp.
- Error dikaitkan ke field.
- Angka dibacakan dengan konteks Rupiah.
- Reduced motion mengikuti preferensi sistem.

## 8. Responsif
- Compact phone: satu kolom, bottom navigation.
- Medium: konten terpusat dengan batas lebar.
- Expanded/tablet: navigation rail dan dua panel bila membantu.
- Minimum 320dp; uji 360, 393, 600, 840dp.
- Keyboard tidak boleh menutupi CTA/form aktif.

## 9. Larangan visual
No neon, no glassmorphism, no violet, no decorative chart, no giant repeated cards, no micro-label for critical information, no excessive gradients, no random icon style, no animation without purpose.
