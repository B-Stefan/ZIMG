package ZIMG.client.controller;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.models.User;
import ZIMG.persistence.services.UserService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserDetailViewController extends BaseController{

    static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    public static final String JSP_PAGE_NAME = "user";

    @Autowired
    private UserService userService;

    @RequestMapping(value=JSP_PAGE_NAME  + "/{userId}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String userId, Model m) {

        try {
            User user =  userService.getUserWithImages(userId);
            m.addAttribute("user", user);
            m.addAttribute("images", user.getImages());

        }catch (NotFoundException e ){
            throw new SpringRuntimeExceptionForUser(e);
        }

        return JSP_PAGE_NAME;
    }
}