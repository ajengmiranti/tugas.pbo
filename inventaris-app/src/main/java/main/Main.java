package main;

import config.DatabaseConnection;
import model.Barang;
import service.BarangService;

import java.util.List;
import java.util.Scanner;

/**
 * Class Main
 * Merupakan tampilan Command Line Interface (CLI) dari aplikasi
 * Manajemen Inventaris Barang.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BarangService barangService = new BarangService();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("  APLIKASI MANAJEMEN INVENTARIS BARANG");
        System.out.println("=========================================");

        while (running) {
            tampilkanMenuUtama();
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1:
                    menuKelolaBarang();
                    break;
                case 2:
                    menuPengelolaanData();
                    break;
                case 3:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi ini. Sampai jumpa!");
                    break;
                default:
                    System.out.println(">> Pilihan tidak valid. Silakan coba lagi.\n");
            }
        }

        DatabaseConnection.closeConnection();
        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n----------- MENU UTAMA -----------");
        System.out.println("1. Kelola Barang");
        System.out.println("2. Pengelolaan Data");
        System.out.println("3. Keluar");
        System.out.print("Pilih menu (1-3): ");
    }

    private static void menuKelolaBarang() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------- KELOLA BARANG -------");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Data Barang");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-3): ");
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    lihatDataBarang();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println(">> Pilihan tidak valid.\n");
            }
        }
    }

    private static void menuPengelolaanData() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------- PENGELOLAAN DATA -------");
            System.out.println("1. Update Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih menu (1-3): ");
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1:
                    updateBarang();
                    break;
                case 2:
                    hapusBarang();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println(">> Pilihan tidak valid.\n");
            }
        }
    }

    // ===================== Fitur-fitur CLI =====================

    private static void tambahBarang() {
        try {
            System.out.println("\n--- Tambah Barang Baru ---");
            System.out.print("Nama Barang   : ");
            String nama = scanner.nextLine();
            System.out.print("Kategori (Elektronik/Makanan/Lainnya): ");
            String kategori = scanner.nextLine();
            System.out.print("Stok          : ");
            int stok = bacaInt();
            System.out.print("Harga         : ");
            double harga = bacaDouble();

            barangService.tambahBarang(nama, kategori, stok, harga);
            System.out.println(">> Barang berhasil ditambahkan!");

        } catch (IllegalArgumentException e) {
            System.out.println(">> Input tidak valid: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void lihatDataBarang() {
        try {
            System.out.println("\n--- Daftar Barang (via View) ---");
            List<Barang> daftarBarang = barangService.lihatSemuaBarang();

            if (daftarBarang.isEmpty()) {
                System.out.println("Belum ada data barang.");
            } else {
                for (Barang b : daftarBarang) {
                    b.tampilInfo(); // Polimorfisme: method berbeda tergantung jenis objek
                }
            }
        } catch (Exception e) {
            System.out.println(">> Terjadi kesalahan saat mengambil data: " + e.getMessage());
        }
    }

    private static void updateBarang() {
        try {
            System.out.println("\n--- Update Data Barang ---");
            System.out.print("Masukkan ID Barang yang akan diupdate: ");
            int id = bacaInt();

            System.out.print("Nama Barang Baru   : ");
            String nama = scanner.nextLine();
            System.out.print("Kategori Baru       : ");
            String kategori = scanner.nextLine();
            System.out.print("Stok Baru           : ");
            int stok = bacaInt();
            System.out.print("Harga Baru          : ");
            double harga = bacaDouble();

            boolean berhasil = barangService.updateBarang(id, nama, kategori, stok, harga);
            if (berhasil) {
                System.out.println(">> Data barang berhasil diperbarui!");
            } else {
                System.out.println(">> Gagal memperbarui data barang.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(">> Input tidak valid: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Terjadi kesalahan: " + e.getMessage());
        }
    }

    private static void hapusBarang() {
        try {
            System.out.println("\n--- Hapus Data Barang ---");
            System.out.print("Masukkan ID Barang yang akan dihapus: ");
            int id = bacaInt();

            boolean berhasil = barangService.hapusBarang(id);
            if (berhasil) {
                System.out.println(">> Data barang berhasil dihapus!");
            } else {
                System.out.println(">> Gagal menghapus data barang.");
            }
        } catch (Exception e) {
            System.out.println(">> Terjadi kesalahan: " + e.getMessage());
        }
    }

    // ===================== Helper input =====================

    private static int bacaPilihan() {
        try {
            int nilai = Integer.parseInt(scanner.nextLine().trim());
            return nilai;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static int bacaInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(">> Input harus berupa angka bulat. Coba lagi: ");
            }
        }
    }

    private static double bacaDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(">> Input harus berupa angka. Coba lagi: ");
            }
        }
    }
}
