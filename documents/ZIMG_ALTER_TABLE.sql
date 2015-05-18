USE zimg;

ALTER TABLE images
  DROP FOREIGN KEY images_ibfk_1,
  DROP INDEX  fk_user,

  ADD FOREIGN KEY fk_user (uploaderId) REFERENCES users (id) ON DELETE CASCADE
;

ALTER TABLE comments
  DROP FOREIGN KEY comments_ibfk_1,
  DROP FOREIGN KEY comments_ibfk_2,

  DROP INDEX  fk_userId,
  DROP INDEX  fk_imageId,

  ADD FOREIGN KEY fk_userId (userId)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageId (imageId)  REFERENCES images(id) ON DELETE SET NULL
;

ALTER TABLE upvotes
  DROP FOREIGN KEY upvotes_ibfk_1,
  DROP FOREIGN KEY upvotes_ibfk_2,

  DROP INDEX  fk_userId,
  DROP INDEX  fk_imageId,

  ADD FOREIGN KEY fk_userId (userId)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageId (imageId)  REFERENCES images(id) ON DELETE SET NULL
;
ALTER TABLE favorites
  DROP FOREIGN KEY favorites_ibfk_1,
  DROP FOREIGN KEY favorites_ibfk_2,

  DROP INDEX  fk_userId,
  DROP INDEX  fk_imageId,

  ADD FOREIGN KEY fk_userId (userId)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageId (imageId)  REFERENCES images(id) ON DELETE CASCADE
;
ALTER TABLE tag2image
  DROP FOREIGN KEY tag2image_ibfk_1,
  DROP FOREIGN KEY tag2image_ibfk_2,


  DROP INDEX  fk_tagId,
  DROP INDEX  fk_imageId,

  ADD FOREIGN KEY fk_tagId (tagId)      REFERENCES tags (id)  ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageId (imageId)  REFERENCES images (id)ON DELETE CASCADE
;