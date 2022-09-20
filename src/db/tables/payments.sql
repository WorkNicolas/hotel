CREATE TABLE IF NOT EXISTS `payments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `amount` float NOT NULL,
  `method` varchar(64) NOT NULL,
  `account` varchar(128) NOT NULL,
  `discount` float NOT NULL
);