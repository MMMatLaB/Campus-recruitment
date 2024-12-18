-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cabdsp
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `major_has_direct`
--

DROP TABLE IF EXISTS `major_has_direct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major_has_direct` (
  `majormid` int NOT NULL,
  `directdirectID` int NOT NULL,
  `MDid` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MDid`),
  KEY `fk_major_has_direct_direct1_idx` (`directdirectID`),
  KEY `fk_major_has_direct_major1_idx` (`majormid`),
  CONSTRAINT `fk_major_has_direct_direct1` FOREIGN KEY (`directdirectID`) REFERENCES `direct` (`directID`),
  CONSTRAINT `fk_major_has_direct_major1` FOREIGN KEY (`majormid`) REFERENCES `major` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_has_direct`
--

LOCK TABLES `major_has_direct` WRITE;
/*!40000 ALTER TABLE `major_has_direct` DISABLE KEYS */;
INSERT INTO `major_has_direct` VALUES (1,1,1),(1,2,2),(1,3,3),(1,4,4),(2,5,5),(2,6,6),(2,7,7),(2,8,8),(3,9,9),(3,10,10),(3,11,11),(4,12,12),(4,13,13),(4,14,14),(4,15,15),(5,16,16),(5,17,17),(5,18,18),(5,19,19),(6,20,20),(6,21,21),(6,22,22),(6,23,23),(7,24,24),(7,25,25),(7,26,26),(8,27,27),(8,28,28),(8,29,29),(8,30,30),(9,31,31),(9,32,32),(9,33,33),(9,34,34),(10,35,35),(10,36,36),(10,37,37),(10,38,38),(11,39,39),(11,40,40),(11,41,41),(11,42,42),(12,43,43),(12,44,44),(12,45,45),(13,46,46),(13,47,47),(13,48,48),(13,49,49),(14,50,50),(14,51,51),(14,52,52),(14,53,53),(15,54,54),(15,55,55),(15,56,56),(16,57,57),(16,58,58),(16,59,59),(0,60,60),(0,61,61),(0,62,62),(0,63,63),(18,64,64),(18,65,65),(18,66,66),(18,67,67),(19,68,68),(19,69,69),(19,70,70),(19,71,71),(20,72,72),(20,73,73),(20,74,74),(20,75,75),(21,76,76),(21,77,77),(21,78,78),(21,79,79),(22,80,80),(22,81,81),(22,82,82),(22,83,83),(23,84,84),(23,85,85),(23,86,86),(23,87,87),(24,88,88),(24,89,89),(24,90,90),(24,91,91),(25,92,92),(25,93,93),(25,94,94),(25,95,95),(26,96,96),(26,97,97),(26,98,98),(26,99,99),(27,100,100),(27,101,101),(27,102,102),(27,103,103),(28,104,104),(28,105,105),(28,106,106),(28,107,107),(29,108,108),(29,109,109),(29,110,110),(29,111,111),(30,112,112),(30,113,113),(30,114,114),(30,115,115),(31,116,116),(31,117,117),(31,118,118),(31,119,119),(32,120,120),(32,121,121),(32,122,122),(32,123,123),(33,124,124),(33,125,125),(33,126,126),(33,127,127),(34,128,128),(34,129,129),(34,130,130),(34,131,131),(35,132,132),(35,133,133),(35,134,134),(35,135,135),(36,136,136),(36,137,137),(36,138,138),(36,139,139),(37,140,140),(37,141,141),(37,142,142),(37,143,143),(38,144,144),(38,145,145),(38,146,146),(38,147,147),(39,148,148),(39,149,149),(39,150,150),(39,151,151);
/*!40000 ALTER TABLE `major_has_direct` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 15:35:52
