package ZIMG.persistence.services;

import ZIMG.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ZIMG.persistence.repositories.UserRepository;

import java.util.List;

@Service
@Scope("prototype")
public class UserService extends  BaseService<UserRepository> {

    public User getUserByName(String username) throws Exception{
        List<User> usersList= this.repository.findByName(username);
        if(usersList.size() > 1){
            throw new Exception("Mupliple user found with name" + username);
        }
        return usersList.get(0);
    }

}
