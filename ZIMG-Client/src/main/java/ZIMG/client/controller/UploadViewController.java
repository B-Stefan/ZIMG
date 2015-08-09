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
package ZIMG.client.controller;

import ZIMG.exceptions.FiletypeNotAcceptedException;
import ZIMG.exceptions.SpringRuntimeExceptionForUser;
import ZIMG.services.ImageService;
import ZIMG.services.UserService;
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

/**
 * Controller for the upload page
 */
@Controller
public class UploadViewController extends BaseController {

    public static final String JSP_PAGE_NAME = "upload";

    private static Logger log = Logger.getLogger(UserDetailViewController.class.getName());

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value=JSP_PAGE_NAME, method= RequestMethod.GET)
    public String uploadPage(Model m) {

        return JSP_PAGE_NAME;
    }

    /**
     * The post method for uploading a picture
     * @param file The multipart file from the form
     * @param m
     * @return
     */
    @RequestMapping(value=JSP_PAGE_NAME, method= RequestMethod.POST)
    public String handleUpload(@RequestParam("file") MultipartFile file, Model m) {

        if (!file.isEmpty()) {
            try {

                String path = servletContext.getRealPath("/uploads/" + file.getOriginalFilename());

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

                imageService.create(file);

                return "redirect:home";
            } catch (FiletypeNotAcceptedException e) {
                throw new SpringRuntimeExceptionForUser(e, SpringRuntimeExceptionForUser.TYPE.ERROR,JSP_PAGE_NAME);
            } catch (Exception e) {
                log.log(Priority.DEBUG, e.getMessage());
                throw new SpringRuntimeExceptionForUser(e);
            }
        } else {
            throw new SpringRuntimeExceptionForUser("Your file is empty", SpringRuntimeExceptionForUser.TYPE.ERROR,JSP_PAGE_NAME);
        }
    }
}