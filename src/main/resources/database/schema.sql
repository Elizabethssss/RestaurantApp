-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `about` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dish_type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `weight` int(11) NOT NULL,
  `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES (1,'Beef tartare','Hand-chop beef and serve it up with an array of traditional goodies like capers, chives, shallots, and a runny egg yolk. But don’t stop there. Beef tartare can really be anything you want—that’s why it’s so amazing.','SNACK',265,160,'tartar_iz_goviadini.jpg'),(2,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,300,'spaghetti_with_seafood.jpg'),(3,'Roasted veggie bowl with eggs','Roasted Vegetable Bowls! Crispy tender roasted veggies, buttery avocado, all together in a bowl with a drizzle of green tahini sauce.','MAIN_DISH',305,290,'Roasted-Veggie-Bowl-with-Egg.jpg'),(4,'Italian Farro Salad','Delicious, filling vegetarian recipe that’s perfect for healthy meal prep, light lunches, or a side dish with dinner. Tastes great leftover and at room temperature, so it’s a perfect potluck and barbecue side dish recipe too!','MAIN_DISH',250,275,'farro-salad.jpg'),(5,'Classic Glazed Ham','This classic holiday ham is basted with a honey pineapple glaze and served with a sweet and tangy pineapple sauce. Classic Glazed Ham with Pineapple Sauce is a fabulous choice for your holiday menu.','MAIN_DISH',550,700,'classic-glazed-ham.jpg'),(6,'Ricotta, Ham and Scallion Tart','Grease a 9-inch fluted tart pan with 1 tablespoon butter. Line the pan with the pizza dough, carefully pressing it against the sides; trim excess dough. Whisk the ricotta, cream and egg in a medium bowl. Mince enough green scallion tops to make 2 tablespoons','MAIN_DISH',375,400,'ricotta-ham-scallion-tart.jpg'),(7,'Creamy Spring Peas With Pancetta\r\n','Cook the pancetta in a large skillet over medium heat until crisp, 8 to 10 minutes. Transfer to a paper towel-lined plate with a slotted spoon.','MAIN_DISH',285,285,'spring-peas-with-pancetta.jpg'),(8,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,400,'spaghetti_with_seafood.jpg'),(9,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,300,'spaghetti_with_seafood.jpg'),(10,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,300,'spaghetti_with_seafood.jpg'),(11,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,300,'spaghetti_with_seafood.jpg'),(12,'Summer Asian Slaw','Colorful, juicy, crunchy, sweet, bitter, herby, spicy… there are so many flavors and textures to put together with all of the great fruits and vegetables that are in season right now.','SALAD',250,275,'asian-slaw.jpg'),(13,'Greek salad','Greek salad (Horiatiki), is an iconic Mediterranean recipe that is dressed simply with a splash of Greek extra virgin olive oil and a little red wine vinegar (or lemon juice).','SALAD',225,250,'greek-salad.jpg'),(14,'French Onion Soup','Stay warm with this GREAT French onion soup! With beef stock base, slow-cooked caramelized onions, French bread, gruyere and Parmesan cheese.','SOUP',195,200,'french-onion-soup.jpg'),(15,'N/A Mojito','The Mojito has risen in the ranks to become one of the most popular cocktails. It is a simple and delightful drink that should be on every drinker\'s list of cocktails that must be tasted.','DRINK',120,200,'mojito.jpg'),(16,'Berry smoothie','A smoothie is so much more than blended fruit. It\'s an art with all of its different elements blending perfectly together.','DRINK',150,200,'smoothie.jpg'),(17,'Vanilla milkshake','Reminiscent of countertop service and carhop days, there is nothing better than a perfect milkshake.','DRINK',120,250,'vanilla-milkshake.jpg'),(18,'Berry panna cotta','This creamy Panna Cotta is a great make-ahead dessert that’s simple to make, and pretty enough for a special occasion.','DESSERT',200,150,'berry-panna-cotta.jpg'),(19,'Pancakes','Pancakes are soft and pillowy, with a mouthwatering malty flavor, and they lovingly soak up your syrup and butter to become the decadent stack of deliciousness they are.','DESSERT',189,220,'pancakes.jpg'),(20,'Latte','If the Italian restaurant asked for a latte, the waiter brought a glass of milk (latte in Italian means “milk”). Coffee with milk, which in the rest of the world is reduced to just a “labyrinth” (in Italian it is called cafe latte) and nothing else.','DRINK',60,250,'latte.jpg'),(21,'Vegan chocolate ice cream','Creamy, 3-ingredient vegan chocolate ice cream infused with cocoa powder, banana, and peanuts. Simple methods, extremely rich, and satisfying results.','DESSERT',75,100,'Vegan-Chocolate-Ice-Cream.jpg'),(22,'Lemonade','Sip this classic from a frosty glass. Its homemade flavor is worth every squeeze!','DRINK',75,250,'lemonade.jpg'),(23,'Tomato Soup','Creamy, rich and bursting with brightness, this tomato soup recipe is the ultimate sidekick to a grilled cheese sandwich. You can\'t beat this homemade version of the classic.','SOUP',150,250,'tomato-soup.jpg'),(24,'Raspberry Lemon Pound Cake','Make the Glaze: Meanwhile, in a blender, purée confectioners\' sugar, cream and jam, scraping down the sides as needed. Drizzle glaze over cooled cake and serve.','DESSERT',380,350,'raspberry-lemon-pound-cake.jpg');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes_ingredients`
