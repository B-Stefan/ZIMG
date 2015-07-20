package ZIMG.models;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="images")
@Inheritance(strategy=InheritanceType.JOINED)
@Transactional
public class Image extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "uploaderid")
    private User uploader;

    @Column(nullable=false)
    private String filename;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "tag2image",
                joinColumns = @JoinColumn(name = "imageid"),
                inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private List<Tag> tags = new ArrayList<Tag>();

    @OneToMany(mappedBy = "image")
    private List<Comment> comments = new ArrayList<Comment>();

    public String getFilename() {
        return filename;
    }
    public List<Tag> getTags(){
        return this.tags;
    }
    public User getUploader(){
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
