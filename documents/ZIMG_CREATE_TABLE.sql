USE zimg;

#Drop old tables
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS upvotes;
DROP TABLE IF EXISTS tag2image;
SET FOREIGN_KEY_CHECKS = 1;


#Create table section
CREATE TABLE  IF NOT EXISTS users(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS images(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS tags(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS comments(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS upvotes(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS favorites(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);
CREATE TABLE  IF NOT EXISTS tag2image(
  id int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(id)
);


#Add all columns
ALTER TABLE users
  ADD COLUMN name VARCHAR(255),
  ADD COLUMN email VARCHAR(255) NOT NULL ,
  ADD COLUMN password VARCHAR(100) NOT NULL,
  ADD COLUMN createdat DATETIME NOT NULL ,
  ADD COLUMN admin bool DEFAULT FALSE
;

ALTER TABLE images
  ADD COLUMN uploaderid int NOT NULL,
  ADD COLUMN filename VARCHAR(500),
  ADD COLUMN createdat DATETIME NOT NULL ,
  ADD FOREIGN KEY fk_images_userid (uploaderid) REFERENCES users (id) ON DELETE CASCADE
;

ALTER TABLE tags
  ADD COLUMN tag VARCHAR(100),
  ADD COLUMN createdat DATETIME NOT NULL
;

ALTER TABLE comments
  ADD COLUMN comment VARCHAR(500) DEFAULT  '',
  ADD COLUMN userid int NOT NULL,
  ADD COLUMN imageid int,
  ADD COLUMN createdat DATETIME NOT NULL ,
  ADD FOREIGN KEY fk_comments_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_comments_imageid (imageid)  REFERENCES images(id) ON DELETE CASCADE
;

ALTER TABLE upvotes
  ADD COLUMN userid int NOT NULL,
  ADD COLUMN imageid int,
  ADD COLUMN createdat DATETIME NOT NULL ,
  ADD FOREIGN KEY fk_upvotes_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_upvotes_imageid (imageid)  REFERENCES images(id) ON DELETE SET NULL
;
ALTER TABLE favorites
  ADD COLUMN userid int NOT NULL,
  ADD COLUMN imageid int NOT NULL ,
  ADD COLUMN createdat DATETIME NOT NULL,
  ADD FOREIGN KEY fk_favorites_userid (userid)    REFERENCES users (id) ON DELETE CASCADE,
  ADD FOREIGN KEY fk_favorites_imageid (imageid)  REFERENCES images(id) ON DELETE CASCADE
;
ALTER TABLE tag2image
  ADD COLUMN tagid int NOT NULL ,
  ADD COLUMN imageid int NOT NULL ,
  ADD FOREIGN KEY fk_tag2image_tagid (tagid)      REFERENCES tags (id)  ON DELETE CASCADE,
  ADD FOREIGN KEY fk_tag2image_imageid (imageid)  REFERENCES images (id)ON DELETE CASCADE
;