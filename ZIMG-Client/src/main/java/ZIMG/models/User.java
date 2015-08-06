package ZIMG.models;

import javax.persistence.*;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends BaseModel {

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private boolean admin;

    @OneToMany(mappedBy = "uploader")
    private List<Image> images = new ArrayList<Image>();

    public User(){

    }
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public User(String email, String password, String name){
        this(email, password);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean getAdmin(){
        return this.admin;
    }

    public List<Image> getImages() {
        return images;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
