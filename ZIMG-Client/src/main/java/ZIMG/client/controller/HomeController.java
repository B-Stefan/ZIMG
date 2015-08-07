package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import ZIMG.persistence.services.ImageService;
import ZIMG.persistence.services.UserService;
import com.sun.javafx.sg.prism.NGShape;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ZIMG.persistence.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    static int imagesPerSite = 3;

    @Autowired
    ImageService imageService;

    @RequestMapping("home")
    public String loadHomepage(Model m) {

        Pageable pageable = new PageRequest(0, imagesPerSite, new Sort(Sort.Direction.DESC, "createdAt"));
        Page<Image> imagePage = this.imageService.findAll(pageable);
        m.addAttribute("imagePage", imagePage);

        return this.loadHomepagePage("0", m);
    }

    @RequestMapping(value="/home/{page}", method= RequestMethod.GET)
    public String loadHomepagePage(@PathVariable String page, Model m) {

        int pageNumber = Integer.parseInt(page);

        Pageable pageable = new PageRequest(pageNumber, imagesPerSite, new Sort(Sort.Direction.DESC, "createdAt"));
        Page<Image> imagePage = this.imageService.findAll(pageable);
        m.addAttribute("imagePage", imagePage);

        m.addAttribute("pagePrevious", pageable.previousOrFirst().getPageNumber());
        m.addAttribute("pageNext", pageable.next().getPageNumber());

        if(pageable.equals(pageable.first())) {
            m.addAttribute("pagePreviousDisable", true);
        } else {
            m.addAttribute("pagePreviousDisable", false);
        }

        Page<Image> imageNextPage = this.imageService.findAll(pageable.next());

        if(imageNextPage.getSize() == 0) {
            m.addAttribute("pageNextDisable", true);
        } else {
            m.addAttribute("pageNextDisable", false);
        }

        return "home";
    }


    @RequestMapping("")
    public String defaultPage(Model m) {
        return this.loadHomepagePage("0", m);
    }

}