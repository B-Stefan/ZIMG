package ZIMG.client.controller;

import ZIMG.models.Favorite;
import ZIMG.models.Image;
import ZIMG.persistence.services.FavoriteService;
import ZIMG.persistence.services.ImageService;
import ZIMG.persistence.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FavoritesController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @RequestMapping("favorites")
    public String loadFavorites(Model m) {

        List<Favorite> favorites = favoriteService.getFavoritesByUser(userService.getCurrentUser());

        m.addAttribute("favorites", favorites);

        return "favorites";
    }
}
