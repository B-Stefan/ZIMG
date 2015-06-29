package web;

import ZIMG.Image;
import ZIMG.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class UserService {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;


    public UserService() {
       sessionFactory = configureSessionFactory();
    }

    public User getUserByName(String username) {

        Session session = sessionFactory.openSession();

        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("name", username));

        User user =  (User) cr.list().get(0);
        session.close();
        return user;
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
