# 12 — MANUAL ENCRYPTED BACKUP & RESTORE

## Tujuan
Memberi pemilik cara membawa salinan database secara manual tanpa cloud sync.

## UX backup
1. Buka Lainnya → Backup.
2. Tampilkan tanggal backup terakhir (jika ada), ukuran perkiraan, dan jumlah record.
3. Pilih “Buat backup”.
4. Masukkan passphrase dan konfirmasi.
5. Beri penjelasan: passphrase tidak dapat dipulihkan.
6. Android SAF membuka pemilih lokasi.
7. Aplikasi membuat konsistensi snapshot database.
8. Serialisasi payload dengan schema version.
9. Enkripsi AES-GCM.
10. Tulis file dan flush.
11. Baca kembali file untuk verifikasi autentikasi/header.
12. Tampilkan lokasi/nama file dan waktu backup.

## Format kontainer
Header konseptual:
- magic bytes `SAKALA`
- container_version
- kdf_id + kdf_parameters
- salt
- nonce
- ciphertext_length
- ciphertext + GCM authentication tag

Payload terenkripsi:
- app_version
- schema_version
- engine_version
- exported_at
- business timezone
- profile
- events
- period closures/snapshots
- integrity metadata

Tidak menyimpan passphrase dalam file. Parameter kriptografi final harus direview keamanan sebelum implementasi produksi.

## Restore UX
1. Pilih file.
2. Masukkan passphrase.
3. Validasi format dan batas ukuran.
4. Derive key.
5. Autentikasi/dekripsi.
6. Validasi schema, versi, referensi, jumlah record, digest.
7. Tampilkan preview: toko, rentang tanggal, jumlah produk/event/periode.
8. Jelaskan bahwa restore mengganti data aktif.
9. Buat backup otomatis kondisi saat ini ke lokasi yang dipilih.
10. Minta konfirmasi eksplisit.
11. Restore ke database staging.
12. Jalankan integrity check dan domain validation.
13. Tukar database secara atomik.
14. Buka aplikasi pada state hasil restore.
15. Jika gagal: database aktif lama tetap utuh.

## Kegagalan
- Passphrase salah → “Kata sandi backup tidak cocok. Data perangkat tidak berubah.”
- File rusak → “File backup tidak lengkap atau rusak.”
- Versi tidak didukung → “Backup dibuat oleh versi aplikasi yang belum didukung.”
- Ruang tidak cukup → batalkan sebelum mengganti data.
- Gagal restore → rollback dan pertahankan database aktif.

## Acceptance
- Salah passphrase tidak mengubah database.
- Bit flip ciphertext terdeteksi.
- Backup terpotong ditolak.
- Schema invalid ditolak.
- Simulasi force-close saat restore tidak menghasilkan database setengah.
- Backup dapat dipulihkan pada perangkat Android lain dengan aplikasi kompatibel.
