# 28 — SECURITY & PRIVACY THREAT MODEL

## Aset yang dilindungi
Data produk, transaksi, stok, biaya/modal, laporan, catatan setoran, file backup, passphrase.

## Batas kepercayaan
- Perangkat Android adalah batas penyimpanan utama.
- Aplikasi tidak mengirim data melalui jaringan pada baseline.
- File backup yang dipilih pengguna dianggap input tidak tepercaya.
- Data hasil restore harus divalidasi sebelum masuk database aktif.

## Ancaman dan mitigasi
- Kehilangan perangkat: jelaskan bahwa data lokal dapat hilang; dorong backup manual.
- Akses fisik ke perangkat terbuka: gunakan screen lock perangkat; aplikasi tidak menjanjikan proteksi jika perangkat sudah terbuka.
- Backup dicuri: enkripsi authenticated dengan passphrase kuat.
- File backup dimodifikasi: verifikasi authentication tag.
- Restore malformed: validasi ukuran, versi, schema, referensi, dan batas parsing.
- Log bocor: redaksi data sensitif.
- Data rusak: integrity check dan backup sebelum restore/migration.
- Double tap: idempotency dan transaksi database.

## Bukan bagian baseline
Tidak ada akun, login, PIN aplikasi, remote wipe, cloud backup, sinkronisasi, analytics, iklan, atau telemetri. Jangan menambahkan diam-diam.

## Disclosure pengguna
- Data disimpan pada perangkat.
- Menghapus aplikasi/perangkat dapat menghilangkan data.
- Backup manual adalah tanggung jawab pengguna.
- Passphrase backup tidak dapat dipulihkan oleh pengembang.
