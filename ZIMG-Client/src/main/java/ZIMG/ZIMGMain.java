package ZIMG;

import ZIMG.model.Image;
import ZIMG.model.User;
import ZIMG.services.ImageService;
import ZIMG.services.UserService;

import java.util.List;

public class ZIMGMain {


    public static void main(String[] args) {



        ImageService imageService = new ImageService();
        UserService userService = new UserService();

        User firstUser = userService.getUserByName("Oklon");
        List<Image> images = imageService.getByUploader(firstUser);

        System.out.print( firstUser.getName() + " uploaded "  + images.size() + "images ");

    }

}
