package ZIMG.client.controller;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.User;
import ZIMG.persistence.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserDetailViewController {


    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/{username}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String username, Model m) {

        try {
            User user =  userService.getUserByName(username);
            m.addAttribute("user", user);
        }catch (MultipleUserForUserNameExistException e ){
            m.addAttribute("err", e.getMessage());
        }

        return "user";
    }
}