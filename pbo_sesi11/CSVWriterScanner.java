import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CSVWriterScanner {

    public static void main(String[] args) {

        String csvFile = "input_students.csv";

        Scanner input = new Scanner(System.in);


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {


            System.out.print("Masukkan jumlah data mahasiswa: ");
            int jumlah = input.nextInt();

            input.nextLine();


            for(int i = 0; i < jumlah; i++) {

                System.out.println("\nData mahasiswa ke-" + (i+1));


                System.out.print("NIM : ");
                String nim = input.nextLine();


                System.out.print("Nama : ");
                String nama = input.nextLine();


                System.out.print("Umur : ");
                String umur = input.nextLine();


                System.out.print("Prodi : ");
                String prodi = input.nextLine();


                String data = nim + "," + nama + "," + umur + "," + prodi;


                bw.write(data);
                bw.newLine();

            }


            System.out.println("\nData berhasil disimpan ke file CSV");


        } catch(IOException e) {

            e.printStackTrace();

        }


        input.close();
    }
}