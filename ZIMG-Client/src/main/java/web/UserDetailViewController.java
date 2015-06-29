package web;

import ZIMG.model.User;
import ZIMG.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserDetailViewController {

    private UserService userService = new UserService();

    @RequestMapping(value="/user/{username}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String username, Model m) {

        User user =  userService.getUserByName(username);

        m.addAttribute("user", user);
        return "user";
    }
}