# 🏦 Java ATM: From Terminal to GUI

Projekt edukacyjny realizowany w ramach przygotowań do roli **Junior Java Developer 2026**. Repozytorium zawiera ewolucję aplikacji bankomatowej – od prostego skryptu konsolowego po pełnoprawną aplikację okienkową.

## 📁 Zawartość Repozytorium

Projekt podzielony jest na dwa etapy rozwoju:

### 1. wersja Terminalowa (`BankomatTerminal.java`)
* **Interfejs:** Tekstowy (konsola).
* **Logika:** Podstawowe operacje bankowe przy użyciu klasy `Scanner`.
* **Cel:** Zrozumienie podstaw składni Javy, pętli i warunków logicznych.

### 2. Wersja GUI (`BankomatGUI.java`) - AKTUALNA
* **Interfejs:** Graficzny (biblioteka **Java Swing**).
* **Logika:** Zarządzanie stanem aplikacji, obsługa zdarzeń (ActionListeners).
* **Persystencja:** Dane są trwale zapisywane i odczytywane z pliku `baza_kont.txt`.
* **Bezpieczeństwo:** Obsługa błędów formatowania danych (try-catch).

---

## 🚀 Funkcje Aplikacji (GUI)
- ✅ Logowanie numerem konta i kodem PIN.
- ✅ Sprawdzanie salda w czasie rzeczywistym.
- ✅ Wpłaty i wypłaty z walidacją środków.
- ✅ System wylogowywania i powrotu do ekranu startowego.
- ✅ Automatyczna synchronizacja z bazą danych w pliku `.txt`.

## 🛠️ Technologie
- **Język:** Java 17+
- **Biblioteki:** Swing, AWT (GUI), Java IO (Przechowywanie danych).
- **Narzędzia:** Git (Kontrola wersji).

## 🏁 Jak uruchomić?
W zależności od tego, którą wersję chcesz przetestować, uruchom odpowiednią klasę `main`:
- Dla wersji w oknie: `BankomatGUI.java`
- Dla wersji w konsoli: `BankomatTerminal.java`

*Uwaga: Obie wersje korzystają z tego samego pliku `baza_kont.txt`, więc zmiany w jednej są widoczne w drugiej!*

---
**Status projektu:** Rozwijany. Kolejny krok: Refaktoryzacja i Bazy Danych SQL.
