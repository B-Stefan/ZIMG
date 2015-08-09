package ZIMG.client.controller;

import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.models.Tag;
import ZIMG.services.TagService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Top ten tags
 */
@Controller
@EnableWebSecurity
public class TopTenTagsViewController extends BaseController {

    public final static String JSP_PAGE_NAME = "tags-top10";

    @Autowired
    private TagService tagService;

    /**
     * Render the top ten tags
     * @param m
     * @return
     */
    @RequestMapping(value = JSP_PAGE_NAME,method= RequestMethod.GET)
    public String loadPage(Model m) {
        List<Tag> topTenTagsList = tagService.getTopTenTags();
        m.addAttribute("topTenTagsList", topTenTagsList);

        return "tags-top10";
    }

    /**
     * Method for delete a tag
     * @param tagId The id of the tag
     * @param m
     * @return
     */
    @RequestMapping(value=JSP_PAGE_NAME+ "/delete/{tagId}", method= RequestMethod.GET)
    public String deleteTag(@PathVariable String tagId, Model m) {
        try {
            tagService.delete(tagId);
        }catch (NotFoundException e){
            throw new SpringRuntimeExceptionForUser(e);
        }

        return "redirect:/" + JSP_PAGE_NAME;
    }

}
