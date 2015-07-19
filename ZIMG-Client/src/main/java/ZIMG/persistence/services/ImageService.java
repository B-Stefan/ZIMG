package ZIMG.persistence.services;

import ZIMG.models.Image;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class ImageService extends BaseService<Image,ImageRepository> {

    public List<Image> getByUploader(User user) {
        return new ArrayList<Image>();
    }

    @Transactional
    public Image getImageById(String id) {
        Image image= this.repository.findOne(Long.parseLong(id));
        // Lazy loading nested lists
        image.getTags().size();
        image.getComments().size();

        return image;
    }

    public List<Image> getTopTenImages() {
        return this.repository.findTopTenImages();
    }

}
