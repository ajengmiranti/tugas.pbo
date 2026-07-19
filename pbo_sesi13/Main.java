import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int pilih;
        do {
            System.out.println("\n==========================");
            System.out.println("       MENU TOKO RETAIL");
            System.out.println("==========================");
            System.out.println("1. Tampil Semua Data");
            System.out.println("2. Tambah Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Hapus Data");
            System.out.println("6. Keluar");
            System.out.print("Pilihan : ");
            pilih = sc.nextInt();

            switch(pilih){
                case 1:
                    tampilData();
                    break;

                case 2:
                    tambahData();
                    break;

                case 3:
                    cariData();
                    break;

                case 4:
                    ubahData();
                    break;

                case 5:
                    hapusData();
                    break;

                case 6:
                    System.out.println("Program selesai");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia");
            }

        }while(pilih != 6);
    }

    // ================= TAMPIL DATA =================
    static void tampilData(){
        try{
            Connection con = Koneksi.getConnection();

            String sql = "SELECT * FROM barang";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n=================================");
            System.out.println("       DATA BARANG RETAIL");
            System.out.println("=================================");

            while(rs.next()){
                System.out.println(
                    rs.getString("kode_barang")
                    +" | "+
                    rs.getString("nama_barang")
                    +" | "+
                    rs.getInt("harga_barang")
                    +" | "+
                    rs.getInt("stok_barang")
                );
            }

        }catch(Exception e){

            System.out.println(e);
        }
    }

    // ================= TAMBAH DATA =================

    static void tambahData(){
        try{
            Connection con = Koneksi.getConnection();

            System.out.print("Kode Barang : ");
            String kode = sc.next();

            System.out.print("Nama Barang : ");
            String nama = sc.next();

            System.out.print("Harga Barang : ");
            int harga = sc.nextInt();

            System.out.print("Stok Barang : ");
            int stok = sc.nextInt();

            String sql =
            "INSERT INTO barang VALUES (?,?,?,?)";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setString(1,kode);
            ps.setString(2,nama);
            ps.setInt(3,harga);
            ps.setInt(4,stok);

            ps.executeUpdate();

            System.out.println("Data berhasil ditambahkan");

        }catch(Exception e){

            System.out.println(e);
        }
    }

    // ================= CARI DATA =================

    static void cariData(){
        try{
            Connection con = Koneksi.getConnection();

            System.out.print("Masukkan kode barang : ");

            String kode = sc.next();

            String sql =
            "SELECT * FROM barang WHERE kode_barang=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setString(1,kode);

            ResultSet rs =
            ps.executeQuery();

            if(rs.next()){
                System.out.println(
                "Kode : "+rs.getString("kode_barang")
                );

                System.out.println(
                "Nama : "+rs.getString("nama_barang")
                );

                System.out.println(
                "Harga : "+rs.getInt("harga_barang")
                );

                System.out.println(
                "Stok : "+rs.getInt("stok_barang")
                );

            }else{

                System.out.println("Data tidak ditemukan");
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }

    // ================= UBAH DATA =================

    static void ubahData(){


        try{
            Connection con = Koneksi.getConnection();

            System.out.print("Kode barang : ");
            String kode = sc.next();

            System.out.print("Harga baru : ");
            int harga = sc.nextInt();

            String sql =
            "UPDATE barang SET harga_barang=? WHERE kode_barang=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setInt(1,harga);
            ps.setString(2,kode);
            ps.executeUpdate();

            System.out.println("Data berhasil diubah");

        }catch(Exception e){

            System.out.println(e);
        }
    }

    // ================= HAPUS DATA =================

    static void hapusData(){
        try{
            Connection con = Koneksi.getConnection();

            System.out.print("Kode barang : ");

            String kode = sc.next();
            String sql =
            "DELETE FROM barang WHERE kode_barang=?";

            PreparedStatement ps =
            con.prepareStatement(sql);

            ps.setString(1,kode);
            ps.executeUpdate();

            System.out.println("Data berhasil dihapus");

        }catch(Exception e){

            System.out.println(e);
        }
    }
}