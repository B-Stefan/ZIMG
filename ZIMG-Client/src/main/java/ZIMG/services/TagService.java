package ZIMG.services;

import ZIMG.exceptions.TagConstrainsException;
import ZIMG.models.Image;
import ZIMG.models.Tag;
import ZIMG.persistence.repositories.TagRepository;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class TagService extends BaseService<Tag,TagRepository> {

    static Logger LOG = Logger.getLogger(TagService.class);

    private static final int MIN_TAG_LENGTH = 1;

    @Autowired
    private ImageService imageService;

    /**
     * Gibt die zehn meist verwendeten Tags als Liste zurück
     * @return List<Tag>
     */
    public List<Tag> getTopTenTags() {
        final PageRequest request= new PageRequest(0,10);
        final List<Tag> list =  this.repository.findTopTenTags(request).getContent();
        list.forEach(tag->tag.getImages().size());
        return list;
    }

    public Tag getTagByTag(String tag) throws NotFoundException {
        List<Tag> tagList = this.repository.getTagByTag(tag);

        if (tagList.size() == 0) {
            throw new NotFoundException("Tag '"+tag+"' not found.");
        }
        return tagList.get(0);
    }

    public Tag saveOrCreate(String tagStr, String imageId) throws NotFoundException,TagConstrainsException{
        Tag tag;
        try {
            tag = this.getTagByTag(tagStr);

            LOG.log(Priority.DEBUG, "AlREADY TAG: " + tagStr);
        } catch (NotFoundException e) {
            LOG.log(Priority.DEBUG, "NEW TAG: " + tagStr);
            tag = this.save(tagStr, imageId);
        }
        this.imageService.addTag(tag,imageId);
        return  tag;

    }
    public Tag save(String tag, String imgId) throws SecurityException,TagConstrainsException{
        if(tag.length() < MIN_TAG_LENGTH){
            throw new TagConstrainsException(tag,MIN_TAG_LENGTH);
        }
        Image img = imageService.getImageById(imgId);
        Tag newTag  = new Tag();
        newTag.setTag(tag);
        return super.save(newTag);
    }
}
