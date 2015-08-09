package ZIMG.services;

import ZIMG.exceptions.CommentConstrainsException;
import ZIMG.models.Comment;
import ZIMG.models.Image;
import ZIMG.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Service for all comments
 */
@Service
@Scope("prototype")
@Transactional
public class CommentService extends BaseService<Comment,CommentRepository> {

    private static final int MIN_LENGTH = 1;
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    /**
     * Save a comment for an image id , check the comment content
     * @param comment The new comment
     * @param imgId The image id
     * @throws CommentConstrainsException
     */
    public void save(String comment, String imgId) throws CommentConstrainsException{
        if(comment.length() < MIN_LENGTH){
            throw new CommentConstrainsException(comment,MIN_LENGTH);
        }
        Image img = imageService.getImageById(imgId);
        Comment newComm  = new Comment();
        newComm.setImage(img);
        newComm.setComment(comment);
        newComm.setUser(userService.getCurrentUser());
        super.save(newComm);
    }

}
