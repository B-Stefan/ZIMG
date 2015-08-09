package ZIMG.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Base class for all models
 */
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="createdat", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }
    public long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Two BaseModels arre equal if the id is the same
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof BaseModel){
            return  this.id == ((BaseModel) other).getId();
        }
        return super.equals(other);
    }
}
