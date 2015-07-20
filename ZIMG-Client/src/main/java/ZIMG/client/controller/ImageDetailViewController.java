package ZIMG.client.controller;

import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.services.*;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@EnableWebSecurity
public class ImageDetailViewController {

    static Logger LOG = Logger.getLogger(ImageDetailViewController.class);
    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value="/image/{imageId}", method= RequestMethod.GET)
    public String getImageDetailPage(@PathVariable String imageId, Model m) {

        Image image = imageService.getImageById(imageId);

    //    LOG.log(Priority.DEBUG,"Size of image tags " +  image.getTags().size());
        m.addAttribute("image", image);

        if(image.getComments().size() == 0) {
            m.addAttribute("CommentHeadline", "No Comments");
        } else if (image.getComments().size() == 1){
            m.addAttribute("CommentHeadline", "1 Comment");
        } else {
            m.addAttribute("CommentHeadline", image.getComments().size() + " Comments");
        }

        return "image";
    }
    @RequestMapping(value="/image/{imageId}/{action}", method= RequestMethod.POST)
    public String addComment(@PathVariable String imageId,
                             @PathVariable String action,
                             @RequestParam(value = "comment", required = false) String commentStr,
                             @RequestParam(value = "tag", required = false) String tagStr,
                             Model m){

        if(action.equals("comment")) {
        // handle new comment here

            LOG.log(Priority.DEBUG, "NEW COMMENTSTR: " + commentStr);
            LOG.log(Priority.DEBUG, "FOR IMAGEID: " + imageId);

            if (!commentStr.isEmpty()) {
                commentService.save(commentStr, imageId);
            } else {
                // @todo: show error message, because comment is to short.
            }
        } else if(action.equals("tag")) {
        // handle new tag here
            LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
            LOG.log(Priority.DEBUG, "FOR IMAGEID: " + imageId);

            Tag tag;

            try {
                tag = tagService.getTagByTag(tagStr);

                LOG.log(Priority.DEBUG, "AlREADY TAG: " + tag);
            } catch (Exception e) {
                LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
                e.printStackTrace();

                if(!tagStr.isEmpty()) {
                    tagService.save(tagStr, imageId);
                } else {
                    // @todo: show error message, because tag is to short.
                }
            }

            try {
                tag = tagService.getTagByTag(tagStr);

                Image image = imageService.getImageById(imageId);

                List<Tag> tags = image.getTags();
                tags.add(tag);
                image.setTags(tags);

                imageService.save(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(action.equals("favorite")) {
            User currentUser = userService.getCurrentUser();
            Image image = imageService.getImageById(imageId);

            Favorite favorite = new Favorite();
            favorite.setUser(currentUser);
            favorite.setImage(image);

            favoriteService.save(favorite);
        }

        return "redirect:/image/" + imageId;
    }
}