CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `room_id` int NOT NULL,
  `tenant_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `cancel` DATE COMMENT "ALLOW NULL",
  FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  FOREIGN KEY (`tenant_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`payment_id`) REFERENCES `payments`(`id`)
);