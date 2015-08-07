package ZIMG.client.controller;

import ZIMG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "logout";
    @Autowired
    private UserService userService;

    @RequestMapping(value = JSP_PAGE_NAME, method = RequestMethod.GET)
    public String getView(Model m) {
        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:" + LoginViewController.JSP_PAGE_NAME;
    }

}
