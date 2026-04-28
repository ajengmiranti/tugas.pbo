public class BankBNI extends Bank {
    
    public BankBNI() {
        this.namaBank = "BNI";
    }

    // Method Overriding: Suku Bunga BNI [cite: 626]
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BNI adalah: 4%");
    }

    // Method Overriding: Transfer dengan nilai bank dipaksa menjadi BNI 
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = this.namaBank; 
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
        System.out.println("Biaya Transfer: Rp " + hitungBiayaTransfer(bankTujuan));
    }
}