public class Bank {
    protected String namaBank = "Standar";

    // Method Overloading 1: Transfer ke rekening lain (sesama bank) [cite: 617]
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " (Sesama Bank)");
        System.out.println("Biaya Transfer: Rp " + hitungBiayaTransfer(this.namaBank));
    }

    // Method Overloading 2: Transfer ke rekening bank berbeda [cite: 618]
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
        System.out.println("Biaya Transfer: Rp " + hitungBiayaTransfer(bankTujuan));
    }

    // Method Overloading 3: Transfer dengan tambahan berita [cite: 619, 620]
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
        System.out.println("Berita: " + berita);
        System.out.println("Biaya Transfer: Rp " + hitungBiayaTransfer(bankTujuan));
    }

    // Method mencetak suku bunga [cite: 621]
    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // Bonus Challenge: Hitung biaya transfer berdasarkan bank tujuan 
    protected int hitungBiayaTransfer(String bankTujuan) {
        if (this.namaBank.equalsIgnoreCase(bankTujuan)) {
            return 0; // Gratis untuk sesama bank
        } else {
            return 6500; // Dikenakan biaya untuk antar bank
        }
    }
}