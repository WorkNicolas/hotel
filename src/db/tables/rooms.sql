CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `size` int NOT NULL,
  `type` enum('BASIC','STANDARD','SUITE') NOT NULL,
  `url` varchar(8000) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
)