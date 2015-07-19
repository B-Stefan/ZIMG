package ZIMG.client.controller;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.User;
import ZIMG.persistence.services.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserDetailViewController {

    static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/{username}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String username, Model m) {

        log.log(Priority.DEBUG,"START REQUEST");
        try {
            User user =  userService.getUserByName(username);
            m.addAttribute("user", user);
            m.addAttribute("images", user.getImages());
            log.log(Priority.DEBUG, "USer Images Size: " + user.getImages().size());

        }catch (MultipleUserForUserNameExistException e ){
            m.addAttribute("err", e.getMessage());
            log.log(Priority.ERROR, e.getMessage());
            return null;
        }
        log.log(Priority.DEBUG,"START REQUEST");

        return "user";
    }
}