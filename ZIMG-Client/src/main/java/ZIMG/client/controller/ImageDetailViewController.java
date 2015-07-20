package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.persistence.services.CommentService;
import ZIMG.persistence.services.ImageService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
@EnableWebSecurity
public class ImageDetailViewController {

    static Logger LOG = Logger.getLogger(ImageDetailViewController.class);
    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value="/image/{imageId}", method= RequestMethod.GET)
    public String getImageDetailPage(@PathVariable String imageId, Model m) {

        Image image = imageService.getImageById(imageId);

    //    LOG.log(Priority.DEBUG,"Size of image tags " +  image.getTags().size());
        m.addAttribute("image", image);
        return "image";
    }
    @RequestMapping(value="/image/{imageId}", method= RequestMethod.POST)
    public String addComment(@PathVariable String imageId, @RequestParam("comment") String commentStr, Model m){

        LOG.log(Priority.DEBUG,"NEW COMMENTSTR: " + commentStr);
        LOG.log(Priority.DEBUG,"FOR IMAGEID: " + imageId);

        commentService.save(commentStr, imageId);
        return "redirect:/image/" + imageId; //this.getImageDetailPage(imageId,m);
    }
}