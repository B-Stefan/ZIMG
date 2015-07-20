package ZIMG.persistence.services;

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

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    public void save(String comment, String imgId) throws SecurityException{
        Image img = imageService.getImageById(imgId);
        Comment newComm  = new Comment();
        newComm.setImage(img);
        newComm.setComment(comment);
        newComm.setUser(userService.getCurrentUser());
        super.save(newComm);
    }

}
