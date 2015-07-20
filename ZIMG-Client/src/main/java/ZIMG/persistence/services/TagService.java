package ZIMG.persistence.services;

import ZIMG.exceptions.MultipleUserForUserNameExistException;
import ZIMG.models.Comment;
import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.models.User;
import ZIMG.persistence.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class TagService extends BaseService<Tag,TagRepository> {

    @Autowired
    private ImageService imageService;

    /**
     * Gibt die zehn meist verwendeten Tags als Liste zurück
     * @return List<Tag>
     */
    public List<Tag> getTopTenTags() {
        return this.repository.findTopTenTags();
    }

    public Tag getTagByTag(String tag) throws Exception {
        List<Tag> tagList = this.repository.getTagByTag(tag);

        if (tagList.size() == 0) {
            throw new Exception("Tag not found.");
        }
        return tagList.get(0);
    }

    public void save(String tag, String imgId) throws SecurityException{
        Image img = imageService.getImageById(imgId);
        Tag newTag  = new Tag();
        newTag.setTag(tag);
        super.save(newTag);
    }
}
