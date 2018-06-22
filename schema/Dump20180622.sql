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
INSERT INTO `account_info` VALUES ('0928297071','0928297071','USER_20180621_b41fed6d-8e77-415c-897f-c720835c83a3','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','seller test','a123456','evan.wang@melonltd.com.tw','桃園市平鎮區文化街217號','SELLERS',NULL,'0','MANAGE','Y','Y','2018-06-21T11:40:45.5860Z',NULL,NULL,'1988/04/06'),('0928297076','0928297076','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157',NULL,'EvanWang','a123456','jnswcy@gmail.com','Addw','NON_STUDENT','中央大學','0','','Y','Y','2018-06-22T12:55:36.2170Z','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/userUSER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157.jpg?alt=media&token=1c74be21-2527-420a-88c8-60d278355d6c',NULL,'1984-06-20');
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
INSERT INTO `advertisement` VALUES ('AD_33c500dd-487f-4768-9000-010ae270848f','title','text','https://www.thelocal.it/userdata/images/article/69523836b0191608c41d640feead8da2be5462038d3409e1e3900fad039c7fc8.jpg','jpg','Y','2018-06-06T07:08:51.110Z'),('AD_3d84aa89-9b8f-4fe1-be1c-d80125434a84','title','text','https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/brunch-friends-food-1524088106.jpg','jpg','Y','2018-06-06T07:08:51.110Z'),('AD_64b59e79-61ec-4974-a6df-7ca99df13648','title','text','https://www.mcdonalds.com/content/dam/usa/documents/mcdelivery/mcdelivery_new11.jpg','jpg','Y','2018-06-06T07:08:51.110Z'),('AD_95d2b87b-1de1-40f9-9093-a38b79f5d7c8','title','text','https://media3.s-nbcnews.com/j/MSNBC/Components/Video/201803/tdy_mk_joy_healthy_food_180319_1920x1080.today-inline-vid-featured-desktop.jpg','jpg','Y','2018-06-06T07:08:51.110Z'),('AD_a69d67a2-4214-4edb-966c-c46b5e0878ae','title','text','https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/3/1/8/4/144813-1-eng-GB/Front.jpg','jpg','Y','2018-06-06T07:08:51.110Z');
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_food_rel`
--

DROP TABLE IF EXISTS `category_food_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_food_rel` (
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
-- Dumping data for table `category_food_rel`
--

LOCK TABLES `category_food_rel` WRITE;
/*!40000 ALTER TABLE `category_food_rel` DISABLE KEYS */;
INSERT INTO `category_food_rel` VALUES ('FOOD_20180622_121236_779_f77346fe-ee65-4bda-be68-64c36b4c9b25','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','一品紅茶','25',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"},{\"name\":\"熱小\",\"price\":\"25\"},{\"name\":\"熱大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:12:36.7830Z'),('FOOD_20180622_121551_064_a60b0228-6d8b-4986-8a5c-2135fc5656f9','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','茉莉鮮綠茶','25',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"25\"},{\"name\":\"熱小\",\"price\":\"25\"},{\"name\":\"熱大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:15:51.0640Z'),('FOOD_20180622_122500_147_67d04292-91b3-4fcf-92a9-95bb9972ab58','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','台灣青茶','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"35\"},{\"name\":\"熱小\",\"price\":\"30\"},{\"name\":\"熱大\",\"price\":\"35\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:25:00.1470Z'),('FOOD_20180622_124928_591_f2ce1535-5cea-4fbb-ac7e-2d50738f2977','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','安溪鐵觀音','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"},{\"name\":\"熱小\",\"price\":\"30\"},{\"name\":\"熱大\",\"price\":\"35\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:49:28.5910Z'),('FOOD_20180622_125004_386_df2582d8-4af7-4264-b1ea-e87951a91b8d','RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','冬瓜茶','30',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"30\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:50:04.3860Z'),('FOOD_20180622_125157_394_6802fd24-2c5b-4bb4-936d-f7c5bc53a774','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','冰釀陳梅','40',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"40\"},{\"name\":\"熱小\",\"price\":\"40\"},{\"name\":\"熱大\",\"price\":\"50\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:51:57.3940Z'),('FOOD_20180622_125242_436_3f4607f4-2386-4ded-8612-08fe99e2176c','RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','蜂蜜紅茶','40',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"45\"},{\"name\":\"熱小\",\"price\":\"40\"},{\"name\":\"熱大\",\"price\":\"50\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:52:42.4360Z'),('FOOD_20180622_125354_107_81ec4b1c-7827-4efe-b15b-c93cad62dc25','RESTAURANT_CATEGORY_20180622_114923_387_722a2f57-d04c-43e0-b6e0-61a9dfb29eb1','翡翠檸檬','55',NULL,NULL,'{\"scopes\":[{\"name\":\"冰大\",\"price\":\"55\"}],\"opts\":[{\"name\":\"仙草\",\"price\":\"5\"},{\"name\":\"珍珠\",\"price\":\"10\"},{\"name\":\"愛玉\",\"price\":\"10\"},{\"name\":\"蘆薈\",\"price\":\"10\"}],\"demands\":[{\"name\":\"冰塊\",\"datas\":[{\"name\":\"多冰\"},{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"去冰\"},{\"name\":\"熱飲\"}]},{\"name\":\"甜度\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少糖\"},{\"name\":\"微糖\"},{\"name\":\"無糖\"}]}]}','OPEN','Y','2018-06-22T12:53:54.1070Z');
/*!40000 ALTER TABLE `category_food_rel` ENABLE KEYS */;
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
  `device_token` varchar(200) NOT NULL COMMENT '裝置ID\n推播所使用的token',
  `device_category` varchar(15) NOT NULL COMMENT '裝置類型\n‘IOS’,''ANDROID’',
  `create_date` varchar(30) NOT NULL COMMENT '建立時間\n2014-10-27T08:09:30.914Z',
  PRIMARY KEY (`device_uuid`),
  KEY `index2` (`account_uuid`),
  KEY `index3` (`device_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mobile_device`
--

LOCK TABLES `mobile_device` WRITE;
/*!40000 ALTER TABLE `mobile_device` DISABLE KEYS */;
INSERT INTO `mobile_device` VALUES ('DEVICE_0d8edc08-7289-467c-8a11-f8d32e01dd73','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:38:18.4550Z'),('DEVICE_15743165-1e12-40c0-98a3-fa8d6a10374f','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:41:39.4700Z'),('DEVICE_157f0ce8-e3f2-4a64-b797-b592581329b7','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:54:14.1520Z'),('DEVICE_18effed4-7719-49e4-b0ce-5533f9334697','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:12:18.9100Z'),('DEVICE_198f3a10-92de-495a-b297-bfd983804ddf','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:51:39.0870Z'),('DEVICE_19fa4e44-5dcd-4ec1-bdbd-661d5c9da15b','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:30:01.9070Z'),('DEVICE_20180622_112153_683_4272d4ac-1981-4bd6-869e-bde5746a4454','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T11:21:53.6780Z'),('DEVICE_20180622_112434_421_8d2089b9-e88b-4ad9-a8f4-29fe87c195c1','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-22T11:24:34.4210Z'),('DEVICE_20180622_113749_881_8f6f9b62-8c84-4ce4-aefa-3d7783215223','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T11:37:49.8800Z'),('DEVICE_20180622_123412_989_8367bdbb-4f34-4a61-953d-4039472f6f94','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T12:34:12.9880Z'),('DEVICE_20180622_123626_546_aefee9c8-57d1-4356-8bf9-e9e79f8ddd38','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T12:36:26.5450Z'),('DEVICE_20180622_123656_291_03e18d9f-e4ec-470c-a52c-cc9e1782af08','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T12:36:56.2910Z'),('DEVICE_20180622_125536_210_e812ea81-4276-47ea-bf63-4e50d20ea827','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','cze_cFPtzVU:APA91bEDWNtZWdsrKxTtVqkvVkfbUWVsAMRvmI8_Q056mDrWwnUvWZlUz_5Yd6kpbQx6A1x0bnG3UkVwQxj39bTxB7wfEsiZaAR2HW-OkS2_-eeUDI1_e0omSSZIRIjO73Iga0IwabrVU-3ZD-0BpyxrHF3cQJ-GbQ','ANDROID','2018-06-22T12:55:36.2090Z'),('DEVICE_275a74f4-8995-4495-bb5f-1d4cd642ffa1','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:45:26.8800Z'),('DEVICE_2761bae4-0be8-461a-b3e4-305627ac3da0','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:41:15.7440Z'),('DEVICE_29848c93-17c8-42a5-9056-e7d3fbc89fbb','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:27:54.3430Z'),('DEVICE_2f048c98-8cd6-4172-a685-895b36922fee','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:33:10.3870Z'),('DEVICE_2f3a8297-d7a4-497d-a4d1-4f6660b7f0ac','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:40:29.6080Z'),('DEVICE_333bf52f-0afd-4dc1-bb91-b292fbc41cc7','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:00:07.1240Z'),('DEVICE_3a60cafc-e63d-421a-a31b-010f38d9a500','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:34:21.1140Z'),('DEVICE_3fa69ade-fc32-4431-83c8-9b45f3598b80','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:44:34.6500Z'),('DEVICE_4054752a-c70a-40ca-89f2-924ca1411471','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:59:27.6910Z'),('DEVICE_46bd05d8-a143-4484-a1ca-321c7ce15dd4','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:36:27.2710Z'),('DEVICE_4c254bb9-3ae1-430f-b95a-5a1646e241dc','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:58:41.0320Z'),('DEVICE_4e5fc888-bb31-49a0-b8bb-0c4bad73aa23','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:55:46.2270Z'),('DEVICE_51851918-6631-4ea6-9424-4e7ecde40ef2','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:36:05.4980Z'),('DEVICE_5196cd19-1ecd-4ac8-bc0b-04fbbef474d7','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:25:54.8990Z'),('DEVICE_52b10214-094b-4f47-bc2e-3ddd87238a03','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:32:39.9590Z'),('DEVICE_61c56d71-17a4-4cfe-9028-c1bb86a8a053','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-20T17:28:22.1480Z'),('DEVICE_6ada4afe-acdd-4b93-8b3a-4329e4a0d078','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:57:00.8020Z'),('DEVICE_6ce8168d-4fa0-45a8-9067-75cbced09323','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:43:50.2440Z'),('DEVICE_71460db0-1bf7-4137-b9a5-ac884bd32316','USER_20180621_b41fed6d-8e77-415c-897f-c720835c83a3','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:40:45.5750Z'),('DEVICE_764259d2-61cd-4fe0-9507-59ea92b31496','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T14:59:32.8630Z'),('DEVICE_7ac1a3a7-c453-4984-8465-9a6860bc574c','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T14:44:29.7240Z'),('DEVICE_7c607a30-3c77-468b-8d84-19b65d4185a6','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T14:48:41.2120Z'),('DEVICE_7eb7e7d2-77a0-48f4-91ee-bd2a4f58ac86','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:44:30.7140Z'),('DEVICE_81368dab-9c8a-4dad-b2a3-961eab22287f','USER_20180621_b41fed6d-8e77-415c-897f-c720835c83a3','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:18:15.9700Z'),('DEVICE_851fe758-d49d-4334-96be-f378f398ee23','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T14:27:48.2790Z'),('DEVICE_862ef207-7da2-4524-9354-7ca631d7f219','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:20:05.5300Z'),('DEVICE_8e937766-b390-44e3-8f88-865ec38025e8','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:35:35.8950Z'),('DEVICE_9426c93f-61f7-4bda-8006-01b4ab6902ab','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:56:52.5250Z'),('DEVICE_95828880-09ba-4617-92ff-937ae7f4ff72','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:42:12.0200Z'),('DEVICE_982b84e0-4084-4270-8421-dddbe51a4e88','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:56:15.6940Z'),('DEVICE_9b7fc322-8691-40e4-9eff-cd327a11c10f','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:53:05.1560Z'),('DEVICE_9c30dbc4-c50c-46aa-9f46-dd2001f1407b','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:52:11.3900Z'),('DEVICE_9c9a884a-0663-4524-8c84-388ba64b7936','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:13:44.3540Z'),('DEVICE_9cdb0c72-1972-4c7c-8748-be44fa60f3dc','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:44:28.1830Z'),('DEVICE_9fa18a06-bed6-4749-afbb-f778bd47a41d','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:06:51.9990Z'),('DEVICE_9ff71ea4-4080-423e-81f1-d785f0496a35','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:01:37.1850Z'),('DEVICE_a2d8e755-06fe-4654-acd7-57078083b79f','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:53:15.3890Z'),('DEVICE_a41efc9f-cdc8-45b3-ab51-fbb08609297c','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:37:26.4580Z'),('DEVICE_a6d090d0-dda8-4128-8e25-1a381258715e','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T16:40:45.9590Z'),('DEVICE_a8d22003-7997-43f7-af4b-3eedb8c2017e','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:46:05.7090Z'),('DEVICE_af7b9540-f5c0-43d7-809f-4b17cb5c7032','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:48:04.5990Z'),('DEVICE_b10ffded-c83b-4e9b-8979-8d1bb9d25a5b','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:05:48.9340Z'),('DEVICE_b612426c-95d6-4947-b69b-56f0cc0a58e0','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:37:26.0470Z'),('DEVICE_ba5e4ee1-e011-43c5-986a-de23d6bdae6d','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:38:48.9520Z'),('DEVICE_c186f899-2631-4e5a-938c-6bda8f7e2acf','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:53:03.7330Z'),('DEVICE_c2b797f1-76e5-41fb-b364-416018bbd0f8','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:02:25.9520Z'),('DEVICE_c9e5464f-d4eb-4027-af3a-a84319a76e48','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:15:27.8090Z'),('DEVICE_ceb8c2f5-edf0-4489-8e01-f33cb5218417','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:14:38.9860Z'),('DEVICE_cf2b8944-3f83-49c3-931e-e9183e142712','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:44:03.9090Z'),('DEVICE_d28e272b-5096-4901-af64-1cf8528a9dd1','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:59:40.5630Z'),('DEVICE_d29265a1-8826-4abe-b341-98e7711b2e9a','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T09:25:25.9110Z'),('DEVICE_d2e421b3-57fa-4ad6-8c50-f4173b8e6f3d','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T14:04:00.9120Z'),('DEVICE_dbf47b99-f0f0-43ec-a025-3a1866570e85','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:03:15.3800Z'),('DEVICE_de6b13fa-24d4-4c44-85ab-44d2e561a99c','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:25:54.0660Z'),('DEVICE_e01c78f0-aa9b-46b5-b44e-5db013b39d7f','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:35:04.3640Z'),('DEVICE_e201bf14-e412-4a8a-9cad-5f33927032b4','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:53:43.2350Z'),('DEVICE_e864cd2d-b978-4ac1-9648-1b4a16e19aad','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-20T17:27:17.9570Z'),('DEVICE_ea9d4f07-9de5-4b97-ab53-d3ce1cec009c','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T11:18:05.3490Z'),('DEVICE_f39bf739-938d-4797-aed7-80bd14bdf890','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T15:20:41.1550Z'),('DEVICE_f8d243e7-3990-41db-92a9-b9962371d808','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-20T17:27:25.0870Z'),('DEVICE_fa5433b9-9c80-4726-a718-5ad08d5405c0','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T10:57:55.3470Z'),('DEVICE_fa842605-80b0-4f36-b50f-2f4b5fb3b5df','USER_20180620_b39c9635-a05e-4def-8180-087bdbaa1157','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-21T13:21:33.1980Z');
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
INSERT INTO `naber_bulletin` VALUES ('NABER_BULLETIN_35509dda-6544-4ebd-95c9-3e846aba4ba3','成為合作店家','想要透過NABER來銷售產品嗎？$split$split客人透過線上點餐功能，到場直接取餐免等待，讓「 不用等」成為你的店家口號。​$split$split如果你想進一步了解NABER對你的幫助，或是想了解更多資訊，請在登入頁面點擊右下角「 申請為店家」，或是透過下列Email與我們聯繫。$split$split service@melonltd.com.tw','APPLY_OF_SELLER','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_3dfd10d0-8a42-47b6-9e78-93149856e32b','首頁','1.IOS即將上線，請稍待片刻。$split2.訂餐享紅利回饋，下次消費可折抵現金! (即將開放)','HOME','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_448167a5-c8ac-4876-9d99-71d0b07b4e6b','與我聯繫','如果上述問題無法滿足您的需求$split歡迎使用下列方法尋求協助$splitE-MAIL：service@melonltd.com.tw$split官方LINE帳號：@gsd8173f','CONTACT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_468efa05-42ee-4327-8bdd-40a117a8dc20','訂餐步驟','選擇餐廳 > 選擇種類、產品、規格 > 選擇你要取餐的時間 > 送出訂單','TEACHING','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_65ff4e27-6195-482b-9137-f01c86ccc81c','關於我們','NABER是一個訂餐APP平台，創立於2017年。$split$split我們知道大多數的人，早上時間都很緊湊，因此出現沒吃正餐的情形。$split$split你可以透過NABER事先訂餐，他能夠讓你免排隊、免遲到。$split$split我們希望NABER可以成為你的得力助手，提升你的生活品質。$split$splitNABER有紅利點數的功能，透過消費累積點數，之後消費可以折抵現金。','ABOUT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_a805e1c2-a4dc-4ab6-8767-57cf6ffa00ca','常見問題','常見問題$split$splitQ　：　我沒有信用卡，我可以使用NABER嗎？$splitA　：　可以，NABER付款方式為「先點餐，再到場付款」。$split$splitQ　：　一定要有手機才可以加入會員嗎？$splitA　：　是的，商家在必要的時候，可能會透過手機與你們聯繫。$split$splitQ　：　選擇身份有什麼差異嗎？$splitA　：　往後推出的功能，與選擇的身份有關。$split$splitQ　：　我要怎麼拿餐？$splitA　：　依照你選的取餐時間，再到商家取餐就可以囉！(告知是NABER取餐)$split$splitQ　：　我要怎麼取消餐點？$splitA　：　如果你所選擇的取餐時間還沒到，你可以透過電話聯絡你所訂餐的商家，請他幫你取消。若商家已經製作，沒有辦法取消餐點。$split$splitQ　：　如果不拿餐點會怎麼樣嗎？$splitA　：　NABER不希望看到這樣的事情發生。我們有懲罰的機制存在，但我們真的不希望動用到！如果你數次沒去拿餐，我們只好以悲痛的心情，將你的帳號凍結，不僅如此，商家會直接透過電話與你聯繫，所以在點餐前請三思。$split$splitQ　：　紅利點數可以做什麼？$splitA　：　在之後消費時，可以折抵現金。$split$splitQ　：　我點錯餐了，怎麼辦？$splitA　：　馬上向商家聯絡，請他幫你取消。$split$splitQ　：　我可以事先預訂明天的餐點嗎？　$splitA　：　可以，不僅如此你還可以預訂「 後天的餐點」，如果你有需要的話。$split$splitQ　：　我想要加點商品，但我已經送出了，怎麼辦？$splitA　：　將加點的商品再送一次，老闆都會看見的。$split$split','FAQ','Y','2018-06-05T05:30:14.837Z');
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
  `account_uuid` varchar(200) NOT NULL COMMENT '帳號 UUID',
  `restaurant_uuid` varchar(200) NOT NULL COMMENT '餐館 UUID',
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
  `account_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_log`
--

LOCK TABLES `order_log` WRITE;
/*!40000 ALTER TABLE `order_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant_category_rel`
--

DROP TABLE IF EXISTS `restaurant_category_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant_category_rel` (
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
-- Dumping data for table `restaurant_category_rel`
--

LOCK TABLES `restaurant_category_rel` WRITE;
/*!40000 ALTER TABLE `restaurant_category_rel` DISABLE KEYS */;
INSERT INTO `restaurant_category_rel` VALUES ('RESTAURANT_CATEGORY_20180622_114813_572_15c69548-9c89-4a58-bf63-ac8f9d014c0d','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','原味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_386_9ffb9fef-cedd-424c-864e-9185b7c62c6d','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','調味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_0b216a9c-ce76-4992-94e0-dfc082632bd8','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','奶味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_722a2f57-d04c-43e0-b6e0-61a9dfb29eb1','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','鮮味茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_114923_387_e82e49f5-68f3-4bd6-a2c4-7e2e9dcdc94f','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','鮮奶茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_873_f63b88be-b0aa-408b-a994-009a5693b106','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','口感茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_874_e572e054-c335-41b5-b28a-9b72cba22154','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','季節限定茶','OPEN','Y','2018-06-22T11:48:13.5580Z'),('RESTAURANT_CATEGORY_20180622_115100_874_e7c6e399-f808-49df-94eb-5007235b0f42','RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','健康好醋','OPEN','Y','2018-06-22T11:48:13.5580Z');
/*!40000 ALTER TABLE `restaurant_category_rel` ENABLE KEYS */;
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
INSERT INTO `restaurant_info` VALUES ('RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','清玉-民族店','冰飲','320桃園市中壢區民族路二段130號','09:00','22:00',NULL,'[{\"status\":\"OPEN\",\"date\":\"09:01-09:30\"},{\"status\":\"OPEN\",\"date\":\"09:31-10:00\"},{\"status\":\"OPEN\",\"date\":\"10:01-10:30\"},{\"status\":\"OPEN\",\"date\":\"10:31-11:00\"},{\"status\":\"OPEN\",\"date\":\"11:01-11:30\"},{\"status\":\"OPEN\",\"date\":\"11:31-12:00\"},{\"status\":\"OPEN\",\"date\":\"12:01-12:30\"},{\"status\":\"OPEN\",\"date\":\"12:31-13:00\"},{\"status\":\"OPEN\",\"date\":\"13:01-13:30\"},{\"status\":\"OPEN\",\"date\":\"13:31-14:00\"},{\"status\":\"OPEN\",\"date\":\"14:01-14:30\"},{\"status\":\"OPEN\",\"date\":\"14:31-15:00\"},{\"status\":\"OPEN\",\"date\":\"15:01-15:30\"},{\"status\":\"OPEN\",\"date\":\"15:31-16:00\"},{\"status\":\"OPEN\",\"date\":\"16:01-16:30\"},{\"status\":\"OPEN\",\"date\":\"16:31-17:00\"},{\"status\":\"OPEN\",\"date\":\"17:01-17:30\"},{\"status\":\"OPEN\",\"date\":\"17:31-18:00\"},{\"status\":\"OPEN\",\"date\":\"18:01-18:30\"},{\"status\":\"OPEN\",\"date\":\"18:31-19:00\"},{\"status\":\"OPEN\",\"date\":\"19:01-19:30\"},{\"status\":\"OPEN\",\"date\":\"19:31-20:00\"},{\"status\":\"OPEN\",\"date\":\"20:01-20:30\"},{\"status\":\"OPEN\",\"date\":\"20:31-21:00\"},{\"status\":\"OPEN\",\"date\":\"21:01-21:30\"},{\"status\":\"OPEN\",\"date\":\"21:31-22:00\"}]','24.957514','121.205150',NULL,'https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=a443d757-f8a9-400e-9012-171e669d981c',NULL,'Y','1','https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Fbackground%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=25502bb8-e397-4541-9d86-7cd304b53e58','2018-06-22T11:30:09.2840Z');
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
INSERT INTO `restaurant_location_template` VALUES ('RESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267','24.957514','121.205150','Y');
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
  `account_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
  `user_message` varchar(220) DEFAULT NULL,
  `create_date` varchar(30) NOT NULL,
  `update_date` varchar(30) NOT NULL,
  `order_price` varchar(20) NOT NULL,
  `order_bonus` varchar(20) NOT NULL,
  `fetch_date` varchar(30) NOT NULL,
  `order_data` mediumtext NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'FINISH' COMMENT 'CANCEL,  FAIL,\nFINISH"',
  `enable` varchar(1) NOT NULL DEFAULT 'Y',
  `account_name` varchar(50) DEFAULT NULL,
  `account_phone` varchar(30) DEFAULT NULL,
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
-- Table structure for table `user_order_log`
--

DROP TABLE IF EXISTS `user_order_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_order_log` (
  `order_uuid` varchar(200) NOT NULL,
  `account_uuid` varchar(200) NOT NULL,
  `restaurant_uuid` varchar(200) NOT NULL,
  `restaurant_name` varchar(150) DEFAULT NULL,
  `restaurant_address` varchar(250) DEFAULT NULL,
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
-- Dumping data for table `user_order_log`
--

LOCK TABLES `user_order_log` WRITE;
/*!40000 ALTER TABLE `user_order_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_order_log` ENABLE KEYS */;
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
INSERT INTO `verify_phone_log` VALUES ('38599b55-cfbb-4604-8102-e7f614143a8e','0931330513','852561','2018-06-21T10:10:09.6110Z'),('5772f26d-679c-437a-a7d2-1bf5a80f1629','0928297076','014347','2018-06-20T17:25:28.4150Z');
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

-- Dump completed on 2018-06-22 18:23:26
