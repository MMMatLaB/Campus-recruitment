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
-- Table structure for table `career`
--

DROP TABLE IF EXISTS `career`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `career` (
  `careerID` int NOT NULL AUTO_INCREMENT,
  `careerName` varchar(45) NOT NULL,
  `salarymax` varchar(45) NOT NULL,
  `educationlevel` varchar(45) NOT NULL,
  `workyear` varchar(45) NOT NULL,
  `careerInfo` text NOT NULL,
  `workplace` varchar(45) NOT NULL,
  `corporationidcorporation` int NOT NULL,
  `status` int NOT NULL,
  `creatdtime` varchar(45) NOT NULL,
  `salarymin` varchar(45) NOT NULL,
  PRIMARY KEY (`careerID`),
  KEY `fk_career_corporation1_idx` (`corporationidcorporation`),
  CONSTRAINT `fk_career_corporation1` FOREIGN KEY (`corporationidcorporation`) REFERENCES `corporation` (`idcorporation`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `career`
--

LOCK TABLES `career` WRITE;
/*!40000 ALTER TABLE `career` DISABLE KEYS */;
INSERT INTO `career` VALUES (11,'字节跳动工程师','10','本科','经验不限','暂无','河北省/秦皇岛市',2,1,'2024-01-10 20:12:26','9'),(13,'C++工程师','13','初中及以下','应届生','C++工程师嗷，帖子们','山西省/长治市',2,0,'2024-01-10 20:04:41','6'),(14,'大数据工程师','100','初中及以下','应届生','大大大是','湖南省/邵阳市',2,1,'2024-01-10 22:57:42','2'),(15,'大数据工程师','8','中专/中技','经验不限','大数据工程师','山西省/晋城市',1,1,'2024-01-11 09:53:06','4'),(16,'C++工程师','7','中专/中技','在校生','C++工程师','河北省/秦皇岛市',1,1,'2024-01-12 10:46:12','4'),(17,'无线通信算法工程师','35','本科','不限','一、岗位职责：\n1.负责Connectivity WCN/WLAN等无线通信制式的链路级仿真平台功能开发，仿真链路优化和验证\n2.负责Connectivity WCN/WLAN等通信制式物理层算法设计和选型、软硬件分工、性能优化相关工作\n3.负责算法模块浮点和定点开发、优化及仿真验证\n4.支持无线通信算法向软硬件的工程映射\n二、任职要求：\n1.通信，电子，电磁场与微波，计算机，数学等相关专业硕士及以上学历\n2.熟悉通信和信号处理理论','四川省/成都市',1,1,'2024-01-12 10:36:06','30'),(18,'软件测试专家','35','本科','5-10年','岗位职责：\n1、负责手机软件的质量保障工作，制定合理的测试方案和测试策略，保证测试工作高效、有序进行；\n2、对项目中的质量问题进行监控、追查和定位，对产品与项目提出优化意见，提高项目的整体效率；\n3、通过自动化、通用工具平台建设等工程效率提升的方式支持相关业务产品线的质量控制；\n4、围绕公司重点业务方向，做能力建设和技术储备。\n岗位要求：\n1、本科及以上学历，计算机、通信相关专业，8年以上手机软件或移动app测试经验；\n2、熟悉C/C++/Java/Python等至少一种编程语言；\n3、熟悉Android系统，Android自动化测试框架，能够独立开发测试工具或开源工具的二次开发；\n4、具备优秀的学习能力、分析能力、技术钻研能力，有制定合理的测试方案和测试策略的能力，能够完成问题定位与任务推动\n5、善于团队合作，理解和适应变化，以结果和行动为准则，努力追求成功；\n6、主动性强，对项目质量负责，有主人翁精神，能够不断挖掘改进方向，推动项目持续进步。','北京市/市辖区',1,1,'2024-01-12 10:37:02','0'),(19,'天线工程师','26','本科','应届生','岗位描述：\n1、负责产品天线的前期方案评估，实现和验证等相关工作；\n2、按时保量完成项目的天线设计及验证工作；\n3、对于项目中问题点能够给出分析和解决意见；\n4、数据处理，给出有意义的报告和结论；\n5、天线相关的工厂支持；\n6、发掘天线新技术相关方向，并协助高级别工程师完成预研工作。\n任职要求：\n1、电磁场、通信相关专业本科及以上学历；\n2、具有扎实的电磁场微波理论基础，熟悉数字通信基本原理；\n3、具有较强的动手能力，熟悉频谱仪、矢量网络分析仪等射频仪表的使用；\n4、熟悉常用的EDA设计工具，熟练运用射频仿真工具进行电路分析与设计；\n5、学习能力较强。','北京市/市辖区',1,1,'2024-01-12 10:38:20','0'),(20,'SRAM设计','60','本科','经验不限','工作内容： \n1.负责SRAM的ReChar\n2.设计SRAM，支撑高性能/低功耗/低成本的设计需求\n任职资格： \n1.微电子、计算机等相关专业本科及以上学历 \n2.五年以上先进工艺SRAM设计经验，有成功量产经历。\n3.具有良好的沟通与组织协调能力','广东省/深圳市',1,1,'2024-01-12 10:40:18','30'),(21,'CallCenter运维工程师','15','本科','经验不限','【岗位职责】\n1. 负责呼叫中心现场桌面运维（PC、操作系统），提供软硬件IT支持服务；\n2. 负责呼叫中心系统（热线、在线）组织、角色、账号权限的管理与维护；\n3. 负责呼叫中心系统平台的日常运维（搭建、维护、配置、优化等），保障呼叫中心系统的正常运转；\n4. 负责呼叫中心系统（400、IVR、CRM、录音、网关等）软硬件日常监测、巡检、维护工作，突发事件响应与处理；\n5. 以业务目标为导向，高质量完成呼叫中心项目方案的规划、实施、交付；\n6. 根据业务发展和技术变革，对呼叫中心系统平台提供合理的改善方案并组织实施；\n7. 配合安全、质量等部门，完成呼叫中心系统安全改造和质量提升；\n8. 通过技术手段提高呼叫中心平台运维效率，降低运维成本；\n9. 定期编写并维护呼叫中心应用服务运维文档；\n  \n【职位要求】 \n1. 计算机相关专业本科及以上学历，具备3年以上大型呼叫中心运维经验优先；\n2. 深度理解并掌握呼叫中心解决方案及系统架构，了解呼叫中心核心组件及相关技术；\n3. 具备良好的技术水平和较强的逻辑分析、判断和解决问题能力；\n4. 具备Avaya、Genesys及国产呼叫中心系统从0到1的项目经验；\n5. 熟悉IVR语音系统，具备一定的IVR流程编写经验；\n6. 熟练掌握Window Server、Linux系统的安装、调试、配置\n7. 熟悉MySQL数据库，了解redis、tomcat等中间件产品；\n8. 具备良好的团队协作精神、沟通协调能力，具有高度的责任心，较强的服务意识，工作积极主动；','河北省/保定市',1,1,'2024-01-12 10:41:28','10'),(22,'用户数据分析工程师','50','本科','应届生','用户数据分析，用户画像','上海市/市辖区',1,1,'2024-01-12 10:51:19','30'),(23,'美团骑手','8','不限','在校生','美团骑手','陕西省/西安市',13,1,'2024-01-12 10:55:36','5'),(24,'测试岗位','10','高中','应届生','测试解释','内蒙古自治区/赤峰市',1,1,'2024-01-12 12:57:49','7'),(25,'测试岗位','5','中专/中技','在校生','测试','河北省/唐山市',13,1,'2024-01-12 13:40:15','3');
/*!40000 ALTER TABLE `career` ENABLE KEYS */;
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
