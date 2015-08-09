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

/**
 * The login view controller only provide the get method
 * because the spring security module catch the post for this route
 * @see web.xml
 *
 */
@Controller
public class LoginViewController extends BaseController {

    /**
     * The name of the jsp page
     */
    public static final String JSP_PAGE_NAME = "login";

    @Autowired
    private UserService userService;

    /**
     * Render the login page if the user is not logged in.
     * If the user is authorised then the controller redirect to home page
     * @param errorMsg Only set if the spring security model throw an error
     * @param m
     * @return
     */
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
