package ZIMG.persistence.repositories;

import ZIMG.models.Image;
import ZIMG.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends BaseRepository<Image> {


    @Query(value = "select i from Image as i order by size(i.upvotes) DESC")
    Page<Image> findTopUpvotedImages(Pageable pageable);

    List<Image> findTopFiveByUploaderOrderByCreatedAtDesc(User u);

}
