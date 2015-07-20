package ZIMG.persistence.services;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.User;
import ZIMG.persistence.services.security.SecurityUser;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ZIMG.persistence.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
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
            throw new MultipleUserForUserNameExistException(username, usersList);
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
    public User findUserByEmail(String email){
        return this.repository.findOneByEmail(email);
    }
    public User findUserByName (String name){
        return this.repository.findOneByName(name);
    }
    
    public SecurityUser getCurrentUser() throws SecurityException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            User loginUser = this.findUserByEmail(email);
            return new SecurityUser(loginUser);
        } else {
            throw new SecurityException("User is not logged in and you try to get the current user ");
        }
    }
}
