package ZIMG.persistence.repositories;

import ZIMG.models.Tag;
import ZIMG.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag> {

    @Query(value = "SELECT t FROM Tag as t order by size(t.images) DESC ")
    Page<Tag> findTopTenTags(Pageable pageable);

    @Query(value = "SELECT DISTINCT t FROM Tag AS t WHERE t.tag =:tag")
    List<Tag> getTagByTag(@Param("tag") String tag);
}
