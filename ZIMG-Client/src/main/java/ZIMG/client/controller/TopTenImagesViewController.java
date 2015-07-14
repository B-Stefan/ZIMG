package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.persistence.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by okeschwien on 14.07.15.
 */
public class TopTenImagesViewController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("images-top10")
    public String loadHomePage(Model m) {

        List<Image> topTenImagesList = imageService.getTopTenImages();
        m.addAttribute("topTenTagsList", topTenImagesList);

        return "images-top10";
    }
}
