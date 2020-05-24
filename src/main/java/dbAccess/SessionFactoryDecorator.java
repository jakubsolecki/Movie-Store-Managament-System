package dbAccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryDecorator {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}
