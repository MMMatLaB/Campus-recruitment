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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `created_time` varchar(45) NOT NULL,
  `status` int NOT NULL COMMENT '启用,禁用',
  `gender` varchar(45) DEFAULT NULL,
  `realName` varchar(45) DEFAULT NULL,
  `major_mid` int NOT NULL,
  `corporation_idcorporation` int NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  KEY `fk_user_major1_idx` (`major_mid`),
  KEY `fk_user_corporation1_idx` (`corporation_idcorporation`),
  CONSTRAINT `fk_user_corporation1` FOREIGN KEY (`corporation_idcorporation`) REFERENCES `corporation` (`idcorporation`),
  CONSTRAINT `fk_user_major1` FOREIGN KEY (`major_mid`) REFERENCES `major` (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (57,'123456','111111','example@qq.com','18263721787','20240108',1,NULL,'管理员',-1,-1),(64,'110','111111','2661531273@qq.com','19935015646','20240110',1,'未知','张炜明',0,-1),(67,'20240110','88888888','2661531273@qq.com','19935015646','20240110',1,'未知','祝融',-1,1),(69,'123','88888888','','18263721787','20240110',1,'未知','李可',-1,-1),(70,'13213123','88888888','','1827131231','20240110',1,'未知','王子涵',-1,-1),(76,'1731832134','88888888','','19273829372','20240111',1,'未知','布克山',-1,-1),(77,'2213712863','11111111','nileonx@gmail.com','18263721787','20240111',1,'未知','倪建乔',1,-1),(78,'20240112','88888888','','18563925689','20240112',1,'未知','金普桂',-1,11),(79,'20240113','88888888','','18569234715','20240112',1,'未知','李一',-1,2),(80,'20240111','88888888','','16585236458','20240112',1,'未知','张伟',-1,13),(81,'20240114','88888888','','15896325648','20240112',1,'未知','施一夏',-1,0),(83,'2312131231','88888888','','18263721787','20240112',0,'未知','测试',0,-1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
