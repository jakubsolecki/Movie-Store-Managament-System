[![Maven badge](https://img.shields.io/badge/Maven-4.0.0-red)](https://maven.apache.org)
[![Lombok badge](https://img.shields.io/badge/Project_Lombok-1.18.12-green)](https://mvnrepository.com/artifact/org.projectlombok/lombok)
[![Hibernate badge](https://img.shields.io/badge/Hibernate-5.4.11-yellow)](https://mvnrepository.com/artifact/org.hibernate/hibernate-core)
[![PostgreSql badge](https://img.shields.io/badge/PostgreSQL-42.2.12-%2346A9EE)](https://mvnrepository.com/artifact/org.postgresql/postgresql)


# Movie Store Management System
Miniprojekt na kurs Bazy Danych. 
Aplikacja desktopowa wspomagająca zarządzanie wypożyczalnią filmów, przeznaczona dla pracownika.  

### Opis funkcjonalności
Aplikacja umożliwia prowadznie ewidencji oraz zarządzniae bazą danych, zawierającą dane zarejestrowanych klientów, filmów dostępnych w ofercie, aktualnych wypożyczeń oraz historii wypożyczeń filmów przez poszczególnych kilentów.

### Schemat
![alt text](https://github.com/jakubsolecki/Movie-Store-Managament-System/blob/master/scheme.png)


### Interfejs dostępu do bazy
Został zdefiniowany w klasie DbMediator, realizującej wzorzec singleton. Zawiera ona metody pozwalające prowadzić interakcje z bazą danych.

### Operacje na bazie
Możliwe jest dodanie nowego klienta, dodanie filmu, wypożycznie filmu przez klienta oraz zwrot filmu przez klienta. Operacje są realizowane za pomocą __Criteria API__ - zapewnia to abstrakcję od użytej bazy danych oraz pozwala zrelizować zapytania w bardziej "obiektowym stylu". Każda z metod rzuca błąd w przypadku wystąpienia określonych zdarzeń: próba dodania klienta o takich samych danych co zarejestrowany,, podanie ID wypożyczenia, które nie istnieje, etc.

#### Dodanie klienta
Realizowane za pomocą metody ```addClient(...)```. Wymagane jest podanie wszystkich danych klienta z ewentualnym pominięciem uwag (które moga zostać dodanie później przy pomocy ```addCLientRemarks(...)```).

#### Dodanie filmu
Realizowane metodą ```addMovie(...)```. Należy podać wszystkie dane filmu wyspecyfikowane w schemacie.

#### Wypożycznienie filmu
Realizowane metodą ```loanMovie(...)```. Wymaga podania ID klienta oraz ID filmu. Wypożyczenie filmu tworzy nowy rekord w tabeli Loans - łączy on klienta z filmem oraz zawiera dane o transkacji.

#### Zwrot filmu
```returnMovie(...)```. Wymaga podania ID wypożycznia. W wyniku tej operacji rekord z Loan odpowiadający wypożyczniu jest "przesuwany" do tabeli LoanHist.


DbMediator zawiera dodatkowo metody ```getClient(...), getAllCLients(...)``` oraz ```getAllMovies(...)```.


## Contributors:
<table>
  <tr>
    <td align="center"><a href="https://github.com/jakubsolecki"><img src="https://avatars2.githubusercontent.com/u/57220835?s=460&v=4" width="100px;" alt=""/><br /><sub><b>Jakub Solecki</b></sub></a><br /></td>
    <td align="center"><a href="https://github.com/Ilargi12"><img src="https://avatars3.githubusercontent.com/u/45597301?s=460&u=0e984d3e0a187a6fb0b8a776b4754b8ceed2041c&v=4" width="100px;" alt=""/><br /><sub><b>Bartłomiej Plewnia</b></sub></a><br />
    </td>
  </tr>
</table>
