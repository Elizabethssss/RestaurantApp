DROP TABLE IF EXISTS `dishes`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `lunches`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `ingredients`;
DROP TABLE IF EXISTS `dishes_ingredients`;
DROP TABLE IF EXISTS `dishes_lunches`;
DROP TABLE IF EXISTS `orders_dishes`;
DROP TABLE IF EXISTS `orders_lunches`;

CREATE TABLE `dishes` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `about` longtext NOT NULL,
    `dish_type` varchar(45) NOT NULL,
    `price` double NOT NULL,
    `weight` int(11) NOT NULL,
    `img` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `username` longtext NOT NULL,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role` varchar(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE `lunches` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `description` longtext NOT NULL,
    `img` varchar(45) NOT NULL,
    `lunch_type` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `status` varchar(45) NOT NULL,
    `cost` int(11) NOT NULL,
    `created_at` date NOT NULL,
    `user_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `ingredients` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `img` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `dishes_ingredients` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `dish_id` bigint(20) NOT NULL,
    `ingredient_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `ingredient_id` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`)
);

CREATE TABLE `dishes_lunches` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `dish_id` bigint(20) NOT NULL,
    `lunch_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `dishs_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `lunch_id` FOREIGN KEY (`lunch_id`) REFERENCES `lunches` (`id`)
);

CREATE TABLE `orders_dishes` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id` bigint(20) NOT NULL,
    `dish_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `disho_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `orderd_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

CREATE TABLE `orders_lunches` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id` bigint(20) NOT NULL,
    `lunch_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `l_id` FOREIGN KEY (`lunch_id`) REFERENCES `lunches` (`id`),
    CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