--

DROP TABLE IF EXISTS `dishes_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes_ingredients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dish_id` bigint(20) NOT NULL,
  `ingredient_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dish_id_idx` (`dish_id`),
  KEY `ingredient_id_idx` (`ingredient_id`),
  CONSTRAINT `dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `ingredient_id` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes_ingredients`
--

LOCK TABLES `dishes_ingredients` WRITE;
/*!40000 ALTER TABLE `dishes_ingredients` DISABLE KEYS */;
INSERT INTO `dishes_ingredients` VALUES (1,2,1),(2,2,2),(3,2,3),(4,2,4),(5,2,5),(6,2,6),(7,2,7),(11,1,8),(12,1,9),(13,1,10),(14,1,11),(15,1,12),(16,1,13),(17,12,14),(18,12,15),(19,12,16),(20,12,17),(21,12,18),(22,12,19),(23,12,2),(24,12,20),(25,13,1),(26,13,21),(27,13,22),(28,13,23),(29,13,24),(30,13,18),(31,13,20),(32,14,25),(33,14,26),(34,14,27),(35,14,28),(36,14,29),(37,14,30),(38,14,31),(39,14,21),(40,15,10),(41,15,20),(42,15,30),(43,15,34),(44,15,37),(45,16,35),(46,16,32),(47,16,33),(48,16,36),(49,16,38),(50,17,35),(51,17,39),(52,17,40),(53,18,41),(54,18,35),(55,18,36),(56,18,30),(57,19,44),(58,19,42),(59,19,35),(60,19,30),(61,19,26),(62,19,38),(63,20,43),(64,20,35),(65,19,45),(66,2,3),(67,21,47),(68,21,48),(69,21,32),(70,3,49),(71,3,50),(72,3,51),(73,3,52),(74,3,53),(75,3,54),(76,3,55),(77,3,10),(78,3,42),(79,22,56),(80,22,57),(81,22,30),(92,23,58),(93,23,53),(94,23,26),(95,23,18),(96,23,49),(97,23,21),(98,23,2),(99,23,27),(100,23,30),(101,23,41),(102,4,61),(103,4,1),(104,4,59),(105,4,18),(106,4,22),(107,4,24),(108,4,60),(109,4,45),(110,4,13),(111,5,62),(112,5,63),(113,6,64),(114,6,65),(115,6,62),(116,6,26),(117,6,41),(118,6,42),(119,6,60),(120,6,19),(121,7,66),(122,7,67),(123,7,56),(124,7,41),(125,7,27),(126,7,44),(127,7,59),(128,24,69),(129,24,30),(130,24,44),(131,24,56),(132,24,26),(133,24,42),(134,24,41),(135,24,36);
/*!40000 ALTER TABLE `dishes_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes_lunches`
--

DROP TABLE IF EXISTS `dishes_lunches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes_lunches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dish_id` bigint(20) NOT NULL,
  `lunch_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dishs_id_idx` (`dish_id`),
  KEY `set_id_idx` (`lunch_id`),
  CONSTRAINT `dishs_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `lunch_id` FOREIGN KEY (`lunch_id`) REFERENCES `lunches` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes_lunches`
--

LOCK TABLES `dishes_lunches` WRITE;
/*!40000 ALTER TABLE `dishes_lunches` DISABLE KEYS */;
INSERT INTO `dishes_lunches` VALUES (1,19,1),(2,20,1),(4,3,2),(5,21,2),(6,23,3),(7,4,3),(8,22,3),(9,5,4),(10,6,4),(11,7,4),(12,24,4);
/*!40000 ALTER TABLE `dishes_lunches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'Tomato cherry','cherry-tomatoes.png'),(2,'Basil','basil.png'),(3,'Mussels','mussels.png'),(4,'Octopus','octopus.png'),(5,'Scallops','scallops.png'),(6,'Shrimps','shrimps.png'),(7,'Spagetti','spagetti.png'),(8,'Beef','beef.png'),(9,'Capers','capers.png'),(10,'Lime','lime.png'),(11,'Egg yolk','yolk.png'),(12,'Shallot','shallot.png'),(13,'Mustard','mustard.png'),(14,'Cabbage','cabbage.png'),(15,'Red cabbage','red-cabbage.png'),(16,'Ginger miso sause','ginger-miso-sauce.png'),(17,'Peach','peach.png'),(18,'Peppers','peppers.png'),(19,'Scallions','scallions.png'),(20,'Mint','mint.png'),(21,'Red onion','red-onion.png'),(22,'Cucumber','cucumber.png'),(23,'Olives','olives.png'),(24,'Feta','feta.png'),(25,'Baguette','baguette.png'),(26,'Butter','butter.png'),(27,'Chicken stock','chicken-stock.png'),(28,'Gruyere','gruyere.png'),(29,'Parmesan','parmesan.png'),(30,'Sugar','sugar.png'),(31,'White wine','white-wine.png'),(32,'Banana','banana.png'),(33,'Blackberry','blackberry.png'),(34,'Ice','ice.png'),(35,'Milk','milk.png'),(36,'Raspberry','raspberry.png'),(37,'Sprite','sprite.png'),(38,'Strawberry','strawberry.png'),(39,'Vanilla ice cream','vanilla-ice-cream.png'),(40,'Whipped cream','whipped-cream.png'),(41,'Heavy cream','heavy-cream.png'),(42,'Eggs','eggs.png'),(43,'Espresso','espresso.png'),(44,'Flour','flour.png'),(45,'Honey','honey.png'),(47,'Cocoa','cocoa.png'),(48,'Peanut','peanut.png'),(49,'Carrot','carrot.png'),(50,'Potato','potato.png'),(51,'Broccoli','broccoli.png'),(52,'Cauliflower','cauliflower.png'),(53,'Garlic','garlic.png'),(54,'Avocado','avocado.png'),(55,'Tofu','tofu.png'),(56,'Lemon','lemon.png'),(57,'Water','water.png'),(58,'Tomatoes','tomatoes.png'),(59,'Peas','peas.png'),(60,'Parsley','parsley.png'),(61,'Farro','farro.png'),(62,'Ham','ham.png'),(63,'Cloves','cloves.png'),(64,'Ricotta cheese','ricotta-cheese.png'),(65,'Dough','dough.png'),(66,'English peas','english-peas.png'),(67,'Pancetta','pancetta.png'),(68,'Dough','dough.png'),(69,'Сream cheese','cream-cheese.png');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lunches`
--

DROP TABLE IF EXISTS `lunches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lunches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `img` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lunch_type` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lunches`
--

LOCK TABLES `lunches` WRITE;
/*!40000 ALTER TABLE `lunches` DISABLE KEYS */;
INSERT INTO `lunches` VALUES (1,'Classic Pancakes with Latte','A pancake is a flat cake, often thin and round, prepared from a starch-based batter that may contain eggs, milk and butter and cooked on a hot surface such as a griddle or frying pan, often frying with oil or butter.','breakfast1.jpg','BREAKFAST'),(2,'Vegetarian bowl with vegan ice cream','My easy solution? Roasting. Roasting vegetables makes them easier to digest, enhances their flavor, and takes them to whole new level of delicious. So we start with carrots, beets, radish, potatoes, and sweet potatoes all tossed in sea salt, oil (or water), and curry powder.','lunch1.jpg','LUNCH'),(3,'Nutritious vegetarian lunch','Simple, vibrant veggie lunch recipes that will keep you full until dinner. Tuck into soups, salads, wraps and more delicious veg-packed dishes.','lunch2.jpg','LUNCH'),(4,'Easter holiday lunch','Find Easter recipes for entertaining and baking over the bank holiday weekend, from roast lamb with all the trimmings to classic bakes, including hot cross buns and simnel cake.','holiday1.jpg','HOLIDAY');
/*!40000 ALTER TABLE `lunches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `cost` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (8,'PAYED',1020,'2020-03-27',2),(10,'PAYED',675,'2020-03-27',2),(11,'PAYED',900,'2020-03-27',2),(12,'PAYED',870,'2020-03-27',2),(13,'PAYED',265,'2020-03-30',9),(14,'FORMED',0,'2020-03-30',6),(18,'PAYED',425,'2020-03-30',2),(19,'CONFIRMED',595,'2020-03-30',1),(20,'FORMED',0,'2020-03-30',1),(21,'CONFIRMED',1110,'2020-03-30',9),(22,'PAYED',1297,'2020-03-30',2),(23,'CONFIRMED',195,'2020-03-30',9),(24,'CONFIRMED',505,'2020-03-30',9),(25,'CONFIRMED',579,'2020-03-30',9),(26,'CONFIRMED',195,'2020-04-01',2),(27,'CONFIRMED',375,'2020-04-01',2),(28,'CONFIRMED',7283,'2020-04-01',9),(29,'FORMED',0,'2020-04-02',9),(30,'CONFIRMED',530,'2020-04-03',2),(31,'CONFIRMED',825,'2020-04-03',2),(32,'FORMED',0,'2020-04-10',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_dishes`
--

DROP TABLE IF EXISTS `orders_dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_dishes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `dish_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`order_id`),
  KEY `dish_id` (`dish_id`),
  CONSTRAINT `disho_id` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `orderd_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_dishes`
--

LOCK TABLES `orders_dishes` WRITE;
/*!40000 ALTER TABLE `orders_dishes` DISABLE KEYS */;
INSERT INTO `orders_dishes` VALUES (111,8,1),(112,8,1),(113,8,15),(114,8,17),(115,8,12),(181,10,13),(182,10,13),(183,10,13),(184,11,16),(185,11,16),(186,11,16),(187,11,16),(188,11,16),(189,11,16),(195,12,2),(196,12,12),(197,12,12),(198,19,18),(199,19,18),(200,19,14),(201,13,1),(202,18,18),(203,18,13),(204,21,2),(205,21,2),(206,21,2),(207,23,14),(208,24,1),(209,24,17),(210,24,17),(219,22,12),(221,22,16),(222,22,16),(229,26,14),(232,25,19),(233,25,14),(234,25,14),(235,28,4),(236,28,4),(237,28,3),(238,28,3),(239,28,22),(240,28,16),(241,28,21),(242,28,19),(243,28,23),(244,28,22),(245,28,22),(246,28,22),(247,28,22),(248,28,21),(249,28,21),(250,28,21),(251,28,21),(252,28,21),(253,28,21),(254,28,21),(255,28,21),(256,28,21),(257,28,23),(258,28,23),(259,28,23),(260,28,23),(261,28,19),(262,28,19),(263,28,19),(264,28,19),(265,28,19),(266,28,16),(267,28,3),(268,28,3),(269,28,4),(270,28,4),(271,28,4),(272,28,4),(273,28,3),(274,28,3),(275,28,3),(276,28,19),(277,28,23),(278,27,23),(285,27,21),(286,27,21),(287,27,21),(290,30,3),(291,30,13),(293,31,13),(294,31,16),(295,31,13),(296,31,13);
/*!40000 ALTER TABLE `orders_dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_lunches`
--

DROP TABLE IF EXISTS `orders_lunches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_lunches` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `lunch_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`order_id`),
  KEY `lunch_id_idx` (`lunch_id`),
  CONSTRAINT `l_id` FOREIGN KEY (`lunch_id`) REFERENCES `lunches` (`id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_lunches`
--

LOCK TABLES `orders_lunches` WRITE;
/*!40000 ALTER TABLE `orders_lunches` DISABLE KEYS */;
INSERT INTO `orders_lunches` VALUES (9,22,1),(10,22,1),(11,22,1),(12,32,1);
/*!40000 ALTER TABLE `orders_lunches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `username` longtext NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Elizabethssss','teddy020301@gmail.com','202cb962ac59075b964b07152d234b70','USER'),(2,'Dasha','dassshka@kiev.ua','4bea249142664d11a289ffdf30225a91','USER'),(6,'ÐÐ°Ð»ÐµÑÐ°','re@re','12eccbdd9b32918131341f38907cbbb5','USER'),(9,'Admin','admin@admin.com','21232f297a57a5a743894a0e4a801fc3','ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 18:09:01
