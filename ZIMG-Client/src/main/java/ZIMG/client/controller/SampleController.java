package ZIMG.client.controller;

import ZIMG.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ZIMG.persistence.repositories.UserRepository;

@Controller
public class SampleController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("home")
    public String loadHomePage(Model m) {

        Iterable<User> list = this.userRepository.findAllAdmins();
        m.addAttribute("userList", list);
        m.addAttribute("name", "asdasdasd");
        return "home";
    }
}