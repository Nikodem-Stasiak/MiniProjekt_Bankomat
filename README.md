# 🏦 Java ATM GUI Project

Projekt funkcjonalnego bankomatu stworzony w ramach ścieżki rozwoju **Junior Java Developer 2026**. Aplikacja posiada graficzny interfejs użytkownika oraz system trwałego przechowywania danych.

## 🚀 Funkcjonalności
- **System logowania:** Weryfikacja numeru konta i kodu PIN z obsługą błędów (try-catch).
- **Zarządzanie kontem:** Sprawdzanie salda, wpłaty i wypłaty środków.
- **Bezpieczeństwo transakcji:** Walidacja dostępnych środków przed wypłatą.
- **Persystencja danych:** Automatyczny odczyt i zapis bazy kont do pliku `baza_kont.txt`.
- **Dynamiczne GUI:** Płynne przełączanie między ekranem logowania a menu głównym (Swing).

## 🛠 Technologie
- **Język:** Java 17+
- **Biblioteki GUI:** Java Swing, AWT
- **Przechowywanie danych:** File I/O (FileWriter, Scanner)
- **Narzędzia:** Git

## 📂 Struktura pliku danych
Dane przechowywane są w formacie: `numer_konta;pin;saldo`.
Przykład: `12345;1111;5000`

## 🏁 Jak uruchomić?
1. Sklonuj repozytorium.
2. Upewnij się, że masz plik `baza_kont.txt` w folderze głównym (program wygeneruje domyślne konta, jeśli plik nie istnieje).
3. Skompiluj i uruchom klasę `BankomatGUI`.

## 📈 Roadmap (Cel: Lato 2026)
- [ ] Refaktoryzacja kodu do wzorca MVC.
- [ ] Implementacja bazy danych SQL (PostgreSQL/MySQL).
- [ ] Migracja na Spring Boot i stworzenie wersji Webowej.
- [ ] Dodanie testów jednostkowych JUnit.

---
*Projekt rozwijany w celach edukacyjnych.*
