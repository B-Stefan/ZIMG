SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `users`(`name`,`email`,`password`, `createdAt`, `admin`) VALUES
	('Oklon','nischiwe@stud.hs-bremen.de', 'hallo123', NOW(), true),
    ('Fabimon','fabimon@fabimon.org', 'password', NOW(), true),
    ('SBI','stefan.bie@live.de', 'qwertz', NOW(), true),
    ('ChrisLaDiDa','cpleines@stud.hs-bremen.de', 'werderbremen', NOW(), true);
    
INSERT INTO `images`(`uploaderId`, `fileName`, `createdAt`) VALUES
	(1, 'not_nsfw_pic.jpg', NOW()),
    (2, 'suesse_robbenbabys_im_soester_zoo.jpg', NOW()),
    (3, 'Pokemon_asdbdnd.jpg', NOW()),
    (4, 'maidemo.jpg', NOW()),
	(5, 'werderhulls.jpg', NOW());
    
INSERT INTO `tags`(`tag`, `createdAt`) VALUES
	('cool', NOW()),
    ('nicht cool', NOW()),
    ('fail', NOW()),
    ('grün', NOW()),
    ('sichlor', NOW());

INSERT INTO `favorite`(`userId`, `imageId`, `createdAt`) VALUES
    (1, 2, NOW()),
    (2, 3, NOW()),
    (3, 1, NOW()),
    (3, 4, NOW()),
    (2, 4, NOW());
    
INSERT INTO `tag2image`(`tagId`, `imageId`, `createdAt`) VALUES
	(1, 4, NOW()),
    (5, 3, NOW()),
    (1, 2, NOW()),
    (1, 5, NOW()),
    (2, 3, NOW());
    
INSERT INTO `comments`(`comment`, `userId`,  `imageId`, `createdAt`) VALUES
	('Voll die süße Robbe', 1, 2, NOW()),
    ('Jo voll süß. :) Die mag bestimmt voll den stinke Fisch', 4, 2, NOW()),
    ('Hoch die Hand!', 3, 4, NOW()),
    ('Viva Revolution', 4, 5, NOW()),
    ('Voll gut das Bild. :)', 0, 4, NOW());
    
INSERT INTO `upvotes`(`userId`, `imageId`, `createdAt`) VALUES
	(1, 1, NOW()),
    (1, 2, NOW()),
    (1, 3, NOW()),
    (1, 4, NOW()),
    (1, 5, NOW()),
    (2, 4, NOW()),
    (2, 5, NOW()),
	(3, 4, NOW()),
    (3, 5, NOW()),
	(4, 4, NOW()),
    (4, 5, NOW()); 
    
SET FOREIGN_KEY_CHECKS = 1;
