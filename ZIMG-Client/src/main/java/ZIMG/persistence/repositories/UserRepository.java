package ZIMG.persistence.repositories;

import java.util.List;

import ZIMG.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByName(String lastName);

    @Query("SELECT u from User as u where u.admin = true ")
    List<User> findAllAdmins();
}
