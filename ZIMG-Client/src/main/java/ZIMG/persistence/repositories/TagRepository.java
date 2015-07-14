package ZIMG.persistence.repositories;

import ZIMG.models.Image;
import ZIMG.models.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends BaseRepository<Tag> {

    @Query(value = "SELECT * FROM top_ten_tags", nativeQuery =  true)
    List<Tag> findTopTenTags();

}
