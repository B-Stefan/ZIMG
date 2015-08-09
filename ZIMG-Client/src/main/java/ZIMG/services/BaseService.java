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
package ZIMG.services;


import ZIMG.exceptions.NoItemsOnThisPageException;
import ZIMG.models.BaseModel;
import ZIMG.models.Tag;
import ZIMG.persistence.repositories.BaseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

/**
 * The base Service for all addition services
 * @param <M> The model class
 * @param <G> The repository class
 */
public abstract class BaseService<M extends BaseModel ,G extends BaseRepository<M>> {

    @Autowired
    protected G repository;

    /**
     * Geht a item by id and throws error if not found
     * @param id the id to find
     * @return The item of type M
     * @throws NotFoundException
     */
    public M getById(String id) throws NotFoundException{
        try{
            return repository.findOne(Long.parseLong(id));
        }catch (Exception e){
            throw  new NotFoundException("The id " + id + "  in service " + this.getClass().getName()  + " is not a valid id ");
        }
    }

    /**
     *
     * @param item
     * @return
     */
    public M create(M item){
        return this.save(item);
    }

    /**
     *
     * @param item
     * @return
     */
    public M save(M item){
        return this.repository.saveAndFlush(item);
    }

    /**
     *
     * @param item
     */
    public void update(M item){
        this.repository.saveAndFlush(item);
    }

    /**
     * General delte method
     * @param item
     */
    public void delete(M item){
        this.repository.delete(item);
    }

    /**
     * Method for delete by id
     * @param id
     * @throws NotFoundException
     */
    @Transactional
    public void delete(String id ) throws NotFoundException{
        M item = this.getById(id);
        this.delete(item);
    }

    /**
     * Methdo for getting a spesific page
     * @param pageable
     * @return
     */
    public Page<M> findAll(Pageable pageable) throws NoItemsOnThisPageException{

        org.springframework.data.domain.Page result = this.repository.findAll(pageable);
        if(result.getTotalPages() < pageable.getPageNumber()){
            throw new NoItemsOnThisPageException(pageable.getPageNumber());
        }

        return result;
    }
}
