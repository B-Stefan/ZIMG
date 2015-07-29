package ZIMG.persistence.services;

import ZIMG.exceptions.FavoriteAlreadyExistException;
import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.FavoriteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class FavoriteService extends  BaseService<Favorite,FavoriteRepository>  {

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;
    public List<Favorite> getFavorites() {
        return this.repository.findFavorites();
    }

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
        return this.addFavorite(image,currentUser);
    }
    private Favorite addFavorite(String imageId, String userId) throws NotFoundException, FavoriteAlreadyExistException{

        final Image image = this.imageService.getById(imageId);
        final User  user = this.userService.getById(userId);

        return this.addFavorite(image,user);
    }
    private Favorite addFavorite(Image image, User user) throws FavoriteAlreadyExistException{

        Favorite f = new Favorite();
        f.setUser(user);
        f.setImage(image);

        List<Favorite> fafs = this.getFavoritesByUser(user);
        if(fafs.contains(f)){
            throw new FavoriteAlreadyExistException(user,image);
        }

        return super.save(f);
    }
}
