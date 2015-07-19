package ZIMG.persistence.repositories;

import java.util.List;

import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT DISTINCT u FROM User as u JOIN FETCH u.images WHERE u.name =:name")
    List<User> findByName(@Param("name") String name);

    @Query("SELECT u from User as u where u.admin = true ")
    List<User> findAllAdmins();

    // @Query(value = "SELECT COUNT(*) AS images, users.name FROM images INNER JOIN users on images.uploaderId = users.id GROUP BY users.id ORDER BY COUNT(*) LIMIT 5", /*nativeQuery =  true*/)
    @Query(value = "SELECT * FROM top_ten_tags", nativeQuery =  true)
    List<User> findTopFiveUsers();

    User findOneByEmail(String email);

    User findOneByName(String name);

//    CREATE VIEW top_uploader AS
//    SELECT COUNT(*) AS images, users.name
//    FROM images
//    INNER JOIN users on images.uploaderId = users.id
//    GROUP BY users.id
//    ORDER BY COUNT(*) DESC
//    LIMIT 5;

}
