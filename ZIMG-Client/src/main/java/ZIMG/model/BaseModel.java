package ZIMG.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="createdat", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }
    public int getId(){
        return this.id;
    }
}
