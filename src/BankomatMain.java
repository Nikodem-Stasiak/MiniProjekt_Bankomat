import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class BankomatMain {
    public static void main(String[] args) {
        ArrayList<KontoBankowe> listaKont = new ArrayList<>();
        Scanner skaner=new Scanner(System.in);
        boolean czyDziala = true;

        File plikBazy = new File("baza_kont.txt");

        if (plikBazy.exists()){
            try(Scanner czytnikPliku = new Scanner(plikBazy)){
                while (czytnikPliku.hasNextLine()){
                    String linia = czytnikPliku.nextLine();
                    String[] czesci = linia.split(";");
                    String nr = czesci[0];
                    int p = Integer.parseInt(czesci[1]);
                    int s = Integer.parseInt(czesci[2]);
                    listaKont.add(new KontoBankowe(nr,p,s));
                }
            }catch (Exception e){
                System.out.println("Błąd podczas wczytywania bazy: " + e.getMessage());
            }
        }else{
            listaKont.add(new KontoBankowe("12345", 1111, 5000));
            listaKont.add(new KontoBankowe("98765", 2222));
        }

        KontoBankowe zalogowaneKonto = null;

        System.out.println("Podaj numer konta: ");
        String wpisanyNumer = skaner.next();

        System.out.println("Podaj PIN: ");
        int wpisanyPin = skaner.nextInt();

        for(KontoBankowe kontoZListy : listaKont){
            if(wpisanyNumer.equals(kontoZListy.getNumerKonta())&& wpisanyPin == kontoZListy.getPin()){
                zalogowaneKonto = kontoZListy;
                break;
            }
        }

        if(zalogowaneKonto != null){
            System.out.println("Logowanie udane!");

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
                            zalogowaneKonto.wyplac(kwota);
                        }catch (Exception e){
                            System.out.println("Transakcja odrzucona!");
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Podaj kwotę do wpłaty: ");
                        int kwota = skaner.nextInt();
                        zalogowaneKonto.wplac(kwota);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Aktualny stan konta wynosi: " + zalogowaneKonto.getSaldo());
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
        } else {
            System.out.println("Błędny numer konta lub PIN! Dostęp odmówiony!");
        }
        try (PrintWriter pisarz = new PrintWriter("baza_kont.txt")){
            for (KontoBankowe k: listaKont){
                pisarz.println(k.getNumerKonta() + ";" + k.getPin() + ";" + k.getSaldo());
            }
            System.out.println("Dane zostały pomyślnie zapisane do bazy.");
        } catch (Exception e){
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }
}
