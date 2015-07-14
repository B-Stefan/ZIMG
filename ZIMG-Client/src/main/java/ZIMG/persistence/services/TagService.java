package ZIMG.persistence.services;

import ZIMG.models.Tag;
import ZIMG.persistence.repositories.TagRepository;

import java.util.List;

/**
 *
 */
public class TagService extends BaseService<Tag,TagRepository> {

    /**
     * Gibt die zehn meist verwendeten Tags als Liste zurück
     * @return List<Tag>
     */
    public List<Tag> getTopTenTags() {
        return this.repository.findTopTenTags();
    }
}
