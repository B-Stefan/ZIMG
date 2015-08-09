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

import ZIMG.exceptions.FavoriteAlreadyExistException;
import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.FavoriteRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class FavoriteService extends  BaseService<Favorite,FavoriteRepository>  {
    static Logger LOG = Logger.getLogger(FavoriteService.class);

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;


    public List<Favorite> getFavoritesByUser(User user) {
        return this.repository.getFavoritesByUser(user.getId());
    }

    /**
     * Add the imageid to the current user
     */
    public Favorite addFavorite(String imageId) throws NotFoundException,SecurityException, FavoriteAlreadyExistException{
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }
        return this.addFavorite(image, currentUser);
    }

    public void removeFavorite(String imageId) throws NotFoundException, SecurityException {
        final User currentUser = this.userService.getCurrentUser();
        final Image image = this.imageService.getById(imageId);
        if(currentUser == null){
            throw new SecurityException("Current user is null thats not allowed ");
        }

        this.removeFavorite(image, currentUser);
    }

    public Boolean isFavorite(Image image, User user) {
        List<Favorite> usersFavorites = this.getFavoritesByUser(user);
        LOG.log(Priority.DEBUG,"isFavorite");

        for (Favorite fav : usersFavorites) {
            LOG.log(Priority.DEBUG, fav.getImage().getFilename());

            if(fav.getImage().getId() == image.getId()) {
                return true;
            }
        }

        return false;
    }

    private Favorite addFavorite(String imageId, String userId) throws NotFoundException, FavoriteAlreadyExistException{

        final Image image = this.imageService.getById(imageId);
        final User  user = this.userService.getById(userId);

        return this.addFavorite(image, user);
    }

    private Favorite addFavorite(Image image, User user) throws FavoriteAlreadyExistException{

        Favorite f = new Favorite();
        f.setUser(user);
        f.setImage(image);

        List<Favorite> usersFavorites = this.getFavoritesByUser(user);

        for (Favorite fav : usersFavorites) {
            if(fav.getImage().getId() == image.getId()) {
                throw new FavoriteAlreadyExistException(user, image);
            }
        }

        return super.save(f);
    }

    private void removeFavorite(Image image, User user) {

        List<Favorite> usersFavorites = this.getFavoritesByUser(user);

        for (Favorite fav : usersFavorites) {
            if(fav.getImage().getId() == image.getId()) {
                super.delete(fav);
            }
        }
    }
}
