package ZIMG.models;

import javax.persistence.*;

@Entity
@Table(name="tags")
@Inheritance(strategy=InheritanceType.JOINED)
public class Tag extends BaseModel {


    @Column(length = 100)
    private String tag;

    public String getTag(){
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
