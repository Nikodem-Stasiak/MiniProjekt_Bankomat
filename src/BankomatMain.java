public class BankomatMain {
    public static void main(String[] args) {
        KontoBankowe k1 = new KontoBankowe(1000);
        k1.wyplac(500);

        try{
            k1.wyplac(2000);
        } catch (Exception e) {
            System.out.println("Transakcja odrzucona!");
        }

        System.out.println("Aktualny stan konta wynosi: " + k1.getSaldo());
    }
}
