 CREATE TABLE IF NOT EXISTS `news` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(8000) NOT NULL
);