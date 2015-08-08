package ZIMG.services;

import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class ImageService extends BaseService<Image,ImageRepository> {

    @Autowired
    private UserService userService;

    public List<Image> getByUploader(User user) {
        return new ArrayList<Image>();
    }

    @Transactional
    public Image getImageById(String id) {
        Image image= this.repository.findOne(Long.parseLong(id));
        // Lazy loading nested lists
        image.getTags().size();
        image.getComments().size();
        image.getUpvotes().size();

        return image;
    }

    public List<Image> getLastFiveImages(User u){
        return this.repository.findTopFiveByUploaderOrderByCreatedAtDesc(u);
    }
    public Image create(MultipartFile file){

        Image image = new Image();
        image.setFilename(file.getOriginalFilename());

        image.setUploader(userService.getCurrentUser());
        return super.create(image);
    }
    public void addTag(Tag tag, String imageId){
        this.addTag(tag,this.getImageById(imageId));
    }
    public void addTag(Tag tag, Image image){
        image.addTag(tag);
        this.save(image);
    }
    public List<Image> getTopTenUpvotedImages() {
        final PageRequest p =  new PageRequest(0,10);
        return this.repository.findTopUpvotedImages(p).getContent();
    }

}
