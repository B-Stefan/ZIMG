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

/**
 * Class for a favorite entry
 */
@Entity
@Table(name="favorites")
@Inheritance(strategy=InheritanceType.JOINED)
public class Favorite extends BaseModel {

    /**
     * The user that belongs to the fav.
     */
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    /**
     * The faved image
     */
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

    /**
     * If the user and the image is the same the a fav are euqal
     * @param other
     * @return
     */
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
