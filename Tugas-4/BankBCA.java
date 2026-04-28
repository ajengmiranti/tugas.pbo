public class BankBCA extends Bank {
    
    public BankBCA() {
        this.namaBank = "BCA";
    }

    // Method Overriding: Suku Bunga BCA [cite: 627]
    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga dari BCA adalah: 4.5%");
    }

    // Method Overriding: Transfer dengan nilai bank dipaksa menjadi BCA 
    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = this.namaBank; 
        System.out.println("Transfer Rp " + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
        System.out.println("Biaya Transfer: Rp " + hitungBiayaTransfer(bankTujuan));
    }
}