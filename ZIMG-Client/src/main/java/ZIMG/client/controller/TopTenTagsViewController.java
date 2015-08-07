package ZIMG.client.controller;

import ZIMG.models.Tag;
import ZIMG.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TopTenTagsViewController {

    @Autowired
    private TagService tagService;

    @RequestMapping("tags-top10")
    public String loadHomePage(Model m) {

        List<Tag> topTenTagsList = tagService.getTopTenTags();
        m.addAttribute("topTenTagsList", topTenTagsList);

        return "tags-top10";
    }

}
