package dao;

import config.DatabaseConnection;
import model.Barang;
import model.BarangElektronik;
import model.BarangMakanan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BarangDAO (Data Access Object)
 * Menjembatani objek Barang dengan database.
 * Berisi operasi Insert, Select, Update, Delete,
 * serta pemanggilan Stored Procedure, Stored Function, dan View.
 */
public class BarangDAO {

    /**
     * Menambahkan data barang baru menggunakan Stored Procedure tambah_barang().
     */
    public void insertBarang(String namaBarang, String kategori, int stok, double harga) throws SQLException {
        String sql = "{CALL tambah_barang(?, ?, ?, ?)}";
        Connection conn = DatabaseConnection.getConnection();
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, namaBarang);
            stmt.setString(2, kategori);
            stmt.setInt(3, stok);
            stmt.setDouble(4, harga);
            stmt.execute();
        }
    }

    /**
     * Mengambil seluruh data barang menggunakan View view_barang
     * (View sudah menghitung total_nilai menggunakan Stored Function).
     * Data dikembalikan sebagai objek turunan Barang sesuai kategori (Polimorfisme).
     */
    public List<Barang> selectAllBarang() throws SQLException {
        List<Barang> daftarBarang = new ArrayList<>();
        String sql = "SELECT id_barang, nama_barang, kategori, stok, harga FROM view_barang ORDER BY id_barang";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id_barang");
                String nama = rs.getString("nama_barang");
                String kategori = rs.getString("kategori");
                int stok = rs.getInt("stok");
                double harga = rs.getDouble("harga");

                Barang barang;
                if (kategori.equalsIgnoreCase("Elektronik")) {
                    barang = new BarangElektronik(id, nama, kategori, stok, harga);
                } else if (kategori.equalsIgnoreCase("Makanan")) {
                    barang = new BarangMakanan(id, nama, kategori, stok, harga);
                } else {
                    barang = new Barang(id, nama, kategori, stok, harga);
                }
                daftarBarang.add(barang);
            }
        }
        return daftarBarang;
    }

    /**
     * Mencari satu barang berdasarkan id_barang.
     */
    public Barang selectBarangById(int idBarang) throws SQLException {
        String sql = "SELECT id_barang, nama_barang, kategori, stok, harga FROM barang WHERE id_barang = ?";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBarang);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Barang(
                            rs.getInt("id_barang"),
                            rs.getString("nama_barang"),
                            rs.getString("kategori"),
                            rs.getInt("stok"),
                            rs.getDouble("harga")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Memperbarui data barang berdasarkan id_barang.
     */
    public boolean updateBarang(int idBarang, String namaBarang, String kategori, int stok, double harga) throws SQLException {
        String sql = "UPDATE barang SET nama_barang = ?, kategori = ?, stok = ?, harga = ? WHERE id_barang = ?";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, namaBarang);
            stmt.setString(2, kategori);
            stmt.setInt(3, stok);
            stmt.setDouble(4, harga);
            stmt.setInt(5, idBarang);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Menghapus data barang berdasarkan id_barang.
     */
    public boolean deleteBarang(int idBarang) throws SQLException {
        String sql = "DELETE FROM barang WHERE id_barang = ?";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBarang);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Menghitung total nilai barang menggunakan Stored Function total_nilai_barang().
     */
    public double hitungTotalNilai(int stok, double harga) throws SQLException {
        String sql = "SELECT total_nilai_barang(?, ?) AS total";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stok);
            stmt.setDouble(2, harga);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0;
    }
}
