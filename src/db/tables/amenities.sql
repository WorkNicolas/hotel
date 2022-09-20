CREATE TABLE IF NOT EXISTS `amenities` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `supply` int NOT NULL,
  `type` enum('BREAKFAST','DISH','DESSERT','BEVERAGE','LUNCH','THING') NOT NULL DEFAULT 
'THING'
);