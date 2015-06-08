package ZIMG;

import javax.persistence.*;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String email;

    private String name;

    private String password;

    private Date createdAt;

//    private List<Image> images;

    public User() {
    }

    public User(String name, String email, String password, Date createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    @Column(name="id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="email", nullable=false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="password", nullable=false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

   /* public List<Image> getCommunityMemberships() {
        return images;
    }

    public void setCommunityMemberships(List<Image> images) {
        this.images = images;
    }*/
}
