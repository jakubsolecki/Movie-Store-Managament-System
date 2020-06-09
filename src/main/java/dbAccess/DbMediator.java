package dbAccess;

import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.DAYS;

public class DbMediator {
    private static DbMediator instance;
    private final Pattern emailPattern;

    private DbMediator() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        this.emailPattern = Pattern.compile(emailRegex);
        Configuration configuration = new Configuration();
        configuration.configure();
    }

    public static DbMediator getInstance() {
        if (instance == null)
            instance = new DbMediator();
        return instance;
    }

    // throws an exception if client with same name, phone and email already exists
    public int addClient(String firstName,
                          String lastName,
                          String phone,
                          String email,
                          String country,
                          String city,
                          String street,
                          String zip) throws Exception {
        if (!this.emailPattern.matcher(email).matches())
            throw new Exception("Incorrect email address");

        Address address = new Address(country, city, street, zip);
        Client client = new Client(firstName, lastName, phone, email, address);
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        Predicate[] predicates = new Predicate[4];
        predicates[0] = cb.like(root.get("firstName"), firstName);
        predicates[1] = cb.like(root.get("lastName"), lastName);
        predicates[2] = cb.like(root.get("phone"), phone);
        predicates[3] = cb.like(root.get("email"), email);
        cr.select(root).where(predicates);

        try {
            session.createQuery(cr).getSingleResult();
        }
        catch (NoResultException e) {
            session.save(client);
            transaction.commit();
            session.close();
            return client.getClientID();
        }

        transaction.rollback();
        session.close();
        throw new Exception("Client already exists");
    }

    // throws an exception if client with given ID does not exist
    public void addClientRemarks(int clientID, String remarks) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        cr.select(root).where(cb.equal(root.get("clientID"), clientID));

        try {
            Client client = session.createQuery(cr).getSingleResult();
            client.setRemarks(remarks);
            session.update(client);
            transaction.commit();
            session.close();
        }
        catch (NoResultException e) {
            transaction.rollback();
            session.close();
            throw new Exception("Client does not exist");
        }
    }

    // throws an exception if there already exists movie with same title, director and script
    public int addMovie (String title,
                          String director,
                          String script,
                          String description,
                          int unitsInStock,
                          double pricePerUnit) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);
        Predicate[] predicates = new Predicate[3];
        predicates[0] = cb.like(root.get("title"), title);
        predicates[1] = cb.like(root.get("director"), director);
        predicates[2] = cb.like(root.get("script"), script);
        cr.select(root).where(predicates);

        Movie movieLookup = null;
        try {
            movieLookup = session.createQuery(cr).getSingleResult();
        }
        catch (NoResultException e) {
            Movie movie = new Movie(title, director, script, description, unitsInStock, pricePerUnit);
            session.save(movie);
            transaction.commit();
            session.close();
            return movie.getMovieID();
        }

        transaction.rollback();
        session.close();
        throw new Exception("Movie already exists");
    }

    // throws an exception indicating that client or movie does not exist
    public int loanMovie(int clientID, int movieID) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        cr.select(root).where(cb.equal(root.get("clientID"), clientID));

        Client client = null;
        try {
            client = session.createQuery(cr).getSingleResult();
        }
        catch (NoResultException e) {
            transaction.rollback();
            session.close();
            throw new Exception("Client does not exist");
        }

        CriteriaQuery<Movie> cr2 = cb.createQuery(Movie.class);
        Root<Movie> root2 = cr2.from(Movie.class);
        cr2.select(root2).where(cb.equal(root2.get("movieID"), movieID));

        try {
            Movie movie = session.createQuery(cr2).getSingleResult();
            movie.takeUnitFromStock();
            Loan loan = new Loan(client, movie, java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(7));
            client.addLoan(loan);

            session.save(loan);
            session.update(client);
            transaction.commit();
            session.close();

            return loan.getLoanID();
        }
        catch (NoResultException e) {
            transaction.rollback();
            session.close();
            throw new Exception("Movie does not exist");
        }
        catch (Exception e) {
            transaction.rollback();
            session.close();
            throw new Exception(e.getMessage());
        }
    }

    // throws exception when loan with given ID does not exist
    public double returnMovie (int loanID, String remarks) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
        Root<Loan> root = cr.from(Loan.class);
        cr.select(root).where(cb.equal(root.get("loanID"), loanID));

        Loan loan = null;
        try {
            loan = session.createQuery(cr).getSingleResult();
        }
        catch (NoResultException e) {
            transaction.rollback();
            session.close();
            throw new Exception("Loan does not exist");
        }
        double fine = 0.0;
        //5 zl fine for 1 day delay
        if(DAYS.between(java.time.LocalDate.now(), loan.getDueDate()) < 0)
            fine = -5.0*(int)DAYS.between(java.time.LocalDate.now(), loan.getDueDate());

        Client client = loan.getClient();
        Movie movie = loan.getMovie();

        LoanHist loanHist = new LoanHist(
                client,
                loan.getMovie(),
                loan.getDueDate(),
                java.time.LocalDate.now()
        );

        if (remarks != null)
            loanHist.setRemarks(remarks);

        if (fine != 0)
            loanHist.setFine(fine);

        movie.returnUnitToStock();
        client.removeLoan(loan);
        client.addLoanHist(loanHist);
        session.update(client);
        session.remove(loan);
        session.save(loanHist);

        transaction.commit();
        session.close();
        return fine;
    }

    public Loan getLoan(int clientID, String movieTitle) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Loan> cr = cb.createQuery(Loan.class);
        Root<Loan> root = cr.from(Loan.class);
        Predicate[] predicates = new Predicate[2];
        Client client = getClient(clientID);
        Movie movie = getMovieByTitle(movieTitle);
        predicates[0] = cb.equal(root.get("client"), client);
        predicates[1] = cb.equal(root.get("movie"), movie);
        cr.select(root).where(predicates);

        Loan loan = null;
        try {
            loan = session.createQuery(cr).getSingleResult();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
            throw new Exception("Loan does not exist");
        }
        finally {
            session.close();
        }

        return loan;
    }



    // return client with given ID or throws an exception if there is no such a client
    public Client getClient(int clientID) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        cr.select(root).where(cb.equal(root.get("clientID"), clientID));

        Client client = null;
        try {
            client = session.createQuery(cr).getSingleResult();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
            throw new Exception("Client does not exist");
        }
        finally {
            session.close();
        }

        return client;
    }

    // returns list of all clients or null if there aren't any
    public List<Client> getAllClients () throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        cr.select(root);

        List<Client> allClients = null;
        try{
            allClients = session.createQuery(cr).getResultList();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return allClients;
    }

    public List<Client> getAllClientsByTheirName (String firstName, String lastName) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);
        Predicate[] predicates = new Predicate[2];
        predicates[0] = cb.like(root.get("firstname"), firstName);
        predicates[1] = cb.like(root.get("lastname"), lastName);
        cr.select(root).where(predicates);

        List<Client> allClientsByTheirName = null;
        try{
            allClientsByTheirName = session.createQuery(cr).getResultList();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return allClientsByTheirName;
    }

    // returns list of all movies or null if there aren't any
    public List<Movie> getAllMovies() throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);
        cr.select(root);

        List<Movie> allMovies = null;
        try{
            allMovies = session.createQuery(cr).getResultList();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return allMovies;
    }

    public Movie getMovieByTitle(String title) throws Exception {
        Session session = SessionFactoryDecorator.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Movie> cr = cb.createQuery(Movie.class);
        Root<Movie> root = cr.from(Movie.class);
        cr.select(root).where(cb.like(root.get("title"), title));

        Movie movie = null;
        try{
            movie = session.createQuery(cr).getSingleResult();
            transaction.commit();
        }
        catch (NoResultException e) {
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return movie;
    }
}
