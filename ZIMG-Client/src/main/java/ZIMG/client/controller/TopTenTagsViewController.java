package ZIMG.client.controller;

import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.services.TagService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TopTenTagsViewController {

    static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    @Autowired
    private TagService tagService;

    @RequestMapping("tags-top10")
    public String loadHomePage(Model m) {

        log.log(Priority.DEBUG,"START REQUEST");

        List<Tag> topTenTagsList = tagService.getTopTenTags();
        m.addAttribute("topTenTagsList", topTenTagsList);

        return "tags-top10";
    }

}
