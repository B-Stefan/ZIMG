package ZIMG.client.controller;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.Image;
import ZIMG.persistence.repositories.ImageRepository;
import ZIMG.persistence.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageDetailViewController {

    @Autowired
    ImageService imageService;

    @RequestMapping(value="/image/{imageId}", method= RequestMethod.GET)
    public String loadHomePage(@PathVariable String imageId, Model m) {

        Image image = imageService.getImageById(imageId);

        m.addAttribute("image", image);
        return "image";
    }
}