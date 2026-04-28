import java.util.ArrayList;
import java.util.Scanner;

// Super Class
class Mahasiswa {
    protected String nim;
    protected String nama;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }
}

// Sub Class yang mewarisi Mahasiswa
class MahasiswaJava extends Mahasiswa {
    private int nilai;

    public MahasiswaJava(String nim, String nama, int nilai) {
        super(nim, nama); // Memanggil constructor dari Super Class
        this.nilai = nilai;
    }

    public int getNilai() {
        return nilai;
    }

    public String getGrade() {
        if (nilai >= 80 && nilai <= 100) return "A";
        if (nilai >= 70 && nilai <= 79) return "B";
        if (nilai >= 60 && nilai <= 69) return "C";
        if (nilai >= 50 && nilai <= 59) return "D";
        return "E"; // Sisa nilai di bawah 50 otomatis E
    }

    public boolean isLulus() {
        String grade = getGrade();
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<MahasiswaJava> daftarMahasiswa = new ArrayList<>();

        System.out.print("Masukkan jumlah mahasiswa yang akan diinput: ");
        int jumlah = input.nextInt();
        input.nextLine(); 

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nData Mahasiswa ke-" + (i + 1));
            System.out.print("NIM: ");
            String nim = input.nextLine();
            System.out.print("Nama: ");
            String nama = input.nextLine();

            int nilai = -1;
            while (true) {
                System.out.print("Nilai: ");
                nilai = input.nextInt();
                input.nextLine(); 
                
                // Validasi input rentang nilai
                if (nilai >= 0 && nilai <= 100) {
                    break;
                } else {
                    System.out.println("Input nilai anda salah");
                }
            }
            
            daftarMahasiswa.add(new MahasiswaJava(nim, nama, nilai));
            System.out.println("===");
        }

        // --- PROSES OUTPUT SESUAI FORMAT ---
        System.out.println("\n--- HASIL DATA NILAI MAHASISWA ---");
        int totalNilai = 0;
        StringBuilder prosesRataRata = new StringBuilder();
        
        ArrayList<String> lulus = new ArrayList<>();
        ArrayList<String> tidakLulus = new ArrayList<>();
        ArrayList<String> gradeA = new ArrayList<>();
        ArrayList<String> gradeB = new ArrayList<>();
        ArrayList<String> gradeC = new ArrayList<>();
        ArrayList<String> gradeD = new ArrayList<>();
        ArrayList<String> gradeE = new ArrayList<>();

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            MahasiswaJava mhs = daftarMahasiswa.get(i);
            System.out.println("NIM: " + mhs.getNim());
            System.out.println("Nama: " + mhs.getNama());
            System.out.println("Nilai: " + mhs.getNilai());
            System.out.println("Grade: " + mhs.getGrade());
            if (i < daftarMahasiswa.size() - 1) System.out.println("===");

            // Perhitungan Rata-rata dan Pengelompokan
            totalNilai += mhs.getNilai();
            prosesRataRata.append(mhs.getNilai());
            if (i < daftarMahasiswa.size() - 1) prosesRataRata.append("+");

            if (mhs.isLulus()) lulus.add(mhs.getNama());
            else tidakLulus.add(mhs.getNama());

            switch (mhs.getGrade()) {
                case "A": gradeA.add(mhs.getNama()); break;
                case "B": gradeB.add(mhs.getNama()); break;
                case "C": gradeC.add(mhs.getNama()); break;
                case "D": gradeD.add(mhs.getNama()); break;
                case "E": gradeE.add(mhs.getNama()); break;
            }
        }

        System.out.println("\nJumlah Mahasiswa: " + daftarMahasiswa.size());
        System.out.println("Jumlah Mahasiswa yg Lulus: " + lulus.size() + (lulus.isEmpty() ? "" : " yaitu " + String.join(", ", lulus)));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus: " + tidakLulus.size() + (tidakLulus.isEmpty() ? "" : " yaitu " + String.join(", ", tidakLulus)));
        
        if (!gradeA.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA.size() + " yaitu " + String.join(", ", gradeA));
        if (!gradeB.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB.size() + " yaitu " + String.join(", ", gradeB));
        if (!gradeC.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeC.size() + " yaitu " + String.join(", ", gradeC));
        if (!gradeD.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD.size() + " yaitu " + String.join(", ", gradeD));
        if (!gradeE.isEmpty()) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeE.size() + " yaitu " + String.join(", ", gradeE));

        double rataRata = (double) totalNilai / daftarMahasiswa.size();
        System.out.println("Rata-rata nilai mahasiswa adalah: " + prosesRataRata.toString() + "/" + daftarMahasiswa.size() + " = " + rataRata);
    }
}