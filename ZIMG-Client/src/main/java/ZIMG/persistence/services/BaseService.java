package ZIMG.persistence.services;


import ZIMG.models.BaseModel;
import ZIMG.persistence.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<M extends BaseModel ,G extends BaseRepository<M>> {

    @Autowired
    protected G repository;

    public void save(M item){
        this.repository.saveAndFlush(item);
    }
    public void update(M item){
        this.repository.saveAndFlush(item);
    }
    public void delete(M item){
        this.repository.delete(item);
    }
}
