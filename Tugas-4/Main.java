public class Main {
    public static void main(String[] args) {
        // Menguji class utama dengan implementasi overloading [cite: 622, 623]
        System.out.println("=== PENGUJIAN CLASS BANK (OVERLOADING) ===");
        Bank bankUmum = new Bank();
        bankUmum.sukuBunga();
        bankUmum.transferUang(50000, "12345678");
        System.out.println("--------------------------------------------------");
        bankUmum.transferUang(100000, "87654321", "Mandiri");
        System.out.println("--------------------------------------------------");
        bankUmum.transferUang(150000, "11223344", "BRI", "Pembayaran SPP");
        
        // Menguji class turunan dengan implementasi overriding
        System.out.println("\n=== PENGUJIAN CLASS BankBNI (OVERRIDING) ===");
        BankBNI bni = new BankBNI();
        bni.sukuBunga();
        // Parameter bank tujuan "Mandiri" akan diabaikan dan ditimpa menjadi "BNI" sesuai soal
        bni.transferUang(200000, "99887766", "Mandiri"); 
        
        System.out.println("\n=== PENGUJIAN CLASS BankBCA (OVERRIDING) ===");
        BankBCA bca = new BankBCA();
        bca.sukuBunga();
        // Parameter bank tujuan "BRI" akan diabaikan dan ditimpa menjadi "BCA" sesuai soal
        bca.transferUang(300000, "55443322", "BRI");
    }
}