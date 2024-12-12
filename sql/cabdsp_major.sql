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
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `major` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `majorName` varchar(45) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (-1,'非学生'),(0,'软件工程'),(1,'国际经济与贸易学'),(2,'法学'),(3,'社会学'),(4,'数学与应用数学'),(5,'应用物理学'),(6,'工程力学'),(7,'机械工程'),(8,'材料科学与工程'),(9,'能源与动力工程'),(10,'电气工程及其自动化'),(11,'电子科学与技术'),(12,'信息工程'),(13,'自动化'),(14,'计算机科学与技术'),(15,'工商管理'),(16,'金融学'),(18,'会计学'),(19,'微电子科学与工程'),(20,'车辆工程'),(21,'过程装备与控制工程'),(22,'核工程与核技术'),(23,'生物医学工程'),(24,'临床医学'),(25,'工业工程'),(26,'信息与计算科学'),(27,'思想政治教育'),(28,'应用化学'),(29,'统计学'),(30,'材料物理'),(31,'新能源科学与工程'),(32,'飞行器设计与工程'),(33,'口腔医学'),(34,'预防医学'),(35,'法医学'),(36,'劳动与社会保障'),(37,'财政学'),(38,'贸易经济'),(39,'电子商务');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 15:35:50
