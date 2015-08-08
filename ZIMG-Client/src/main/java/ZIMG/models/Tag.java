package ZIMG.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tags")
@Inheritance(strategy=InheritanceType.JOINED)
public class Tag extends BaseModel {



    @ManyToMany
    @JoinTable( name = "tag2image",
            joinColumns = @JoinColumn(name = "tagid"),
            inverseJoinColumns = @JoinColumn(name = "imageid")
    )
    private List<Image> images;

    @Column(length = 100)
    private String tag;

    public String getTag(){
        return this.tag;
    }
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
