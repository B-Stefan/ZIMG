package ZIMG.services;

import ZIMG.model.Image;
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

import java.util.List;

@Service
@Scope("prototype")
public class ImageService extends BaseService {

    public List<Image> getByUploader(User user) {

        Session session = this.createSession();
        Criteria cr = session.createCriteria(User.class);
        cr.add(Restrictions.eq("uploaderid", user.getId()));

        List<Image>  list =  (List<Image>) cr.list();
        session.close();
        return list;
    }


}
