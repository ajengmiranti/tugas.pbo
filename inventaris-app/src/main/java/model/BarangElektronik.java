package model;

/**
 * Class BarangElektronik (Child Class)
 * Merupakan turunan dari class Barang (Inheritance).
 * Mengoverride method tampilInfo() untuk menampilkan informasi
 * khusus barang elektronik (Polimorfisme).
 */
public class BarangElektronik extends Barang {

    public BarangElektronik() {
        super();
    }

    public BarangElektronik(String namaBarang, String kategori, int stok, double harga) {
        super(namaBarang, kategori, stok, harga);
    }

    public BarangElektronik(int idBarang, String namaBarang, String kategori, int stok, double harga) {
        super(idBarang, namaBarang, kategori, stok, harga);
    }

    @Override
    public void tampilInfo() {
        System.out.printf("[ELEKTRONIK] [%d] %-25s | Stok: %-5d | Harga: Rp%,.2f | Total Nilai: Rp%,.2f%n",
                getIdBarang(), getNamaBarang(), getStok(), getHarga(), getTotalNilai());
    }
}
