# 24 — ACCESSIBILITY & RESPONSIVE QUALITY

## Aksesibilitas
- Semua kontrol memiliki semantics dan label TalkBack.
- Informasi status tidak hanya dibedakan melalui warna.
- Target sentuh minimal 48 dp.
- Kontras teks normal minimal WCAG AA.
- Ukuran font sistem dihormati; layout tidak memotong nilai.
- Dialog memiliki urutan fokus dan aksi yang jelas.
- Error field diumumkan oleh accessibility semantics.
- Grafik, bila ada, memiliki ringkasan tekstual.
- Jangan memakai ikon tanpa label untuk aksi penting.

## Responsif
Target uji: 320, 360, 393, 412, 600, 840 dp.
- Ponsel: satu kolom, bottom navigation.
- Tablet: rail/navigation adaptif; konten dapat menjadi dua kolom bila hierarki tetap jelas.
- Landscape: form dan ringkasan tidak boleh terpotong.
- Insets: status bar, navigation bar, keyboard, display cutout.
- Foldable: hindari elemen utama melewati hinge.

## Keyboard dan navigasi
- Urutan fokus mengikuti alur input.
- Next berpindah ke field berikutnya; Done menutup keyboard atau menyelesaikan form.
- Back dari form yang berubah menampilkan konfirmasi buang draft.
- Back dari dialog menutup dialog tanpa menyimpan.
- Deep link internal harus mempertahankan konteks periode.
- Rotasi dan process recreation tidak boleh menggandakan event.

## Kualitas visual
- Prioritaskan angka dan label penting.
- Hindari semua konten dibungkus card.
- Spasi antarbagian konsisten.
- Empty state menjelaskan langkah pertama.
- Hindari dashboard penuh grafik dekoratif.
