-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: melonltd_nabercDB
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_info`
--

DROP TABLE IF EXISTS `account_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_info` (
  `account` varchar(100) NOT NULL,
  `phone` varchar(30) NOT NULL COMMENT '電話號碼',
  `account_uuid` varchar(200) NOT NULL COMMENT 'date +”_”+ UUID\n帳號唯一值',
  `restaurant_uuid` varchar(200) DEFAULT NULL,
  `name` varchar(50) NOT NULL COMMENT '名稱',
  `password` varchar(50) NOT NULL COMMENT '密碼',
  `email` varchar(100) NOT NULL COMMENT '電子郵箱',
  `address` varchar(250) DEFAULT NULL COMMENT '地址',
  `identity` varchar(20) NOT NULL COMMENT '身份\n‘ELEMENTARY'',\n''SENOR'',\n''JUNOR'',\n''UNIVERSITY'',\n''NON_STUDENT'',\n''SELLERS''',
  `school_name` varchar(50) DEFAULT NULL COMMENT '學校名稱\n身份為 ’SENOR’, ’JUNOR’, ’UNIVERSITY’  不可為空',
  `bonus` varchar(20) NOT NULL DEFAULT '0' COMMENT '所得紅利',
  `level` varchar(20) DEFAULT NULL COMMENT '級別\n‘MANAGE'',\n''EMPLOYEE’\n\n身份為 ’SELLERS’ 不可為空',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '帳號是否啟用',
  `is_login` varchar(1) NOT NULL DEFAULT 'N',
  `login_date` varchar(30) DEFAULT NULL COMMENT '登入成功記錄時間\n‘2014-10-27T08:09:30.914Z’',
  `photo` varchar(2000) DEFAULT NULL COMMENT '頭像 URL',
  `photo_type` varchar(10) DEFAULT NULL COMMENT '圖片類型',
  `birth_day` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account`),
  KEY `index2` (`account_uuid`),
  KEY `index3` (`phone`),
  KEY `index4` (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES ('0928297076','0928297076','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157',NULL,'Developer','a123456','jnswcy@gmail.com','Addw','NON_STUDENT',NULL,'93','','Y','Y','2018-07-16T17:37:49.3990Z','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/user%2FUSER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157.jpg?alt=media&token=407f5b2c-016f-4cdb-a47e-7abb78215383',NULL,'1984-06-20'),('0928297077','0928297077','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1158',NULL,'EvanWang2','a123456','jnswcy@gmail.com','Addw','NON_STUDENT',NULL,'225','','Y','Y','2018-06-28T14:23:54.9150Z','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/user%2FUSER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157.jpg?alt=media&token=a6ef042c-1a09-42bf-bf78-07f614dd2f06',NULL,'1984-06-20'),('0928297078','0928297078','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1159',NULL,'EvanWang3','a123456','jnswcy@gmail.com','Addw','NON_STUDENT',NULL,'160','','Y','Y','2018-06-28T15:03:44.3480Z','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/user%2FUSER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157.jpg?alt=media&token=a6ef042c-1a09-42bf-bf78-07f614dd2f06',NULL,'1984-06-20'),('admin','0999999999','ADMIN_20180709_104811_950_4812c9c9-218e-4f26-9ba9-66c199ca81e3',NULL,'naber_admin','admin','admin@gmail.com','admin','ADMIN',NULL,'0','ADMIN','Y','Y','2018-07-09T10:48:11.9520Z',NULL,NULL,NULL),('demo','0000000000','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a',NULL,'DEMO','d654321','demo@gmail.com','demo','NON_STUDENT',NULL,'0',NULL,'Y','Y','2018-07-12T17:29:24.5200Z',NULL,NULL,NULL),('NER-1712XX1','0928297072','SELLER_20180625_115133_339_a893434a-d52d-4395-a637-d683e80ac266','RESTAURANT_20180625_115446_901_822510ad-bf95-4a93-9bde-1b3bcef83655','朝司暮想-永豐店','a123456','evan.wang2@melonltd.com.tw','桃園區八德區永豐路546號','SELLERS',NULL,'0','MANAGE','Y','Y','2018-07-04T16:38:05.4540Z',NULL,NULL,NULL),('NER-18X1X14','0928297071','SELLER_20180625_115133_341_7cace495-eb20-4b50-9f48-20efaa33232a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','清玉-民族店','s123456','evan.wang1@melonltd.com.tw','桃園市平鎮區文化街217號','SELLERS',NULL,'0','MANAGE','Y','Y','2018-07-16T12:10:17.6850Z',NULL,NULL,NULL),('NER-18X1X15','0928297073','SELLER_20180625_115133_341_7fb8f034-aac7-473f-8487-b8acbc7e135e','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','健行科大-這一刻','a123456','evan.wang3@melonltd.com.tw','桃園市中壢區龍岡路二段41號','SELLERS',NULL,'0','MANAGE','Y','Y','2018-06-21T11:40:45.5860Z',NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `ad_uuid` varchar(200) NOT NULL COMMENT '廣告 UUID',
  `title` varchar(200) DEFAULT NULL COMMENT '標題',
  `content_text` varchar(400) DEFAULT NULL COMMENT '內文',
  `photo` varchar(2000) DEFAULT NULL COMMENT '廣告圖片url',
  `photo_type` varchar(10) DEFAULT NULL COMMENT '圖片類型',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否啟用',
  `create_date` varchar(30) NOT NULL COMMENT '建立時間',
  PRIMARY KEY (`ad_uuid`),
  KEY `index2` (`ad_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES ('AD_20180704_134357_562_a050b828-2504-4c1c-9056-6a620be969ee','title','text','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/naber%2Fad%2FAD_20180704_134357_562_a050b828-2504-4c1c-9056-6a620be969ee.jpg?alt=media&token=18456790-9e00-4d74-9331-9c53ffa34781','jpg','Y','2018-07-04T13:54:16.0700Z'),('AD_20180704_134357_564_a0b04170-3a6d-499e-a247-3fd55607c7de','title','text','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/naber%2Fad%2FAD_20180704_134357_564_e6c08d60-5595-44ec-9d37-591888ed8f92.jpg?alt=media&token=ca3f57e9-0486-48b7-be06-e1e213cb34db','jpg','Y','2018-07-04T13:54:16.0700Z'),('AD_20180704_134357_564_e6c08d60-5595-44ec-9d37-591888ed8f92','title','text','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/naber%2Fad%2FAD_20180704_134357_564_a0b04170-3a6d-499e-a247-3fd55607c7de.jpg?alt=media&token=5f8edbee-b6d8-43b9-b425-68b05cedcffb','jpg','Y','2018-07-04T13:54:16.0700Z');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_rel`
--

DROP TABLE IF EXISTS `category_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_rel` (
  `category_uuid` varchar(200) NOT NULL COMMENT '餐館系列 UUID',
  `restaurant_uuid` varchar(200) NOT NULL COMMENT '餐館 UUID',
  `category_name` varchar(50) NOT NULL COMMENT '系列名稱',
  `status` varchar(25) NOT NULL DEFAULT 'OPEN' COMMENT '是否顯示及下單\n“OPEN’,\n‘CLOSE’”',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '系列是否刪除\nY/N',
  `create_date` varchar(30) NOT NULL,
  PRIMARY KEY (`category_uuid`),
  KEY `index2` (`category_uuid`),
  KEY `index3` (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_rel`
--

LOCK TABLES `category_rel` WRITE;
/*!40000 ALTER TABLE `category_rel` DISABLE KEYS */;
INSERT INTO `category_rel` VALUES ('RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','原味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','調味茶','OPEN','Y','2018-06-22T12:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_0b216a9c-ce76-4992-94e0-dfc082632bd8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','奶味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_722a2f57-d04c-43e0-b6e0-61a9dfb29eb1','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','鮮味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_e82e49f5-68f3-4bd6-a2c4-7e2e9dcdc94f','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','鮮奶茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_873_f63b88be-b0aa-408b-a994-009a5693b106','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','口感茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_874_e572e054-c335-41b5-b28a-9b72cba22154','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','季節限定茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_874_e7c6e399-f808-49df-94eb-5007235b0f42','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','健康好醋','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','鮮奶茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_442_40947052-ba3e-4689-aa30-971b1bc92c25','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','品味鮮茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_442_6790b3f1-2d78-4607-b4d0-068781dedaae','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','香濃奶茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_442_7d1d5d2d-18f5-4fbc-a5b5-67455a29c8ef','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','水果茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_442_e3e7ea95-8e10-40cf-9c45-cb92cb569bde','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','鮮榨檸檬','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124434_443_f3061626-712b-4f10-abd4-7b06108eb971','RESTAURANT_20180625_120756_787_28a328ed-5bdd-4f5f-9975-5173a5369eeb','熱熱喝','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','RESTAURANT_20180625_115446_901_822510ad-bf95-4a93-9bde-1b3bcef83655','吐司','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124723_863_1b77be51-da94-4706-a2c8-fa0f1767bf0d','RESTAURANT_20180625_115446_901_822510ad-bf95-4a93-9bde-1b3bcef83655','大滿足吃法','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180625_124723_863_8458f543-3bd4-4238-b9ed-a8eab3ac1778','RESTAURANT_20180625_115446_901_822510ad-bf95-4a93-9bde-1b3bcef83655','飲料','OPEN','Y','2018-06-22T11:48:23.5580Z'),('RESTAURANT_CATEGORY_20180709_152511_739_07cea179-8f12-46c5-b9b1-3adad75ecbe7','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','特調','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152511_739_0b8d5d5f-b019-4d74-bdc7-f11a83fafd8b','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','清涼','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152511_739_0d00b529-cb65-45ad-99cc-be7d4ce73e37','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','純奶','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152511_739_5f36ccff-0485-4270-87c3-1f8c04464cf6','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','水果','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152511_739_ba0a7ca8-ca74-4d67-ac51-c8a04cb69d3c','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','荔枝','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152511_739_e68a0438-9f7d-4b96-ab2d-cd7881d8aec3','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','AA','OPEN','Y','2018-07-09T15:25:11.6090Z'),('RESTAURANT_CATEGORY_20180709_152649_356_407fa61d-9299-4f98-8a24-9091f4311b48','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','清2涼','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_152649_356_597c69f4-6072-426b-aaa4-1b0581bc9973','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','特2調','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_152649_356_a309bf11-c80a-4eb1-a957-1d246635dc33','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','純2奶','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_152649_356_ea0fb98c-5b92-461a-bb02-5aab8b288b23','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','荔2枝','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_152649_356_ee6889fd-ff03-4095-b064-8bca11e0ad87','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','水2果','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_152649_357_b45aa52d-3652-48db-9e73-6ea5c59c8fc6','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','A2A','OPEN','Y','2018-07-09T15:26:49.3550Z'),('RESTAURANT_CATEGORY_20180709_160531_424_32cfd85d-d204-4725-8e6f-a01b53def1ef','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','特2調','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_160531_425_3fd765a6-e311-4347-af4d-86408a47408d','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','清2涼','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_160531_425_537c2210-31d3-4e95-a883-5e391a9ed2f9','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','荔2枝','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_160531_425_9bd882ec-b812-4a1d-840b-aae7d8296fdd','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','A2A','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_160531_425_d0cd5bd9-0619-4b1c-9953-f378b35781c6','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','純2奶','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_160531_425_e24d020f-9d3d-437c-aa02-22292085cf5c','RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','水2果','OPEN','Y','2018-07-09T16:05:31.4190Z'),('RESTAURANT_CATEGORY_20180709_164607_580_44322a86-9aa0-44d7-b86f-2dc8b924b873','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','清涼','OPEN','Y','2018-07-09T16:46:07.4830Z'),('RESTAURANT_CATEGORY_20180709_164607_580_47a5adc3-51d4-4c2b-bd78-fe0949c7702a','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','純奶','OPEN','Y','2018-07-09T16:46:07.4830Z'),('RESTAURANT_CATEGORY_20180709_164607_580_90e0151b-a6fb-4d08-908f-dd07fb6d146b','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','特調','OPEN','Y','2018-07-09T16:46:07.4830Z'),('RESTAURANT_CATEGORY_20180709_164607_581_0086185d-a324-45c7-8212-72b86938a6c9','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','AA','OPEN','Y','2018-07-09T16:46:07.4830Z'),('RESTAURANT_CATEGORY_20180709_164607_581_0791400f-9175-4d34-8c85-3a9fd2dca96b','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','水果','OPEN','Y','2018-07-09T16:46:07.4830Z'),('RESTAURANT_CATEGORY_20180709_164607_581_212c99ef-f999-487a-a41e-6cb09a1a3323','RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','荔枝','OPEN','Y','2018-07-09T16:46:07.4830Z');
/*!40000 ALTER TABLE `category_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_info`
--

DROP TABLE IF EXISTS `food_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food_info` (
  `food_uuid` varchar(200) NOT NULL COMMENT '食物品項 UUID',
  `category_uuid` varchar(200) NOT NULL COMMENT '餐館系列 UUID',
  `food_name` varchar(50) NOT NULL COMMENT '品項名稱',
  `default_price` varchar(45) DEFAULT NULL,
  `photo` varchar(2000) DEFAULT NULL COMMENT '食物品項圖片	url',
  `photo_type` varchar(10) DEFAULT NULL COMMENT '圖片類型	png,jpg,svg,pdf,gif',
  `food_data` mediumtext NOT NULL COMMENT 'Json 儲存食品相關數據	預設，價格，名稱，附加，規格，需求',
  `status` varchar(25) NOT NULL DEFAULT 'OPEN' COMMENT '是否顯示及下單	\n“OPEN’,\n‘CLOSE’”',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '系列是否刪除	Y/N',
  `create_date` varchar(30) NOT NULL,
  PRIMARY KEY (`food_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_info`
--

LOCK TABLES `food_info` WRITE;
/*!40000 ALTER TABLE `food_info` DISABLE KEYS */;
INSERT INTO `food_info` VALUES ('FOOD_20180622_121236_779_f77346fe-ee65-4bda-be68-64c36b4c9b25','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','一品紅茶','25','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=a443d757-f8a9-400e-9012-171e669d981c',NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"},{\"name\":\"熱小\",\"price\":\"25\"},{\"name\":\"熱大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','CLOSE','Y','2018-06-22T12:12:36.7830Z'),('FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','茉莉鮮綠茶','25',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"},{\"name\":\"熱小\",\"price\":\"25\"},{\"name\":\"熱大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:15:51.0640Z'),('FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','台灣青茶','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"},{\"name\":\"熱小\",\"price\":\"30\"},{\"name\":\"熱大\",\"price\":\"35\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"20\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:25:00.1470Z'),('FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','安溪鐵觀音','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"},{\"name\":\"熱小\",\"price\":\"30\"},{\"name\":\"熱大\",\"price\":\"35\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:49:28.5910Z'),('FOOD_20180622_125004_386_df2582d8-4af7-4264-b1ea-e87951a91b8d','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','冬瓜茶','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','CLOSE','Y','2018-06-22T12:50:04.3860Z'),('FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','冰釀陳梅','40',NULL,NULL,'{\"food_photo\":\"\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"},{\"name\":\"熱小\",\"price\":\"40\"},{\"name\":\"熱大\",\"price\":\"50\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:51:57.3940Z'),('FOOD_20180622_125242_436_3f4607f4-2386-4ded-8612-08fe99e2176c','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','蜂蜜紅茶','40',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"45\"},{\"name\":\"熱小\",\"price\":\"40\"},{\"name\":\"熱大\",\"price\":\"50\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:52:42.4360Z'),('FOOD_20180622_125354_107_81ec4b1c-7827-4efe-b15b-c93cad62dc25','RESTAURANT_CATEGORY_20180622_114923_387_722a2f57-d04c-43e0-b6e0-61a9dfb29eb1','翡翠檸檬','55',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_376_23d099df-49a4-4363-bf96-611bcf602fe3','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','沙拉蛋吐司','40',NULL,NULL,'{\"scopes\":[{\"name\":\"沙拉蛋吐司\",\"price\":\"40\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_378_3518e272-c5df-4c18-937a-57b5bbd746e1','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','肉鬆蛋吐司','40',NULL,NULL,'{\"scopes\":[{\"name\":\"肉鬆蛋吐司\",\"price\":\"40\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_378_63f9abc4-eadd-4b50-990f-f4075345a07a','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','肉排蛋吐司','45',NULL,NULL,'{\"scopes\":[{\"name\":\"肉排蛋吐司\",\"price\":\"45\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_378_c6efb826-efe1-4a7a-b536-5ecb6e72ac02','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','培根蛋吐司','50',NULL,NULL,'{\"scopes\":[{\"name\":\"培根蛋吐司\",\"price\":\"50\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_378_e08d7192-7e75-43b4-b5bf-016275efff1f','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','蛋吐司','25',NULL,NULL,'{\"scopes\":[{\"name\":\"蛋吐司\",\"price\":\"25\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_379_1c1f42d7-a298-4f99-8f5c-f0e14bd6cbc8','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','雞排蛋吐司','50',NULL,NULL,'{\"scopes\":[{\"name\":\"雞排蛋吐司\",\"price\":\"50\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_379_723a2247-3c66-458f-9e57-ffe36350e298','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','火腿起司蛋吐司','40',NULL,NULL,'{\"scopes\":[{\"name\":\"火腿起司蛋吐司\",\"price\":\"40\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_379_789a6ce5-03c4-452e-9f19-e6a4076b92af','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','鮪魚蛋吐司','50',NULL,NULL,'{\"scopes\":[{\"name\":\"鮪魚蛋吐司\",\"price\":\"50\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_379_9465bf01-a5d1-4413-aac8-88385a56d894','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','起司蛋吐司','35',NULL,NULL,'{\"scopes\":[{\"name\":\"起司蛋吐司\",\"price\":\"35\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132356_379_a7385b3e-a389-4a40-a259-95aa1162a3dd','RESTAURANT_CATEGORY_20180625_124723_861_f4a0b55e-30b0-4ce0-8fb1-8c32b4a75a46','辣味肉排蛋吐司','45',NULL,NULL,'{\"scopes\":[{\"name\":\"辣味肉排蛋吐司\",\"price\":\"45\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132855_165_d76629ea-962a-410d-b828-09a59aaecdcb','RESTAURANT_CATEGORY_20180625_124723_863_1b77be51-da94-4706-a2c8-fa0f1767bf0d','肉排蛋吐司+鮪魚','70',NULL,NULL,'{\"scopes\":[{\"name\":\"肉排蛋吐司+鮪魚\",\"price\":\"70\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132855_167_0f0f2de8-e2a6-4dd2-96e0-da906e6bd389','RESTAURANT_CATEGORY_20180625_124723_863_1b77be51-da94-4706-a2c8-fa0f1767bf0d','肉排蛋吐司+薯泥','55',NULL,NULL,'{\"scopes\":[{\"name\":\"肉排蛋吐司+薯泥\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_132855_167_c9bb631c-ce45-48a1-9748-4d8726d71192','RESTAURANT_CATEGORY_20180625_124723_863_1b77be51-da94-4706-a2c8-fa0f1767bf0d','特製總匯(吐司+肉排+蛋+吐司+火腿+起司+吐司)','70',NULL,NULL,'{\"scopes\":[{\"name\":\"特製總匯(吐司+肉排+蛋+吐司+火腿+起司+吐司)\",\"price\":\"70\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_133157_996_8f07cf81-f55b-4dcc-a5fe-d952e374b792','RESTAURANT_CATEGORY_20180625_124723_863_1b77be51-da94-4706-a2c8-fa0f1767bf0d','特製特製DOUBLE滿足','65',NULL,NULL,'{\"scopes\":[{\"name\":\"特製特製DOUBLE滿足\",\"price\":\"65\"}],\"opts\":[],\"demands\":[{\"name\":\"DOUBLE\",\"datas\":[{\"name\":\"雞肉+肉排\"},{\"name\":\"肉排+肉排\"},{\"name\":\"雞肉+雞肉\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_133919_441_b8a50294-6c5d-4a9d-8a2c-f56153802d8d','RESTAURANT_CATEGORY_20180625_124723_863_8458f543-3bd4-4238-b9ed-a8eab3ac1778','奶茶','20',NULL,NULL,'{\"scopes\":[{\"name\":\"奶茶\",\"price\":\"20\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"冰\"},{\"name\":\"熱\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_133919_443_332f2fed-d494-4621-b522-050086a5b059','RESTAURANT_CATEGORY_20180625_124723_863_8458f543-3bd4-4238-b9ed-a8eab3ac1778','紅茶','15',NULL,NULL,'{\"scopes\":[{\"name\":\"紅茶\",\"price\":\"15\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"冰\"},{\"name\":\"熱\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_499_16665eb4-576c-4425-a368-ae4d6d035e7a','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','這一刻灰奶茶','75',NULL,NULL,'{\"scopes\":[{\"name\":\"這一刻灰奶茶\",\"price\":\"75\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_501_95b0bc02-b432-4632-a60a-865cec7e01d1','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','冬瓜鮮奶茶','50',NULL,NULL,'{\"scopes\":[{\"name\":\"冬瓜鮮奶茶\",\"price\":\"50\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_501_9b17b10b-18c7-43a2-90b9-02e1c72ae5a7','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','抹茶鮮奶茶','60',NULL,NULL,'{\"scopes\":[{\"name\":\"抹茶鮮奶茶\",\"price\":\"60\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_501_c21b2cb8-9ac2-4f16-ae4f-f3df29298f03','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','紅茶鮮奶茶','50',NULL,NULL,'{\"scopes\":[{\"name\":\"抹茶鮮奶茶\",\"price\":\"60\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_502_182db530-de2e-4a27-86e0-8091c3b7ebfe','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','可可鮮奶茶','55',NULL,NULL,'{\"scopes\":[{\"name\":\"可可鮮奶茶\",\"price\":\"55\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_502_314335fd-2eb5-4b9f-a894-5ad4bc58f178','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','布丁紅茶鮮奶茶','65',NULL,NULL,'{\"scopes\":[{\"name\":\"布丁紅茶鮮奶茶\",\"price\":\"65\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_502_9f3c1aa8-2096-4cc5-bd22-3b6766de0c7a','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','綠茶鮮奶茶','50',NULL,NULL,'{\"scopes\":[{\"name\":\"綠茶鮮奶茶\",\"price\":\"50\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_502_b4ac6015-eb1e-4160-ad89-dfe6b4e26daa','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','珍珠紅茶鮮奶茶','55',NULL,NULL,'{\"scopes\":[{\"name\":\"珍珠紅茶鮮奶茶\",\"price\":\"55\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180625_135645_502_ccaaf9dc-8da8-482c-9c14-7ef4c3994141','RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa','抹茶紅豆鮮奶茶','65',NULL,NULL,'{\"scopes\":[{\"name\":\"抹茶紅豆鮮奶茶\",\"price\":\"65\"}],\"opts\":[{\"name\":\"椰果\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"統一布丁\",\"price\":\"15\"},{\"name\":\"珍珠\",\"price\":\"5\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"},{\"name\":\"微冰\"},{\"name\":\"少冰\"},{\"name\":\"全 \"},{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"無糖\"},{\"name\":\"微糖\"},{\"name\":\"半糖\"},{\"name\":\"少糖\"},{\"name\":\"全糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z'),('FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','翡翠檸檬11','55',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-01T13:30:02.8100Z'),('FOOD_20180701_133046_735_867286ac-2366-48b6-b675-ced1f12b8523','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','翡翠檸檬11','55','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180701_133046_735_867286ac-2366-48b6-b675-ced1f12b8523.jpg?alt=media&token=40a4de58-4043-4054-abf8-cf067adc0877',NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-01T13:30:46.7350Z'),('FOOD_20180701_142607_561_b993d88c-0541-4d2d-9076-a3cc7f2a21eb','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','asasa','23',NULL,NULL,'{\"scopes\":[{\"name\":\"asasa\",\"price\":\"23\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-01T14:26:07.5610Z'),('FOOD_20180701_142814_322_7720dcfb-c962-4add-b7fe-088195bb4a3d','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','3434','4353',NULL,NULL,'{\"scopes\":[{\"name\":\"3434\",\"price\":\"4353\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-01T14:28:14.3220Z'),('FOOD_20180701_142946_824_2af1c11b-3cbb-45ce-b8f3-fa7db427179e','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','ff','23','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180701_142946_824_2af1c11b-3cbb-45ce-b8f3-fa7db427179e.jpg?alt=media&token=7ebbcede-34a5-4d29-95e1-60d88d078cb1',NULL,'{\"scopes\":[{\"name\":\"ff\",\"price\":\"23\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-01T14:29:46.8240Z'),('FOOD_20180702_104151_219_71d64166-244e-4f69-b265-b9acb5d9e01f','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','test','23','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180702_104151_219_71d64166-244e-4f69-b265-b9acb5d9e01f.jpg?alt=media&token=65f060ed-ff8f-4484-97a1-4547efb8d1ef',NULL,'{\"scopes\":[{\"name\":\"test2\",\"price\":\"23\"}],\"opts\":[{\"name\":\"wewe\",\"price\":\"23\"}],\"demands\":[]}','CLOSE','N','2018-07-02T10:41:51.2200Z'),('FOOD_20180702_121833_076_06fb05f4-e24e-4d22-881c-fc0ada8c76bb','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','RTRT','22',NULL,NULL,'{\"scopes\":[{\"name\":\"default\",\"price\":\"22\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-02T12:18:33.0760Z'),('FOOD_20180702_133511_630_69d8c0a7-4474-40ec-9c25-bfbf180d8d7b','RESTAURANT_CATEGORY_20180702_133431_638_b60c17fa-ef5e-42f6-99cf-6f67a58027a7','rr food','22','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180702_133511_630_69d8c0a7-4474-40ec-9c25-bfbf180d8d7b.jpg?alt=media&token=b03b7f35-3c8c-4113-9c3d-d222666cd03b',NULL,'{\"scopes\":[{\"name\":\"default\",\"price\":\"22\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-02T13:35:11.6300Z'),('FOOD_20180702_133543_256_56d036d3-d70d-41da-a11b-a751ab3b1367','RESTAURANT_CATEGORY_20180702_133431_638_b60c17fa-ef5e-42f6-99cf-6f67a58027a7','dsd','22','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180702_133543_256_56d036d3-d70d-41da-a11b-a751ab3b1367.jpg?alt=media&token=d64e3f00-18d2-475f-8826-33cec4e06e23',NULL,'{\"scopes\":[{\"name\":\"default\",\"price\":\"22\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-02T13:35:43.2570Z'),('FOOD_20180703_144955_243_768af273-149a-4eab-84b0-a07e67308369','RESTAURANT_CATEGORY_20180703_144942_092_996ceecd-4745-4777-b9be-16c0a28459c9','tgtgt','333','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Ffood%2FFOOD_20180703_144955_243_768af273-149a-4eab-84b0-a07e67308369.jpg?alt=media&token=fc4d3c33-d02b-4331-97f9-2e197211f788',NULL,'{\"scopes\":[{\"name\":\"tgtg\",\"price\":\"333\"},{\"name\":\"yyy\",\"price\":\"66\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-03T14:49:55.2440Z'),('FOOD_20180708_065115_384_8d4120a2-b292-4af4-bdaf-047bad458dda','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','dfsdf','33',NULL,NULL,'{\"scopes\":[{\"name\":\"統一價格\",\"price\":\"33\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-08T06:51:15.3850Z'),('FOOD_20180708_065216_948_6ce857b2-a604-4072-808f-c25a11e00c14','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','sds','23',NULL,NULL,'{\"scopes\":[{\"name\":\"統一價格\",\"price\":\"23\"}],\"opts\":[],\"demands\":[]}','CLOSE','N','2018-07-08T06:52:16.9480Z'),('FOOD_20180708_165515_398_9ccbe24a-8a58-4fe3-939d-a1c811723899','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','AAA','199',NULL,NULL,'{\"scopes\":[{\"name\":\"e\",\"price\":\"199\"},{\"name\":\"w\",\"price\":\"200\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-08T16:55:15.3980Z'),('FOOD_20180709_153146_737_4298c715-296b-4bff-ba21-38b2e90aa57d','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','TTTT','10',NULL,NULL,'{\"scopes\":[{\"name\":\"大杯\",\"price\":\"40\"},{\"name\":\"中杯\",\"price\":\"30\"},{\"name\":\"小杯\",\"price\":\"10\"},{\"name\":\"超大杯\",\"price\":\"50\"}],\"opts\":[{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"仙草\",\"price\":\"10\"},{\"name\":\"綠豆\",\"price\":\"10\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"無糖\"}]},{\"name\":\"冰量\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"}]}]}','OPEN','Y','2018-07-09T15:31:46.7370Z'),('FOOD_20180709_154624_878_7b799a1e-5ae4-4270-ab5f-25fb375dafce','RESTAURANT_CATEGORY_20180709_152649_356_407fa61d-9299-4f98-8a24-9091f4311b48','冰奶茶','10',NULL,NULL,'{\"scopes\":[{\"name\":\"大杯\",\"price\":\"40\"},{\"name\":\"中杯\",\"price\":\"30\"},{\"name\":\"小杯\",\"price\":\"10\"},{\"name\":\"超大杯\",\"price\":\"50\"}],\"opts\":[{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"仙草\",\"price\":\"10\"},{\"name\":\"綠豆\",\"price\":\"10\"},{\"name\":\"紅豆\",\"price\":\"10\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"無糖\"}]},{\"name\":\"冰量\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"}]}]}','OPEN','Y','2018-07-09T15:46:24.7850Z'),('FOOD_20180709_154624_881_a92eb450-670d-412c-831c-4b6dccaec129','RESTAURANT_CATEGORY_20180709_152649_356_407fa61d-9299-4f98-8a24-9091f4311b48','拉茶','20',NULL,NULL,'{\"scopes\":[{\"name\":\"統一價格\",\"price\":\"20\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-09T15:46:24.7850Z'),('FOOD_20180709_154624_881_b3ab258a-17b1-4c51-82c7-37701168c584','RESTAURANT_CATEGORY_20180709_152649_356_407fa61d-9299-4f98-8a24-9091f4311b48','熱奶茶','10',NULL,NULL,'{\"scopes\":[{\"name\":\"統一價格\",\"price\":\"10\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-09T15:46:24.7850Z'),('FOOD_20180709_154624_882_420dab5e-a81b-4b0d-81fb-fdb743be7d80','RESTAURANT_CATEGORY_20180709_152649_356_407fa61d-9299-4f98-8a24-9091f4311b48','冰清茶','10',NULL,NULL,'{\"scopes\":[{\"name\":\"統一價格\",\"price\":\"10\"}],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-07-09T15:46:24.7850Z');
/*!40000 ALTER TABLE `food_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mobile_device`
--

DROP TABLE IF EXISTS `mobile_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mobile_device` (
  `device_uuid` varchar(200) NOT NULL,
  `account_uuid` varchar(200) NOT NULL COMMENT '帳號 UUID',
  `device_token` mediumtext NOT NULL,
  `device_category` varchar(15) NOT NULL COMMENT '裝置類型\n‘IOS’,''ANDROID’',
  `create_date` varchar(30) NOT NULL COMMENT '建立時間\n2014-10-27T08:09:30.914Z',
  PRIMARY KEY (`device_uuid`),
  KEY `index2` (`account_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mobile_device`
--

LOCK TABLES `mobile_device` WRITE;
/*!40000 ALTER TABLE `mobile_device` DISABLE KEYS */;
INSERT INTO `mobile_device` VALUES ('DEVICE_20180712_171434_665_4ec74032-2dd3-40c8-889c-24f817ca224c','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','[\"fsdvI12RfVM:APA91bGmHXF0-9iML2pmwwrsFXteFk_0fXYUjJodOauMlH_5lULcdkLK5ww0J1mNrDtfC8L8xC8bBIDVhOHgZDpiKOLDd9sdCuL8R6htF22iRRFqd6FEo5hXQJaop_y1hdNYIfGca6Dkax-BL0oTCqe920IZWCc2iA\",\"e-_Ml0OOp-g:APA91bGDXRyt4B-E5tZKHXicWf7TXMQOz-RdYKWrQp0fs-bKyfxlwp_wsR2tRAq3UZptgvlHuSCwBE_4Cmz1YUzFu5PpuRuJuA3ZHyJHtA3mifm2oJ9dSqfyJOxyCyOwcVtO_oT6_quJCpke_MXvOSooLCLjeuIyGw\"]','ANDROID','2018-07-12T17:14:34.6650Z'),('DEVICE_20180712_171434_733_72eeb520-cb08-41b2-a5c4-5ab2cc716bd6','SELLER_20180703_123835_290_7504d73f-51c8-4198-b81c-a90d17b6dc5e','[]','ANDROID','2018-07-12T17:14:34.7330Z'),('DEVICE_20180712_171434_734_15aad1b5-720a-452b-ab99-39c159d9c94c','SELLER_20180703_173207_751_3b4a89b3-f8d3-46a9-adb5-7b76c4cd5d2b','[]','ANDROID','2018-07-12T17:14:34.7340Z'),('DEVICE_20180712_171434_734_3143ca43-1cb3-4b14-a04b-51b9262d7c2f','SELLER_20180703_141149_915_d2b81041-f261-46a2-a245-984bbb74d962','[]','ANDROID','2018-07-12T17:14:34.7340Z'),('DEVICE_20180712_171434_734_bcb67ac6-52f2-47ac-9c4d-dae933d26408','SELLER_20180703_172156_113_a9020559-b015-4ffd-b049-45b70171a44d','[]','ANDROID','2018-07-12T17:14:34.7340Z'),('DEVICE_20180712_171434_734_ee762780-3014-4f62-9347-2f14d346a62c','USER_20180709_123225_469_bbdd78cc-9786-49eb-8ca5-5ad07446a2ed','[\"ffDlRkmPuJ8:APA91bHC7pQVZldgPT8GFSloBNj6a9x-W-IcquMW3K92xiQ3CNyG7Nw0ibXqwDdd94Zz7Qo9DG6EroINcesQ3TBFibIcV_DvBoNsUfOGhSKwt0tK-OI3Vp2l9xgNUS53puEszHNI8vOyptSla64AkG-31_zlR_M_6Q\"]','ANDROID','2018-07-12T17:14:34.7340Z'),('DEVICE_20180712_171434_735_2a8c80eb-3d16-49a4-91a4-4bb8041a41bb','USER_20180709_153354_726_0ac56bb6-25c0-440c-95a4-c05a2d435baf','[\"c4pLseDXUfE:APA91bEffgqiyCGiZksGZ686w8Hq8HqKbs9pEfAhA7ygoKBbG53tYCVFFLXZH093C1NogSLW49_wRZKRPS0zmzvna0SOP7rDSP1-2qvw5tsFFFNNTzCDi2S4Ce4SpbAI6PP82ZWoetI7Sru5HTT3ZDQbwNxioOV4Ag\"]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_735_5f26abd8-dde7-41a8-8d7f-c8c790a3a879','SELLER_20180703_134217_830_d95e508c-828b-4ea7-b093-938fac9b5948','[]','ANDROID','2018-07-12T17:14:34.7340Z'),('DEVICE_20180712_171434_735_751e0219-f3fe-480b-b0a6-338fca11e7a1','SELLER_20180703_145221_253_a331666a-0409-4cf1-b191-d26c58bf1702','[]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_735_b0c4c383-5156-4d93-ac54-510b9f01b4da','SELLER_20180703_135100_693_40f198d0-01a3-440f-a85c-adbb46cac794','[]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_735_fa75fcbd-41ac-4409-af4e-cb6156e43553','USER_20180709_165153_960_48c88397-5c45-4f3e-b534-3d9e76e742a7','[\"dsu1VJYyFco:APA91bFDfCvpHFCww3saSxATjNnH-h0LL2_-xLd13w_0k19ardgwuOMjdk4c25qiZbWEX0A_bI5mL9k0mYavyAy7ghy-P7fnn0Md5Yb4YntjkG_9hgGmvvo6Cbu9CMu6iFKv2pHB-dil66nr-l_3SxLjWd8daIp1JQ\"]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_735_fcd5a7f1-e531-4ea7-bb3f-b9749693d8f0','SELLER_20180703_143011_633_cc02c0a8-4b47-400a-901f-ad74b20530be','[]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_736_3f78415b-f203-4d9a-b6d2-f96c07127952','SELLER_20180703_162909_593_fb96ed06-f104-4488-b205-bf37ee950bb7','[]','ANDROID','2018-07-12T17:14:34.7360Z'),('DEVICE_20180712_171434_736_949581b6-17b7-4218-9bf4-477e475458e9','USER_20180709_200909_744_0bbf28b2-9f8a-4cdb-8334-1df2dc4b1b03','[\"fsdvI12RfVM:APA91bGmHXF0-9iML2pmwwrsFXteFk_0fXYUjJodOauMlH_5lULcdkLK5ww0J1mNrDtfC8L8xC8bBIDVhOHgZDpiKOLDd9sdCuL8R6htF22iRRFqd6FEo5hXQJaop_y1hdNYIfGca6Dkax-BL0oTCqe920IZWCc2iA\"]','ANDROID','2018-07-12T17:14:34.7350Z'),('DEVICE_20180712_171434_736_9e977967-b909-4399-a7e6-0cea98e0128d','SELLER_20180703_160533_043_df42fa71-d51f-4dc4-a2a9-4322db33edcb','[]','ANDROID','2018-07-12T17:14:34.7360Z'),('DEVICE_20180712_171434_736_a5a23040-c6c5-4339-8ee8-a049e95f16d3','SELLER_20180703_153300_223_cd1a8d0e-74c5-4f6d-89cc-8f214433ee91','[]','ANDROID','2018-07-12T17:14:34.7360Z'),('DEVICE_20180712_171434_736_cf78eec2-99a5-4175-8e9b-af963049a867','USER_20180710_115519_987_86df092e-76cb-41df-acf1-3cb7754fdc69','[\"eV5QB7NfGBM:APA91bEUifYOEWN0Cv-Me0llddKqoVJZeaqJTc3xWYLUrk0w2A2z2TM1rleAY_Vi5FodW1xnVHvGTPFxSrLpMDS-K_dkoyCSBnV33EV0KPZBLJlQQjgU6w2I1Hg3VDQs3Ex6VXaxzv_1t8EPAsdpPl5Ziel3R3Mamw\"]','ANDROID','2018-07-12T17:14:34.7360Z'),('DEVICE_20180712_171434_736_e01c1ac7-886d-4973-81cf-186b6a7b4a07','USER_20180710_154348_764_3c10ef4f-734c-45ee-b45f-05aeb14d35b1','[\"fsdvI12RfVM:APA91bGmHXF0-9iML2pmwwrsFXteFk_0fXYUjJodOauMlH_5lULcdkLK5ww0J1mNrDtfC8L8xC8bBIDVhOHgZDpiKOLDd9sdCuL8R6htF22iRRFqd6FEo5hXQJaop_y1hdNYIfGca6Dkax-BL0oTCqe920IZWCc2iA\"]','ANDROID','2018-07-12T17:14:34.7360Z'),('DEVICE_20180712_171434_737_463eb672-1fde-4f43-a299-b519f2ad4cf1','USER_20180710_171542_425_2eee68b9-b907-49ff-9547-c5cc6f4111cd','[\"fbQDZ9G2484:APA91bG3TEhuAqpXJfIc3BSAiIo5xrHLQNNITbxAc3hMeUh0LKUxylB4XSQOky1deQn-C37XHSBXrCzmUEBuD49Oge22L0QYygiKlTu7QjiXt4uJwx1DgQcZK5VCk43NQFTe33MsebmPEexGCjon1U8UFi-vdGdpCA\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_737_742f56b4-627f-4645-bd5e-0284f5e3c4dd','USER_20180710_174135_948_a1eae114-9bc6-494e-9064-16bf3fe365a0','[\"dFYlO_Rq4iI:APA91bGkx8xmTkCPcF-8DnJ2CFNvO-mxEgQTj3iixTAeYmRNZvT6V_Dm6kSR27bsZ-faST8O73Jvu8onlVOXFc_hU9HXriuqX0bvp5oIfIjhofK8HXtXtW8CusCH-XTnEJw3ZsTKarg7cXLjtkN-iMP3sBAnrMU34g\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_737_97e115a0-1220-497b-b0e3-7e2c065f0590','USER_20180711_135013_889_ae5ca77d-a825-428b-ae96-789199d521f4','[\"fsdvI12RfVM:APA91bGmHXF0-9iML2pmwwrsFXteFk_0fXYUjJodOauMlH_5lULcdkLK5ww0J1mNrDtfC8L8xC8bBIDVhOHgZDpiKOLDd9sdCuL8R6htF22iRRFqd6FEo5hXQJaop_y1hdNYIfGca6Dkax-BL0oTCqe920IZWCc2iA\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_737_a27f4831-c548-4dff-940b-2ac123b95dc2','USER_20180711_042306_690_5a2b18d7-aafb-4e6e-9633-222359ca5a05','[\"edhcl5xk8GQ:APA91bGaFed-cdqgNblrZfV5cFyKih9HYcYJJ9fb5cnboTsUmDmfoGVZZl19Lbuzu8t7NRAeNGu3L0wHYyMz9d_HwWtCT9lxQnW5uqUnU2RdviJnKB9fI1P0HKiHzQK2Mw21z5uVK6OPRXPxGbQv9A35_88TMSNJag\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_737_c8ea65cb-4769-42d9-a374-bfb878bb136d','USER_20180711_110727_196_7d195985-941b-42a5-9a4f-cf72232bc00f','[\"eV5QB7NfGBM:APA91bEUifYOEWN0Cv-Me0llddKqoVJZeaqJTc3xWYLUrk0w2A2z2TM1rleAY_Vi5FodW1xnVHvGTPFxSrLpMDS-K_dkoyCSBnV33EV0KPZBLJlQQjgU6w2I1Hg3VDQs3Ex6VXaxzv_1t8EPAsdpPl5Ziel3R3Mamw\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_737_dd4216f2-245f-4b3b-af70-6e0777f6abdc','SELLER_20180703_175534_325_2f3fc8cc-4ec2-4669-be06-b745ce389f47','[]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_738_1338ed90-8f99-4dd5-a1d8-585f74c4e77b','SELLER_20180704_123630_689_4a1b126a-14d0-4022-8f3b-1a7d0e364f82','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_738_6e9e1a83-b0af-40d5-bc24-8ebd88bf6c12','SELLER_20180703_163944_366_5ea7b9ae-d35b-4a9a-82e8-5dd397ffd6db','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_738_822516da-cc96-493e-8f18-234becf1186a','SELLER_20180703_132500_256_d476890e-47e8-4e02-a94c-2453dd250491','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_738_8578cdd6-ca74-41b8-96e2-c1813b1509f8','SELLER_20180703_171401_437_1f02f637-ecc5-45cf-9569-eb6cb7148443','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_738_90ca2e48-13c6-48ba-b09d-cdd93e6a2402','SELLER_20180703_170526_809_7efb062c-e64b-4224-a04a-8f9b06eeb75c','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_738_b8394bbe-5389-41ba-a65f-b072deae0434','USER_20180711_193934_203_2028cf53-8080-4094-824a-755975b5cb62','[\"cxSxdgWhCXU:APA91bF-O1bHbIzrnkKT7Dolmh3g74RasA7vDblYR6Rc1L4J3TTpXTaz4bbmWiwA7whN8t-wocx_fiOBcLDktrZNQv0E8mhiA0PRk6rfAI-wj1wnyZxMwIDsdpyBwA2sRjLYbqtNChmL__6319yZMJtE7G2fGobMLg\"]','ANDROID','2018-07-12T17:14:34.7370Z'),('DEVICE_20180712_171434_738_f27524c3-8d22-4b30-a7cc-3a75d10f3b6d','SELLER_20180703_180917_065_6eb7121a-1f32-407e-9d1e-7d6050d21f19','[]','ANDROID','2018-07-12T17:14:34.7380Z'),('DEVICE_20180712_171434_739_8d8cd97b-38be-47fc-9137-a313620bea93','SELLER_20180704_102555_221_a0cc4d33-f1cb-43a0-9e9f-180da00b07da','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_739_902b84f8-c10f-48c5-b740-ab05eec624f2','SELLER_20180703_183503_410_42b3754c-de9f-4996-bd52-cafaf41980cd','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_739_951ed9a5-5612-4831-b242-4847d3040e82','SELLER_20180703_184748_231_9f4430d7-08bf-4be0-8ec0-a27365f9e8b2','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_739_a1b23606-b0b8-4b69-9ee9-e60c53d29744','SELLER_20180704_095800_841_c13160d1-96f8-4ef5-ab2c-88233374c08a','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_739_af3e2b38-809f-4222-b4c2-29645fb3fc29','SELLER_20180704_100959_900_87ecf872-a27d-47e8-9932-24dbc56cbaac','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_740_20fdfe1f-6b45-42b2-89f7-43ac40865320','SELLER_20180704_113053_525_af918e31-77a7-46c5-9e8b-386071eb1fd2','[]','ANDROID','2018-07-12T17:14:34.7400Z'),('DEVICE_20180712_171434_740_34bcd72c-af6c-4ac7-b1e2-f52a9e6828af','SELLER_20180704_110609_239_9058684c-dd88-41a0-b997-2a4098e7111d','[]','ANDROID','2018-07-12T17:14:34.7400Z'),('DEVICE_20180712_171434_740_b026b161-c752-4e48-90e2-0e504a0c9c5f','SELLER_20180704_104421_818_891c4a7e-1d8c-4f52-9a77-ec0ebf876659','[]','ANDROID','2018-07-12T17:14:34.7400Z'),('DEVICE_20180712_171434_740_da567780-b9d7-44fa-8969-fcee095ff6bf','SELLER_20180704_103530_518_1af0aa84-1ecc-4e68-aeaa-f7772b9a8997','[]','ANDROID','2018-07-12T17:14:34.7390Z'),('DEVICE_20180712_171434_740_dcabe4a7-e8ce-444b-9318-406c0ef5278d','SELLER_20180704_112340_238_66fdb091-69eb-48a2-a85e-58ae11f82bde','[]','ANDROID','2018-07-12T17:14:34.7400Z'),('DEVICE_20180712_171434_740_e78c8359-21cf-4733-b881-9333903d2cd7','SELLER_20180704_105452_066_e849a211-b928-45a1-b364-7fe9865eccb4','[]','ANDROID','2018-07-12T17:14:34.7400Z'),('DEVICE_20180712_172349_906_9c7c39ea-efca-41da-baa6-472272522506','SELLER_20180625_115133_341_7cace495-eb20-4b50-9f48-20efaa33232a','[\"e-_Ml0OOp-g:APA91bGDXRyt4B-E5tZKHXicWf7TXMQOz-RdYKWrQp0fs-bKyfxlwp_wsR2tRAq3UZptgvlHuSCwBE_4Cmz1YUzFu5PpuRuJuA3ZHyJHtA3mifm2oJ9dSqfyJOxyCyOwcVtO_oT6_quJCpke_MXvOSooLCLjeuIyGw\",\"d014z10rtDE:APA91bEhsTPWD_V2AsWGkE0JrUP7zIE_vaNts-VpkaEzrRfcyEgeXd53YlMxcp-7VF-n1DtM4zIXGTQoW1CrK2d0RutWtwCVsBBMebhp-o7cpy8pVQ8lWnzYZmHfN9a00Vad7EqldPXtH_uwAryI6LLFnH4nzbYX8A\",\"fAK8EuuAeEo:APA91bFXPGOxP1BiYtNQnlPp2_uwY4NkhV-Lolsf3xuwP_eQt5t-ABWGUjiySIHeVnR4auqVzile6upWA4GL3OajBI2loqrHWU5UvNntWGbST3NzHMfrbvs3YtbLgkTKFN-ny16jkqNkkncw2H33aqAlfabk04OXJg\"]','ANDROID','2018-07-12T17:23:49.9060Z'),('DEVICE_20180712_224754_338_83eaa0b2-e326-49cd-86fa-d9248de2c2f0','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','[\"cN-fT92bHII:APA91bGi6tgQjemk4yVGOxNwKiXDJct2mYXelqEKP7ZBT1mpLTNCVUrkHYQGc50Hl9l_dF1-3BdhBCRPAu_HAig6J05kVtsVTrzHSXGgBRzpLnYbiezRWXTyS5EC_op5vmNOOBH9yl5TpNleCTvkYSrqaWNqxv5f6A\"]','ANDROID','2018-07-12T22:47:54.3340Z');
/*!40000 ALTER TABLE `mobile_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `naber_bulletin`
--

DROP TABLE IF EXISTS `naber_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `naber_bulletin` (
  `bulletin_uuid` varchar(200) NOT NULL COMMENT '公告 UUID',
  `title` varchar(200) DEFAULT NULL COMMENT '標題',
  `content_text` varchar(1000) DEFAULT NULL COMMENT '內文',
  `bulletin_category` varchar(50) NOT NULL COMMENT '公告類型	"HOME,ABOUT_US,TEACHING,CONTACT_US,FAQ,APPLY_OF_SELLER\n"',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '是否啟用',
  `create_date` varchar(30) NOT NULL COMMENT '建立時間',
  PRIMARY KEY (`bulletin_uuid`),
  KEY `index2` (`bulletin_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `naber_bulletin`
--

LOCK TABLES `naber_bulletin` WRITE;
/*!40000 ALTER TABLE `naber_bulletin` DISABLE KEYS */;
INSERT INTO `naber_bulletin` VALUES ('NABER_BULLETIN_35509dda-6544-4ebd-95c9-3e846aba4ba3','成為合作店家','想要透過NABER來銷售產品嗎？$split$split客人透過線上點餐功能，到場直接取餐免等待，讓「 不用等」成為你的店家口號。​$split$split如果你想進一步了解NABER對你的幫助，或是想了解更多資訊，請在登入頁面點擊右下角「 申請為店家」，或是透過下列Email與我們聯繫。$split$split service@melonltd.com.tw','APPLY_OF_SELLER','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_3dfd10d0-8a42-47b6-9e78-93149856e32b','首頁','1、IOS即將上線，請稍待片刻。$split2、訂餐享紅利回饋，下次消費可折抵現金! (即將開放)。$split3、Naber可以隔夜接單，今天可以訂購明後天的餐點，歡迎多加利用','HOME','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_448167a5-c8ac-4876-9d99-71d0b07b4e6b','與我聯繫','如果上述問題無法滿足您的需求$split歡迎使用下列方法尋求協助$splitE-MAIL：service@melonltd.com.tw$split官方LINE帳號：@gsd8173f','CONTACT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_468efa05-42ee-4327-8bdd-40a117a8dc20','訂餐步驟','選擇餐廳 > 選擇種類、產品、規格 > 選擇你要取餐的時間 > 送出訂單','TEACHING','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_65ff4e27-6195-482b-9137-f01c86ccc81c','關於我們','NABER是一個訂餐APP平台，創立於2017年。$split$split我們知道大多數的人，早上時間都很緊湊，因此出現沒吃正餐的情形。$split$split你可以透過NABER事先訂餐，他能夠讓你免排隊、免遲到。$split$split我們希望NABER可以成為你的得力助手，提升你的生活品質。$split$splitNABER有紅利點數的功能，透過消費累積點數，之後消費可以折抵現金。','ABOUT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_a805e1c2-a4dc-4ab6-8767-57cf6ffa00ca','常見問題','常見問題$split$splitQ　：　我沒有信用卡，我可以使用NABER嗎？$splitA　：　可以，NABER付款方式為「先點餐，再到場付款」。$split$splitQ　：　一定要有手機才可以加入會員嗎？$splitA　：　是的，商家在必要的時候，可能會透過手機與你們聯繫。$split$splitQ　：　選擇身份有什麼差異嗎？$splitA　：　往後推出的功能，與選擇的身份有關。$split$splitQ　：　我要怎麼拿餐？$splitA　：　依照你選的取餐時間，再到商家取餐就可以囉！(告知是NABER取餐)$split$splitQ　：　我要怎麼取消餐點？$splitA　：　如果你所選擇的取餐時間還沒到，你可以透過電話聯絡你所訂餐的商家，請他幫你取消。若商家已經製作，沒有辦法取消餐點。$split$splitQ　：　如果不拿餐點會怎麼樣嗎？$splitA　：　NABER不希望看到這樣的事情發生。我們有懲罰的機制存在，但我們真的不希望動用到！如果你數次沒去拿餐，我們只好以悲痛的心情，將你的帳號凍結，不僅如此，商家會直接透過電話與你聯繫，所以在點餐前請三思。$split$splitQ　：　紅利點數可以做什麼？$splitA　：　在之後消費時，可以折抵現金。$split$splitQ　：　我點錯餐了，怎麼辦？$splitA　：　馬上向商家聯絡，請他幫你取消。$split$splitQ　：　我可以事先預訂明天的餐點嗎？　$splitA　：　可以，不僅如此你還可以預訂「 後天的餐點」，如果你有需要的話。$split$splitQ　：　我想要加點商品，但我已經送出了，怎麼辦？$splitA　：　將加點的商品再送一次，老闆都會看見的。$split$split','FAQ','Y','2018-06-05T05:30:14.837Z');
/*!40000 ALTER TABLE `naber_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `order_uuid` varchar(200) NOT NULL COMMENT '訂單 UUID',
  `restaurant_uuid` varchar(200) NOT NULL COMMENT '餐館 UUID',
  `account_uuid` varchar(200) NOT NULL COMMENT '帳號 UUID',
  `user_message` varchar(220) DEFAULT NULL COMMENT '消費者留言',
  `create_date` varchar(30) NOT NULL COMMENT '訂單建立時間	2014-10-27T08:09:30.914Z',
  `update_date` varchar(30) NOT NULL COMMENT '訂單狀態更新時間	2014-10-27T08:09:30.914Z',
  `order_price` varchar(20) NOT NULL COMMENT '訂單總金額',
  `order_bonus` varchar(20) NOT NULL COMMENT '訂單紅利',
  `fetch_date` varchar(30) NOT NULL COMMENT '訂單拿取時間	2014-10-27T08:09:30.914Z',
  `order_data` mediumtext NOT NULL COMMENT '訂單詳細資料	Json 格式',
  `status` varchar(25) NOT NULL DEFAULT 'UNFINISH' COMMENT '判斷客服是否已經處理	\nUNFINISH,\nPROCESSING, CAN_FETCH CANCEL,  FAIL,\nFINISH"',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '訂單是否刪除	Y/N',
  PRIMARY KEY (`order_uuid`),
  KEY `index2` (`order_uuid`),
  KEY `index3` (`account_uuid`),
  KEY `index4` (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES ('ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T09:58:02.6800Z','2018-07-09T10:00:52.1220Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FAIL','N'),('ORDER_20180709_101310_090_2480efa9-dda4-4320-9706-480dc9cfef79','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T10:13:10.0940Z','2018-07-09T10:13:28.5870Z','25','2','2018-07-09T10:33:06.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:33:06.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),('ORDER_20180712_141229_744_10441a44-1323-45be-a428-7833c6022921','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:12:29.7480Z','2018-07-12T14:13:56.5290Z','35','3','2018-07-12T14:32:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:32:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_141431_978_b69ee2a8-d3fe-4a49-abc9-06c05e5e9b86','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:14:31.9800Z','2018-07-12T14:20:44.9650Z','25','2','2018-07-12T14:34:28.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:34:28.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_141902_993_40a5bac1-0df0-4740-a5f8-a373a2c34dae','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:19:02.9960Z','2018-07-12T14:20:47.5900Z','25','2','2018-07-12T14:39:00.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:39:00.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_142053_319_1422eb74-5ba9-4f9e-8d52-ba4c72aca062','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:20:53.3210Z','2018-07-12T14:22:40.8690Z','25','2','2018-07-12T14:40:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:40:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_142209_286_5e066dd8-37f3-432d-8524-00c6ddd8e8c1','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:22:09.2880Z','2018-07-12T14:29:28.6180Z','30','3','2018-07-12T14:42:05.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:42:05.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_142842_519_71c6167d-3029-4dff-ab78-1f6a21796b5a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:28:42.5230Z','2018-07-12T14:29:20.2240Z','25','2','2018-07-12T14:48:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:48:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_142942_126_4b32da80-6c3f-4f0e-b957-f5790898d817','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:29:42.1280Z','2018-07-12T14:41:39.6780Z','25','2','2018-07-12T14:49:38.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:49:38.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','N'),('ORDER_20180712_143108_652_e5cca223-26d1-42f7-82a9-6710e4a8f1be','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:31:08.6550Z','2018-07-12T14:41:42.5440Z','55','5','2018-07-12T14:51:04.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:51:04.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','CANCEL','N'),('ORDER_20180712_144202_142_d5d835fe-faba-4f36-aa7a-a1931b7b7d83','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:42:02.1430Z','2018-07-12T14:42:02.1430Z','25','2','2018-07-12T15:01:58.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T15:01:58.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),('ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T16:17:13.4250Z','2018-07-12T16:27:20.8480Z','25','2','2018-07-12T16:37:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FINISH','N'),('ORDER_20180712_162915_037_4272a40b-99b4-4bfb-9595-c31f650456a5','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:29:15.0370Z','2018-07-12T16:37:47.1230Z','50','5','2018-07-12T16:49:08.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:49:08.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),('ORDER_20180712_163217_787_3c531b98-8d2f-4f7b-9ada-a812dee3e272','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:32:17.7880Z','2018-07-12T16:37:55.0880Z','35','3','2018-07-12T16:52:12.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:52:12.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),('ORDER_20180712_163250_691_6af0f68f-94f6-4aab-9143-310a6f5556fe','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:32:50.6910Z','2018-07-12T16:32:50.6910Z','55','5','2018-07-13T16:52:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-13T16:52:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','UNFINISH','Y'),('ORDER_20180712_164001_663_55159e7a-8e60-40fc-b11e-49483312daf3','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:40:01.6630Z','2018-07-12T16:40:01.6630Z','30','3','2018-07-12T16:59:55.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:59:55.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),('ORDER_20180712_164042_180_472f989a-049b-4991-a5e2-7d2b7e315f06','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:40:42.1800Z','2018-07-12T17:29:37.6140Z','25','2','2018-07-12T22:00:34.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T22:00:34.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),('ORDER_20180712_164114_821_2e15495d-8387-4de4-9727-3bc9d7ac29f4','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:41:14.8210Z','2018-07-12T17:29:31.6210Z','40','4','2018-07-12T18:01:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T18:01:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774\",\"category_name\":\"調味茶\",\"food_name\":\"冰釀陳梅\",\"price\":\"40\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),('ORDER_20180712_172959_062_ac264342-9ae8-4fab-9427-64148e93f001','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T17:29:59.0620Z','2018-07-12T17:29:59.0620Z','25','2','2018-07-12T20:49:51.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T20:49:51.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),('ORDER_20180712_173034_664_432feabd-d568-4f1c-9c8b-d301b63f85ce','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T17:30:34.6640Z','2018-07-12T17:30:34.6640Z','25','2','2018-07-12T17:50:21.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T17:50:21.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y');
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_log`
--

DROP TABLE IF EXISTS `order_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
  `account_uuid` varchar(200) NOT NULL,
  `user_message` varchar(220) DEFAULT NULL,
  `create_date` varchar(30) NOT NULL,
  `update_date` varchar(30) NOT NULL,
  `order_price` varchar(20) NOT NULL,
  `order_bonus` varchar(20) NOT NULL,
  `fetch_date` varchar(30) NOT NULL,
  `order_data` mediumtext NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'UNFINISH',
  `enable` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_log`
--

LOCK TABLES `order_log` WRITE;
/*!40000 ALTER TABLE `order_log` DISABLE KEYS */;
INSERT INTO `order_log` VALUES (237,'ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T09:58:02.6800Z','2018-07-09T09:58:02.6800Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(238,'ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T09:58:02.6800Z','2018-07-09T09:58:27.4320Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),(239,'ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T09:58:02.6800Z','2018-07-09T09:59:05.1420Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),(240,'ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T09:58:02.6800Z','2018-07-09T10:00:52.1220Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FAIL','Y'),(241,'ORDER_20180709_101310_090_2480efa9-dda4-4320-9706-480dc9cfef79','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T10:13:10.0940Z','2018-07-09T10:13:10.0940Z','25','2','2018-07-09T10:33:06.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:33:06.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(242,'ORDER_20180709_101310_090_2480efa9-dda4-4320-9706-480dc9cfef79','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T10:13:10.0940Z','2018-07-09T10:13:28.5870Z','25','2','2018-07-09T10:33:06.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:33:06.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),(243,'ORDER_20180712_141229_744_10441a44-1323-45be-a428-7833c6022921','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:12:29.7480Z','2018-07-12T14:12:29.7480Z','35','3','2018-07-12T14:32:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:32:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(244,'ORDER_20180712_141229_744_10441a44-1323-45be-a428-7833c6022921','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:12:29.7480Z','2018-07-12T14:13:56.5290Z','35','3','2018-07-12T14:32:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:32:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(245,'ORDER_20180712_141431_978_b69ee2a8-d3fe-4a49-abc9-06c05e5e9b86','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:14:31.9800Z','2018-07-12T14:14:31.9800Z','25','2','2018-07-12T14:34:28.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:34:28.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(246,'ORDER_20180712_141902_993_40a5bac1-0df0-4740-a5f8-a373a2c34dae','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:19:02.9960Z','2018-07-12T14:19:02.9960Z','25','2','2018-07-12T14:39:00.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:39:00.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(247,'ORDER_20180712_141431_978_b69ee2a8-d3fe-4a49-abc9-06c05e5e9b86','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:14:31.9800Z','2018-07-12T14:20:44.9650Z','25','2','2018-07-12T14:34:28.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:34:28.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(248,'ORDER_20180712_141902_993_40a5bac1-0df0-4740-a5f8-a373a2c34dae','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:19:02.9960Z','2018-07-12T14:20:47.5900Z','25','2','2018-07-12T14:39:00.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:39:00.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(249,'ORDER_20180712_142053_319_1422eb74-5ba9-4f9e-8d52-ba4c72aca062','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:20:53.3210Z','2018-07-12T14:20:53.3210Z','25','2','2018-07-12T14:40:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:40:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(250,'ORDER_20180712_142209_286_5e066dd8-37f3-432d-8524-00c6ddd8e8c1','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:22:09.2880Z','2018-07-12T14:22:09.2880Z','30','3','2018-07-12T14:42:05.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:42:05.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(251,'ORDER_20180712_142053_319_1422eb74-5ba9-4f9e-8d52-ba4c72aca062','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:20:53.3210Z','2018-07-12T14:22:40.8690Z','25','2','2018-07-12T14:40:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:40:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(252,'ORDER_20180712_142842_519_71c6167d-3029-4dff-ab78-1f6a21796b5a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:28:42.5230Z','2018-07-12T14:28:42.5230Z','25','2','2018-07-12T14:48:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:48:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(253,'ORDER_20180712_142842_519_71c6167d-3029-4dff-ab78-1f6a21796b5a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:28:42.5230Z','2018-07-12T14:29:20.2240Z','25','2','2018-07-12T14:48:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:48:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(254,'ORDER_20180712_142209_286_5e066dd8-37f3-432d-8524-00c6ddd8e8c1','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:22:09.2880Z','2018-07-12T14:29:28.6180Z','30','3','2018-07-12T14:42:05.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:42:05.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(255,'ORDER_20180712_142942_126_4b32da80-6c3f-4f0e-b957-f5790898d817','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:29:42.1280Z','2018-07-12T14:29:42.1280Z','25','2','2018-07-12T14:49:38.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:49:38.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(256,'ORDER_20180712_143108_652_e5cca223-26d1-42f7-82a9-6710e4a8f1be','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:31:08.6550Z','2018-07-12T14:31:08.6550Z','55','5','2018-07-12T14:51:04.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:51:04.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','UNFINISH','Y'),(257,'ORDER_20180712_142942_126_4b32da80-6c3f-4f0e-b957-f5790898d817','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:29:42.1280Z','2018-07-12T14:41:39.6780Z','25','2','2018-07-12T14:49:38.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:49:38.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),(258,'ORDER_20180712_143108_652_e5cca223-26d1-42f7-82a9-6710e4a8f1be','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:31:08.6550Z','2018-07-12T14:41:42.5440Z','55','5','2018-07-12T14:51:04.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:51:04.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','CANCEL','Y'),(259,'ORDER_20180712_144202_142_d5d835fe-faba-4f36-aa7a-a1931b7b7d83','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:42:02.1430Z','2018-07-12T14:42:02.1430Z','25','2','2018-07-12T15:01:58.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T15:01:58.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(260,'ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T16:17:13.4250Z','2018-07-12T16:17:13.4250Z','25','2','2018-07-12T16:37:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(261,'ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T16:17:13.4250Z','2018-07-12T16:18:39.1580Z','25','2','2018-07-12T16:37:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),(262,'ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T16:17:13.4250Z','2018-07-12T16:27:20.8480Z','25','2','2018-07-12T16:37:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FINISH','Y'),(263,'ORDER_20180712_162915_037_4272a40b-99b4-4bfb-9595-c31f650456a5','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:29:15.0370Z','2018-07-12T16:29:15.0370Z','50','5','2018-07-12T16:49:08.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:49:08.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(264,'ORDER_20180712_163217_787_3c531b98-8d2f-4f7b-9ada-a812dee3e272','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:32:17.7880Z','2018-07-12T16:32:17.7880Z','35','3','2018-07-12T16:52:12.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:52:12.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(265,'ORDER_20180712_163250_691_6af0f68f-94f6-4aab-9143-310a6f5556fe','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:32:50.6910Z','2018-07-12T16:32:50.6910Z','55','5','2018-07-13T16:52:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-13T16:52:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','UNFINISH','Y'),(266,'ORDER_20180712_162915_037_4272a40b-99b4-4bfb-9595-c31f650456a5','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:29:15.0370Z','2018-07-12T16:37:47.1230Z','50','5','2018-07-12T16:49:08.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:49:08.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),(267,'ORDER_20180712_163217_787_3c531b98-8d2f-4f7b-9ada-a812dee3e272','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:32:17.7880Z','2018-07-12T16:37:55.0880Z','35','3','2018-07-12T16:52:12.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:52:12.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','PROCESSING','Y'),(268,'ORDER_20180712_164001_663_55159e7a-8e60-40fc-b11e-49483312daf3','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:40:01.6630Z','2018-07-12T16:40:01.6630Z','30','3','2018-07-12T16:59:55.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:59:55.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(269,'ORDER_20180712_164042_180_472f989a-049b-4991-a5e2-7d2b7e315f06','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:40:42.1800Z','2018-07-12T16:40:42.1800Z','25','2','2018-07-12T22:00:34.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T22:00:34.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(270,'ORDER_20180712_164114_821_2e15495d-8387-4de4-9727-3bc9d7ac29f4','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:41:14.8210Z','2018-07-12T16:41:14.8210Z','40','4','2018-07-12T18:01:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T18:01:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774\",\"category_name\":\"調味茶\",\"food_name\":\"冰釀陳梅\",\"price\":\"40\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(271,'ORDER_20180712_164114_821_2e15495d-8387-4de4-9727-3bc9d7ac29f4','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:41:14.8210Z','2018-07-12T17:29:31.6210Z','40','4','2018-07-12T18:01:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T18:01:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774\",\"category_name\":\"調味茶\",\"food_name\":\"冰釀陳梅\",\"price\":\"40\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),(272,'ORDER_20180712_164042_180_472f989a-049b-4991-a5e2-7d2b7e315f06','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T16:40:42.1800Z','2018-07-12T17:29:37.6140Z','25','2','2018-07-12T22:00:34.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T22:00:34.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CAN_FETCH','Y'),(273,'ORDER_20180712_172959_062_ac264342-9ae8-4fab-9427-64148e93f001','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T17:29:59.0620Z','2018-07-12T17:29:59.0620Z','25','2','2018-07-12T20:49:51.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T20:49:51.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y'),(274,'ORDER_20180712_173034_664_432feabd-d568-4f1c-9c8b-d301b63f85ce','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','','2018-07-12T17:30:34.6640Z','2018-07-12T17:30:34.6640Z','25','2','2018-07-12T17:50:21.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T17:50:21.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','UNFINISH','Y');
/*!40000 ALTER TABLE `order_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_info`
--

DROP TABLE IF EXISTS `restaurant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_info` (
  `restaurant_uuid` varchar(200) NOT NULL COMMENT '餐館唯一值',
  `name` varchar(150) NOT NULL COMMENT '餐館名稱',
  `restaurant_category` varchar(80) NOT NULL DEFAULT '',
  `address` varchar(250) NOT NULL COMMENT '餐館地址',
  `store_start` varchar(30) NOT NULL COMMENT '營業開始時間\n2014-10-27T08:09:30.914Z',
  `store_end` varchar(30) NOT NULL COMMENT '營業結束時間\n2014-10-27T08:09:30.914Z',
  `not_business` varchar(150) DEFAULT NULL COMMENT '不可接單日期控制（3日），json array \n{\n  "can_business": [\n    "2014-10-27T08:09:30.914Z",\n    "2014-11-27T08:09:30.914Z",\n    "2014-12-27T08:09:30.914Z"\n  ]\n}',
  `can_store_range` varchar(2000) DEFAULT NULL COMMENT '餐館可接單時間  json array，\nAP Server 依照store_start ＆ store_end 計算出範圍，\n預設全部為Y\n\n\n“can_store_time":[\n    {\n      "status":"Y",\n      "date":"08:31 ~ 09:00"\n    },\n    {\n      "status":"N",\n      "date":"09:01 ~ 09:30"\n    }\n    ]',
  `latitude` varchar(20) NOT NULL COMMENT '緯度，地理位置',
  `longitude` varchar(20) NOT NULL COMMENT '經度，地理位置',
  `bulletin` varchar(500) DEFAULT NULL COMMENT '餐館公告\n\n歡迎xxxxxxxooxxx優惠',
  `photo` varchar(2000) DEFAULT NULL COMMENT '餐館圖片 URL',
  `photo_type` varchar(10) DEFAULT NULL COMMENT '圖片類型\npng,jpg,svg,pdf,gif',
  `enable` varchar(1) NOT NULL DEFAULT 'Y' COMMENT '餐館是否刪除',
  `top` varchar(5) NOT NULL DEFAULT '-1' COMMENT '推薦排行 ',
  `background_photo` varchar(2000) DEFAULT NULL,
  `create_date` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`restaurant_uuid`),
  KEY `index2` (`restaurant_category`),
  KEY `index4` (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_info`
--

LOCK TABLES `restaurant_info` WRITE;
/*!40000 ALTER TABLE `restaurant_info` DISABLE KEYS */;
INSERT INTO `restaurant_info` VALUES ('RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','清玉-民族店','冰飲','320桃園市中壢區民族路二段130號','09:00','22:00','[]','[{\"status\":\"OPEN\",\"date\":\"09:01-09:30\"},{\"status\":\"OPEN\",\"date\":\"09:31-10:00\"},{\"status\":\"OPEN\",\"date\":\"10:01-10:30\"},{\"status\":\"OPEN\",\"date\":\"10:31-11:00\"},{\"status\":\"OPEN\",\"date\":\"11:01-11:30\"},{\"status\":\"OPEN\",\"date\":\"11:31-12:00\"},{\"status\":\"OPEN\",\"date\":\"12:01-12:30\"},{\"status\":\"OPEN\",\"date\":\"12:31-13:00\"},{\"status\":\"OPEN\",\"date\":\"13:01-13:30\"},{\"status\":\"OPEN\",\"date\":\"13:31-14:00\"},{\"status\":\"OPEN\",\"date\":\"14:01-14:30\"},{\"status\":\"OPEN\",\"date\":\"14:31-15:00\"},{\"status\":\"OPEN\",\"date\":\"15:01-15:30\"},{\"status\":\"OPEN\",\"date\":\"15:31-16:00\"},{\"status\":\"OPEN\",\"date\":\"16:01-16:30\"},{\"status\":\"OPEN\",\"date\":\"16:31-17:00\"},{\"status\":\"OPEN\",\"date\":\"17:01-17:30\"},{\"status\":\"OPEN\",\"date\":\"17:31-18:00\"},{\"status\":\"OPEN\",\"date\":\"18:01-18:30\"},{\"status\":\"OPEN\",\"date\":\"18:31-19:00\"},{\"status\":\"OPEN\",\"date\":\"19:01-19:30\"},{\"status\":\"OPEN\",\"date\":\"19:31-20:00\"},{\"status\":\"OPEN\",\"date\":\"20:01-20:30\"},{\"status\":\"OPEN\",\"date\":\"20:31-21:00\"},{\"status\":\"OPEN\",\"date\":\"21:01-21:30\"},{\"status\":\"OPEN\",\"date\":\"21:31-22:00\"}]','24.957514','121.205150',NULL,'https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2F%E6%B8%85%E7%8E%89.jpg?alt=media&token=ba281c06-47d8-43fd-803a-08b199f58c76',NULL,'Y','1',NULL,'2018-07-03T12:38:35.3770Z'),('RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','TEST店','早午餐','AA店地址','10:00','21:00','[]','[{\"status\":\"OPEN\",\"date\":\"10:01-10:30\"},{\"status\":\"OPEN\",\"date\":\"10:31-11:00\"},{\"status\":\"OPEN\",\"date\":\"11:01-11:30\"},{\"status\":\"OPEN\",\"date\":\"11:31-12:00\"},{\"status\":\"OPEN\",\"date\":\"12:01-12:30\"},{\"status\":\"OPEN\",\"date\":\"12:31-13:00\"},{\"status\":\"OPEN\",\"date\":\"13:01-13:30\"},{\"status\":\"OPEN\",\"date\":\"13:31-14:00\"},{\"status\":\"OPEN\",\"date\":\"14:01-14:30\"},{\"status\":\"OPEN\",\"date\":\"14:31-15:00\"},{\"status\":\"OPEN\",\"date\":\"15:01-15:30\"},{\"status\":\"OPEN\",\"date\":\"15:31-16:00\"},{\"status\":\"OPEN\",\"date\":\"16:01-16:30\"},{\"status\":\"OPEN\",\"date\":\"16:31-17:00\"},{\"status\":\"OPEN\",\"date\":\"17:01-17:30\"},{\"status\":\"OPEN\",\"date\":\"17:31-18:00\"},{\"status\":\"OPEN\",\"date\":\"18:01-18:30\"},{\"status\":\"OPEN\",\"date\":\"18:31-19:00\"},{\"status\":\"OPEN\",\"date\":\"19:01-19:30\"},{\"status\":\"OPEN\",\"date\":\"19:31-20:00\"},{\"status\":\"OPEN\",\"date\":\"20:01-20:30\"},{\"status\":\"OPEN\",\"date\":\"20:31-21:00\"}]','24.957514','121.205150','NA',NULL,NULL,'Y','0',NULL,'2018-07-09T15:25:11.6090Z'),('RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','TEST店','早午餐','AA店地址','10:00','21:00','[]','[{\"status\":\"OPEN\",\"date\":\"10:01-10:30\"},{\"status\":\"OPEN\",\"date\":\"10:31-11:00\"},{\"status\":\"OPEN\",\"date\":\"11:01-11:30\"},{\"status\":\"OPEN\",\"date\":\"11:31-12:00\"},{\"status\":\"OPEN\",\"date\":\"12:01-12:30\"},{\"status\":\"OPEN\",\"date\":\"12:31-13:00\"},{\"status\":\"OPEN\",\"date\":\"13:01-13:30\"},{\"status\":\"OPEN\",\"date\":\"13:31-14:00\"},{\"status\":\"OPEN\",\"date\":\"14:01-14:30\"},{\"status\":\"OPEN\",\"date\":\"14:31-15:00\"},{\"status\":\"OPEN\",\"date\":\"15:01-15:30\"},{\"status\":\"OPEN\",\"date\":\"15:31-16:00\"},{\"status\":\"OPEN\",\"date\":\"16:01-16:30\"},{\"status\":\"OPEN\",\"date\":\"16:31-17:00\"},{\"status\":\"OPEN\",\"date\":\"17:01-17:30\"},{\"status\":\"OPEN\",\"date\":\"17:31-18:00\"},{\"status\":\"OPEN\",\"date\":\"18:01-18:30\"},{\"status\":\"OPEN\",\"date\":\"18:31-19:00\"},{\"status\":\"OPEN\",\"date\":\"19:01-19:30\"},{\"status\":\"OPEN\",\"date\":\"19:31-20:00\"},{\"status\":\"OPEN\",\"date\":\"20:01-20:30\"},{\"status\":\"OPEN\",\"date\":\"20:31-21:00\"}]','24.957514','121.205150','NA',NULL,NULL,'Y','0',NULL,'2018-07-09T16:46:07.4830Z');
/*!40000 ALTER TABLE `restaurant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_location_template`
--

DROP TABLE IF EXISTS `restaurant_location_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_location_template` (
  `restaurant_uuid` varchar(200) NOT NULL,
  `latitude` varchar(30) NOT NULL,
  `longitude` varchar(30) NOT NULL,
  `enable` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant_location_template`
--

LOCK TABLES `restaurant_location_template` WRITE;
/*!40000 ALTER TABLE `restaurant_location_template` DISABLE KEYS */;
INSERT INTO `restaurant_location_template` VALUES ('RESTAURANT_20180703_123835_292_c47aaae3-d034-4c99-bfeb-d3f5fb322741','24.916939','121.185289','Y'),('RESTAURANT_20180703_132500_258_45a808ed-62c6-4dc8-a575-7959d08e872e','24.917155','121.185031','Y'),('RESTAURANT_20180703_134217_832_657ed9cc-96f2-497f-afb3-b66cc0810851','24.975053','121.272488','Y'),('RESTAURANT_20180703_135100_695_42bc0046-848c-4f47-9dad-72b462c13c75','24.951671','121.209686','Y'),('RESTAURANT_20180703_141149_917_fddc2e23-c638-46fb-a256-ab9f24249eb9','24.990274','121.303354','Y'),('RESTAURANT_20180703_143011_635_17df8728-0144-4dde-8c49-66df17f8895f','24.956670','121.240274','Y'),('RESTAURANT_20180703_145221_256_da17e989-0cad-44c1-9f93-f5d249edb1f5','24.955403','121.221208','Y'),('RESTAURANT_20180703_153300_225_b753d702-c67e-4fc1-89e5-0db323dc3b41','24.962306','121.223459','Y'),('RESTAURANT_20180703_160533_045_3a2ea34a-b577-4809-b2c5-9cbdf4fd4b77','24.954702','121.241303','Y'),('RESTAURANT_20180703_162909_595_9a09d8c1-9ecf-4def-8dfb-05a0073c6cf8','24.956964','121.239402','Y'),('RESTAURANT_20180703_163944_368_60ee15f2-0efa-432a-8dba-e84c552f10df','24.964712','121.190490','Y'),('RESTAURANT_20180703_170526_811_e9e10e4b-5e41-4de1-89b7-53a6746c4056','24.955898','121.215039','Y'),('RESTAURANT_20180703_171401_439_a1ef1fdd-a82f-4df2-b8ef-a09f876662cd','24.950324','121.213796','Y'),('RESTAURANT_20180703_172156_115_bae2c0f6-3e64-4a04-aa21-dc65335a5c1d','24.966135','121.224183','Y'),('RESTAURANT_20180703_173207_752_13e7eca4-c540-4242-a9fe-f1eceb40b7f4','24.950501','121.214066','Y'),('RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','24.957514','121.205150','Y'),('RESTAURANT_20180703_180917_067_76921b0e-6fc8-4886-9eb7-d4bc7c1682a4','24.947070','121.228868','Y'),('RESTAURANT_20180703_183503_412_27bcfe6e-98bd-434a-b38b-811d43ee0dba','24.910702','121.182749','Y'),('RESTAURANT_20180703_184748_234_cd8b87b8-e030-4187-a622-b07351f10cbb','24.959470','121.214393','Y'),('RESTAURANT_20180704_095800_845_a2e531ae-b500-43ab-9b43-85e9d66c98c2','24.968420','121.195666','Y'),('RESTAURANT_20180704_100959_902_2076cbdc-3546-46d9-ad9e-17096b30b729','24.968420','121.195666','Y'),('RESTAURANT_20180704_102555_222_02053f9b-91c6-49a2-b3ed-ab724dfa6696','24.954872','121.217067','Y'),('RESTAURANT_20180704_103530_521_b5027853-fb6c-4af6-8f35-bf369780314b','24.954126','121.224517','Y'),('RESTAURANT_20180704_104421_819_85682c41-b6a5-438d-b1ff-7fc90ab08c16','24.956225','121.225295','Y'),('RESTAURANT_20180704_105444_113_9e9a243e-8f5c-4eb7-be0b-2b18280f485a','24.968420','121.195666','Y'),('RESTAURANT_20180704_105444_115_5ed44b9f-53c0-49f4-9370-d6fc864465f9','24.990607','121.232380','Y'),('RESTAURANT_20180704_105444_115_af768812-f1ff-4683-85ff-e78ac66fca48','24.968420','121.195666','Y'),('RESTAURANT_20180704_105444_115_ea486bc3-3aa3-44dc-9db6-ca119cd9422e','24.970147','121.263482','Y'),('RESTAURANT_20180704_105444_116_3a57cbaf-2c69-495f-9ceb-ff9d65f8de90','24.990607','121.232380','Y'),('RESTAURANT_20180704_105444_116_62c39bd2-6683-4bfd-aa28-4de0436bc0e5','24.970147','121.263482','Y'),('RESTAURANT_20180704_105444_116_71251933-33c6-49c4-bd60-56b7479c3653','24.968420','121.195666','Y'),('RESTAURANT_20180704_105452_068_eb48ad1c-c9ac-48e0-9a91-b7ec1492eb10','25.011367','121.270208','Y'),('RESTAURANT_20180704_105550_486_977c2309-db21-4f55-9323-ba41d7834ffe','24.970147','121.263482','Y'),('RESTAURANT_20180704_110609_240_509bc70b-4f96-40b0-916e-34ca3502b098','25.011367','121.270208','Y'),('RESTAURANT_20180704_112340_240_149c5091-bc17-4204-8929-ea7504c5ef9c','25.011367','121.270208','Y'),('RESTAURANT_20180704_113053_527_fecc6b1d-2bb7-4d06-a729-125e08b9e3e1','25.011367','121.270208','Y'),('RESTAURANT_20180704_113528_106_4f564d41-3692-4a97-841e-f1db0f105ccd','25.011367','121.270208','Y'),('RESTAURANT_20180704_114200_807_90e192e5-a1d7-4440-8699-4ad15da194b5','25.011367','121.270208','Y'),('RESTAURANT_20180704_115120_691_778865bc-5792-45b6-be7a-797756b2b6a7','25.011367','121.270208','Y'),('RESTAURANT_20180704_123343_135_b2d34e88-e0b0-452d-9703-803197b615ee','24.958349','121.202657','Y'),('RESTAURANT_20180709_142621_869_d4af5d04-bc22-4370-9020-de9262188286','24.957514','121.205150','Y'),('RESTAURANT_20180709_142744_948_8a163f0c-4c50-41bf-a181-8fbff698892e','24.957514','121.205150','Y'),('RESTAURANT_20180709_142758_769_91fd5e26-7cf6-47c2-9a81-ea366744b662','24.957514','121.205150','Y'),('RESTAURANT_20180709_142854_662_75f08358-9744-4d26-a130-aab8ee49d6df','24.957514','121.205150','Y'),('RESTAURANT_20180709_152511_609_55b35850-d38f-4099-895c-3b391853aaf8','24.957514','121.205150','Y'),('RESTAURANT_20180709_164607_482_386e1408-9228-46da-91d4-ab3a24a9cead','24.957514','121.205150','Y');
/*!40000 ALTER TABLE `restaurant_location_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_order_finish`
--

DROP TABLE IF EXISTS `seller_order_finish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_order_finish` (
  `order_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
  `account_uuid` varchar(200) NOT NULL,
  `user_message` varchar(220) DEFAULT NULL,
  `create_date` varchar(30) NOT NULL,
  `update_date` varchar(30) NOT NULL,
  `order_price` varchar(20) NOT NULL,
  `order_bonus` varchar(20) NOT NULL,
  `fetch_date` varchar(30) NOT NULL,
  `order_data` mediumtext NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'FINISH' COMMENT 'CANCEL,  FAIL,\nFINISH"',
  `enable` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`order_uuid`),
  KEY `index2` (`order_uuid`),
  KEY `index3` (`account_uuid`),
  KEY `index4` (`restaurant_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_order_finish`
--

LOCK TABLES `seller_order_finish` WRITE;
/*!40000 ALTER TABLE `seller_order_finish` DISABLE KEYS */;
INSERT INTO `seller_order_finish` VALUES ('ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-09T10:00:52.1220Z','2018-07-09T10:00:52.1220Z','25','2','2018-07-09T10:17:59.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FAIL','Y'),('ORDER_20180712_141229_744_10441a44-1323-45be-a428-7833c6022921','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:13:56.5290Z','2018-07-12T14:13:56.5290Z','35','3','2018-07-12T14:32:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:32:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_141431_978_b69ee2a8-d3fe-4a49-abc9-06c05e5e9b86','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:20:44.9650Z','2018-07-12T14:20:44.9650Z','25','2','2018-07-12T14:34:28.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:34:28.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_141902_993_40a5bac1-0df0-4740-a5f8-a373a2c34dae','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:20:47.5900Z','2018-07-12T14:20:47.5900Z','25','2','2018-07-12T14:39:00.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:39:00.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_142053_319_1422eb74-5ba9-4f9e-8d52-ba4c72aca062','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:22:40.8690Z','2018-07-12T14:22:40.8690Z','25','2','2018-07-12T14:40:26.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:40:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_142209_286_5e066dd8-37f3-432d-8524-00c6ddd8e8c1','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:29:28.6180Z','2018-07-12T14:29:28.6180Z','30','3','2018-07-12T14:42:05.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:42:05.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_142842_519_71c6167d-3029-4dff-ab78-1f6a21796b5a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:29:20.2240Z','2018-07-12T14:29:20.2240Z','25','2','2018-07-12T14:48:39.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:48:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_142942_126_4b32da80-6c3f-4f0e-b957-f5790898d817','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:41:39.6780Z','2018-07-12T14:41:39.6780Z','25','2','2018-07-12T14:49:38.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:49:38.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','CANCEL','Y'),('ORDER_20180712_143108_652_e5cca223-26d1-42f7-82a9-6710e4a8f1be','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T14:41:42.5440Z','2018-07-12T14:41:42.5440Z','55','5','2018-07-12T14:51:04.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:51:04.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}','CANCEL','Y'),('ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','','2018-07-12T16:27:20.8480Z','2018-07-12T16:27:20.8480Z','25','2','2018-07-12T16:37:07.0000Z','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}','FINISH','Y');
/*!40000 ALTER TABLE `seller_order_finish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_registered`
--

DROP TABLE IF EXISTS `seller_registered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_registered` (
  `phone` varchar(30) NOT NULL COMMENT '聯繫號碼',
  `device_id` varchar(200) NOT NULL COMMENT '同 push_token，判斷是否重複註冊',
  `seller_name` varchar(250) NOT NULL COMMENT '商家名稱',
  `address` varchar(250) DEFAULT NULL COMMENT '地址',
  `name` varchar(15) NOT NULL COMMENT '聯繫人',
  `create_date` varchar(30) NOT NULL COMMENT '建立時間\n2014-10-27T08:09:30.914Z',
  `status` varchar(25) NOT NULL DEFAULT 'UNFINISHED' COMMENT '判斷客服是否已經處理\n‘UNFINISHED’,‘FINISHED’',
  PRIMARY KEY (`phone`,`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_registered`
--

LOCK TABLES `seller_registered` WRITE;
/*!40000 ALTER TABLE `seller_registered` DISABLE KEYS */;
/*!40000 ALTER TABLE `seller_registered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order_info`
--

DROP TABLE IF EXISTS `user_order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order_info` (
  `order_uuid` varchar(200) NOT NULL,
  `account_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
  `user_message` varchar(220) DEFAULT NULL,
  `create_date` varchar(30) NOT NULL,
  `update_date` varchar(30) NOT NULL,
  `order_price` varchar(20) NOT NULL,
  `order_bonus` varchar(20) NOT NULL,
  `fetch_date` varchar(30) NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'UNFINISH',
  `enable` varchar(1) DEFAULT 'Y',
  `order_data` mediumtext NOT NULL,
  PRIMARY KEY (`order_uuid`),
  KEY `index2` (`account_uuid`),
  KEY `index3` (`order_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order_info`
--

LOCK TABLES `user_order_info` WRITE;
/*!40000 ALTER TABLE `user_order_info` DISABLE KEYS */;
INSERT INTO `user_order_info` VALUES ('ORDER_20180709_095802_676_dffbcb1f-9588-40cb-ac6d-ecebe8f32f19','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-09T09:58:02.6800Z','2018-07-09T10:00:52.1220Z','25','2','2018-07-09T10:17:59.0000Z','FAIL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:17:59.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180709_101310_090_2480efa9-dda4-4320-9706-480dc9cfef79','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-09T10:13:10.0940Z','2018-07-09T10:13:28.5870Z','25','2','2018-07-09T10:33:06.0000Z','PROCESSING','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-09T10:33:06.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_141229_744_10441a44-1323-45be-a428-7833c6022921','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:12:29.7480Z','2018-07-12T14:13:56.5290Z','35','3','2018-07-12T14:32:26.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:32:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_141431_978_b69ee2a8-d3fe-4a49-abc9-06c05e5e9b86','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:14:31.9800Z','2018-07-12T14:20:44.9650Z','25','2','2018-07-12T14:34:28.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:34:28.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_141902_993_40a5bac1-0df0-4740-a5f8-a373a2c34dae','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:19:02.9960Z','2018-07-12T14:20:47.5900Z','25','2','2018-07-12T14:39:00.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:39:00.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_142053_319_1422eb74-5ba9-4f9e-8d52-ba4c72aca062','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:20:53.3210Z','2018-07-12T14:22:40.8690Z','25','2','2018-07-12T14:40:26.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:40:26.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_142209_286_5e066dd8-37f3-432d-8524-00c6ddd8e8c1','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:22:09.2880Z','2018-07-12T14:29:28.6180Z','30','3','2018-07-12T14:42:05.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:42:05.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_142842_519_71c6167d-3029-4dff-ab78-1f6a21796b5a','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:28:42.5230Z','2018-07-12T14:29:20.2240Z','25','2','2018-07-12T14:48:39.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:48:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_142942_126_4b32da80-6c3f-4f0e-b957-f5790898d817','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:29:42.1280Z','2018-07-12T14:41:39.6780Z','25','2','2018-07-12T14:49:38.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:49:38.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_143108_652_e5cca223-26d1-42f7-82a9-6710e4a8f1be','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:31:08.6550Z','2018-07-12T14:41:42.5440Z','55','5','2018-07-12T14:51:04.0000Z','CANCEL','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T14:51:04.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}'),('ORDER_20180712_144202_142_d5d835fe-faba-4f36-aa7a-a1931b7b7d83','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T14:42:02.1430Z','2018-07-12T14:42:02.1430Z','25','2','2018-07-12T15:01:58.0000Z','UNFINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T15:01:58.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_161713_421_6d97c055-39d6-48fa-b818-0490645689c8','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:17:13.4250Z','2018-07-12T16:27:20.8480Z','25','2','2018-07-12T16:37:07.0000Z','FINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:37:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"Developer\",\"user_phone\":\"0928297076\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_162915_037_4272a40b-99b4-4bfb-9595-c31f650456a5','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:29:15.0370Z','2018-07-12T16:37:47.1230Z','50','5','2018-07-12T16:49:08.0000Z','PROCESSING','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:49:08.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_163217_787_3c531b98-8d2f-4f7b-9ada-a812dee3e272','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:32:17.7880Z','2018-07-12T16:37:55.0880Z','35','3','2018-07-12T16:52:12.0000Z','PROCESSING','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:52:12.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58\",\"category_name\":\"原味茶\",\"food_name\":\"台灣青茶\",\"price\":\"35\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_163250_691_6af0f68f-94f6-4aab-9143-310a6f5556fe','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:32:50.6910Z','2018-07-12T16:32:50.6910Z','55','5','2018-07-13T16:52:39.0000Z','UNFINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-13T16:52:39.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180701_133002_810_f1ba0bf3-3825-49fc-ad00-05d4d5f75167\",\"category_name\":\"原味茶\",\"food_name\":\"翡翠檸檬11\",\"price\":\"55\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[],\"demands\":[]}}]}'),('ORDER_20180712_164001_663_55159e7a-8e60-40fc-b11e-49483312daf3','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:40:01.6630Z','2018-07-12T16:40:01.6630Z','30','3','2018-07-12T16:59:55.0000Z','UNFINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T16:59:55.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977\",\"category_name\":\"原味茶\",\"food_name\":\"安溪鐵觀音\",\"price\":\"30\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_164042_180_472f989a-049b-4991-a5e2-7d2b7e315f06','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:40:42.1800Z','2018-07-12T17:29:37.6140Z','25','2','2018-07-12T22:00:34.0000Z','CAN_FETCH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T22:00:34.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_164114_821_2e15495d-8387-4de4-9727-3bc9d7ac29f4','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T16:41:14.8210Z','2018-07-12T17:29:31.6210Z','40','4','2018-07-12T18:01:07.0000Z','CAN_FETCH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T18:01:07.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774\",\"category_name\":\"調味茶\",\"food_name\":\"冰釀陳梅\",\"price\":\"40\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_172959_062_ac264342-9ae8-4fab-9427-64148e93f001','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T17:29:59.0620Z','2018-07-12T17:29:59.0620Z','25','2','2018-07-12T20:49:51.0000Z','UNFINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T20:49:51.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}'),('ORDER_20180712_173034_664_432feabd-d568-4f1c-9c8b-d301b63f85ce','USER_20180706_165011_477_78766b68-806e-423a-9feb-2e86fbe5276a','RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686','','2018-07-12T17:30:34.6640Z','2018-07-12T17:30:34.6640Z','25','2','2018-07-12T17:50:21.0000Z','UNFINISH','Y','{\"restaurant_uuid\":\"RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686\",\"fetch_date\":\"2018-07-12T17:50:21.0000Z\",\"user_message\":\"\",\"restaurant_name\":\"清玉-民族店\",\"restaurant_address\":\"320桃園市中壢區民族路二段130號\",\"user_name\":\"DEMO\",\"user_phone\":\"0000000000\",\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d\",\"count\":\"1\",\"item\":{\"food_uuid\":\"FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9\",\"category_name\":\"原味茶\",\"food_name\":\"茉莉鮮綠茶\",\"price\":\"25\",\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"}],\"opts\":[],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"}]}]}}]}');
/*!40000 ALTER TABLE `user_order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verify_phone_log`
--

DROP TABLE IF EXISTS `verify_phone_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verify_phone_log` (
  `batch_id` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL COMMENT '電話號碼',
  `verify_code` varchar(10) NOT NULL COMMENT '驗證碼 6碼數字',
  `verify_date` varchar(30) NOT NULL COMMENT '紀錄每次驗證時間\n2014-10-27T08:09:30.914Z\nAp Server 重置時間為 UTC 16:00 ，因台灣時區為 +08:00',
  PRIMARY KEY (`batch_id`),
  KEY `index2` (`phone`,`batch_id`,`verify_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verify_phone_log`
--

LOCK TABLES `verify_phone_log` WRITE;
/*!40000 ALTER TABLE `verify_phone_log` DISABLE KEYS */;
INSERT INTO `verify_phone_log` VALUES ('302513ee-18c9-4a23-9f08-bd89599c5d4c','0987654321','758118','2018-07-04T16:35:19.3340Z'),('5aaeccf1-0a3e-42ee-9c35-9f28a2505436','0928297076','021612','2018-07-09T15:05:33.0750Z'),('a800d1a1-0603-4507-890b-a655214922f6','0987654321','195441','2018-07-04T16:35:46.5940Z');
/*!40000 ALTER TABLE `verify_phone_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'melonltd_nabercDB'
--
/*!50003 DROP PROCEDURE IF EXISTS `change_login_status_and_insert_mobile_token` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `change_login_status_and_insert_mobile_token`(in_phone VARCHAR(20) , in_password VARCHAR(50), in_date  VARCHAR(30), in_device_uuid VARCHAR(200), in_device_token VARCHAR(200), in_device_category  VARCHAR(15))
BEGIN

	DECLARE total_count INT DEFAULT 0;
    DECLARE var_account_uuid VARCHAR(200) ;
    
 	SELECT COUNT(*) INTO total_count  FROM account_info  a WHERE a.phone= in_phone AND a.password = in_password;

    IF(total_count> 0) THEN
    
		SELECT a.account_uuid INTO var_account_uuid FROM account_info  a WHERE a.phone=in_phone AND a.password = in_password;
		INSERT INTO mobile_device (`device_uuid`, `device_token`, `account_uuid`, `device_category`, `create_date`) VALUES (in_device_uuid, in_device_token, var_account_uuid, in_device_category, in_date);
		UPDATE account_info a SET is_login = '1' , login_date = in_date WHERE  a.phone= in_phone AND a.password = in_password;
		
	SELECT a.* FROM account_info  a WHERE a.phone= in_phone AND a.password = in_password;
    END IF;

    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `change_order_finish` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `change_order_finish`(in_order_uuid varchar(200), in_status varchar(25), in_date varchar(30))
BEGIN

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `change_order_status` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `change_order_status`(in_order_uuid varchar(200), in_status varchar(25), in_date varchar(30))
BEGIN
	
    -- 更改為製作中
	IF (in_status = 'PROCESSING') THEN

        UPDATE  
			order_info o, user_order_log u 
        SET 
			o.status=in_status , o.update_date=in_date, u.status=in_status, u.update_date=in_date
		WHERE 
			o.order_uuid =in_order_uuid AND o.status='UNFINISH' AND o.enable='Y' 
        AND 
			u.order_uuid =in_order_uuid AND u.status='UNFINISH' AND u.enable='Y';
        
        
         -- 最後存入 log
		INSERT INTO 
			order_log (`order_uuid`, `account_uuid`, `restaurant_uuid`,`user_message`,  `create_date`, `update_date`, `order_price`, `order_bonus`, `fetch_date`, `order_data`, `status`, `enable`)
		SELECT 
			o.order_uuid, o.account_uuid, o.restaurant_uuid, o.user_message, in_date, in_date, o.order_price, o.order_bonus, o.fetch_date, o.order_data, in_status , 'Y'
		FROM
			order_info o
		WHERE 
			o.order_uuid = in_order_uuid;
		 SELECT * FROM  order_info;
    
    -- 更改為可領取
    ELSEIF (in_status='CAN_FETCH') THEN
    
		UPDATE  
			order_info o, user_order_log u 
        SET 
			o.status=in_status , o.update_date=in_date, u.status=in_status, u.update_date=in_date
		WHERE 
			o.order_uuid =in_order_uuid AND o.status IN ('UNFINISH','PROCESSING')  AND o.enable='Y' 
        AND 
			u.order_uuid =in_order_uuid AND u.status IN ('UNFINISH','PROCESSING')  AND u.enable='Y';

		 -- 最後存入 log
		INSERT INTO 
			order_log (`order_uuid`, `account_uuid`, `restaurant_uuid`,`user_message`,  `create_date`, `update_date`, `order_price`, `order_bonus`, `fetch_date`, `order_data`, `status`, `enable`)
		SELECT 
			o.order_uuid, o.account_uuid, o.restaurant_uuid, o.user_message, in_date, in_date, o.order_price, o.order_bonus, o.fetch_date, o.order_data, in_status , 'Y'
		FROM
			order_info o
		WHERE 
			o.order_uuid = in_order_uuid;
		 SELECT * FROM  order_info;
     
    END IF;
    
    SELECT 
		o.*
	FROM
		order_info o
	WHERE
		o.status=in_status
	AND
		o.order_uuid = in_order_uuid;

    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_category_food_status` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_category_food_status`( in_food_uuids varchar(200))
BEGIN

	SELECT
		f.food_uuid
    FROM 
		category_food_rel f, restaurant_category_rel r
	WHERE 
		FIND_IN_SET(f.food_uuid , in_food_uuids)
	AND
		f.status ='OPEN'  AND f.enable='Y'
	AND 
		r.status = 'OPEN' AND r.enable ='Y'
	AND 
		f.category_uuid = r.category_uuid;
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_mobile_token_by_account_uuid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_mobile_token_by_account_uuid`(in_account_uuid VARCHAR(200))
BEGIN
	SELECT *  FROM  mobile_device WHERE  account_uuid = in_account_uuid ORDER BY create_date DESC LIMIT 1 ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_order_by_like_phone_and_between` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_order_by_like_phone_and_between`(in_phone varchar(30) , in_start_date varchar(30), in_end_date varchar(30))
BEGIN


-- BETWEEN 2 day


	SELECT 
		s.*
	FROM 
		seller_order_finish s
	WHERE
		(s.fetch_date BETWEEN in_start_date AND in_end_date)
	AND
		s.status != 'UNFINISH'
	AND
		s.account_uuid IN(
			SELECT 
				a.account_uuid
			FROM 
				account_info  a
			WHERE
				a.phone LIKE   CONCAT('%', in_phone )
		)
		
	UNION ALL

	SELECT 
		o.*
	FROM 
		order_info o
	WHERE
		(o.fetch_date BETWEEN in_start_date AND in_end_date)
	AND
		o.account_uuid IN(
			SELECT 
				a.account_uuid
			FROM 
				account_info  a
			WHERE
				a.phone LIKE '%21'
		);



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_restaurant_by_area` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_restaurant_by_area`( in_area VARCHAR(10), in_page INT(3))
BEGIN

-- 依照區域收索

SELECT 
	r.*, (-1) AS distance
FROM 
	restaurant_info r 
WHERE 
	r.enable ='Y'
AND
	r.address     
LIKE
	CONCAT('%', in_area , '%')
ORDER BY  
	-- 排序建立時間
    r.create_date ASC 
LIMIT 10 OFFSET in_page;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_restaurant_by_category` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_restaurant_by_category`( in_category VARCHAR(10), in_page INT(3))
BEGIN

-- 依照餐館類型收索
-- DECLARE distance VARCHAR(1) DEFAULT '0';

SELECT 
	r.*, (-1) AS distance
FROM 
	restaurant_info r 
WHERE 
	r.enable ='Y'
AND
	r.restaurant_category     
LIKE
	CONCAT('%', in_category , '%')
ORDER BY  
	-- 排序建立時間
    r.create_date ASC 
LIMIT 10 OFFSET in_page;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_restaurant_distance_by_latlng` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_restaurant_distance_by_latlng`(in_latitude VARCHAR(20), in_longitude VARCHAR(20), in_page INT(3))
BEGIN

-- 依照最進距離收索，並限制20公里內

SELECT
 *
FROM  
	(
		SELECT r.*, 
             ROUND( 6378.138 * 2 *
				ASIN(
						SQRT(POW(SIN((
                        
                        CAST(in_latitude AS DECIMAL(20,10)) * PI()/180-r.latitude*PI()/180)/2),2)+COS(
                        
                        CAST(in_latitude AS DECIMAL(20,10)) * PI()/180)*COS(r.latitude*PI()/180) * POW(SIN((
                        
                        CAST(in_longitude AS DECIMAL(20,10)) * PI()/180-r.longitude*PI()/180)/2),2))) *1000
                        
			) AS distance
		FROM
			restaurant_info r
            
		) AS innerTable
       
WHERE 

 enable ='Y' AND distance < 20000
-- 限制距離20公里以內

ORDER BY  
-- 排序距離
    distance ASC 
LIMIT 10 OFFSET in_page;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_restaurant_top_by_latlng` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_restaurant_top_by_latlng`(in_latitude VARCHAR(20), in_longitude VARCHAR(20), in_top INT)
BEGIN

-- 依照 推薦排名搜索

SELECT r.*,
-- 距離計算公式
	ROUND( 6378.138 * 2 * ASIN(  
							SQRT(  POW(  SIN(  ( 
								
                                 CAST(in_latitude AS DECIMAL(20,10))  * PI() / 180 - r.latitude * PI() / 180  ) / 2  ),  2 ) + COS(
                                
                                 CAST(in_latitude AS DECIMAL(20,10))  * PI() / 180) * COS(r.latitude * PI() / 180) * POW(  SIN(  (  
							
                                 CAST(in_longitude AS DECIMAL(20,10))  * PI() / 180 - r.longitude * PI() / 180  ) / 2  ),  2  )  )  )*1000
				 ) AS distance  
FROM  
    restaurant_info  r
    
WHERE 
	r.enable ='Y'
    
ORDER BY  
-- 排序排名
    r.top ASC 
LIMIT in_top ;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_seller_mobile_token_by_restaurant_uuid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_seller_mobile_token_by_restaurant_uuid`(in_restaurant_uuid varchar(200))
BEGIN
	SELECT 
		m.* 
	FROM 
		mobile_device m, account_info a
	WHERE
		a.restaurant_uuid = in_restaurant_uuid
	AND
		a.account_uuid = m.account_uuid
	ORDER BY  
	-- 排序排名
		m.create_date DESC 
	LIMIT 1 ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_user_order_log` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_user_order_log`(in_account_uuid VARCHAR(200), in_page INT(3))
BEGIN
	SELECT a.* 
	FROM 
		user_order_log a
	WHERE
		a.enable = 'Y'
	AND
		a.account_uuid = in_account_uuid
	AND 
		a.status IN ('UNFINISH','PROCESSING','CAN_FETCH ','CANCEL','FAIL','FINISH')
	ORDER BY  
	-- 排序建立時間
		a.fetch_date DESC 
	LIMIT 10 OFFSET in_page;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `subimt_ordaer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `subimt_ordaer`(in_order_uuid VARCHAR(200),  in_account_uuid varchar(200), in_restaurant_uuid VARCHAR(200), in_restaurant_name VARCHAR(150),in_restaurant_address VARCHAR(250), in_user_massage VARCHAR(220),  in_data varchar(30), in_order_price  varchar(20),  in_order_bonus varchar(20), in_fetch_date varchar(30),  in_order_data MEDIUMTEXT)
BEGIN
	
	DECLARE var_count INT DEFAULT 0;
    
	SELECT COUNT(*) INTO var_count FROM user_order_log  u WHERE u.account_uuid = in_account_uuid AND u.status IN('UNFINISH', 'PROCESSING','CAN_FETCH');
    
     IF(var_count < 3) THEN
     
		INSERT INTO 
			order_info (`order_uuid`, `account_uuid`, `restaurant_uuid`,`user_message`,  `create_date`, `update_date`, `order_price`, `order_bonus`, `fetch_date`, `order_data`, `status`, `enable`)
		VALUES 
			(in_order_uuid, in_account_uuid, in_restaurant_uuid, in_user_massage, in_data, in_data, in_order_price, in_order_bonus, in_fetch_date, in_order_data, 'UNFINISH', 'Y');
			
		INSERT INTO 
			user_order_log (`order_uuid`, `account_uuid`, `restaurant_uuid`, `restaurant_name`, `restaurant_address`, `create_date`, `update_date`, `order_price`, `order_bonus`, `fetch_date`, `order_data`, `status`, `enable`) 
		VALUES 
			(in_order_uuid, in_account_uuid, in_restaurant_uuid, in_restaurant_name, in_restaurant_address, in_data, in_data, in_order_price, in_order_bonus, in_fetch_date, in_order_data, 'UNFINISH', 'Y');

		INSERT INTO 
			order_log (`order_uuid`, `account_uuid`, `restaurant_uuid`,`user_message`,  `create_date`, `update_date`, `order_price`, `order_bonus`, `fetch_date`, `order_data`, `status`, `enable`)
		VALUES 
			(in_order_uuid, in_account_uuid, in_restaurant_uuid, in_user_massage, in_data, in_data, in_order_price, in_order_bonus, in_fetch_date, in_order_data, 'UNFINISH', 'Y');
	
	END IF;
    
    	SELECT o.* , u.restaurant_name, u.restaurant_address
		FROM 
			order_info o, user_order_log u
		WHERE
			o.order_uuid = in_order_uuid
		AND
			u.order_uuid = in_order_uuid;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-17  9:49:43
