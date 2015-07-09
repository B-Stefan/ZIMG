package ZIMG.persistence.services;


import ZIMG.models.BaseModel;
import ZIMG.persistence.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class BaseService<G extends BaseRepository<? extends BaseModel>> {

    @Autowired
    protected G repository;
}
