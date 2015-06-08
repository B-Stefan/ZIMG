package ZIMG;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fabian on 25.05.15.
 */
@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue
    private int id;

    private User uploader;

    private String filename;

    private Date createdAt;

    public Image() {
    }

    public Image(User uploader, String filename, Date createdAt) {
        this.uploader = uploader;
        this.filename = filename;
        this.createdAt = createdAt;
    }

    @Column(name="id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="uploaderid", nullable=false)
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @Column(name="filename", nullable=false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name="createdat", nullable=false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
