import dbAccess.DbMediator;
import model.Client;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

public class Main {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }
//
    public static void main(final String[] args) throws Exception {
        try {
            DbMediator dbm = DbMediator.getInstance();
//            dbm.addClient("Zbigniew0",
//                    "Stonoga",
//                    "678945123",
//                    "zbys@o2.com",
//                    "Poland",
//                    "Kraków",
//                    "Dietla 25A/6",
//                    "39-390");
//            dbm.addMovie(
//                    "Batman",
//                    "Someone",
//                    "Someone else",
//                    "Bat-man",
//                    1,
//                    12.5
//            );

//            dbm.loanMovie(1, 4);
//            dbm.returnMovie(5, null, 0);
            System.out.println(dbm.getAllClients());
            System.out.println(dbm.getAllMovies());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }



////        Client client = new Client("Zbigniew", "Stonoga");
//        final Session session = getSession();
//        Transaction tx = session.beginTransaction();
////        session.save(client);
//        tx.commit();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
    }
}