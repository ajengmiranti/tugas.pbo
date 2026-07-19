# Aplikasi Manajemen Inventaris Barang (CLI)

Aplikasi berbasis **Command Line Interface (CLI)** menggunakan **Java** dan **MySQL**
untuk mengelola data barang pada toko/gudang sederhana.

## Struktur Folder

```
inventaris-app/
├── pom.xml                              # konfigurasi Maven & dependency MySQL
├── database/
│   └── db_inventaris.sql                # script database lengkap (tabel, procedure, function, trigger, view)
├── src/main/java/
│   ├── config/
│   │   └── DatabaseConnection.java      # koneksi database (Singleton)
│   ├── model/
│   │   ├── Barang.java                  # parent class
│   │   ├── BarangElektronik.java        # child class (inheritance + polimorfisme)
│   │   └── BarangMakanan.java           # child class (inheritance + polimorfisme)
│   ├── dao/
│   │   └── BarangDAO.java               # akses database (CRUD, procedure, function, view)
│   ├── service/
│   │   └── BarangService.java           # business logic & validasi
│   └── main/
│       └── Main.java                    # tampilan CLI (menu utama)
└── README.md
```

## Persyaratan

- Java JDK 11 atau lebih baru
- Maven 3.6+
- MySQL Server 5.7+ / 8.0+

## Langkah Menjalankan

### 1. Setup Database

Buka MySQL client (Workbench, CLI, dll), lalu jalankan file SQL:

```bash
mysql -u root -p < database/db_inventaris.sql
```

Script ini akan otomatis membuat:
- Database `db_inventaris`
- Tabel `barang`
- Trigger `trg_validasi_stok` (validasi stok tidak boleh negatif)
- Stored Procedure `tambah_barang()`
- Stored Function `total_nilai_barang()`
- View `view_barang`
- 3 data contoh

### 2. Konfigurasi Koneksi Database

Buka file `src/main/java/config/DatabaseConnection.java` dan sesuaikan:

```java
private static final String USER = "root";
private static final String PASSWORD = ""; // isi sesuai password MySQL Anda
```

### 3. Build Project dengan Maven

```bash
cd inventaris-app
mvn clean package
```

Perintah ini akan menghasilkan file `target/inventaris-app.jar` (fat jar, sudah termasuk driver MySQL).

### 4. Jalankan Aplikasi

```bash
java -jar target/inventaris-app.jar
```

Atau langsung tanpa build jar, jalankan lewat Maven:

```bash
mvn compile exec:java -Dexec.mainClass="main.Main"
```

(Jika plugin `exec` belum ada, cukup gunakan cara `mvn clean package` di atas.)

## Fitur Aplikasi

**1. Kelola Barang**
- Tambah Barang → memanggil Stored Procedure `tambah_barang()`
- Lihat Data Barang → mengambil data dari View `view_barang` (total nilai dihitung otomatis via Stored Function)

**2. Pengelolaan Data**
- Update Barang
- Hapus Barang

**3. Keluar**

## Konsep OOP yang Diimplementasikan

| Konsep | Implementasi |
|---|---|
| Class & Object | `Barang`, `BarangElektronik`, `BarangMakanan`, dll. Objek dibuat saat tambah/lihat data |
| Inheritance | `BarangElektronik` & `BarangMakanan` extends `Barang` |
| Polimorfisme | Method `tampilInfo()` di-override di masing-masing child class |
| Enkapsulasi | Seluruh atribut `private`, diakses via getter/setter |
| Package | `config`, `model`, `dao`, `service`, `main` |
| Exception Handling | Menangani `SQLException`, `ClassNotFoundException`, `NumberFormatException`, dan `IllegalArgumentException` |

## Troubleshooting

- **Error "Gagal terhubung ke database"** → pastikan MySQL server berjalan, dan cek USER/PASSWORD di `DatabaseConnection.java`.
- **Error "Unknown database db_inventaris"** → jalankan ulang langkah 1 (import script SQL).
- **Port MySQL berbeda** → ubah bagian `localhost:3306` di URL koneksi sesuai port MySQL Anda.
