package service;

import dao.BarangDAO;
import model.Barang;

import java.sql.SQLException;
import java.util.List;

/**
 * Class BarangService
 * Berperan sebagai lapisan business logic antara Main (tampilan CLI)
 * dan BarangDAO (akses database). Melakukan validasi input sebelum
 * data diteruskan ke DAO, serta menangani exception yang mungkin terjadi.
 */
public class BarangService {

    private final BarangDAO barangDAO;

    public BarangService() {
        this.barangDAO = new BarangDAO();
    }

    public void tambahBarang(String namaBarang, String kategori, int stok, double harga) throws Exception {
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong.");
        }
        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong.");
        }
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh bernilai negatif.");
        }

        try {
            barangDAO.insertBarang(namaBarang, kategori, stok, harga);
        } catch (SQLException e) {
            throw new Exception("Gagal menambahkan barang ke database: " + e.getMessage());
        }
    }

    public List<Barang> lihatSemuaBarang() throws Exception {
        try {
            return barangDAO.selectAllBarang();
        } catch (SQLException e) {
            throw new Exception("Gagal mengambil data barang: " + e.getMessage());
        }
    }

    public boolean updateBarang(int idBarang, String namaBarang, String kategori, int stok, double harga) throws Exception {
        if (namaBarang == null || namaBarang.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong.");
        }
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh bernilai negatif.");
        }

        try {
            Barang existing = barangDAO.selectBarangById(idBarang);
            if (existing == null) {
                throw new Exception("Data dengan ID " + idBarang + " tidak ditemukan.");
            }
            return barangDAO.updateBarang(idBarang, namaBarang, kategori, stok, harga);
        } catch (SQLException e) {
            throw new Exception("Gagal memperbarui data barang: " + e.getMessage());
        }
    }

    public boolean hapusBarang(int idBarang) throws Exception {
        try {
            Barang existing = barangDAO.selectBarangById(idBarang);
            if (existing == null) {
                throw new Exception("Data dengan ID " + idBarang + " tidak ditemukan.");
            }
            return barangDAO.deleteBarang(idBarang);
        } catch (SQLException e) {
            throw new Exception("Gagal menghapus data barang: " + e.getMessage());
        }
    }

    public double hitungTotalNilai(int stok, double harga) throws Exception {
        try {
            return barangDAO.hitungTotalNilai(stok, harga);
        } catch (SQLException e) {
            throw new Exception("Gagal menghitung total nilai barang: " + e.getMessage());
        }
    }
}
