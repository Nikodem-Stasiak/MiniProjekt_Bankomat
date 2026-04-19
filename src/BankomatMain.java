import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class BankomatMain {
    public static void main(String[] args) {
        DatabaseConnector.setupDatabase();
        ArrayList<KontoBankowe> listaKont = new ArrayList<>();
        Scanner skaner = new Scanner(System.in);
        boolean czyDziala = true;

        File plikBazy = new File("baza_kont.txt");

        if (plikBazy.exists()) {
            try (Scanner czytnikPliku = new Scanner(plikBazy)) {
                while (czytnikPliku.hasNextLine()) {
                    String linia = czytnikPliku.nextLine();
                    String[] czesci = linia.split(";");
                    String nr = czesci[0];
                    int p = Integer.parseInt(czesci[1]);
                    int s = Integer.parseInt(czesci[2]);
                    listaKont.add(new KontoBankowe(nr, p, s));
                }
            } catch (Exception e) {
                System.out.println("Błąd podczas wczytywania bazy: " + e.getMessage());
            }
        } else {
            listaKont.add(new KontoBankowe("12345", 1111, 5000));
            listaKont.add(new KontoBankowe("98765", 2222));
        }

        while (czyDziala) {
            System.out.println("--- Menu Główne ---");
            System.out.println("1. Zaloguj się na istniejące konto.");
            System.out.println("2. Załóż nowe konto.");
            System.out.println("3. Koniec");
            int wyborStart = skaner.nextInt();
            switch (wyborStart) {
                case 1: {
                    KontoBankowe zalogowaneKonto = null;

                    System.out.println("Podaj numer konta: ");
                    String wpisanyNumer = skaner.next();

                    System.out.println("Podaj PIN: ");
                    int wpisanyPin = skaner.nextInt();

                    for (KontoBankowe kontoZListy : listaKont) {
                        if (wpisanyNumer.equals(kontoZListy.getNumerKonta()) && wpisanyPin == kontoZListy.getPin()) {
                            zalogowaneKonto = kontoZListy;
                            break;
                        }
                    }

                    if (zalogowaneKonto != null) {
                        System.out.println("Logowanie udane!");

                        boolean sesjaAktywna = true;
                        while (sesjaAktywna) {
                            System.out.println("--- Menu Bankomatu ---");
                            System.out.println("1. Wypłata");
                            System.out.println("2. Wpłata");
                            System.out.println("3. Sprawdź stan konta");
                            System.out.println("4. Koniec");
                            System.out.println("Wybierz opcję: ");
                            int wybor = skaner.nextInt();

                            switch (wybor) {
                                case 1: {
                                    System.out.println("Podaj kwotę do wypłaty: ");
                                    int kwota = skaner.nextInt();
                                    try {
                                        zalogowaneKonto.wyplac(kwota);
                                    } catch (Exception e) {
                                        System.out.println("Transakcja odrzucona!");
                                    }
                                    break;
                                }
                                case 2: {
                                    System.out.println("Podaj kwotę do wpłaty: ");
                                    int kwota = skaner.nextInt();
                                    zalogowaneKonto.wplac(kwota);
                                    break;
                                }
                                case 3: {
                                    System.out.println("Aktualny stan konta wynosi: " + zalogowaneKonto.getSaldo());
                                    break;
                                }
                                case 4: {
                                    System.out.println("Wylogowano.");
                                    sesjaAktywna = false;
                                    break;
                                }
                                default: {
                                    System.out.println("Nieznana opcja!");
                                }
                            }
                        }
                    } else {
                        System.out.println("Błędny numer konta lub PIN! Dostęp odmówiony!");
                    }
                    break;
                }
                case 2: {
                    String nr = "";
                    boolean numerJestOk = false;

                    while(!numerJestOk){
                        System.out.println("Stwórz numer konta (5 znaków): ");
                        nr = skaner.next();
                        if(nr.length() != 5){
                            System.out.println("BŁĄD! Numer musi zawierać 5 znaków.");
                            continue;
                        }
                        boolean znalezionoDuplikat = false;
                        for(KontoBankowe k : listaKont){
                            if(k.getNumerKonta().equals(nr)){
                                znalezionoDuplikat=true;
                                break;
                            }
                        }
                        if(znalezionoDuplikat){
                            System.out.println("BŁĄD! Konto o tym numerze już istnieje w naszym banku!");
                        }else{
                            numerJestOk = true;
                        }
                    }

                    System.out.println("Stwórz PIN do konta (4 znaki): ");
                    int p = skaner.nextInt();
                    while(p < 1000 || p > 9999){
                        System.out.println("BŁĄD! PIN musi zawierać 4 znaki. Podaj ponownie: ");
                        p = skaner.nextInt();
                    }

                    listaKont.add(new KontoBankowe(nr,p));
                    System.out.println("Konto założone pomyślnie!");
                    break;
                }
                case 3: {
                    System.out.println("Zamykanie systemu...");
                    czyDziala = false;
                    break;
                }
                default: {
                    System.out.println("Nieznana opcja!");
                }
            }
        }

        try (PrintWriter pisarz = new PrintWriter("baza_kont.txt")) {
            for (KontoBankowe k : listaKont) {
                pisarz.println(k.getNumerKonta() + ";" + k.getPin() + ";" + k.getSaldo());
            }
            System.out.println("Dane zostały pomyślnie zapisane do bazy.");
        } catch (Exception e) {
            System.out.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }
}
