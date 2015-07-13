package ZIMG.client.controller;

import ZIMG.models.Image;
import ZIMG.persistence.services.ImageService;
import ZIMG.persistence.services.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class UploadViewController {

    static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String uploadPage(Model m) {

        return "upload";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public String handleUpload(@RequestParam("uploadname") String name, @RequestParam("file") MultipartFile file, Model m) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("/Users/fabian/Development/ZIMG/ZIMG-Client/src/main/webapp/resources/upload/" +file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                log.log(Priority.DEBUG, "hallo");

                Image image = new Image();
                image.setFilename(file.getOriginalFilename());
                image.setUploader(userService.getUserByName("Oklon"));

                imageService.save(image);
                return "redirect:home";
            } catch (Exception e) {
                log.log(Priority.DEBUG, e.getMessage());
                return "redirect:home";
            }
        } else {
            return "redirect:home";
        }
    }
}