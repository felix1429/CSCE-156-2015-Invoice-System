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

DROP TABLE IF EXISTS `Persons`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Persons` (
  `PersonID`        INT(11)     NOT NULL AUTO_INCREMENT,
  `PersonCode`      VARCHAR(20) NOT NULL,
  `AddressID`   INT(11)     NOT NULL,
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

  INSERT INTO `Persons` VALUES (1,'944c',1,'Castro','Starlin'),(2,'306a',2,'Sampson','Brock'),(3,'55bb',3,'0Brien','Miles'),(4,'2342',4,'OBrien','Miles'),
  (5,'aef1',5,'Gekko','Gordon'),(6,'321f',6,'Fox','Bud'),(7,'ma12',7,'Sveum','Dale'),(8,'321nd',8,'Hartnell','William'),(9,'nf32a',9,'Pertwee','Jon'),
  (10,'231',10,'Baker','Tom'),(11,'6doc',11,'Hurndall','Richard'),(12,'321dr',12,'Baker','C.'),(13,'1svndr',13,'McCoy','Sylvester'),(14,'1231st',14,'McGann','Paul'),
    (15,'nwdoc1',15,'Ecceleston','Chris'),(16,'2ndbestd',16,'Tennant','David'),(17,'wrddoc',17,'Smith','Matt'),(18,'bbchar',18,'Ehrmantraut','Kaylee'),
    (19,'doc05',19,'Davison','Peter');

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

INSERT INTO `Email` VALUES(1,1,'scastro@cubs.com,starlin_castro13@gmail.com'),(2,2,'brock_f_sampson@gmail.com,bsampson@venture.com'),(3,3,'obrien@ds9.com,obrien@enterprise.gov'),
  (4,4,''),(5,5,''),(6,6,'bfox@gmail.com,csheen@crazy.net'),(7,7,'sveum@cub.com'),(8,8,'whartnell@doctors.com,dr@who.com'),(9,9,'ptroug@cse.unl.edu,ptrou32@unl.edu'),
  (10,10,'jpet@whofan.com'),(11,11,'famousdoc@who.com,tbaker@cse.unl.edu,mostfamous@whovian.com,thedoctor@bbc.com'),(12,12,'rhurndall@cse.unl.edu,richard@unl.edu'),
  (13,13,'dr@baker.com'),(14,14,'slyguy@hotmail.com,mccoy@whofan.com'),(15,15,'pmcgann@mlb.com,foo@bar.com,pmc@unl.edu'),(16,16,'newguy@whovian.com'),
  (17,17,'actor@shakespeare.com,tdavid@unl.com'),(18,18,'msmith@who.com,thedoc@cse.unl.edu'),(19,19,''),(20,20,'');

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Address` (
  `AddressID` INT(11)     NOT NULL AUTO_INCREMENT,
  `Street`        VARCHAR(30) NOT NULL,
  `City`          VARCHAR(30) NOT NULL,
  `CityState`     VARCHAR(30) NOT NULL,
  `ZipCode`       VARCHAR(20) NOT NULL,
  `Country`       VARCHAR(20) NOT NULL,
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

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;

CREATE TABLE `Customers` (
  `CustomerID`    INT(11)     NOT NULL AUTO_INCREMENT,
  `AddressID` INT(11)     NOT NULL,
  `CustomerCode`  VARCHAR(20) NOT NULL,
  `CustomerType`  VARCHAR(2)  NOT NULL,
  `PersonCode`    VARCHAR(20) NOT NULL,
  `PersonID`      INT(11)     NOT NULL,
  `CustomerName`  VARCHAR(40) NOT NULL,
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
