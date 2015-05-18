USE zimg;

ALTER TABLE images
  MODIFY COLUMN uploaderid int NOT NULL,
  DROP FOREIGN KEY images_ibfk_1,
  DROP INDEX  fk_userid,

  ADD FOREIGN KEY fk_userid (uploaderid) REFERENCES users (id) ON DELETE CASCADE
;

ALTER TABLE comments
  MODIFY COLUMN userid int NOT NULL,

  DROP FOREIGN KEY comments_ibfk_1,
  DROP FOREIGN KEY comments_ibfk_2,

  DROP INDEX  fk_userid,
  DROP INDEX  fk_imageid,

  ADD FOREIGN KEY fk_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageid (imageid)  REFERENCES images(id) ON DELETE SET NULL
;

ALTER TABLE upvotes
  MODIFY COLUMN userid int NOT NULL,

  DROP FOREIGN KEY upvotes_ibfk_1,
  DROP FOREIGN KEY upvotes_ibfk_2,

  DROP INDEX  fk_userid,
  DROP INDEX  fk_imageid,

  ADD FOREIGN KEY fk_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageId (imageid)  REFERENCES images(id) ON DELETE SET NULL
;
ALTER TABLE favorites
  MODIFY COLUMN userid int NOT NULL,
  MODIFY COLUMN imageid int NOT NULL ,

  DROP FOREIGN KEY favorites_ibfk_1,
  DROP FOREIGN KEY favorites_ibfk_2,

  DROP INDEX  fk_userid,
  DROP INDEX  fk_imageid,


  ADD FOREIGN KEY fk_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageid (imageid)  REFERENCES images(id) ON DELETE CASCADE
;
ALTER TABLE tag2image

  MODIFY COLUMN tagid int NOT NULL ,
  MODIFY COLUMN imageid int NOT NULL ,

  DROP FOREIGN KEY tag2image_ibfk_1,
  DROP FOREIGN KEY tag2image_ibfk_2,


  DROP INDEX  fk_tagid,
  DROP INDEX  fk_imageid,

  ADD FOREIGN KEY fk_tagid (tagid)      REFERENCES tags (id)  ON DELETE CASCADE,
  ADD FOREIGN KEY fk_imageid (imageid)  REFERENCES images (id)ON DELETE CASCADE
;