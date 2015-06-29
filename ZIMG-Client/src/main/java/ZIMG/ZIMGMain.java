package ZIMG;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import web.UserService;

import java.util.Date;
import java.util.Properties;

public class ZIMGMain {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    public static void main(String[] args) {

        sessionFactory = configureSessionFactory();

        for (int i = 0; i < 200; i ++ ) {
            User user = new User();

            user.setName("User" + i);
            user.setEmail("fjunge@fabimon.org");
            user.setPassword("hallo123");
            user.setCreatedAt(new Date());

            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            User firstuser  = (User) session.get(User.class, new Integer(1));

            System.out.println(firstuser.getName());
            session.close();

        }

        sessionFactory.close();

        UserService userService = new UserService();

        User user199 = userService.getUserByName("User199");

        System.out.print(user199.getEmail());

    }

    public static SessionFactory configureSessionFactory() {
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
