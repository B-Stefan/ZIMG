package ZIMG.models;

import javax.persistence.*;

@Entity
@Table(name="favorites")
@Inheritance(strategy=InheritanceType.JOINED)
public class Favorite extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "imageid")
    private Image image;

    public Image getImage(){
        return this.image;
    }

    public User getUser(){
        return this.user;
    }
}
