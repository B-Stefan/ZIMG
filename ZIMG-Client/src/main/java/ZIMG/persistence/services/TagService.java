package ZIMG.persistence.services;

import ZIMG.models.Tag;
import ZIMG.persistence.repositories.TagRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope("prototype")
@Transactional
public class TagService extends BaseService<Tag,TagRepository> {

    /**
     * Gibt die zehn meist verwendeten Tags als Liste zurück
     * @return List<Tag>
     */
    public List<Tag> getTopTenTags() {
        return this.repository.findTopTenTags();
    }
}
