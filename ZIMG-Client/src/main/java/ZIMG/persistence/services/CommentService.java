package ZIMG.persistence.services;

import ZIMG.exceptions.CommentConstrainsException;
import ZIMG.models.Comment;
import ZIMG.models.Image;
import ZIMG.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Scope("prototype")
@Transactional
public class CommentService extends BaseService<Comment,CommentRepository> {

    private static final int MIN_LENGTH = 1;
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

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
