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

@Controller
public class ThumbnailController extends BaseController {

    final static Logger LOG = Logger.getLogger(ThumbnailController.class);
    @Autowired
    ImageService imageService;

    @Autowired
    private ServletContext servletContext;

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
