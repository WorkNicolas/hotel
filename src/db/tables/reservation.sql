CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `startsAt` date NOT NULL,
  `length` tinyint DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `tenant_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`),
  KEY `tenant_id` (`tenant_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`tenant_id`) REFERENCES `users` (`id`)
)