DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `dishes`;
DROP TABLE IF EXISTS `ingredients`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `lunches`;
DROP TABLE IF EXISTS `dishes_ingredients`;
DROP TABLE IF EXISTS `dishes_lunches`;
DROP TABLE IF EXISTS `orders_dishes`;

CREATE TABLE `users` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `username` longtext NOT NULL,
    `email` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role` varchar(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE `dishes` (
    `id` bigint(19) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `dish_type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `price` double NOT NULL,
    `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ingredients` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `orders` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `cost` double NOT NULL,
    `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `created_at` date NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `lunches` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `description` longtext COLLATE utf8_unicode_ci NOT NULL,
    `time_from` date NOT NULL,
    `time_to` date NOT NULL,
    `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    `lunch_type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `dishes_ingredients` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `dish_id` bigint(20) NOT NULL,
    `ingredient_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `dish_id_idx` (`dish_id`),
    KEY `ingredient_id_idx` (`ingredient_id`),
    CONSTRAINT `dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `ingredient_id` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`)
);

CREATE TABLE `dishes_lunches` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `dish_id` bigint(20) NOT NULL,
    `lunch_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `dishs_id_idx` (`dish_id`),
    KEY `set_id_idx` (`lunch_id`),
    CONSTRAINT `dishs_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `lunch_id` FOREIGN KEY (`lunch_id`) REFERENCES `lunches` (`id`)
);

CREATE TABLE `orders_dishes` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id` bigint(20) NOT NULL,
    `dish_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `order_id_idx` (`order_id`),
    KEY `dish_id` (`dish_id`),
    CONSTRAINT `disho_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
    CONSTRAINT `orderd_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);

