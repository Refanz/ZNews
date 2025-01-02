# ZNews

ZNews adalah aplikasi berita sederhana yang dikembangkan menggunakan Android Studio dengan bahasa pemrograman Kotlin. Aplikasi ini menggunakan Jetpack Compose sebagai toolkit UI modern untuk Android dan mengimplementasikan berbagai teknologi terkini seperti pagination menggunakan Paging 3, loading gambar dengan Coil, dan komunikasi API dengan Retrofit. Aplikasi ini juga menerapkan dependency injection menggunakan Hilt untuk manajemen dependensi. Arsitektur yang digunakan adalah MVVM (Model-View-ViewModel).

## Fitur Utama

- Menampilkan daftar berita terbaru.
- Mendukung pagination untuk memuat artikel berita secara bertahap.
- Memuat gambar dengan efisien menggunakan Coil.
- Menggunakan Retrofit untuk mengambil data dari API.
- Dependency injection dengan Hilt untuk kode yang lebih modular dan terorganisir.

## Teknologi yang Digunakan

- **Kotlin**: Bahasa pemrograman utama.
- **Jetpack Compose**: Toolkit UI untuk membuat antarmuka pengguna yang deklaratif.
- **Paging 3**: Untuk mendukung pagination pada data berita.
- **Coil**: Untuk memuat dan menampilkan gambar secara efisien.
- **Retrofit**: Library HTTP untuk berkomunikasi dengan API.
- **Hilt**: Library dependency injection dari Jetpack.
- **MVVM**: Arsitektur aplikasi untuk memisahkan logika bisnis dari logika UI.

## Struktur Folder

```plaintext
ZNews
├── data
│   ├── local
│   │   └── (Class untuk data lokal)
│   ├── model
│   │   └── (Class untuk model data)
│   ├── remote
│   │   └── (Class untuk API service dan konfigurasi retrofit)
│   ├── repository
│       └── (Class untuk mengelola data dari local dan remote)
├── navigation
│   └── (Class untuk mengelola navigasi antar layar)
├── ui
│   └── (Class untuk UI dengan Jetpack Compose)
├── utils
    └── (Class untuk fungsi-fungsi utilitas)
```

## Cara Instalasi dan Menjalankan

1. Clone repository ini ke komputer Anda.
   ```bash
   git clone <url-repository>
   ```

2. Buka proyek di Android Studio.

3. Pastikan Anda telah mengatur API key jika diperlukan di file konfigurasi.

4. Jalankan aplikasi di emulator atau perangkat fisik.

## Kontribusi

Kontribusi sangat diterima! Jika Anda ingin berkontribusi, silakan buat pull request atau buka issue untuk mendiskusikan perubahan yang ingin Anda lakukan.

## Lisensi

Proyek ini dilisensikan di bawah lisensi MIT. Silakan lihat file `LICENSE` untuk detail lebih lanjut.

---

Terima kasih telah menggunakan ZNews! Semoga aplikasi ini bermanfaat untuk Anda.
