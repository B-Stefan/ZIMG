package ZIMG.persistence.repositories;

import ZIMG.models.Image;
import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends BaseRepository<Image> {


    @Query(value = "SELECT * FROM top_ten_images", nativeQuery =  true)
    List<Image> findTopTenImages();


}
