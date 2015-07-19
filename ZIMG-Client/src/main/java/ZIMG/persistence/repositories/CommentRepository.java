package ZIMG.persistence.repositories;

import ZIMG.models.Comment;
import ZIMG.models.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {

}
