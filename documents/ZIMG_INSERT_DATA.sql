USE zimg;

INSERT INTO `users` (`name`, `email`, `password`, `createdat`, `admin`) VALUES
  ('Oklon', 'nischiwe@stud.hs-bremen.de', 'hallo123', NOW(), TRUE),
  ('Fabimon', 'fabimon@fabimon.org', 'password', NOW(), TRUE),
  ('SBI', 'stefan.bie@live.de', 'qwertz', NOW(), TRUE),
  ('ChrisLaDiDa', 'cpleines@stud.hs-bremen.de', 'werderbremen', NOW(), TRUE);

INSERT INTO `images` (`uploaderid`, `filename`, `createdat`) VALUES
  (1, 'not_nsfw_pic.jpg', NOW()),
  (2, 'suesse_robbenbabys_im_soester_zoo.jpg', NOW()),
  (3, 'Pokemon_asdbdnd.jpg', NOW()),
  (4, 'maidemo.jpg', NOW()),
  (3, 'werderhulls.jpg', NOW());

INSERT INTO `tags` (`tag`, `createdat`) VALUES
  ('cool', NOW()),
  ('nicht cool', NOW()),
  ('fail', NOW()),
  ('grün', NOW()),
  ('sichlor', NOW());

INSERT INTO `favorites` (`userid`, `imageid`, `createdat`) VALUES
  (1, 2, NOW()),
  (2, 3, NOW()),
  (3, 1, NOW()),
  (3, 4, NOW()),
  (2, 4, NOW());

INSERT INTO `tag2image` (`tagid`, `imageid`) VALUES
  (1, 4),
  (5, 3),
  (1, 2),
  (1, 5),
  (2, 3);

INSERT INTO `comments` (`comment`, `userid`, `imageid`, `createdat`) VALUES
  ('Voll die süße Robbe', 1, 2, NOW()),
  ('Jo voll süß. :) Die mag bestimmt voll den stinke Fisch', 4, 2, NOW()),
  ('Hoch die Hand!', 3, 4, NOW()),
  ('Viva Revolution', 4, 5, NOW()),
  ('Voll gut das Bild. :)', 1, 4, NOW());

INSERT INTO `upvotes` (`userid`, `imageid`, `createdat`) VALUES
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