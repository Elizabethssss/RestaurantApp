INSERT INTO `dishes` VALUES
(1,'Beef tartare','Hand-chop beef and serve it up with an array of traditional goodies like capers, chives, shallots, and a runny egg yolk. But don’t stop there. ' ||
 'Beef tartare can really be anything you want—that’s why it’s so amazing.','SNACK',265,160,'tartar_iz_goviadini.jpg'),
(2,'Spaghetti with seafood','This seafood pasta is a mix of shrimp, clams, mussels and scallops, all tossed together with spaghetti in a homemade tomato sauce. ' ||
 'An easy yet elegant meal that’s perfect for entertaining!','MAIN_DISH',370,300,'spaghetti_with_seafood.jpg'),
(3,'Summer Asian Slaw','Colorful, juicy, crunchy, sweet, bitter, herby, spicy… there are so many flavors and textures to put together with all of the great ' ||
  'fruits and vegetables that are in season right now.','SALAD',250,275,'asian-slaw.jpg');

INSERT INTO `users` VALUES
(1,'Elizabethssss','teddy020301@gmail.com','202cb962ac59075b964b07152d234b70','USER'),
(2,'Dasha','dassshka@kiev.ua','4bea249142664d11a289ffdf30225a91','USER'),
(3,'Admin','admin@admin.com','21232f297a57a5a743894a0e4a801fc3','ADMIN');

INSERT INTO `lunches` VALUES
(1,'Classic Pancakes with Latte','A pancake is a flat cake, often thin and round, prepared from a starch-based batter that may contain eggs,
 milk and butter and cooked on a hot surface such as a griddle or frying pan, often frying with oil or butter.','breakfast1.jpg','BREAKFAST'),
(2,'Pancakes with Latte','A pancake is a flat cake.','breakfast2.jpg','BREAKFAST');

INSERT INTO `orders` VALUES
(8,'PAYED',1020,'2020-03-27',2),
(9,'PAYED',13590,'2020-03-27',2),
(10,'PAYED',675,'2020-03-27',2),
(11,'CONFIRMED',900,'2020-03-27',2),
(12,'CONFIRMED',870,'2020-03-27',2),
(13,'CONFIRMED',265,'2020-03-30',3);

INSERT INTO `ingredients` VALUES
(1,'Tomato cherry','cherry-tomatoes.png'),(2,'Basil','basil.png'),
(3,'Mussels','mussels.png'),(4,'Octopus','octopus.png'),
(5,'Scallops','scallops.png'),(6,'Shrimps','shrimps.png'),
(7,'Spagetti','spagetti.png'),(8,'Beef','beef.png'),
(9,'Capers','capers.png'),(10,'Lime','lime.png'),
(11,'Egg yolk','yolk.png'),(12,'Shallot','shallot.png'),
(13,'Mustard','mustard.png'),(14,'Cabbage','cabbage.png');

INSERT INTO `dishes_ingredients` VALUES
(1,2,1), (2,2,2), (3,2,3), (4,2,4), (5,2,5), (6,2,6), (7,2,7), (11,1,8),(12,1,9),(13,1,10),(14,1,11),
(15,1,12),(16,1,13),(17,3,14),(18,3,1),(19,3,3),(20,3,5),(21,3,7),(22,3,9);

INSERT INTO `dishes_lunches` VALUES (1,1,1),(2,2,1);

INSERT INTO `orders_dishes` VALUES
(1, 8, 1), (2, 8, 2), (3, 9, 3), (4, 10, 2), (5, 13, 1), (6, 8, 1), (7, 8, 2);

INSERT INTO `orders_lunches` VALUES (1,11,1),(2,11,1),(3,12,1),(4,11,2),(5,11,2);
