package ZIMG.models;

import javax.persistence.*;

@Entity
@Table(name="upvotes")
@Inheritance(strategy=InheritanceType.JOINED)
public class Upvote extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "imageid")
    private Image image;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
