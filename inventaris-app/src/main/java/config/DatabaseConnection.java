package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class DatabaseConnection
 * Bertanggung jawab untuk membuat dan mengelola koneksi ke database MySQL.
 * Menggunakan pola Singleton agar koneksi hanya dibuat sekali.
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_inventaris?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // sesuaikan dengan password MySQL masing-masing

    private static Connection connection = null;

    // Private constructor supaya class ini tidak bisa diinstansiasi dari luar
    private DatabaseConnection() {
    }

    /**
     * Mendapatkan koneksi ke database.
     * Jika koneksi belum ada atau sudah tertutup, maka akan dibuat koneksi baru.
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Gagal terhubung ke database db_inventaris. "
                    + "Pastikan MySQL server aktif dan konfigurasi koneksi sudah benar. "
                    + "Detail: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Menutup koneksi database jika masih terbuka.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi database: " + e.getMessage());
        }
    }
}
