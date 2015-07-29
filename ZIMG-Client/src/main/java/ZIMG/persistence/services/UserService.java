package ZIMG.persistence.services;

import ZIMG.exceptions.*;
import ZIMG.models.User;
import ZIMG.persistence.services.security.SecurityUser;
import ZIMG.utils.EmailValidator;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ZIMG.persistence.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class UserService extends  BaseService<User,UserRepository> {
    /**
     * Logger for the class
     */
    private static final Logger LOG  = Logger.getLogger(UserService.class);

    private static final int PASSWORD_MIN_LENGTH = 5;

    private static final int USERNAME_MIN_LENGTH = 3;


    @Transactional
    public User getUserWithImages(String userId) throws NotFoundException{
        User  u = super.getById(userId);
        u.getImages().size();
        return u;
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

    public User findUserByName (String name) {
        return this.repository.findOneByName(name);
    }

    public User register(String userEmail, String password, String userName) throws UserEmailAlreadyInUseException,UserPasswordConstrainsException,EmailNotValidException,UserNameConstrainsException{
        if(userName.length() < USERNAME_MIN_LENGTH){
            throw new UserNameConstrainsException(userName,USERNAME_MIN_LENGTH);
        }
        User u = this.register(userEmail, password);
        u.setName(userName);
        return this.repository.save(u);
    }
    public User register(final String userEmail,final String password) throws UserEmailAlreadyInUseException, UserPasswordConstrainsException,EmailNotValidException{

        if(password.length() < PASSWORD_MIN_LENGTH ){
                throw new UserPasswordConstrainsException(password,PASSWORD_MIN_LENGTH );
        }
        EmailValidator validEmail = new EmailValidator();
        if(!validEmail.validate(userEmail)) {
            throw new EmailNotValidException(userEmail);
        }

        final User existing = this.findUserByEmail(userEmail);
        if(existing != null){
            throw new UserEmailAlreadyInUseException(userEmail);
        }

        User newUser = new User();
        newUser.setEmail(userEmail);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String hashedPassword = passwordEncoder.encode(password);

        newUser.setPassword(hashedPassword);

        return this.repository.save(newUser);
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
