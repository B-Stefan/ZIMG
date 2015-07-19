package ZIMG.persistence.services;

import ZIMG.models.Favorite;
import ZIMG.persistence.repositories.FavoriteRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class FavoriteService extends  BaseService<Favorite,FavoriteRepository>  {

    public List<Favorite> getFavorites() {
        return this.repository.findFavorites();
    }
}
