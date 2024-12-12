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
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `idResume` int NOT NULL AUTO_INCREMENT,
  `workplace` varchar(45) NOT NULL,
  `educationlevel` varchar(45) NOT NULL,
  `age` varchar(45) NOT NULL,
  `workyear` varchar(45) NOT NULL,
  `idealcity` varchar(45) DEFAULT NULL,
  `idealsalary` varchar(45) DEFAULT NULL,
  `idealcareer` varchar(45) DEFAULT NULL,
  `idealdomain` varchar(45) DEFAULT NULL,
  `score` int DEFAULT NULL COMMENT '简历打分',
  `complete` float DEFAULT NULL COMMENT '完成度',
  `user_userID` int unsigned NOT NULL,
  `modifiedBit` int NOT NULL,
  `description` text,
  `rname` varchar(45) DEFAULT NULL,
  `rTel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idResume`),
  KEY `fk_Resume_user1_idx` (`user_userID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,'成都','本科','25','2','西安','20','','',90,90,30,0,'本科期间成绩优异，熟悉springboot、vue、sql、springCloud等单词的拼写','jh','65656'),(13,'','本科','23','1年以内','滕州市','10k-15k','','企业服务',0,0,64,1,'本人张炜明，23岁，本科毕业。我性格开朗活泼，兴趣广泛，在校期间积极参与各类活动并成功策划组织多场校园活动，这不仅锻炼了我在团队工作中的组织协调能力和执行力，也充分展示了我的积极性和责任感。\n\n对待职业发展，我始终保持积极向上的态度，勇于接受挑战，热衷于通过攻克高难度任务来不断提升自我能力与综合素质，且具备较强的学习适应能力，乐于探索新技术新知识，不断追求进步和突破。\n\n作为一位严谨的时间管理者，无论面对何种工作任务，我都能够以坚定认真的态度去完成，不畏艰难困苦，不怕挫折打击，始终保持坚韧不拔的精神风貌。在人际交往中，我真诚待人，擅长沟通交流，能够有效整合资源，营造和谐的工作氛围。\n\n我期望能在滕州市的企业服务行业，特别是在电子商务领域获得一个宝贵的学习与锻炼机会，加入贵公司共同发展。我将全力以赴，珍视每一个成长的机会，为贵公司的发展贡献自己的力量。如有意向深入了解或进一步联系，请随时拨打我的电话：1823131232。','张炜明','1823131232'),(14,'','本科','21','5-10年','滕州市','','','',0,0,77,-1,'','倪建乔','18263721787');
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
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
