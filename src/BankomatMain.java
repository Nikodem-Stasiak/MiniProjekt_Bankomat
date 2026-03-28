import java.util.Scanner;

public class BankomatMain {
    public static void main(String[] args) {
        KontoBankowe k1 = new KontoBankowe();
        Scanner skaner=new Scanner(System.in);
        boolean czyDziala = true;

        while(czyDziala){
            System.out.println("--- Menu Bankomatu ---");
            System.out.println("1. Wypłata");
            System.out.println("2. Wpłata");
            System.out.println("3. Sprawdź stan konta");
            System.out.println("4. Koniec");
            System.out.println("Wybierz opcję: ");
            int wybor = skaner.nextInt();

            switch (wybor){
                case 1:
                {
                    System.out.println("Podaj kwotę do wypłaty: ");
                    int kwota = skaner.nextInt();
                    try{
                        k1.wyplac(kwota);
                    }catch (Exception e){
                        System.out.println("Transakcja odrzucona!");
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("Podaj kwotę do wpłaty: ");
                    int kwota = skaner.nextInt();
                    k1.wplac(kwota);
                    break;
                }
                case 3:
                {
                    System.out.println("Aktualny stan konta wynosi: " + k1.getSaldo());
                    break;
                }
                case 4:
                {
                    System.out.println("Dziękujemy za skorzystanie z bankomatu!");
                    czyDziala = false;
                    break;
                }
                default:
                {
                    System.out.println("Nieznana opcja!");
                }
            }
        }
    }
}
