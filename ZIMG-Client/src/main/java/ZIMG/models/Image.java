/*
 * ZIMG-JAVA - Game, Copyright 2015  Nils Oke S., Fabian J., Chris P., Stefan Bieliauskas  -  All Rights Reserved.
 * Hochschule Bremen - University of Applied Sciences
 *
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

 * Web:
 *     https://github.com/B-Stefan/ZIMG
 *
 */
package ZIMG.models;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * The class for the image entries
 */
@Entity
@Table(name="images")
@Inheritance(strategy=InheritanceType.JOINED)
@Transactional
public class Image extends BaseModel {

    /**
     * The uploader user
     */
    @ManyToOne
    @JoinColumn(name = "uploaderid")
    private User uploader;

    /**
     * The name of the image in the server file system.
     * This name has to be the same as the name of the image in the server file system!
     */
    @Column(nullable=false)
    private String filename;

    /**
     * List of tags for this image
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "tag2image",
                joinColumns = @JoinColumn(name = "imageid"),
                inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private List<Tag> tags = new ArrayList<Tag>();

    /**
     * All comments for this image
     */
    @OneToMany(mappedBy = "image")
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * All upvotes for this image
     */
    @OneToMany(mappedBy = "image")
    private List<Upvote> upvotes = new ArrayList<Upvote>();

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

    public List<Upvote> getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(List<Upvote> upvotes) {
        this.upvotes = upvotes;
    }
}
