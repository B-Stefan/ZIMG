package ZIMG.persistence.services;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ZIMG.persistence.repositories.UserRepository;

import java.util.List;

@Service
@Scope("prototype")
public class UserService extends  BaseService<User,UserRepository> {

    /**
     * Gibt User zurueck, dessen Username angegeben wird,
     * wenn mehrere User gefunden werden, wird MultipleUserForUserNameExistException geworfen
     * @param username
     * @return User
     * @throws MultipleUserForUserNameExistException
     */
    public User getUserByName(String username) throws MultipleUserForUserNameExistException{
        List<User> usersList = this.repository.findByName(username);
        if(usersList.size() > 1){
            throw new MultipleUserForUserNameExistException(username,usersList);
        }
        return usersList.get(0);
    }

    /**
     * Gibt die fuenf User als Liste zurück, die aktuell die meisten Uploads haben
     * @return List<User>
     */
    public List<User> getTopFiveUsers() {
        return this.repository.findTopFiveUsers();
    }

}
