package ZIMG.client.controller;

import ZIMG.exceptions.*;
import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.services.*;
import com.mysql.fabric.FabricCommunicationException;
import javassist.NotFoundException;
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
public class ImageDetailViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "image";
    static Logger LOG = Logger.getLogger(ImageDetailViewController.class);
    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    UpvoteService upvoteService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/"+JSP_PAGE_NAME+"/{imageId}", method= RequestMethod.GET)
    public String getImageDetailPage(@PathVariable String imageId, Model m) {

        Image image = imageService.getImageById(imageId);
        User user =  userService.getCurrentUser();

        m.addAttribute("image", image);

        if(image.getComments().size() == 0) {
            m.addAttribute("CommentHeadline", "No Comments");
        } else if (image.getComments().size() == 1){
            m.addAttribute("CommentHeadline", "1 Comment");
        } else {
            m.addAttribute("CommentHeadline", image.getComments().size() + " Comments");
        }

        if(favoriteService.isFavorite(image, user)) {
            m.addAttribute("isFavorite", true);
        } else {
            m.addAttribute("isFavorite", false);
        }

        if(upvoteService.hasAlreadyUpvoted(image, user)) {
            m.addAttribute("isUpvoted", true);
        } else {
            m.addAttribute("isUpvoted", false);
        }

        return JSP_PAGE_NAME;
    }
    @RequestMapping(value="/"+JSP_PAGE_NAME+"/{imageId}/{action}", method= RequestMethod.POST)
    public String addComment(@PathVariable String imageId,
                             @PathVariable String action,
                             @RequestParam(value = "comment", required = false) String commentStr,
                             @RequestParam(value = "tag", required = false) String tagStr,
                             Model m){

        if(action.equals("comment")) {
        // handle new comment here

            LOG.log(Priority.DEBUG, "NEW COMMENTSTR: " + commentStr);
            LOG.log(Priority.DEBUG, "FOR IMAGEID: " + imageId);

            try {
                commentService.save(commentStr, imageId);
            }catch (CommentConstrainsException e){
                throw new SpringRuntimeExceptionForUser(e);
            }

        } else if(action.equals("tag")) {
        // handle new tag here
            LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
            LOG.log(Priority.DEBUG, "FOR IMAGEID: " + imageId);

            Tag tag;

            try {
                tag = tagService.saveOrCreate(tagStr,imageId);

                LOG.log(Priority.DEBUG, "AlREADY TAG: " + tag);
            } catch (NotFoundException e) {
                LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
            }catch (TagConstrainsException e){
                throw new SpringRuntimeExceptionForUser(e);
            }

        } else if(action.equals("favorite")) {
            try{
                favoriteService.addFavorite(imageId);
            }catch (FavoriteAlreadyExistException e){
                throw new SpringRuntimeExceptionForUser(e, SpringRuntimeExceptionForUser.TYPE.WARNING,JSP_PAGE_NAME);
            }
            catch (NotFoundException e){
                throw new SpringRuntimeExceptionForUser(e);
            }

        } else if(action.equals("unfavorite")) {
            try {
                favoriteService.removeFavorite(imageId);
            } catch (NotFoundException e) {
                throw new SpringRuntimeExceptionForUser(e);
            }

        } else if(action.equals("upvote")) {
            try{
                upvoteService.addUpvote(imageId);
            }catch (UpvoteAlreadyExistException e){
                throw new SpringRuntimeExceptionForUser(e, SpringRuntimeExceptionForUser.TYPE.WARNING,JSP_PAGE_NAME);
            }
            catch (NotFoundException e){
                throw new SpringRuntimeExceptionForUser(e);
            }

        } else if(action.equals("unupvote")) {
            try {
                upvoteService.removeUpvote(imageId);
            } catch (NotFoundException e) {
                throw new SpringRuntimeExceptionForUser(e);
            }
        }

        return "redirect:/"+JSP_PAGE_NAME+"/" + imageId;
    }
}