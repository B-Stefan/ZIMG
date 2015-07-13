package ZIMG.persistence.repositories;

import java.util.List;

import ZIMG.models.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT DISTINCT u FROM User as u JOIN FETCH u.images WHERE u.name =:name")
    List<User> findByName(@Param("name") String name);

    @Query("SELECT u from User as u where u.admin = true ")
    List<User> findAllAdmins();

}
