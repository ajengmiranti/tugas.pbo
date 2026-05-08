package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Laptop laptopPilihan = null;

        System.out.println("=== SILAKAN PILIH LAPTOP ===");
        System.out.println("1. Lenovo");
        System.out.println("2. Toshiba");
        System.out.println("3. MacBook");
        System.out.print("Pilih (1/2/3): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1: laptopPilihan = new Lenovo(); break;
            case 2: laptopPilihan = new Toshiba(); break;
            case 3: laptopPilihan = new MacBook(); break;
            default: 
                laptopPilihan = new Lenovo(); 
                System.out.println("Default: Lenovo terpilih.");
        }

        // Memasukkan laptop pilihan ke dalam LaptopUser sesuai Lecture Notes
        LaptopUser user = new LaptopUser(laptopPilihan);

        String input;
        System.out.println("\n=== KONTROL LAPTOP (Ketik 'EXIT' untuk keluar) ===");
        do {
            System.out.print("Masukkan perintah (ON/OFF/UP/DOWN): ");
            input = scanner.nextLine().toUpperCase();

            switch (input) {
                case "ON":
                    user.turnOnLaptop();
                    break;
                case "OFF":
                    user.turnOffLaptop();
                    break;
                case "UP":
                    user.makeLaptopLouder();
                    break;
                case "DOWN":
                    user.makeLaptopSilence();
                    break;
                case "EXIT":
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Perintah tidak valid!");
            }
        } while (!input.equals("EXIT"));

        scanner.close();
    }
}