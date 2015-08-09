package ZIMG.services.security;

import ZIMG.models.User;
import ZIMG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The service for the security module
 */
@Service
public class UserDetailService implements UserDetailsService  {
    @Autowired
    private UserService userService;

    /**
     * Load method if a user try to login this method  called
     * @param email The emailaddress of the user
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userService.findUserByEmail(email);

        if(user != null){
            return new SecurityUser(user);
        }
        return null;

    }
}
