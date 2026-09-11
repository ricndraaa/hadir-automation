# Login User (Mobile Mirroring) — /absen/login

**URL:** https://magang.dikahadir.com/absen/login
**Tanggal ambil DOM:** 2026-09-09
**Diambil via:** Playwright MCP (browser_snapshot + browser_evaluate outerHTML)
**UI Library:** Material UI (MUI) — ⛔ TIDAK ada data-testid buatan tim Hadir

## Locator

| Elemen | Locator Selenium | Stabilitas | Catatan |
|---|---|---|---|
| Input Email | `By.id("email")` | 🟢 Tinggi | punya `id` dan `name`, keduanya `email` |
| Input Password | `By.id("password")` | 🟢 Tinggi | hanya punya `id`, TIDAK punya `name` |
| Tombol Masuk | `By.cssSelector("button[type='submit']")` | 🟢 Tinggi | satu-satunya submit di halaman |
| Toggle lihat password | `By.xpath("//input[@id='password']/following::button[1]")` | 🟡 Sedang | tombol tanpa atribut apa pun; dijangkar dari input password |
| Ikon alert ERROR | `By.cssSelector("[role='alert'] svg[data-testid='ErrorOutlineIcon']")` | 🟢 Tinggi | bawaan MUI, penanda severity |
| Teks pesan alert | `By.cssSelector("[role='alert'] .MuiAlert-message p")` | 🟡 Sedang | untuk logging saja, ⛔ JANGAN di-assert |
| Link "Lupa password ?" | `By.xpath("//button[normalize-space()='Lupa password ?']")` | 🔴 Rendah | berbasis teks, mati kalau kalimat diubah |
| Link "disini" (Buat akun) | `By.xpath("//button[normalize-space()='disini']")` | 🔴 Rendah | berbasis teks |

## ⛔ Yang DILARANG dipakai sebagai locator

- Class hash Emotion: `css-1o6z5ng`, `css-4di9ve`, `css-1yhx7lq` — regenerate setiap kali CSS berubah
- Class semantik MUI sendirian: `MuiButton-containedPrimary` dipakai semua tombol primary di seluruh aplikasi, tidak unik

## Alur

**Login berhasil**
- Redirect ke `https://magang.dikahadir.com/apps/absent`
- ⚠️ URL login pakai `/absen` (Indonesia), URL tujuan pakai `/absent` (Inggris) — rawan typo
- `POST /api/login` → 200, body: `{"success": true, "message": "Berhasil login", "level": {...}, "isLeader": true}`
- Tidak ada token di response → sesi lewat cookie, Selenium menanganinya otomatis
- Elemen penanda halaman tujuan: tombol "Absen Masuk"

**Login gagal**
- URL tetap di `/absen/login`
- Muncul alert error: class `MuiAlert-filledError`, ikon `data-testid="ErrorOutlineIcon"`, teks "Email atau password salah"
- `POST /api/login` → 401, body: `{"name": "NotAuthenticated", "code": 401, "className": "not-authenticated"}`

## Catatan akun uji

`hadirsqa1@gmail.com` → level **SPV** (level 3), `isLeader: true`, `employee_type: PKWT`
⚠️ Ini akun leader, bukan karyawan biasa — menu yang tampil kemungkinan berbeda dari user non-leader.

## Yang belum diverifikasi

- [ ] outerHTML tombol "Absen Masuk" di halaman `/apps/absent`
- [ ] Bentuk alert saat login BERHASIL (ikon apa yang dipakai?)
- [ ] Apakah elemen `[role='alert']` unik per halaman atau dipakai bersama