package ZIMG.client.controller;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.Comment;
import ZIMG.models.Image;
import ZIMG.persistence.repositories.ImageRepository;
import ZIMG.persistence.services.ImageService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
public class ImageDetailViewController {

    static Logger LOG = Logger.getLogger(ImageDetailViewController.class);
    @Autowired
    ImageService imageService;

    @RequestMapping(value="/image/{imageId}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String imageId, Model m) {

        Image image = imageService.getImageById(imageId);

    //    LOG.log(Priority.DEBUG,"Size of image tags " +  image.getTags().size());
        m.addAttribute("image", image);
        return "image";
    }
    @RequestMapping(value="/image/{imageId}", method= RequestMethod.POST)
    public String addComment(@PathVariable String imageId, @RequestParam("comment") String commentStr, Model m){

        Image img = imageService.getImageById(imageId);

        return "image";
    }
}