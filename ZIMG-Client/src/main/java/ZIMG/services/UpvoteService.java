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

import ZIMG.exceptions.UpvoteAlreadyExistException;
import ZIMG.models.Image;
import ZIMG.models.Upvote;
import ZIMG.models.User;
import ZIMG.persistence.repositories.UpvoteRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for upvote a image
 */
@Service
@Scope("prototype")
@Transactional
public class UpvoteService extends  BaseService<Upvote, UpvoteRepository>  {
    static Logger LOG = Logger.getLogger(UpvoteService.class);

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    /**
     * Add the imageid to the current user
     */
    public Upvote addUpvote(String imageId) throws NotFoundException, SecurityException, UpvoteAlreadyExistException {
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }
        return this.addUpvote(image, currentUser);
    }

    /**
     * Remove an upvote
     * @param imageId The image id
     * @throws NotFoundException of id not found
     * @throws SecurityException if user notted logged in
     */
    public void removeUpvote(String imageId) throws NotFoundException, SecurityException {
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }

        this.removeUpvote(image, currentUser);
    }

    /**
     * Check if the user has already upvoated
     * @param image The image
     * @param user The user
     * @return
     */
    public Boolean hasAlreadyUpvoted(Image image, User user) {
        List<Upvote> imagesUpvotes = image.getUpvotes();

        for (Upvote upvote : imagesUpvotes) {

            if(upvote.getUser().getId() == user.getId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Add the upvoate for a user and image
     * @param image Image class
     * @param user User class
     * @return
     * @throws UpvoteAlreadyExistException
     */
    private Upvote addUpvote(Image image, User user) throws UpvoteAlreadyExistException {

        Upvote upvote = new Upvote();
        upvote.setUser(user);
        upvote.setImage(image);

        List<Upvote> imagesUpvote = image.getUpvotes();

        for (Upvote vote : imagesUpvote) {
            if(vote.getUser().getId() == user.getId()) {
                throw new UpvoteAlreadyExistException(user, image);
            }
        }

        return super.save(upvote);
    }

    /**
     * Remove the upvoate
     * @param image
     * @param user
     */
    private void removeUpvote(Image image, User user) {

        List<Upvote> imagesUpvote = image.getUpvotes();

        for (Upvote vote : imagesUpvote) {
            if(vote.getUser().getId() == user.getId()) {
                delete(vote);
            }
        }
    }
}
