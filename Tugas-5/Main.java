import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- SIMULASI DATA DOSEN ---
        System.out.println("=== SIMULASI DATA DOSEN ===");
        System.out.print("Masukkan Nama Dosen: ");
        String dosenName = scanner.nextLine();
        System.out.print("Masukkan Alamat Dosen: ");
        String dosenAddress = scanner.nextLine();

        Dosen dosen = new Dosen(dosenName, dosenAddress);
        System.out.println("\n[Berhasil Dibuat] " + dosen.toString());

        System.out.print("\nBerapa mata kuliah yang ingin ditambahkan untuk dosen ini? ");
        int tCourses = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < tCourses; i++) {
            System.out.print("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            String course = scanner.nextLine();
            if (dosen.addCourse(course)) {
                System.out.println("-> Mata kuliah '" + course + "' berhasil ditambahkan.");
            } else {
                System.out.println("-> Gagal: Mata kuliah '" + course + "' sudah ada!");
            }
        }

        System.out.print("\nMasukkan mata kuliah yang ingin DIHAPUS (kosongkan lalu enter jika tidak ada): ");
        String courseToRemove = scanner.nextLine();
        if (!courseToRemove.trim().isEmpty()) {
            if (dosen.removeCourse(courseToRemove)) {
                System.out.println("-> Mata kuliah '" + courseToRemove + "' berhasil dihapus.");
            } else {
                System.out.println("-> Gagal: Mata kuliah '" + courseToRemove + "' tidak ditemukan!");
            }
        }

        // --- SIMULASI DATA STUDENT ---
        System.out.println("\n\n=== SIMULASI DATA STUDENT ===");
        System.out.print("Masukkan Nama Mahasiswa: ");
        String studentName = scanner.nextLine();
        System.out.print("Masukkan Alamat Mahasiswa: ");
        String studentAddress = scanner.nextLine();

        Student student = new Student(studentName, studentAddress);
        System.out.println("\n[Berhasil Dibuat] " + student.toString());

        System.out.print("\nBerapa mata kuliah yang nilainya ingin diinput? ");
        int sCourses = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < sCourses; i++) {
            System.out.print("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            String course = scanner.nextLine();
            System.out.print("Masukkan nilai angka untuk '" + course + "': ");
            int grade = scanner.nextInt();
            scanner.nextLine(); 
            student.addCourseGrade(course, grade);
        }

        System.out.println("\n=== HASIL REKAPITULASI NILAI MAHASISWA ===");
        System.out.println(student.toString());
        student.printGrades();
        System.out.println("Rata-rata Nilai: " + student.getAverageGrade());
        
        scanner.close();
    }
}