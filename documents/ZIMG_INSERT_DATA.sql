USE zimg;

INSERT INTO `users` (`name`, `email`, `password`, `createdat`, `admin`) VALUES
  ('Oklon', 'okeschwien@gmail.com', '$2a$10$3kauVegeThAtuCWLzeZ3IO2HNpiQyEa0s7RkOfoNaBf2gwuc0cj2C', NOW(), TRUE),
  ('faBimon', 'fabimon@fabimon.org', '$2a$10$7MXD/vxUqxfQuImDrrKZ/ezmsDScUXzZPxTY9p20Gql8wlxI.YsN6', NOW(), TRUE),
  ('SBI', 'stefan.bie@live.de', '$2a$10$79TI9jfXK/xpQoiEPdzMBOt1OzXSH4WaW8z8d/r5z5rPjs3o7Uzby', NOW(), TRUE),
  ('ChrisLaDiDa', 'cpleines@stud.hs-bremen.de', '$2a$10$lYE/gshYNVsdYrUpLj7qGOzCGXZubVn5RW4BNARZof7p4EKVY1qAq', NOW(), TRUE);

# Oklon Password: hallo123
# faBimon Password: hallo123
# SBI Password: qwertz
# ChrisLaDiDa Password: werderbremen

INSERT INTO `images` (`uploaderid`, `filename`, `createdat`) VALUES
  (1, 'not_nsfw_pic.jpg', NOW()),
  (1, 'a09wM0d_460s.jpg', NOW()),
  (2, 'suesse_robbenbabys_im_soester_zoo.jpg', NOW()),
  (2, '8cb9378cef976172.jpg', NOW()),
  (2, 'ac69ce80c5312a7c.png', NOW()),
  (2, 'ae3Ne1j_460sa_v1.gif', NOW()),
  (2, 'f34a379bf37409b1.gif', NOW()),
  (2, 'fb50eee5b838e376.jpg', NOW()),
  (2, 'i6l7yli.gif', NOW()),
  (3, 'Pokemon_asdbdnd.jpg', NOW()),
  (3, '1236796_621682624530210_1560674262_n.jpg', NOW()),
  (4, 'maidemo.jpg', NOW()),
  (4, '10557310_792640010767803_7157748472305996283_n.jpg', NOW()),
  (3, 'werderhulls.jpg', NOW()),
  (3, 'open-poster-small.png', NOW());

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