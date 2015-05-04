USE zimg;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS upvotes;
DROP TABLE IF EXISTS tag2Image;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS users(
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(500),
  createdAt DATETIME,
  admin bool,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS images(
  id int NOT NULL AUTO_INCREMENT,
  uploaderId int,
  fileName VARCHAR(500),
  createdAt DATETIME,
  FOREIGN KEY (uploaderId) REFERENCES users (id),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tags(
  id int NOT NULL AUTO_INCREMENT,
  tag VARCHAR(500),
  createdAt DATETIME,
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS comments(
  id int NOT NULL AUTO_INCREMENT,
  comment VARCHAR(500),
  userId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (userId) REFERENCES users (id),
  FOREIGN KEY (imageId) REFERENCES images(id),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS upvotes(
  id int NOT NULL AUTO_INCREMENT,
  userId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (userId) REFERENCES users (id),
  FOREIGN KEY (imageId) REFERENCES images(id),
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS favorite(
  id int NOT NULL AUTO_INCREMENT,
  userId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (userId) REFERENCES users (id),
  FOREIGN KEY (imageId) REFERENCES images(id),
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS tag2image(
  id int NOT NULL AUTO_INCREMENT,
  tagId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (tagId) REFERENCES tags (id),
  FOREIGN KEY (imageId) REFERENCES images (id),
  PRIMARY KEY(id)
);