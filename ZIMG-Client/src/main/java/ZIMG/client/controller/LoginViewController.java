package ZIMG.client.controller;

import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "login";
    @Autowired
    private UserService userService;

    @RequestMapping(value = JSP_PAGE_NAME, method = RequestMethod.GET)
    public String getView(@RequestParam(value = "error", required = false) Optional<String> errorMsg, Model m) {
        try {
            userService.getCurrentUser();
        }catch (SecurityException e){
            //if user not logged in
            if(errorMsg.isPresent()){
                throw new SpringRuntimeExceptionForUser("We can't find an existing user for your email/password combination", SpringRuntimeExceptionForUser.TYPE.ERROR,JSP_PAGE_NAME);
            }
            return JSP_PAGE_NAME;
        }
        return "redirect:/" + HomeController.JSP_PAGE_NAME;

    }

}
