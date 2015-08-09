package ZIMG.models;

import javax.persistence.*;
import java.util.List;

/**
 * Tag entry class
 */
@Entity
@Table(name="tags")
@Inheritance(strategy=InheritanceType.JOINED)
public class Tag extends BaseModel {


    /**
     * List of images for this spesific tag
     */
    @ManyToMany
    @JoinTable( name = "tag2image",
            joinColumns = @JoinColumn(name = "tagid"),
            inverseJoinColumns = @JoinColumn(name = "imageid")
    )
    private List<Image> images;

    /**
     * The name of the tag
     */
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
