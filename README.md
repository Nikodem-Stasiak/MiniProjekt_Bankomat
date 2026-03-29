# 🏧 Advanced Console ATM – System Bankomatowy (Java)

Kompletny, konsolowy system bankomatowy napisany w języku Java. Projekt przeszedł ewolucję od prostego skryptu do zaawansowanej aplikacji z logiką biznesową, walidacją danych oraz systemem trwałego zapisu.

## 🚀 Kluczowe Funkcjonalności

* **System Persystencji (File I/O):** Dane użytkowników (numer konta, PIN, saldo) nie giną po zamknięciu programu. Są automatycznie zapisywane do pliku `baza_kont.txt` i wczytywane przy każdym starcie aplikacji.
* **Bezpieczeństwo i Unikalność:** System uniemożliwia założenie konta o numerze, który już istnieje w bazie. Każdy użytkownik musi posiadać unikalny identyfikator.
* **Zaawansowana Walidacja:** * Numer konta musi mieć dokładnie **5 znaków**.
    * Kod PIN musi być liczbą **4-cyfrową** (zakres 1000-9999).
    * Program wymusza poprawne dane za pomocą pętli walidacyjnych – nie przerywa działania przy błędzie użytkownika.
* **Dwuetapowa Architektura Menu:**
    * **Menu Główne:** Logowanie, Rejestracja nowych użytkowników, Wyjście.
    * **Menu Transakcyjne:** Wpłaty, wypłaty, sprawdzanie salda, wylogowanie.
* **Obsługa Wyjątków:** System chroni przed błędami (np. wypłata kwoty większej niż dostępne saldo) za pomocą mechanizmu `try-catch`.

## 🛠️ Wykorzystane Technologie i Koncepcje

* **Język:** Java 21+
* **Programowanie Obiektowe (OOP):** Hermetyzacja pól, przeciążanie konstruktorów, praca na obiektach.
* **Kolekcje:** `ArrayList` do dynamicznego zarządzania bazą kont w pamięci RAM.
* **Strumienie I/O:** `Scanner` (odczyt), `PrintWriter` (zapis), `File` (zarządzanie plikami).
* **Logika sterująca:** Zagnieżdżone instrukcje `switch` oraz pętle `while` zarządzające sesją użytkownika.

## 📁 Struktura Projektu

* `KontoBankowe.java` – Klasa modelowa (mózg systemu), odpowiada za logikę wpłat/wypłat.
* `BankomatMain.java` – Klasa sterująca interfejsem użytkownika i zarządzająca bazą danych.
* `baza_kont.txt` – Lokalna baza danych w formacie tekstowym (generowana automatycznie).

## 🚦 Jak uruchomić?

1. Skopiuj repozytorium na swój dysk.
2. Otwórz projekt w dowolnym środowisku IDE (zalecane IntelliJ IDEA).
3. Uruchom klasę `BankomatMain`.
4. Jeśli uruchamiasz program pierwszy raz, system zaproponuje startowe konta testowe.

## 🗺️ Roadmap (Rozwój Projektu)

- [x] Implementacja bazy danych w pliku tekstowym.
- [x] Walidacja unikalności numerów kont.
- [x] Obsługa wielu sesji użytkowników.
- [ ] **W trakcie:** Migracja z terminala do interfejsu graficznego (GUI) przy użyciu Java Swing.
- [ ] Dodanie historii transakcji dla każdego konta.

---
*Projekt stworzony w ramach intensywnej nauki fundamentów inżynierii oprogramowania w języku Java.*
