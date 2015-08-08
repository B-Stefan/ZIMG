package ZIMG.client.controller;

import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.services.TagService;
import ZIMG.services.UserService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagDetailViewController extends BaseController{

    static Logger log = Logger.getLogger(TagDetailViewController.class);

    public static final String JSP_PAGE_NAME = "tag";

    @Autowired
    private TagService tagService;

    @RequestMapping(value=JSP_PAGE_NAME  + "/{tagName}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String tagName, Model m) {

        try {
            Tag tag = tagService.getTagByTagAndLoadImages(tagName);

            m.addAttribute("tag", tag);

        } catch (NotFoundException e) {
            throw new SpringRuntimeExceptionForUser(e);
        }

        return JSP_PAGE_NAME;
    }
}