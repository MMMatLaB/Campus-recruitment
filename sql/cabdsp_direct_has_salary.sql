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
-- Table structure for table `direct_has_salary`
--

DROP TABLE IF EXISTS `direct_has_salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_has_salary` (
  `directid` int DEFAULT NULL,
  `salaryid` int DEFAULT NULL,
  `num` varchar(45) DEFAULT NULL,
  `dsid` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`dsid`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direct_has_salary`
--

LOCK TABLES `direct_has_salary` WRITE;
/*!40000 ALTER TABLE `direct_has_salary` DISABLE KEYS */;
INSERT INTO `direct_has_salary` VALUES (2,1,'4',11),(2,2,'22',12),(2,3,'49',13),(2,4,'25',14),(2,5,'18',15),(2,6,'29',16),(8,1,'0',17),(8,2,'30',18),(8,3,'88',19),(8,4,'55',20),(8,5,'63',21),(8,6,'63',22),(5,2,'35',23),(5,3,'73',24),(5,4,'59',25),(5,5,'71',26),(5,6,'52',27),(5,1,'10',28),(6,1,'4',29),(6,2,'31',30),(6,3,'40',31),(6,4,'35',32),(6,5,'19',33),(6,6,'21',34),(7,1,'9',35),(7,2,'61',36),(7,3,'35',37),(7,4,'11',38),(7,5,'23',39),(7,6,'10',40),(1,1,'1',41),(1,2,'25',42),(1,3,'76',43),(1,4,'21',44),(1,5,'16',45),(1,6,'10',46),(3,1,'1',47),(3,2,'31',48),(3,3,'76',49),(3,4,'21',50),(3,5,'13',51),(3,6,'8',52),(4,1,'1',53),(4,2,'33',54),(4,3,'46',55),(4,4,'25',56),(4,5,'11',57),(4,6,'3',58),(9,1,'1',59),(9,2,'60',60),(9,3,'52',61),(9,4,'12',62),(9,5,'14',63),(9,6,'11',64),(10,1,'1',65),(10,2,'14',66),(10,3,'38',67),(10,4,'31',68),(10,5,'23',69),(10,6,'42',70),(11,1,'15',71),(11,2,'94',72),(11,3,'32',73),(11,4,'4',74),(11,5,'2',75),(11,6,'3',76),(12,1,'3',77),(12,2,'16',78),(12,3,'44',79),(12,4,'17',80),(12,5,'35',81),(12,6,'35',82),(13,1,'3',89),(13,2,'101',90),(13,3,'37',91),(13,4,'6',92),(13,5,'1',93),(15,1,'6',94),(15,2,'50',95),(15,3,'59',96),(15,4,'9',97),(15,5,'16',98),(15,6,'9',99),(14,1,'1',100),(14,2,'18',101),(14,3,'22',102),(14,4,'16',103),(14,5,'19',104),(14,6,'74',105),(16,2,'6',106),(16,3,'9',107),(16,4,'16',108),(16,5,'22',109),(16,6,'96',110),(17,2,'27',117),(17,3,'46',118),(17,4,'27',119),(17,5,'14',120),(17,6,'28',121),(18,2,'5',122),(18,3,'11',123),(18,4,'22',124),(18,5,'26',125),(18,6,'86',126),(60,2,'5',127),(60,3,'25',128),(60,4,'10',129),(60,5,'29',130),(60,6,'79',131),(61,1,'1',132),(61,2,'2',133),(61,3,'15',134),(61,4,'16',135),(61,5,'23',136),(61,6,'92',137),(62,1,'1',138),(62,2,'11',139),(62,3,'21',140),(62,4,'22',141),(62,5,'32',142),(62,6,'63',143),(63,2,'2',144),(63,3,'9',145),(63,4,'12',146),(63,5,'19',147),(63,6,'108',148);
/*!40000 ALTER TABLE `direct_has_salary` ENABLE KEYS */;
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
