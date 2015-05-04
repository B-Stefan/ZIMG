
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
  FOREIGN KEY (userId) REFERENCES users (id),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS comments2Image(
  id int NOT NULL AUTO_INCREMENT,
  commentId int,
  imageId int,
  FOREIGN KEY (commentId) REFERENCES comments(id),
  FOREIGN KEY (imageId) REFERENCES images(id),
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS tag2Image(
  id int NOT NULL AUTO_INCREMENT,
  tagId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (tagId) REFERENCES tags (id),
  FOREIGN KEY (imageId) REFERENCES images (id),
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS upvote2Image(
  id int NOT NULL AUTO_INCREMENT,
  upvoteId int,
  imageId int,
  createdAt DATETIME,
  FOREIGN KEY (imageId) REFERENCES images(id),
  FOREIGN KEY (upvoteId) REFERENCES upvotes(id),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS upvotes(
  id int NOT NULL AUTO_INCREMENT,
  createdAt DATETIME,
  PRIMARY KEY(id)
);