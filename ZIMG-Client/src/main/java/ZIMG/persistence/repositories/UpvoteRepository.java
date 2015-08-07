package ZIMG.persistence.repositories;

import ZIMG.models.Favorite;
import ZIMG.models.Tag;
import ZIMG.models.Upvote;
import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UpvoteRepository extends BaseRepository<Upvote> {

}
