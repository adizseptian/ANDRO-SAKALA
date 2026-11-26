# 33 — RELEASE & DEVICE VALIDATION

## Kategori perangkat
- Android entry-level dengan RAM terbatas.
- Android mid-range.
- Android high-end.
- Tablet Android.
- Perangkat dengan font display besar.
- Perangkat dengan dark mode aktif.

## Validasi sebelum rilis
- Install bersih.
- Upgrade dari versi sebelumnya.
- Database berisi banyak event.
- Storage hampir penuh.
- Rotasi layar saat form aktif.
- Aplikasi masuk background ketika menyimpan.
- Proses aplikasi dihentikan sistem.
- Restore file valid, salah passphrase, rusak, dan versi tidak didukung.
- Ekspor PDF/CSV ke beberapa provider dokumen.
- Font scale 1.0, 1.3, 1.5, 2.0.
- TalkBack aktif.
- Mode gelap.
- Locale Indonesia dan locale perangkat berbeda.

## Release artifact
- APK internal untuk pengujian.
- AAB untuk distribusi Play bila dipilih.
- Signing key disimpan aman di luar repository.
- Version code selalu meningkat.
- Release notes menyebut perubahan bisnis dan migrasi.
- Jangan menyertakan database produksi dalam artifact.
