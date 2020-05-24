package dbAccess;

import model.Client;
import model.Loan;
import model.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class DbMediator {
    private static DbMediator instance;

    private DbMediator() {
    }

    public static DbMediator getInstance() {
        if (instance == null)
            instance = new DbMediator();
        return instance;
    }

//    TODO: validate an existence of the entity before adding new

    public void addClient(String firstName,
                          String lastName,
                          String country,
                          String city,
                          String street,
                          String zip) {
        Client client = new Client(firstName, lastName, country, city, street, zip);
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public void addClientRemarks(Client client, String remarks) {
        client.setRemarks(remarks);
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void addMovie (String title,
                          String director,
                          String script,
                          String description,
                          int unitsInStock,
                          double pricePerUnit) {
        Movie movie = new Movie(title, director, script, description, unitsInStock, pricePerUnit);
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(movie);
        transaction.commit();
        session.close();
    }

    public void loanMovie(Client client, Movie movie) {
        Loan loan = new Loan(client, movie, java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(7));
        client.addLoan(loan);
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(loan);
        session.update(client);
        transaction.commit();
        session.close();
    }

    public void returnMovie (Client client, Movie movie) {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

    }
}
