import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class CSVReaderJumlah {

    public static void main(String[] args) {

        String csvFile = "students.csv";
        String line;
        int jumlahBaris = 0;


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {


            while ((line = br.readLine()) != null) {

                jumlahBaris++;

            }


            System.out.println("Jumlah data dalam file: " + (jumlahBaris - 1));


        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}