USE zimg;

CREATE VIEW last_upvotes AS
	SELECT images.fileName, users.name, upvotes.createdAt
	FROM upvotes
	INNER JOIN images on images.id = upvotes.imageid
	INNER JOIN users on users.id = upvotes.userid
	WHERE upvotes.createdAt > (NOW() - INTERVAL 10 DAY);

CREATE VIEW top_ten_tags AS
	SELECT COUNT(*) AS tag2image, tags.tag, tags.createdAt
	FROM tag2image, tags
	WHERE tags.id = tag2image.tagId
	GROUP BY tag2image.tagId
	ORDER BY COUNT(*) DESC
	LIMIT 10;

CREATE VIEW top_ten_images AS
	SELECT COUNT(*) AS favorites, images.fileName, images.createdAt
	FROM images, favorites
	WHERE images.id = favorites.imageId
	GROUP BY favorites.imageId
	ORDER BY COUNT(*) DESC
	LIMIT 10;

CREATE VIEW admins AS
    SELECT users.id, users.name, users.email, users.password, users.createdAt
    FROM users
    WHERE users.admin = 1;

CREATE VIEW top_uploader AS
	SELECT COUNT(*) AS images, users.name
	FROM images
	INNER JOIN users on images.uploaderId = users.id
	GROUP BY users.id
	ORDER BY COUNT(*) DESC
	LIMIT 5;