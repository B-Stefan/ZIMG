package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import ZIMG.persistence.services.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ZIMG.persistence.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SampleController {

    static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ImageRepository imageRepository;

    @RequestMapping("home")
    public String loadOverview(Model m) {

        Iterable<User> list = this.userRepository.findAllAdmins();
        m.addAttribute("userList", list);

        Iterable<Image> imageList = this.imageRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
        m.addAttribute("imageList", imageList);

        return "home";
    }

    @RequestMapping("")
    public String loadLogin(Model m) {

        return "login";
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loadLoginPage(Model m) {

        return "login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model m) {
        Boolean loginSuccess = false;
        log.log(Priority.DEBUG, username + " " + password);

        //Boolean loginSuccess = userService.authUser(username, md5(password));

        // fakelogin - begin
        if(username.equals("test") && password.equals("test")) {
            loginSuccess = true;
        }
        // fakelogin - end

        if(loginSuccess) {
            // @todo: auth here
            return "redirect:home";
        } else {
            return "redirect:";
        }
    }
}