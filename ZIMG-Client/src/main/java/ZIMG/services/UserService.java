/*
 * ZIMG-JAVA - Game, Copyright 2015  Nils Oke S., Fabian J., Chris P., Stefan Bieliauskas  -  All Rights Reserved.
 * Hochschule Bremen - University of Applied Sciences
 *
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 * Web:
 *     https://github.com/B-Stefan/ZIMG
 *
 */
package ZIMG.services;

import ZIMG.exceptions.*;
import ZIMG.models.User;
import ZIMG.services.security.SecurityUser;
import ZIMG.utils.EmailValidator;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * Geht the user with the images
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Transactional
    public User getUserWithImages(String userId) throws NotFoundException{
        User  u = super.getById(userId);
        u.getImages().size();
        return u;
    }

    /**
     * Get the user by name with images
     * @param name
     * @return
     * @throws NotFoundException
     */
    @Transactional
    public User getUserByNameWithImages (String name) throws NotFoundException{
        User  u = this.repository.findOneByName(name);
        u.getImages().size();
        return u;
    }


    /**
     * Gibt die fuenf User als Liste zurück, die aktuell die meisten Uploads haben und läd seine bilder mit
     * @return List<User>
     */
    public List<User> getTopFiveByUploadsUsers() {
        List<User> l =  this.repository.findTopUsers();
        l.forEach(user -> user.getImages().size());
        return this.repository.findTopUsers();
    }

    /**
     * Get the user by there emal address
     * @param email
     * @return
     */
    public User findUserByEmail(String email){
        return this.repository.findOneByEmail(email);
    }

    /**
     * Mehtod for register new user with string
     * @param userEmail
     * @param password
     * @param userName
     * @return
     * @throws UserEmailAlreadyInUseException
     * @throws UserPasswordConstrainsException
     * @throws EmailNotValidException
     * @throws UserNameConstrainsException
     */
    public User register(String userEmail, String password, String userName) throws UserEmailAlreadyInUseException,UserPasswordConstrainsException,EmailNotValidException,UserNameConstrainsException{
        if(userName.length() < USERNAME_MIN_LENGTH){
            throw new UserNameConstrainsException(userName,USERNAME_MIN_LENGTH);
        }
        User u = this.register(userEmail, password);
        u.setName(userName);
        return this.repository.save(u);
    }

    /**
     * Heigh level mehtod for new user
     * @param userEmail
     * @param password
     * @return
     * @throws UserEmailAlreadyInUseException
     * @throws UserPasswordConstrainsException
     * @throws EmailNotValidException
     */
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

    /**
     * Return the current user or null if the user not logged in
     * @return The security user WITHOUT IMAGES FOR THE USER, ONLY BASE INFORMATION !
     * @throws SecurityException
     */
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
