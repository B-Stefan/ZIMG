package ZIMG.persistence.repositories;

import ZIMG.models.Favorite;
import ZIMG.models.Tag;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends BaseRepository<Favorite> {

    @Query(value = "SELECT * FROM users", nativeQuery =  true)
    List<Favorite> findFavorites();

}
