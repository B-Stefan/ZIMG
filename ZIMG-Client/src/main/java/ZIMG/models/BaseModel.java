package ZIMG.models;

import javax.persistence.*;
import java.util.Date;

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
}
