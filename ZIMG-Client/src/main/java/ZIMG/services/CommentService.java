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
package ZIMG.services;

import ZIMG.exceptions.CommentConstrainsException;
import ZIMG.models.Comment;
import ZIMG.models.Image;
import ZIMG.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Service for all comments
 */
@Service
@Scope("prototype")
@Transactional
public class CommentService extends BaseService<Comment,CommentRepository> {

    private static final int MIN_LENGTH = 1;
    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    /**
     * Save a comment for an image id , check the comment content
     * @param comment The new comment
     * @param imgId The image id
     * @throws CommentConstrainsException
     */
    public void save(String comment, String imgId) throws CommentConstrainsException{
        if(comment.length() < MIN_LENGTH){
            throw new CommentConstrainsException(comment,MIN_LENGTH);
        }
        Image img = imageService.getImageById(imgId);
        Comment newComm  = new Comment();
        newComm.setImage(img);
        newComm.setComment(comment);
        newComm.setUser(userService.getCurrentUser());
        super.save(newComm);
    }

}
