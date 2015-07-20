package ZIMG.persistence.repositories;

import ZIMG.models.Tag;
import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag> {

    @Query(value = "SELECT * FROM top_ten_tags", nativeQuery =  true)
    List<Tag> findTopTenTags();

    @Query(value = "SELECT DISTINCT t FROM Tag AS t WHERE t.tag =:tag")
    List<Tag> getTagByTag(@Param("tag") String tag);
}
