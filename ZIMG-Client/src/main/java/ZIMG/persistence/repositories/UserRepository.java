package ZIMG.persistence.repositories;

import java.util.List;

import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends BaseRepository<User> {

    List<User> findByName(String lastName);
    User fiEqualsName(String lastName);
    User fIsStartingWithName(String lastName);
    List<User> findByEmail(String email);
    List<User> IsTrueAdmin(boolean admin);

    @Query("SELECT u from User as u where u.admin = true ")
    List<User> findAllAdmins();

}
