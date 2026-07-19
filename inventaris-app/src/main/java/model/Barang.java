package model;

/**
 * Class Barang (Parent Class)
 * Merepresentasikan data barang secara umum.
 * Menerapkan konsep Enkapsulasi: seluruh atribut bersifat private
 * dan hanya bisa diakses melalui getter dan setter.
 */
public class Barang {

    private int idBarang;
    private String namaBarang;
    private String kategori;
    private int stok;
    private double harga;

    public Barang() {
    }

    public Barang(String namaBarang, String kategori, int stok, double harga) {
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stok = stok;
        this.harga = harga;
    }

    public Barang(int idBarang, String namaBarang, String kategori, int stok, double harga) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stok = stok;
        this.harga = harga;
    }

    // ===================== Getter & Setter =====================

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getTotalNilai() {
        return stok * harga;
    }

    /**
     * Method tampilInfo() akan dioverride oleh masing-masing child class
     * sehingga menghasilkan output yang berbeda (Polimorfisme).
     */
    public void tampilInfo() {
        System.out.printf("[%d] %-25s | Kategori: %-12s | Stok: %-5d | Harga: Rp%,.2f | Total Nilai: Rp%,.2f%n",
                idBarang, namaBarang, kategori, stok, harga, getTotalNilai());
    }
}
