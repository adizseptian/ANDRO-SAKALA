# 11 — SECURITY, PRIVACY & DATA LIFECYCLE

## Batas keamanan
Aplikasi tidak memiliki akun/login. Karena itu, perlindungan utama adalah keamanan perangkat, penguncian layar Android, dan kendali fisik perangkat. Jangan mengklaim aplikasi memiliki autentikasi pengguna.

## Data lokal
- Semua data usaha disimpan pada Room/SQLite lokal.
- Tidak dikirim ke server.
- Tidak ada analytics/ads/telemetry.
- Tidak ada INTERNET permission.
- `allowBackup=false` pada baseline agar mekanisme backup Android tidak disamakan dengan fitur backup produk.

## Data sensitif
Data usaha dapat mengandung omzet, biaya, stok, dan informasi pegawai. Jangan memasukkan data ke log produksi. Redaksi log wajib dilakukan.

## Backup
Backup manual harus dienkripsi AES-GCM. Passphrase tidak disimpan di plaintext. KDF harus menggunakan parameter yang terdokumentasi dan dapat diupgrade. Salt dan nonce harus unik. Format file memiliki magic header dan version.

## Restore
File harus divalidasi sebelum database aktif disentuh. Buat backup kondisi sekarang. Restore harus atomik dan memiliki rollback bila gagal. Jangan menghapus database lama sebelum verifikasi database baru selesai.

## Kehilangan perangkat
Tanpa backup eksternal, uninstall/kerusakan perangkat dapat menyebabkan kehilangan data. Jelaskan ini kepada pengguna sebelum penggunaan nyata.

## Keamanan supply chain
Tidak ada PAT, token GitHub, API key, signing key, atau passphrase dalam source repository. Signing keystore disimpan aman di luar repo. Dependency diperbarui secara terencana dan dipindai.
