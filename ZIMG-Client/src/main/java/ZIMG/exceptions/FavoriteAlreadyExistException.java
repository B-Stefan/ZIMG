package ZIMG.exceptions;


import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.models.User;

public class FavoriteAlreadyExistException extends Exception {

    public FavoriteAlreadyExistException(final User user, final Image img){
        super("The user " +user.getName()+ " has already the image " + img.getId() + " as fav");
    }

}
