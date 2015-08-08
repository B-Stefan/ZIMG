package ZIMG.client.controller;

import ZIMG.models.User;
import ZIMG.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@EnableWebSecurity
public class TopFiveUserViewController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("user-top5")
    public String loadHomePage(Model m) {

        List<User> topFiveUserList =  userService.getTopFiveByUploadsUsers();
        m.addAttribute("topFiveUserList", topFiveUserList);

        return "user-top5";
    }

}
