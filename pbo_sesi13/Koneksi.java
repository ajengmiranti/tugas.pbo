import java.sql.Connection;
import java.sql.DriverManager;


public class Koneksi {

    static Connection con;


    public static Connection getConnection(){

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toko_retail",
                "root",
                ""
            );

            System.out.println("Database Terhubung");

            return con;


        } catch(Exception e){

            System.out.println("Koneksi gagal : " + e);

            return null;

        }

    }

}