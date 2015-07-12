package ZIMG;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.services.ImageService;
import ZIMG.persistence.services.UserService;
import jdk.nashorn.internal.runtime.ECMAException;


import java.util.List;

public class ZIMGMain {


    public static void main(String[] args) {



        ImageService imageService = new ImageService();
        UserService userService = new UserService();

        try {
            User firstUser = userService.getUserByName("Oklon");
        }catch (Exception e){

        }

    }

}
