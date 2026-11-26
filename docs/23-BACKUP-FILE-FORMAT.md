# 23 — BACKUP FILE FORMAT

## Tujuan
File backup harus portabel, terenkripsi, tervalidasi, dan tidak bergantung pada akun/cloud.

## Container konseptual
- Magic bytes: `SAKALA`.
- Container version.
- Encryption suite identifier.
- KDF identifier dan parameter.
- Random salt.
- Random nonce.
- Ciphertext.
- Authentication tag.
- Encrypted manifest: app version, schema version, payload version, created timestamp, period range, record counts.

## Kriptografi
- AES-256-GCM untuk authenticated encryption.
- Passphrase tidak disimpan.
- Key derivation memakai Argon2id bila dependency yang terawat dan kompatibel tersedia; jika memakai PBKDF2-HMAC-SHA256, parameter harus ditetapkan dan diuji pada perangkat target.
- Salt dan nonce harus unik/acak dengan SecureRandom.
- Authentication failure membatalkan restore.
- Jangan menulis passphrase, key, plaintext backup, atau data sensitif ke log.

## Restore validation
1. Periksa magic bytes dan batas ukuran file.
2. Baca versi container.
3. Validasi parameter KDF dalam rentang aman.
4. Turunkan key.
5. Verifikasi tag GCM sebelum menggunakan plaintext.
6. Parse manifest dan cek versi.
7. Validasi semua record, referensi, dan invariant.
8. Tampilkan preview.
9. Buat backup kondisi saat ini.
10. Restore dalam staging database.
11. Jalankan integrity checks.
12. Tukar database secara atomik.
13. Jika langkah gagal, database aktif tetap utuh.

## Batas dan kompatibilitas
- Ukuran maksimum file harus ditentukan berdasarkan perangkat; implementasi menggunakan streaming untuk file besar.
- Format memiliki `containerVersion` dan `payloadVersion`.
- Versi lama yang didukung dimigrasikan pada staging; versi lebih baru ditolak dengan pesan yang jelas.
- File rusak tidak boleh menyebabkan penghapusan database aktif.
