package ZIMG.services;

import ZIMG.model.User;
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
public class UserService extends  BaseService {


    public User getUserByName(String username) {
        Session session = this.createSession();

        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("name", username));

        User user =  (User) cr.list().get(0);
        session.close();
        return user;
    }

}
