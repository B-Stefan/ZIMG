package ZIMG.persistence.repositories;

import ZIMG.models.Image;
import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends BaseRepository<Image> {


    @Query(value = "SELECT * FROM top_ten_images", nativeQuery =  true)
    List<Image> findTopTenImages();

    @Query("SELECT i FROM Image as i WHERE i.id =:id")
    List<Image> findById(@Param("id") int Id);
}
