package ZIMG.persistence.repositories;

import ZIMG.models.Favorite;
import ZIMG.models.Tag;
import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends BaseRepository<Favorite> {

    @Query(value = "SELECT * FROM favorites as f WHERE f.userid =:userId", nativeQuery =  true)
    List<Favorite> getFavoritesByUser(@Param("userId") long userId);
}
