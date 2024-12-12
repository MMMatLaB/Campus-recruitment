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
-- Table structure for table `major_companymarket`
--

DROP TABLE IF EXISTS `major_companymarket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major_companymarket` (
  `mcmid` int NOT NULL AUTO_INCREMENT,
  `num` int DEFAULT NULL,
  `marketid` int DEFAULT NULL,
  `majorid` int DEFAULT NULL,
  PRIMARY KEY (`mcmid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_companymarket`
--

LOCK TABLES `major_companymarket` WRITE;
/*!40000 ALTER TABLE `major_companymarket` DISABLE KEYS */;
INSERT INTO `major_companymarket` VALUES (4,189,1,1),(5,10,2,1),(6,17,3,1),(7,6,4,1),(8,3,5,1),(9,3,6,1),(10,16,7,1),(11,136,8,1),(12,297,1,2),(13,18,2,2),(14,15,3,2),(15,11,4,2),(16,1,5,2),(17,5,6,2),(18,25,7,2),(19,190,8,2),(20,146,1,3),(21,9,2,3),(22,15,3,3),(23,4,4,3),(24,1,5,3),(25,5,6,3),(26,35,7,3),(27,87,8,3),(28,149,1,4),(29,15,2,4),(30,9,3,4),(31,19,4,4),(32,6,5,4),(33,6,6,4),(34,50,7,4),(35,153,8,4),(36,163,1,5),(37,16,2,5),(38,28,3,5),(39,17,4,5),(40,6,5,5),(41,2,6,5),(42,71,7,5),(43,123,8,5),(44,145,1,0),(45,37,2,0),(46,38,3,0),(47,26,4,0),(48,15,5,0),(49,18,6,0),(50,84,7,0),(51,126,8,0);
/*!40000 ALTER TABLE `major_companymarket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 15:35:51
