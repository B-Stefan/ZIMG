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

import ZIMG.models.Image;
import ZIMG.services.ImageService;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Controller for creating thumbnails for the imagePreview.tag
 */
@Controller
public class ThumbnailController extends BaseController {

    final static Logger LOG = Logger.getLogger(ThumbnailController.class);

    @Autowired
    ImageService imageService;

    @Autowired
    private ServletContext servletContext;

    /**
     * Get the type of the image
     * @param path The total file path with the file name in it
     * @return The file type (jpg | png | gif)
     */
    private String getImageType(String path){
        final String type;
        if(path.contains(".gif")){
            type = "gif";
        }else if(path.contains(".png")){
            type = "png";
        }else {
            type = "jpg";
        }
        return type;
    }

    /**
     * The get mehtod to render a high res image into a thumbnail
     * @param imageId the id of the image
     * @param response the http response object
     * @return
     */
    @RequestMapping(value = "thumbnail/{imageId}", method = { RequestMethod.GET })
    @ResponseBody
    public byte[] getImageThumbnail(@PathVariable("imageId") String imageId, HttpServletResponse response) {
        try {

            Image image= imageService.getById(imageId);
            String path = servletContext.getRealPath("/uploads/" + image.getFilename());
            InputStream imageInputStream = new FileInputStream(path);



            BufferedImage img = ImageIO.read(imageInputStream);

            response.setContentType("image/"+this.getImageType(path));

            int width=(int)(img.getWidth() * 0.5);
            int height=(int)(img.getHeight() * 0.5);

            if(this.getImageType(path).equals("gif") ||this.getImageType(path).equals("png") ){
                return getDataFromBufferedImage(img,path);
            }else {
                ResampleOp resampleOp = new ResampleOp(width,height);
                BufferedImage thumbnail = resampleOp.filter(img, null);
                return getDataFromBufferedImage(thumbnail,path);
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);

        }
    }

    /**
     * Convert a image into a byte array
     * @param thumbnail The image for converting
     * @param path The total path with file name
     * @return A array of bytes to render this into html
     * @throws IOException
     */
    private byte[] getDataFromBufferedImage(BufferedImage thumbnail,String path) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            ImageIO.write(thumbnail, this.getImageType(path), baos);
            baos.flush();
            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }
}
