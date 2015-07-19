package ZIMG.persistence.services;


import ZIMG.models.BaseModel;
import ZIMG.persistence.repositories.BaseRepository;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<M extends BaseModel ,G extends BaseRepository<M>> {

    @Autowired
    protected G repository;

    public void getById(String id) throws NotFoundException{
        try{
            repository.findOne(Long.parseLong(id));
        }catch (Exception e){
            throw  new NotFoundException("The id " + id + "  in service " + this.getClass().getName()  + " is not a valid id ");
        }
    }
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
