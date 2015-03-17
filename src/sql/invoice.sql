USE thennig;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `Persons`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Persons` (
  `PersonID`        INT(11)     NOT NULL AUTO_INCREMENT,
  `PersonCode`      VARCHAR(20) NOT NULL,
  `AddressID`       INT(11)     NOT NULL,
  `PersonLastName`  VARCHAR(30) NOT NULL,
  `PersonFirstName` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`PersonID`),
  FOREIGN KEY (`AddressID`) REFERENCES Address (`AddressID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Product` (
  `ID`           INT(11)     NOT NULL AUTO_INCREMENT,
  `GameDateTime` VARCHAR(30),
  `Code`         VARCHAR(30) NOT NULL,
  `VenueCode`    VARCHAR(30),
  `Teams`        VARCHAR(45),
  `Type`         VARCHAR(30) NOT NULL,
  `HourlyFee`    FLOAT(15),
  `LicenseFee`   FLOAT(15),
  `Refreshment`  VARCHAR(30),
  `Cost`         FLOAT(15),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`VenueCode`) REFERENCES Venue (`Code`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table Structure for table `InvoiceProduct`
--

DROP TABLE IF EXISTS `InvoiceProduct`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `InvoiceProduct` (
  `ProductID`     VARCHAR(30) NOT NULL,
  `InvoiceID`     VARCHAR(30) NOT NULL,
  `NumberOfHours` VARCHAR(30),
  `NumberOfUnits` VARCHAR(30),
  `BeginDate`     VARCHAR(30),
  `EndDate`       VARCHAR(30),
  `Date`          VARCHAR(30),
  `Seats`         VARCHAR(30),
  PRIMARY KEY (`ProductID`),
  FOREIGN KEY (`ProductID`) REFERENCES Product (`ID`),
  FOREIGN KEY (`InvoiceID`) REFERENCES Invoice (`ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Email`
--

DROP TABLE IF EXISTS `Email`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Email` (
  `EmailID`      INT(11) NOT NULL AUTO_INCREMENT,
  `PersonID`     INT(11) NOT NULL,
  `EmailAddress` VARCHAR(30)      DEFAULT NULL,
  PRIMARY KEY (`EmailID`),
  FOREIGN KEY (`PersonID`) REFERENCES Persons (`PersonID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Address` (
  `AddressID` INT(11)     NOT NULL AUTO_INCREMENT,
  `Street`    VARCHAR(30) NOT NULL,
  `City`      VARCHAR(30) NOT NULL,
  `CityState` VARCHAR(30) NOT NULL,
  `ZipCode`   VARCHAR(20) NOT NULL,
  `Country`   VARCHAR(20) NOT NULL,
  PRIMARY KEY (`AddressID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*--UNLOCK TABLES;*/;

/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Customers` (
  `CustomerID`   INT(11)     NOT NULL AUTO_INCREMENT,
  `AddressID`    INT(11)     NOT NULL,
  `CustomerCode` VARCHAR(20) NOT NULL,
  `CustomerType` VARCHAR(2)  NOT NULL,
  `PersonCode`   VARCHAR(20) NOT NULL,
  `PersonID`     INT(11)     NOT NULL,
  `CustomerName` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  FOREIGN KEY (`PersonID`) REFERENCES Persons (`PersonID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`AddressID`) REFERENCES Address (`AddressID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
