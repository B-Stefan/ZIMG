package ZIMG.services;

import ZIMG.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class BaseService {

    private static SessionFactory sessionFactory = null;


    static {
        sessionFactory = configureSessioginFactory();
    }
    protected  Session createSession(){
        return sessionFactory.openSession();
    }

    public static SessionFactory configureSessioginFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.configure();
            configuration.addAnnotatedClass(User.class);
            // configuration.addAnnotatedClass(Image.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException hbe) {

            hbe.printStackTrace();

        }

        return sessionFactory;
    }
}
