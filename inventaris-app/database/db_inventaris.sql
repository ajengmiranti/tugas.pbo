-- =====================================================
-- Database: db_inventaris
-- Aplikasi Manajemen Inventaris Barang CLI
-- =====================================================

DROP DATABASE IF EXISTS db_inventaris;
CREATE DATABASE db_inventaris;
USE db_inventaris;

-- =====================================================
-- Tabel barang
-- =====================================================
CREATE TABLE barang (
    id_barang     INT AUTO_INCREMENT PRIMARY KEY,
    nama_barang   VARCHAR(100) NOT NULL,
    kategori      VARCHAR(50)  NOT NULL,
    stok          INT NOT NULL DEFAULT 0,
    harga         DECIMAL(15,2) NOT NULL DEFAULT 0
);

-- =====================================================
-- Trigger BEFORE INSERT
-- Jika stok kurang dari 0 maka otomatis menjadi 0
-- =====================================================
DELIMITER $$
CREATE TRIGGER trg_validasi_stok
BEFORE INSERT ON barang
FOR EACH ROW
BEGIN
    IF NEW.stok < 0 THEN
        SET NEW.stok = 0;
    END IF;
END$$
DELIMITER ;

-- =====================================================
-- Stored Procedure: tambah_barang
-- Menambahkan data barang baru
-- =====================================================
DELIMITER $$
CREATE PROCEDURE tambah_barang (
    IN p_nama_barang VARCHAR(100),
    IN p_kategori    VARCHAR(50),
    IN p_stok        INT,
    IN p_harga       DECIMAL(15,2)
)
BEGIN
    INSERT INTO barang (nama_barang, kategori, stok, harga)
    VALUES (p_nama_barang, p_kategori, p_stok, p_harga);
END$$
DELIMITER ;

-- =====================================================
-- Stored Function: total_nilai_barang
-- Mengembalikan hasil perkalian stok x harga
-- =====================================================
DELIMITER $$
CREATE FUNCTION total_nilai_barang (
    p_stok  INT,
    p_harga DECIMAL(15,2)
)
RETURNS DECIMAL(18,2)
DETERMINISTIC
BEGIN
    RETURN p_stok * p_harga;
END$$
DELIMITER ;

-- =====================================================
-- View: view_barang
-- Menampilkan seluruh data barang beserta total nilai barang
-- =====================================================
CREATE VIEW view_barang AS
SELECT
    id_barang,
    nama_barang,
    kategori,
    stok,
    harga,
    total_nilai_barang(stok, harga) AS total_nilai
FROM barang;

-- =====================================================
-- Data contoh (opsional, boleh dihapus)
-- =====================================================
CALL tambah_barang('Laptop Asus', 'Elektronik', 10, 7500000);
CALL tambah_barang('Mie Instan Sedaap', 'Makanan', 50, 3500);
CALL tambah_barang('Mouse Wireless Logitech', 'Elektronik', 25, 150000);
