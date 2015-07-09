package ZIMG.persistence.services;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ZIMG.persistence.repositories.UserRepository;

import java.util.List;

@Service
@Scope("prototype")
public class UserService extends  BaseService<UserRepository> {

    public User getUserByName(String username) throws MultipleUserForUserNameExistException{
        List<User> usersList= this.repository.findByName(username);
        if(usersList.size() > 1){
            throw new MultipleUserForUserNameExistException(username,usersList);
        }
        return usersList.get(0);
    }

}
