package ZIMG.persistence.services;

import ZIMG.exceptions.FavoriteAlreadyExistException;
import ZIMG.exceptions.UpvoteAlreadyExistException;
import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.Upvote;
import ZIMG.models.User;
import ZIMG.persistence.repositories.FavoriteRepository;
import ZIMG.persistence.repositories.UpvoteRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class UpvoteService extends  BaseService<Upvote, UpvoteRepository>  {
    static Logger LOG = Logger.getLogger(UpvoteService.class);

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    /**
     * Add the imageid to the current user
     */
    public Upvote addUpvote(String imageId) throws NotFoundException, SecurityException, UpvoteAlreadyExistException {
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }
        return this.addUpvote(image, currentUser);
    }

    public void removeUpvote(String imageId) throws NotFoundException, SecurityException {
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }

        this.removeUpvote(image, currentUser);
    }

    public Boolean hasAlreadyUpvoted(Image image, User user) {
        List<Upvote> imagesUpvotes = image.getUpvotes();

        for (Upvote upvote : imagesUpvotes) {

            if(upvote.getUser().getId() == user.getId()) {
                return true;
            }
        }

        return false;
    }

    private Upvote addUpvote(Image image, User user) throws UpvoteAlreadyExistException {

        Upvote upvote = new Upvote();
        upvote.setUser(user);
        upvote.setImage(image);

        List<Upvote> imagesUpvote = image.getUpvotes();

        for (Upvote vote : imagesUpvote) {
            if(vote.getUser().getId() == user.getId()) {
                throw new UpvoteAlreadyExistException(user, image);
            }
        }

        return super.save(upvote);
    }

    private void removeUpvote(Image image, User user) {

        List<Upvote> imagesUpvote = image.getUpvotes();

        for (Upvote vote : imagesUpvote) {
            if(vote.getUser().getId() == user.getId()) {
                delete(vote);
            }
        }
    }
}
