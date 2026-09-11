# Temuan Observasi Awal — Login User

| ID | Temuan | Bukti | Status |
|---|---|---|---|
| OBS-01 | Response `/api/login` sukses mengembalikan `"role": null` padahal `level.name = "SPV"` terisi. Dua field yang tampak tumpang tindih. | Response body Prompt B | ⚠️ Perlu konfirmasi PIC — belum tentu bug |
| OBS-02 | Elemen `[role='alert']` sudah ada di DOM saat halaman dimuat dengan teks kosong, bukan disisipkan saat dibutuhkan. | outerHTML Prompt A | ℹ️ Bukan bug, catatan teknis untuk penulisan locator |
| OBS-03 | Halaman `/apps/absent` menampilkan alert berisi teks "HADIR" tanpa aksi apa pun dari user. | Snapshot Prompt B | ⚠️ Perlu dicek manual — alert biasanya untuk notifikasi |