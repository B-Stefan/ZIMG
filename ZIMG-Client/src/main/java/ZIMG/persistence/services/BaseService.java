package ZIMG.persistence.services;


import ZIMG.models.BaseModel;
import ZIMG.persistence.repositories.BaseRepository;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class BaseService<M extends BaseModel ,G extends BaseRepository<M>> {

    @Autowired
    protected G repository;

    public M getById(String id) throws NotFoundException{
        try{
            return repository.findOne(Long.parseLong(id));
        }catch (Exception e){
            throw  new NotFoundException("The id " + id + "  in service " + this.getClass().getName()  + " is not a valid id ");
        }
    }
    public M create(M item){
        return this.save(item);
    }
    public M save(M item){
        return this.repository.saveAndFlush(item);
    }
    public void update(M item){
        this.repository.saveAndFlush(item);
    }
    public void delete(M item){
        this.repository.delete(item);
    }
    public Page<M> findAll(Pageable pageable){


        org.springframework.data.domain.Page result = this.repository.findAll(pageable);

        return result;
    }
}
