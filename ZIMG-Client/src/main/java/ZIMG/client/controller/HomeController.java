package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    static final int imagesPerSite = 3;
    static final String JSP_PAGE_NAME = "home";
    @Autowired
    ImageService imageService;

    @RequestMapping(JSP_PAGE_NAME)
    public String loadHomepage(Model m) {

        Pageable pageable = new PageRequest(0, imagesPerSite, new Sort(Sort.Direction.DESC, "createdAt"));
        Page<Image> imagePage = this.imageService.findAll(pageable);
        m.addAttribute("imagePage", imagePage);

        return this.loadHomepagePage("0", m);
    }

    @RequestMapping(value="/"+JSP_PAGE_NAME+"/{page}", method= RequestMethod.GET)
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

        return JSP_PAGE_NAME;
    }


    @RequestMapping("")
    public String defaultPage(Model m) {
        return this.loadHomepagePage("0", m);
    }

}