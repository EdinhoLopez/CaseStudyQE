-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for QEProjectDatabase
CREATE DATABASE IF NOT EXISTS `qeprojectdatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `QEProjectDatabase`;

-- Dumping structure for table QEProjectDatabase.brands
CREATE TABLE IF NOT EXISTS `brands` (
  `brandId` int(11) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(50) NOT NULL DEFAULT '',
  `brandDescription` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.category
CREATE TABLE IF NOT EXISTS `category` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(50) DEFAULT NULL,
  `categoryDescription` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.inventory
CREATE TABLE IF NOT EXISTS `inventory` (
  `itemID` int(11) NOT NULL,
  `itemQuantity` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`itemID`),
  CONSTRAINT `FK__items` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.items
CREATE TABLE IF NOT EXISTS `items` (
  `itemID` int(11) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `brandID` int(11) NOT NULL,
  `itemPrice` double NOT NULL DEFAULT -1,
  `itemDescription` text NOT NULL DEFAULT '-1',
  `itemInstrument` binary(50) NOT NULL DEFAULT '-1\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  PRIMARY KEY (`itemID`),
  KEY `FK__category` (`categoryID`),
  KEY `FK_items_brands` (`brandID`),
  CONSTRAINT `FK__category` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `FK_items_brands` FOREIGN KEY (`brandID`) REFERENCES `brands` (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.order
CREATE TABLE IF NOT EXISTS `order` (
  `orderID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `orderDate` date DEFAULT NULL,
  `orderTotal` double NOT NULL DEFAULT 0,
  `orderStatus` tinytext NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderID`),
  KEY `users_Foreign` (`userID`),
  CONSTRAINT `users_Foreign` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.orderitems
CREATE TABLE IF NOT EXISTS `orderitems` (
  `orderID` int(11) NOT NULL,
  `itemID` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`itemID`),
  KEY `itemID_ForeignKey` (`itemID`),
  CONSTRAINT `itemID_ForeignKey` FOREIGN KEY (`itemID`) REFERENCES `items` (`itemID`),
  CONSTRAINT `order_ForeignKey` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.userdetails
CREATE TABLE IF NOT EXISTS `userdetails` (
  `userID` int(11) NOT NULL,
  `userFName` varchar(50) NOT NULL DEFAULT '',
  `userLName` varchar(50) NOT NULL DEFAULT '',
  `dateBirth` date NOT NULL DEFAULT '0000-00-00',
  `userAddress` text NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `FK__users` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table QEProjectDatabase.users
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `userPassword` varchar(50) DEFAULT NULL,
  `userEmail` varchar(50) NOT NULL,
  `userAdmin` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName` (`userName`),
  UNIQUE KEY `userEmail` (`userEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
