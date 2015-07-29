package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import ZIMG.persistence.services.UserService;
import com.sun.javafx.sg.prism.NGShape;
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
public class HomeController {

    @Autowired
    ImageRepository imageRepository;

    @RequestMapping("home")
    public String homePage(Model m) {

        Iterable<Image> imageList = this.imageRepository.findAll(new Sort(Sort.Direction.DESC, "createdAt"));
        m.addAttribute("imageList", imageList);

        return "home";
    }

    @RequestMapping("")
    public String defaultPage(Model m){
        return this.homePage(m);
    }

}