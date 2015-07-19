package ZIMG.persistence.repositories;

import ZIMG.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BaseRepository<G extends BaseModel> extends JpaRepository<G,Long> {

}
