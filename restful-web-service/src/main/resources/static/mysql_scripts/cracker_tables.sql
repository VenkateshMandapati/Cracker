#Calorie Tracker DB

CREATE DATABASE IF NOT EXISTS `calorie_tracker`;
USE `calorie_tracker`;

#create table customer
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `time_stamp` TIMESTAMP NOT NULL,
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


#create table food_storage
DROP TABLE IF EXISTS `food_storage`;

CREATE TABLE `food_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `customer_id` int(11) DEFAULT NULL,
  `day` DATE DEFAULT NULL,
  `part_of_day` varchar(15) DEFAULT NULL,
  `food` varchar(50) DEFAULT NULL,
  `calories` int DEFAULT NULL,
  `time_stamp` TIMESTAMP NOT NULL,

  FOREIGN KEY (`customer_id`) REFERENCES customer(`id`)
    ON DELETE SET NULL ON UPDATE CASCADE

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

