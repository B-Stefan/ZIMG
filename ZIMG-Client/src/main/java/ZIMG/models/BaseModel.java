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
import java.util.Date;

/**
 * Base class for all models
 */
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="createdat", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }
    public long getId(){
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Two BaseModels arre equal if the id is the same
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(other instanceof BaseModel){
            return  this.id == ((BaseModel) other).getId();
        }
        return super.equals(other);
    }
}
