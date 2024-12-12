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
-- Table structure for table `direct_avgsalary`
--

DROP TABLE IF EXISTS `direct_avgsalary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_avgsalary` (
  `directAvgSalaryID` int NOT NULL AUTO_INCREMENT,
  `Salary` float NOT NULL,
  `datee` varchar(45) NOT NULL,
  `directName` varchar(45) NOT NULL,
  PRIMARY KEY (`directAvgSalaryID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direct_avgsalary`
--

LOCK TABLES `direct_avgsalary` WRITE;
/*!40000 ALTER TABLE `direct_avgsalary` DISABLE KEYS */;
INSERT INTO `direct_avgsalary` VALUES (4,198.443,'2024','前端开发'),(5,247.214,'2024','后端开发'),(6,305.313,'2024','软件架构'),(7,168.637,'2024','软件测试'),(8,86.1513,'2024','商务'),(9,94.7467,'2024','外贸'),(10,96.4195,'2024','市场营销'),(11,122.711,'2024','经济'),(12,135.696,'2024','专利'),(13,86.1653,'2024','司法'),(14,137.684,'2024','律师'),(15,105.613,'2024','法务'),(16,57.9998,'2024','统计'),(17,143.994,'2024','金融'),(18,81.9084,'2024','数学教育'),(19,205.24,'2024','数据分析'),(20,89.9833,'2024','人力资源'),(21,148.471,'2024','政策分析'),(22,57.252,'2024','社会工作'),(23,219.752,'2024','光学'),(24,220.767,'2024','声学'),(25,95.0608,'2024','物理教育'),(26,114.722,'2024','电子');
/*!40000 ALTER TABLE `direct_avgsalary` ENABLE KEYS */;
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
