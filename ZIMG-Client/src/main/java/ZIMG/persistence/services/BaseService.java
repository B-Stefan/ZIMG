package ZIMG.persistence.services;


import ZIMG.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class BaseService<G extends CrudRepository<? extends BaseModel,Long>> {

    @Autowired
    protected G repository;
}
