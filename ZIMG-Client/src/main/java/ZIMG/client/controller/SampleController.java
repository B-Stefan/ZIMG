package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ZIMG.persistence.repositories.UserRepository;

@Controller
public class SampleController {

    @Autowired
    UserRepository userRepository;

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
}