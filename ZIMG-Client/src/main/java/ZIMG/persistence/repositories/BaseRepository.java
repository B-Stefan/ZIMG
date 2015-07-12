package ZIMG.persistence.repositories;

import ZIMG.models.BaseModel;
import org.springframework.data.repository.CrudRepository;


public interface BaseRepository<G extends BaseModel> extends CrudRepository<G,Long> {

}
