CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `size` int NOT NULL,
  `type` enum('BASIC','STANDARD','SUITE') NOT NULL,
  `url` varchar(8000) NOT NULL,
  `rate` int NOT NULL,
  `name` VARCHAR(128) NOT NULL
);