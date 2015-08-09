package ZIMG.persistence.repositories;

import ZIMG.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Base repro. for additional methods
 * @param <G>
 */
public interface BaseRepository<G extends BaseModel> extends JpaRepository<G,Long> {

}
