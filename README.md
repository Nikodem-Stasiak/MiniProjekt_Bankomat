# 🏧 Zaawansowany Symulator Bankomatu (Java)

Druga, rozbudowana wersja aplikacji konsolowej symulującej system bankowy. Projekt kładzie nacisk na trwałość danych oraz zaawansowane mechanizmy programowania obiektowego.

## ✨ Nowości w wersji 2.0
* **System Persystencji (File I/O):** Dane nie giną po zamknięciu programu! System zapisuje stan wszystkich kont do pliku `baza_kont.txt` przy wyjściu i wczytuje je przy starcie.
* **Baza Kont (Collections):** Zastosowanie `ArrayList` pozwala na obsługę wielu klientów jednocześnie.
* **Bezpieczeństwo:** System logowania wymagający podania numeru konta oraz kodu PIN.
* **Parser Danych:** Autorski mechanizm przetwarzania plików tekstowych na obiekty klasy `KontoBankowe`.

## 🛠️ Technologie i zagadnienia
* **Java Standard Edition** (Scanner, PrintWriter, File)
* **Programowanie Obiektowe:** Hermetyzacja, Przeciążanie konstruktorów.
* **Obsługa Błędów:** Zaawansowane bloki `try-catch` przy operacjach na plikach i wyjątkach finansowych.
* **Format Danych:** System bazujący na separatorach (CSV-style).

## 🚀 Jak uruchomić?
1. Sklonuj repozytorium.
2. Skompiluj i uruchom klasę `BankomatMain`.
3. Jeśli plik `baza_kont.txt` nie istnieje, system automatycznie utworzy startowe konta testowe.
