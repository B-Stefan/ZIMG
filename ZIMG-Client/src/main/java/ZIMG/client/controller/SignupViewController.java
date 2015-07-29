package ZIMG.client.controller;

import ZIMG.exceptions.*;
import ZIMG.models.BaseModel;
import ZIMG.persistence.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "signup";

    @Autowired
    private UserService userService;

    @RequestMapping(value = JSP_PAGE_NAME, method = RequestMethod.GET)
    public String getView(Model m) {
        return "signup";
    }

    @RequestMapping(value=JSP_PAGE_NAME, method= RequestMethod.POST)
    public String postView(@RequestParam("userEmail") String userEmail,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password) {

        try {

            this.userService.register(userEmail,password,username);

        }catch (UserEmailAlreadyInUseException e){

            throw new SpringRuntimeExceptionForUser("An User with this email address might already exist",
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }catch (UserPasswordConstrainsException e){

            throw new SpringRuntimeExceptionForUser(e,
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }
        catch (EmailNotValidException e){

            throw new SpringRuntimeExceptionForUser("Your email address is not valid, please signup with a valid email adress",
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }
        catch (UserNameConstrainsException e){
            throw new SpringRuntimeExceptionForUser(e,
                    SpringRuntimeExceptionForUser.TYPE.ERROR,
                    JSP_PAGE_NAME);
        }

        return "redirect:" + LoginViewController.JSP_PAGE_NAME;
    }
}
