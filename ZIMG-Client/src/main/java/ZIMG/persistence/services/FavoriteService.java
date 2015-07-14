package ZIMG.persistence.services;

import ZIMG.models.Favorite;
import ZIMG.persistence.repositories.FavoriteRepository;

import java.util.List;

public class FavoriteService extends  BaseService<Favorite,FavoriteRepository>  {

    public List<Favorite> getFavorites() {
        return this.repository.findFavorites();
    }
}
