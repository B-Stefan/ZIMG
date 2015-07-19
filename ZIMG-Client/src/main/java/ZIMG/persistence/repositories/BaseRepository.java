package ZIMG.persistence.repositories;

import ZIMG.models.BaseModel;
import ZIMG.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BaseRepository<G extends BaseModel> extends JpaRepository<G,Long> {


}
