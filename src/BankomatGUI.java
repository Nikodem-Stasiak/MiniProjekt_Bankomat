import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class BankomatGUI {
    private JFrame rama;
    private ArrayList<KontoBankowe> listaKont;
    private KontoBankowe zalogowaneKonto = null;

    public BankomatGUI() {
        listaKont = new ArrayList<>();
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

        rama = new JFrame("Bankomat 2026");
        rama.setSize(400, 300);
        rama.setLayout(null);
        rama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pokazEkranLogowania();

        rama.setVisible(true);
    }

    private void pokazEkranLogowania(){
        rama.getContentPane().removeAll();
        rama.repaint();

        JLabel numerKonta = new JLabel("Podaj numer konta:");
        numerKonta.setBounds(20, 50, 120, 30);
        rama.add(numerKonta);

        JTextField poleNr = new JTextField();
        poleNr.setBounds(150, 50, 150, 30);
        rama.add(poleNr);

        JLabel numerPin = new JLabel("Podaj PIN:");
        numerPin.setBounds(20, 100, 120,30);
        rama.add(numerPin);

        JPasswordField polePin = new JPasswordField();
        polePin.setBounds(150, 100, 150, 30);
        rama.add(polePin);

        JButton przyciskLoguj = new JButton("Zaloguj");
        przyciskLoguj.setBounds(150, 150, 100, 40);
        rama.add(przyciskLoguj);

        przyciskLoguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String podanyNumer = poleNr.getText();
                String wpisanyPinStr = new String(polePin.getPassword());

                try {
                    int pinLiczba = Integer.parseInt(wpisanyPinStr);
                    boolean znaleziono = false;

                    for (KontoBankowe k : listaKont) {
                        if (k.getNumerKonta().equals(podanyNumer) && k.getPin() == pinLiczba) {
                            znaleziono = true;
                            zalogowaneKonto = k;
                            break;
                        }
                    }

                    if (znaleziono) {
                        JOptionPane.showMessageDialog(rama, "Witaj, " + podanyNumer + "!");
                        pokazMenu();
                    } else {
                        JOptionPane.showMessageDialog(rama, "Błędny numer lub PIN", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rama, "PIN musi być liczbą!", "Błąd formatu", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void pokazMenu() {
        rama.getContentPane().removeAll();

        rama.revalidate();
        rama.repaint();

        JButton przyciskWyplac = new JButton("Wypłać pieniądze");
        przyciskWyplac.setBounds(100, 160, 200, 40);
        rama.add(przyciskWyplac);

        przyciskWyplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String odpowiedz = JOptionPane.showInputDialog(rama, "Jaką kwotę chcesz wypłacić?");

                if (odpowiedz != null && !odpowiedz.isEmpty()) {
                    try {
                        int kwota = Integer.parseInt(odpowiedz);

                        if (kwota <= zalogowaneKonto.getSaldo()) {
                            int noweSaldo = zalogowaneKonto.getSaldo() - kwota;
                            zalogowaneKonto.setSaldo(noweSaldo);
                            zapiszDoPliku();
                            JOptionPane.showMessageDialog(rama, "Wypłacono: " + kwota + " zł.");
                        } else {
                            JOptionPane.showMessageDialog(rama, "Brak środków na koncie!", "Błąd", JOptionPane.WARNING_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rama, "Błąd: Wpisz poprawną liczbę!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton przyciskWplac = new JButton("Wpłać pieniądze");
        przyciskWplac.setBounds(100, 100, 200, 40);
        rama.add(przyciskWplac);

        przyciskWplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String odpowiedz = JOptionPane.showInputDialog(rama, "Jaką kwotę chcesz wpłacić?");

                if (odpowiedz != null && !odpowiedz.isEmpty()) {
                    try {
                        int kwota = Integer.parseInt(odpowiedz);

                        int noweSaldo = zalogowaneKonto.getSaldo() + kwota;
                        zalogowaneKonto.setSaldo(noweSaldo);
                        zapiszDoPliku();

                        JOptionPane.showMessageDialog(rama, "Wpłacono: " + kwota + " zł. \nObecne saldo: " + noweSaldo + " zł");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(rama, "Błąd: Wpisz poprawną liczbę!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton przyciskSaldo = new JButton("Sprawdź saldo");
        przyciskSaldo.setBounds(100, 40, 200, 40);
        rama.add(przyciskSaldo);

        przyciskSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rama, "Twoje saldo to: " + zalogowaneKonto.getSaldo() + " złotych.");
            }
        });

        JButton przyciskWyloguj = new JButton("Wyloguj");
        przyciskWyloguj.setBounds(150, 220, 100, 40);
        rama.add(przyciskWyloguj);

        przyciskWyloguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zalogowaneKonto = null;

                rama.getContentPane().removeAll();

                pokazEkranLogowania();

                rama.revalidate();
                rama.repaint();
            }
        });

        rama.revalidate();
        rama.repaint();
    }

    private void zapiszDoPliku() {
        try (PrintWriter pisarz = new PrintWriter(new FileWriter("baza_kont.txt"))) {
            for (KontoBankowe k : listaKont) {
                pisarz.println(k.getNumerKonta() + ";" + k.getPin() + ";" + k.getSaldo());
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankomatGUI());
    }
}