package ZIMG.persistence.services;

import ZIMG.models.Image;
import ZIMG.models.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class ImageService extends BaseService {

    public List<Image> getByUploader(User user) {

        return new ArrayList<Image>();
    }


}
