package model;

/**
 * Class BarangMakanan (Child Class)
 * Merupakan turunan dari class Barang (Inheritance).
 * Mengoverride method tampilInfo() untuk menampilkan informasi
 * khusus barang makanan (Polimorfisme).
 */
public class BarangMakanan extends Barang {

    public BarangMakanan() {
        super();
    }

    public BarangMakanan(String namaBarang, String kategori, int stok, double harga) {
        super(namaBarang, kategori, stok, harga);
    }

    public BarangMakanan(int idBarang, String namaBarang, String kategori, int stok, double harga) {
        super(idBarang, namaBarang, kategori, stok, harga);
    }

    @Override
    public void tampilInfo() {
        System.out.printf("[MAKANAN]    [%d] %-25s | Stok: %-5d | Harga: Rp%,.2f | Total Nilai: Rp%,.2f%n",
                getIdBarang(), getNamaBarang(), getStok(), getHarga(), getTotalNilai());
    }
}
