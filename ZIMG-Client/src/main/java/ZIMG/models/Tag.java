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
import java.util.List;

/**
 * Tag entry class
 */
@Entity
@Table(name="tags")
@Inheritance(strategy=InheritanceType.JOINED)
public class Tag extends BaseModel {


    /**
     * List of images for this spesific tag
     */
    @ManyToMany
    @JoinTable( name = "tag2image",
            joinColumns = @JoinColumn(name = "tagid"),
            inverseJoinColumns = @JoinColumn(name = "imageid")
    )
    private List<Image> images;

    /**
     * The name of the tag
     */
    @Column(length = 100)
    private String tag;

    public String getTag(){
        return this.tag;
    }
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
