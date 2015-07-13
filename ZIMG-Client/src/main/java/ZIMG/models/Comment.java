package ZIMG.models;

import javax.persistence.*;

@Entity
@Table(name="comments")
@Inheritance(strategy=InheritanceType.JOINED)
public class Comment extends BaseModel {

    @Column(length = 500)
    private String comment;

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