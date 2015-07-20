package ZIMG.client.controller;

import ZIMG.exceptions.FiletypeNotAcceptedException;
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

import javax.servlet.ServletContext;
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

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String uploadPage(Model m) {

        return "upload";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public String handleUpload(@RequestParam("file") MultipartFile file, Model m) {

        if (!file.isEmpty()) {
            try {

                String path = servletContext.getRealPath("/resources/upload/" + file.getOriginalFilename());

                if (file.getContentType().equals("image/jpeg") ||
                        file.getContentType().equals("image/gif") ||
                        file.getContentType().equals("image/png")
                ) {
                    log.log(Priority.DEBUG, "Filetype accepted");
                } else {
                    throw new FiletypeNotAcceptedException(file.getContentType());
                }

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.close();

                Image image = new Image();
                image.setFilename(file.getOriginalFilename());

                image.setUploader(userService.getCurrentUser());

                imageService.save(image);
                return "redirect:home";
            } catch (FiletypeNotAcceptedException e) {
                return "redirect:upload?error=yes";
            } catch (Exception e) {
                log.log(Priority.DEBUG, e.getMessage());
                return "redirect:home";
            }
        } else {
            return "redirect:home";
        }
    }
}