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

    @Override
    public boolean equals(Object other){

        if(other instanceof Favorite){
            Favorite otherFav = (Favorite) other;
            if(this.getImage() == null && otherFav.getImage() == null && this.getImage().getId() != otherFav.getImage().getId() ){
                return  false;
            }

            return !(this.getUser() == null && otherFav.getUser() == null && this.getUser().getId() != otherFav.getUser().getId());
        }
        return super.equals(other);
    }
}
