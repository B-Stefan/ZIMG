package ZIMG.models;

import javax.persistence.*;

/**
 * Comment class
 */
@Entity
@Table(name="comments")
@Inheritance(strategy=InheritanceType.JOINED)
public class Comment extends BaseModel {


    /**
     * The comment as string
     */
    @Column(length = 500)
    private String comment;

    /**
     * The creator of the comment
     */
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    /**
     * The image that the comment belongs to
     */
    @ManyToOne
    @JoinColumn(name = "imageid")
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
