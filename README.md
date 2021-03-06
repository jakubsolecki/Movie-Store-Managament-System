[![](https://img.shields.io/badge/Maven-4.0.0-red)](https://maven.apache.org)
[![](https://img.shields.io/badge/Project_Lombok-1.18.12-green)](https://mvnrepository.com/artifact/org.projectlombok/lombok)
[![](https://img.shields.io/badge/Hibernate-5.4.11-yellow)](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)
[![](https://img.shields.io/badge/PostgreSQL-42.2.12-%2346A9EE)](https://mvnrepository.com/artifact/org.postgresql/postgresql)
[![](https://img.shields.io/badge/JavaFX-14.0.1-blue)](https://openjfx.io/)



# Movie Store Management System
Miniprojekt na kurs Bazy Danych. 
Aplikacja desktopowa wspomagająca zarządzanie wypożyczalnią filmów, przeznaczona dla pracownika.  

### Opis funkcjonalności
Aplikacja umożliwia prowadzenie ewidencji oraz zarządzanie bazą danych, zawierającą dane zarejestrowanych klientów, filmów dostępnych w ofercie, aktualnych wypożyczeń oraz historii wypożyczeń filmów przez poszczególnych kilentów.

### Schemat
![alt text](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/master/scheme.png)

### Struktura projektu
Klasy znajdują się w katalogu __src/main/java__

#### Model
Klasy będące encjami mapowanymi do bazdy danych najdują się w katalogu [model](https://github.com/jakubsolecki/Movie-Store-Managament-System/tree/master/src/main/java/model).

#### Dostęp do bazy
Cały interfejs zdefiniowany jest w katalogu [dbAccess](https://github.com/jakubsolecki/Movie-Store-Managament-System/tree/master/src/main/java/dbAccess) w klasie [DbMediator](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/master/src/main/java/dbAccess/DbMediator.java).

#### GUI
Wizualizacja zawarta jest w katalogu [visualization](https://github.com/jakubsolecki/Movie-Store-Managament-System/tree/master/src/main/java/visualization).

### Interfejs dostępu do bazy
Został zdefiniowany w klasie [DbMediator](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/master/src/main/java/dbAccess/DbMediator.java), realizującej wzorzec singleton. Zawiera ona metody pozwalające prowadzić interakcje z bazą danych.

### Operacje na bazie
Możliwe jest dodanie nowego klienta, dodanie filmu, wypożycznie filmu przez klienta oraz zwrot filmu przez klienta. Operacje są realizowane za pomocą __Criteria API__ - zapewnia to abstrakcję od użytej bazy danych oraz pozwala zrelizować zapytania w bardziej "obiektowym stylu". Każda z metod rzuca błąd w przypadku wystąpienia określonych zdarzeń: próba dodania klienta o takich samych danych co zarejestrowany,, podanie ID wypożyczenia, które nie istnieje, etc.

#### Dodanie klienta
Realizowane za pomocą metody [```addClient(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L36). Wymagane jest podanie wszystkich danych klienta z ewentualnym pominięciem uwag (które moga zostać dodanie później przy pomocy [```addCLientRemarks(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L78)).

#### Dodanie filmu
Realizowane metodą [```addMovie(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L102). Należy podać wszystkie dane filmu wyspecyfikowane w schemacie.

#### Wypożycznienie filmu
Realizowane metodą [```loanMovie(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L138). Wymaga podania ID klienta oraz ID filmu. Wypożyczenie filmu tworzy nowy rekord w tabeli Loans - łączy on klienta z filmem oraz zawiera dane o transkacji.

#### Zwrot filmu
[```returnMovie(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L187). Wymaga podania ID wypożycznia. W wyniku tej operacji rekord z Loan odpowiadający wypożyczniu jest "przesuwany" do tabeli LoanHist.


DbMediator zawiera dodatkowo metody [```getClient(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L271), [```getAllCLients(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L297), [```getLoan(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L238) oraz [```getAllMovies(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L349), [```getAllClientsByTheirName(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L321), [```getMovieByTitle(...)```](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/666ea7287ff6349cba6384b3bfeecab109a3dfc0/src/main/java/dbAccess/DbMediator.java#L373).

## Contributors:
<table>
  <tr>
    <td align="center"><a href="https://github.com/jakubsolecki"><img src="https://avatars2.githubusercontent.com/u/57220835?s=460&v=4" width="100px;" alt=""/><br /><sub><b>Jakub Solecki</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/Ilargi12"><img src="https://avatars3.githubusercontent.com/u/45597301?s=460&u=0e984d3e0a187a6fb0b8a776b4754b8ceed2041c&v=4" width="100px;" alt=""/><br /><sub><b>Bartłomiej Plewnia</b></sub></a><br />
    </td>
  </tr>
</table>
