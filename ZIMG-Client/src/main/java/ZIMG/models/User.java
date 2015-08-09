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

    @Transient
    private List<Image> lastFiveImages;

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

    public List<Image> getLastFiveImages() {
        return lastFiveImages;
    }

    public void setLastFiveImages(List<Image> lastFiveImages) {
        this.lastFiveImages = lastFiveImages;
    }
}
