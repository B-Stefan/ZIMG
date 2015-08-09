
package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@EnableWebSecurity
public class TopTenImagesViewController  extends BaseController{

    @Autowired
    private ImageService imageService;

    @RequestMapping("images-top10")
    public String loadHomePage(Model m) {

        List<Image> topTenImagesList = imageService.getTopTenUpvotedImages();
        m.addAttribute("images", topTenImagesList);

        return "images-top10";
    }
}
