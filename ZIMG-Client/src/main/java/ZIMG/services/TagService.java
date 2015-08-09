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

import ZIMG.exceptions.TagConstrainsException;
import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.repositories.TagRepository;
import ZIMG.services.security.SecurityUser;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Tag service
 */
@Service
@Scope("prototype")
@Transactional
public class TagService extends BaseService<Tag,TagRepository> {

    static Logger LOG = Logger.getLogger(TagService.class);

    private static final int MIN_TAG_LENGTH = 1;

    @Autowired
    private ImageService imageService;


    @Autowired
    private UserService userService;


    /**
     * Delte the tag
     * @param t tag
     */
    public void delete(final Tag t){
        final SecurityUser currentUser = this.userService.getCurrentUser();
        if(currentUser!= null && currentUser.isUserInRolePresent("ROLE_ADMIN")){
            super.delete(t);
        }else {
            throw new SecurityException("You have no permissions for deleting a tag");
        }
    }

    /**
     * Geht the top ten Tag
     * @return Top ten Tags
     */
    public List<Tag> getTopTenTags() {
        final PageRequest request= new PageRequest(0,10);
        final List<Tag> list =  this.repository.findTopTenTags(request).getContent();
        list.forEach(tag->tag.getImages().size());
        return list;
    }

    /**
     * Get the tag by name of the tag
     * @param tag the nmae of the tag
     * @return The tagg class instance
     * @throws NotFoundException if name not found
     */
    public Tag getTagByTag(String tag) throws NotFoundException {
        List<Tag> tagList = this.repository.getTagByTag(tag);

        if (tagList.size() == 0) {
            throw new NotFoundException("Tag '"+tag+"' not found.");
        }
        return tagList.get(0);
    }

    /**
     * Get the tag by the name and load the images for this tag
     * @param tag the name of the tag
     * @return
     * @throws NotFoundException
     */
    @Transactional
    public Tag getTagByTagAndLoadImages(String tag) throws  NotFoundException{
        final Tag t = this.getTagByTag(tag);
        t.getImages().size();
        return t;
    }

    /**
     * Create or save a new tag for a image
     * @param tagStr The new or old tag str
     * @param imageId the image id
     * @return
     * @throws NotFoundException
     * @throws TagConstrainsException
     */
    public Tag saveOrCreate(String tagStr, String imageId) throws NotFoundException,TagConstrainsException{
        Tag tag;
        try {
            tag = this.getTagByTag(tagStr);

            LOG.log(Priority.DEBUG, "AlREADY TAG: " + tagStr);
        } catch (NotFoundException e) {
            LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
            tag = this.save(tagStr, imageId);
        }
        this.imageService.addTag(tag,imageId);
        return  tag;

    }

    /**
     * Save the tag string for the image
     * @param tag the name of the tag
     * @param imgId the image id
     * @return
     * @throws SecurityException
     * @throws TagConstrainsException
     */
    public Tag save(String tag, String imgId) throws SecurityException,TagConstrainsException, NotFoundException{
        if(tag.length() < MIN_TAG_LENGTH){
            throw new TagConstrainsException(tag,MIN_TAG_LENGTH);
        }
        Image img = imageService.getImageById(imgId);
        Tag newTag  = new Tag();
        newTag.setTag(tag);
        return super.save(newTag);
    }
}
