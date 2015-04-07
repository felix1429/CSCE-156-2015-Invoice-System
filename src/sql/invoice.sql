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

CREATE TABLE `Person` (
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
-- Dumping data for table `Persons`
--

/*--LOCK TABLES `Persons` WRITE;*/;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person`
VALUES (1, '944c', 1, 'Castro', 'Starlin'), (2, '306a', 2, 'Sampson', 'Brock'), (3, '55bb', 3, '0Brien', 'Miles'),
  (4, '2342', 4, 'OBrien', 'Miles'),
  (5, 'aef1', 5, 'Gekko', 'Gordon'), (6, '321f', 6, 'Fox', 'Bud'), (7, 'ma12', 7, 'Sveum', 'Dale'),
  (8, '321nd', 8, 'Hartnell', 'William'), (9, 'nf32a', 9, 'Pertwee', 'Jon'),
  (10, '231', 10, 'Baker', 'Tom'), (11, '6doc', 11, 'Hurndall', 'Richard'), (12, '321dr', 12, 'Baker', 'C.'),
  (13, '1svndr', 13, 'McCoy', 'Sylvester'), (14, '1231st', 14, 'McGann', 'Paul'),
  (15, 'nwdoc1', 15, 'Ecceleston', 'Chris'), (16, '2ndbestd', 16, 'Tennant', 'David'),
  (17, 'wrddoc', 17, 'Smith', 'Matt'), (18, 'bbchar', 18, 'Ehrmantraut', 'Kaylee'),
  (19, 'doc05', 19, 'Davison', 'Peter');
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

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
  `StartDate`    VARCHAR(30),
  `EndDate`      VARCHAR(30),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`VenueCode`) REFERENCES Venue (`Code`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*--LOCK TABLES `Product` WRITE;*/;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product`
VALUES (1, NULL, 'b29e', NULL, 'Chicago Bears', 'TS', NULL, NULL, NULL, 1238, NULL, NULL),
  (2, NULL, 'ff23', NULL, NULL, 'SR', NULL, NULL, 'Labatt Beer - 02z', 4.99, NULL, NULL),
  (3, '2014-02-11 13:10', 'fp12', 'st329', 'Cleveland Browns,Detroit Lions', 'TG', NULL, NULL, NULL, 187, NULL, NULL),
  (4, NULL, '90fa', 'a001', NULL, 'SP', 35, NULL, NULL, NULL, NULL, NULL),
  (5, '2014-02-11 15:30', '1239', 'ai10', 'Dallas Cowboys,Chicago Bears', 'TG', NULL, NULL, NULL, 340, NULL, NULL),
  (6, '2014-04-07 10:00', '782g', 'w111', 'Seattle Seahawks,Atlanta Falcons', 'TG', NULL, NULL, NULL, 225, NULL, NULL),
  (7, '2014-04-07 10:00', '3289', 'w111', 'Seattle Seahawks,Atlanta Falcons', 'SL', NULL, 55, NULL, 225, NULL, NULL),
  (8, NULL, '32f4', NULL, NULL, 'SR', NULL, NULL, 'Italian Buffet', 18, NULL, NULL),
  (9, NULL, '3y92', NULL, 'Carolina Panthers', 'TS', NULL, NULL, NULL, 231, '2014-04-07', '2014-09-29'),
  (10, NULL, '90fb', 'ai10', NULL, 'SP', 20, NULL, NULL, NULL, NULL, NULL),
  (11, '2014-02-11 13:10', 'xer4', 'st329', 'Cleveland Browns,Detroit Lions', 'SL', NULL, 73, NULL, 187, NULL, NULL),
  (12, NULL, 'yp23', NULL, 'Carolina Panthers', 'SL', NULL, 39, NULL, 231, '2014-04-07', '2014-04-29');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

--
-- Table Structure for table `InvoiceProduct`
--

DROP TABLE IF EXISTS `InvoiceProduct`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `InvoiceProduct` (
  `ProductID`     INT(11) NOT NULL,
  `InvoiceID`     INT(11) NOT NULL,
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

/*--LOCK TABLES `InvoiceProduct` WRITE;*/;
/*!40000 ALTER TABLE `InvoiceProduct` DISABLE KEYS */;
INSERT INTO `InvoiceProduct`
VALUES (1, 1, NULL, NULL, '2014-01-01', '2014-05-31', NULL, NULL);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

--
-- Table structure for table `Email`
--

DROP TABLE IF EXISTS `Email`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Email` (
  `EmailID`      INT(11) NOT NULL AUTO_INCREMENT,
  `PersonID`     INT(11) NOT NULL,
  `EmailAddress` VARCHAR(300)      DEFAULT NULL,
  PRIMARY KEY (`EmailID`),
  FOREIGN KEY (`PersonID`) REFERENCES Person (`PersonID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Email`
--

/*--LOCK TABLES `Email` WRITE;*/;
/*!40000 ALTER TABLE `Email` DISABLE KEYS */;
<<<<<<< HEAD
INSERT INTO `Email` VALUES(1,1,'scastro@cubs.com'),(2,1,'starlin_castro13@gmail.com'),(3,2,'brock_f_sampson@gmail.com'),(4,2,'bsampson@venture.com'),(5,3,'obrien@ds9.com'),
  (6,3,'obrien@enterprise.gov'),(7,4,''),(8,5,''),(9,6,'bfox@gmail.com'),(10,6,'csheen@crazy.net'),(11,7,'sveum@cub.com'),(12,8,'whartnell@doctors.com'),(13,8,'dr@who.com'),
  (14,9,'ptroug@cse.unl.edu'),(15,9,'ptrou32@unl.edu'),(16,10,'jpet@whofan.com'),(17,11,'famousdoc@who.com'),(18,11,'tbaker@cse.unl.edu'),(19,11,'mostfamous@whovian.com'),
  (20,11,'thedoctor@bbc.com'),(21,12,'rhurndall@cse.unl.edu'),(22,12,'richard@unl.edu'),
  (23,13,'dr@baker.com'),(24,14,'slyguy@hotmail.com'),(25,14,'mccoy@whofan.com'),(26,15,'pmcgann@mlb.com,foo@bar.com'),(27,15,'pmc@unl.edu'), (28,16,'newguy@whovian.com'),
  (29,17,'actor@shakespeare.com'),(30,17,'tdavid@unl.com'),(31,18,'msmith@who.com'),(32,18,'thedoc@cse.unl.edu'),(33,19,''),(34,20,'');
/*!40000 ALTER TABLE `Email` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

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

INSERT INTO `Address` VALUES(1,'1 MetLife Stadium Drive','East Rutherford','NJ','07073','USA'),(2,'1 AT&T Way','Arlington','TX','76011','USA'),
  (3,'1 Arrowhead Drive','Kansas City','MO','64129','USA'),(4,'347 Don Shula Dr','Miami Gardens','FL','33056','USA'),
  (5,'800 South Mint Street','Charlotte','NC','28202','USA'),(6,'100 Alfred Lerner Way','Cleveland','OH','44114','USA'),
  (7,'1410 Museum Campus Drive','Chicago','IL','60605','USA'),(8,'800 Occidental Avenue South','Seattle','WA','98134','USA'),
  (9,'1 Cardinals Drive','Glendale','AZ','85305','USA'),(10,'1 Georgia Dome Drive Northwest','Atlanta','GA','30313','USA'),
  (11,'1060 West Addison Street','Chicago','IL','60613','USA'),(12,'123 N 1st Street','Omaha','NE','68116','USA'),(13,'8753 West 3rd Ave.','Dallas','TX','75001','USA'),
  (14,'123 Friendly St','Ottawa','ON','K1A 0G9','Canada'),(15,'1 Wall Street','New York','NY','10005-0012','USA'),(16,'321 Bronx Street','New York','NY','10004','USA'),
  (17,'1060 West Addison Street','Chicago','IL','60613','USA'),(18,'1060 West Addison Street','Chicago','IL','60613','USA'),
  (19,'301 Front St W','Toronto','ON','M5V 2T6','Canada'),(20,'1 Blue Jays Way','Toronto','ON','M5V 1J1','Canada'),(21,'Campos E1290','Mexico City','FD','NULL','Mexico'),
  (22,'Avery Hall','Lincoln','NE','68503','USA'),(23,'126-01 Roosevelt Ave','Flushing','NY','11368','USA'),(24,'1 Metlife Stadium Dr','East Rutherford','NJ','07073','USA'),
  (25,'1 E 161st St','Bronx','NY','10451','USA'),(26,'700 E Grand Ave','Chicago','IL','60611','USA'),(27,'800 West 7th Street','Albuquerque','NM','87105','USA'),
  (28,'123 Cabo San Lucas','Los Cabos','BCS',' ','Mexico'),(29,'259 Concorde Suites','Lincoln','NE','68588-0115','USA'),(30,'184 Marvel Way','New York','NY','10453','USA'),
  (31,'123 Venture Way','Culver City','CA','90230','USA'),(32,'9800 Savage Rd','Fort Meade','MD','20755','USA'),(33,'1060 West Addison','Chicago','IL','60601','USA'),
  (34,'456 West 7th St.','Omaha','NE','68500','USA')

/*--UNLOCK TABLES;*/;

--
-- Table structure for table `Customers`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Customer` (
  `CustomerID`   INT(11)     NOT NULL AUTO_INCREMENT,
  `AddressID`    INT(11)     NOT NULL,
  `CustomerCode` VARCHAR(20) NOT NULL,
  `CustomerType` VARCHAR(2)  NOT NULL,
  `PersonCode`   VARCHAR(20) NOT NULL,
  `PersonID`     INT(11)     NOT NULL,
  `CustomerName` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`CustomerID`),
  FOREIGN KEY (`PersonID`) REFERENCES Person (`PersonID`)
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

--
-- Table structure for table `Invoice`
--

DROP TABLE IF EXISTS `Invoice`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Invoice` (
  `ID`   INT(11)     NOT NULL AUTO_INCREMENT,
  `InvoiceCode` VARCHAR(40) NOT NULL,
  `CustomerID`  INT(11)     NOT NULL,
  `Date`        VARCHAR(40) NOT NULL,
  `PersonID`    INT(11)     NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`CustomerID`) REFERENCES Customer (`CustomerID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (`PersonID`) REFERENCES Person (`PersonID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoice`
--

/*--LOCK TABLES `Invoice` WRITE;*/;
/*!40000 ALTER TABLE `Invoice` DISABLE KEYS */;
INSERT INTO `Invoice`
VALUES (1, 'INV001', 1, '2014-02-03', 16), (2, 'INV002', 2, '2014-01-10', 17), (3, 'INV003', 3, '2014-01-29', 18),
  (4, 'INV004', 4, '2014-02-16', 12);
/*!40000 ALTER TABLE `Invoice` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

--
-- Table structure for table `Venue`
--

DROP TABLE IF EXISTS `Venue`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Venue` (
  `VenueID`       INT(11)     NOT NULL AUTO_INCREMENT,
  `AddressID`     INT(11)     NOT NULL,
  `VenueName`     VARCHAR(40) NOT NULL,
  `VenueCapacity` INT(11)     NOT NULL,
  PRIMARY KEY (`VenueID`),
  FOREIGN KEY (`AddressID`) REFERENCES Address (`AddressID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Venue`
--

/*--LOCK TABLES `Venue` WRITE;*/;
/*!40000 ALTER TABLE `Venue` DISABLE KEYS */;
INSERT INTO `Venue`
VALUES (1, 1, 'Metlife Stadium', 82566), (2, 2, 'AT&T Stadium', 80000), (3, 3, 'Arrowhead Stadium', 79541),
  (4, 4, 'Sun Life Stadium', 65326),
  (5, 5, 'Bank of America Stadium', 73779), (6, 6, 'FirstEnergy Stadium', 73204), (7, 7, 'Soldier Field', 61500),
  (8, 8, 'CenturyLink Field', 67135),
  (9, 9, 'University of Phoenix Stadium', 63400), (10, 10, 'Georgia Dome', 71228);
/*!40000 ALTER TABLE `Venue` ENABLE KEYS */;
/*--UNLOCK TABLES;*/;

/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;


