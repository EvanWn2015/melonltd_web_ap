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
  `phone` varchar(30) NOT NULL COMMENT '電話號碼',
  `account_uuid` varchar(200) NOT NULL COMMENT 'date +”_”+ UUID\n帳號唯一值',
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
  `restaurant_uuid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`phone`),
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
INSERT INTO `account_info` VALUES ('0928297076','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','test_seller','a123456','evan.wang@melonltd.com.tw','桃園市平鎮區文化街217號','USER',NULL,'0','USER','Y','Y','2018-06-19T17:21:15.5200Z',NULL,NULL,'1988/04/06',NULL),('0987654321','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','test_seller','GVGhhGhb','jnswcy@gmail.com','桃園市平鎮區文化街217號','SELLERS',NULL,'22','MANAGE','Y','Y','2018-06-18T17:57:48.5500Z',NULL,NULL,'1988/04/06','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c');
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
INSERT INTO `category_food_rel` VALUES ('FOOD_0868b913-616f-4cad-b797-d56561daee3f','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-15T03:25:05.1740Z'),('FOOD_192093fc-b9f7-4997-bbe6-a721adb26bc3','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"22\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:44:44.2880Z'),('FOOD_54cab531-daa0-4adf-83e9-46ff24dd4681','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"22\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:46:27.0290Z'),('FOOD_59b30d2f-b590-4c24-9600-fea372557f5c','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"sds\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:34:27.6630Z'),('FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','黑糖珍珠奶茶','45','https://media-01.creema.net/user/1286435/exhibits/3077929/0_8beae16fd09790255c5295dc379eeddb_583x585.jpg','jpg','{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-02-05T05:30:14.837Z'),('FOOD_90a17fbb-e05f-4a70-87b0-c4dc4434b737','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-15T03:33:20.1010Z'),('FOOD_91b83656-1aae-4860-8cf2-c6ce23751729','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"22\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:46:19.2520Z'),('FOOD_9ab58eaa-b0a1-4410-9f2c-daa139e223bf','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TEST','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:23:50.7360Z'),('FOOD_b71f580d-1995-4bb2-afcc-7d84cd2b261a','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"sds\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:34:00.6310Z'),('FOOD_c04082a3-f43e-4a80-b1e0-02d8d21df164','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','菜菜菜菜菜','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"22\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:46:47.7940Z'),('FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','珍珠奶茶','35','https://media-01.creema.net/user/1286435/exhibits/3077929/0_8beae16fd09790255c5295dc379eeddb_583x585.jpg','jpg','{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-02-01T05:30:14.837Z'),('FOOD_cd227587-f579-4b1b-97d2-9aaba1de5c68','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[],\"opts\":[],\"demands\":[]}','OPEN','Y','2018-06-15T03:29:13.6550Z'),('FOOD_cff1aac2-b595-4a1e-9bfd-5d6545d714a6','','TEST','',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:03:11.3030Z'),('FOOD_e297d929-ed17-4314-a1d1-3921a2835e09','','TEST','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:16:48.6190Z'),('FOOD_fb20cb4d-7500-4a52-991b-6881012b8807','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','TTTT','33',NULL,NULL,'{\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"22\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-06-15T03:43:47.7210Z');
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
INSERT INTO `mobile_device` VALUES ('DEVICE_03921c59-940b-4896-adf5-0947f1e23861','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:05:03.9970Z'),('DEVICE_0727f25c-cceb-4313-acca-f1f9ae6ad6cf','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:43:58.4220Z'),('DEVICE_0f042892-ddb6-436e-a601-e1e4c953303f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:52:59.3240Z'),('DEVICE_144f50f1-b20f-407a-a08e-e261a966eed1','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:56:16.7080Z'),('DEVICE_15a1d652-55f8-4ab7-b713-497d8dab10aa','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:50:57.9940Z'),('DEVICE_18924f15-6b8a-4208-b2d2-2ba929dbf348','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:33:43.7980Z'),('DEVICE_19795704-9c12-4df5-a437-659c47781c6c','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T11:09:36.4790Z'),('DEVICE_1e59b29f-d25e-459a-8ef7-aa7fe9360bec','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:24:43.4370Z'),('DEVICE_1ff3ea71-6683-432b-8304-02242be49e97','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:22:33.5100Z'),('DEVICE_20838af6-f3c5-4e25-989b-e1213e268238','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:24:12.3560Z'),('DEVICE_22e56382-31ea-4b92-9f46-2f6dc8b5f6cb','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T13:20:03.0340Z'),('DEVICE_265eb09c-fef6-4c6e-947d-845d41843f5a','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:31:43.1980Z'),('DEVICE_27d9ed6c-bbd8-4293-9cc7-0217e97d278a','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T11:08:53.7540Z'),('DEVICE_2a14acf9-b4ac-46f1-b6dd-3a5f35d4fb18','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','c735ba04-d674-4d89-b047-1aa2c89b7657','IOS','2018-06-13T10:43:15.8720Z'),('DEVICE_2cbb90e8-9145-4d02-8b48-846f6fad5a9d','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:25:16.8470Z'),('DEVICE_300cdc80-99c0-49dc-b202-ebbe24921bcb','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:58:18.2430Z'),('DEVICE_330279ca-c6e9-44ea-8a23-fa82d6c2549d','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:47:57.7230Z'),('DEVICE_33ca8636-3f1c-4627-9b8d-c5fe1b0b0571','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','cDefUqhW78k:APA91bG6v85QEA1P4BYRBTNMsEUa5BW2OdFi4Zu9RLtK5CWUKLsnT0RcXv3cavw5pXymCOkBnz6sSCt0QG7ee9mZTJ0CjOH9UykcqDjZV_PXaINMeppkvs6cwvHWmbxn0wXdCbzsgCgR','ANDROID','2018-06-18T17:56:26.8090Z'),('DEVICE_37a4e14c-45a5-4ebc-adab-ea80226c2e0b','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:27:44.3190Z'),('DEVICE_39f1a024-8f31-47ec-a038-93a3a5b435ab','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:44:48.8580Z'),('DEVICE_3be55cf0-95bb-4bd5-b326-339c5789295b','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:05:12.8970Z'),('DEVICE_3db7a86f-3ed8-4cdd-b483-0f23d926523f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:36:59.6060Z'),('DEVICE_414fe4f2-8399-4947-93a6-9a9cc7603dfc','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:01:03.8680Z'),('DEVICE_42f39fc4-305a-4795-84af-126a03b27c68','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T13:22:31.8090Z'),('DEVICE_4315a4d6-9970-4db5-a81a-23626f9fc6d3','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:33:27.3830Z'),('DEVICE_4a0beade-654e-4ba9-837f-a228ba95560e','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:35:53.3060Z'),('DEVICE_4d0daadc-2f19-41b3-b292-ec467b5cba35','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:22:25.7750Z'),('DEVICE_4df44e8f-d1ab-4efc-a3e3-9c362c1507fc','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:20:02.7470Z'),('DEVICE_4fcdfd2e-66fd-48bf-b2b4-e212c96291f2','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:52:04.2530Z'),('DEVICE_5351a9cd-32fb-428c-9ac7-f3252d884a77','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:19:12.1970Z'),('DEVICE_559e77df-154d-4f6d-ae74-8e4f0cd0d74c','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:32:39.5000Z'),('DEVICE_581f1d1b-bf06-490b-9bfd-6ff36dc16b6f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:56:57.0890Z'),('DEVICE_608c9ca7-9976-4834-9c2d-56022f68bb64','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:22:19.6040Z'),('DEVICE_6119f3f6-8800-4aa4-9fe1-9bb4913ff1f2','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:51:21.7470Z'),('DEVICE_635e6268-68fa-4907-bf6c-991d5ae08171','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:14:20.2460Z'),('DEVICE_68ef5057-deed-452f-ae18-02d5aff314b8','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:26:58.7700Z'),('DEVICE_69d77981-77e2-4971-85ab-330d2e5dccba','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:43:30.3750Z'),('DEVICE_6d93d0dd-7057-4d9d-9f20-aeecb64b2c2d','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:09:55.8810Z'),('DEVICE_6fb30584-6654-4cf9-bd37-a06f6415b86b','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T16:08:30.6530Z'),('DEVICE_723a68af-4f45-4e17-a17d-27d88c472283','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:33:43.9350Z'),('DEVICE_7dfde7cc-9931-4251-aee0-a88f441d839f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:57:45.8680Z'),('DEVICE_86e38354-27b6-43b0-a7a8-7adbd122b033','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:55:16.5270Z'),('DEVICE_87e5a977-ffc5-44a4-8209-ea4482705cd7','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:41:02.3370Z'),('DEVICE_88bd4c02-f2c3-46c7-90fb-67bc30b364f0','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:20:38.0610Z'),('DEVICE_8e81fd79-5fae-4069-8442-02a8b033b66b','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:35:22.9520Z'),('DEVICE_9478bee7-ad02-418b-8f8b-c460b17d340b','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:41:58.4090Z'),('DEVICE_94f62936-b14d-43cc-86c8-ffa31d391e3e','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T17:06:24.2360Z'),('DEVICE_9557dc72-8ec7-4512-9a36-764c8c2631b2','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:23:03.1400Z'),('DEVICE_98da5e33-a9c8-4ea5-8818-1cefa11f465a','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:22:46.9840Z'),('DEVICE_9a574a46-4ea9-4c79-86c8-b1135680782f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:09:37.3850Z'),('DEVICE_9c0b8bdd-b032-4f55-b7dc-a780d802db60','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T11:00:23.1470Z'),('DEVICE_9ef6ddc6-dc6c-4f13-85b3-781a69fc3dd3','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:53:30.8100Z'),('DEVICE_a133fa02-fde9-49f4-bdb7-bbdc9cdd342f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:58:47.1470Z'),('DEVICE_a1560ffb-48c3-4b1e-8a80-cba5d5db4456','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T17:12:19.2670Z'),('DEVICE_a2e8e33b-a04c-46d4-aa7a-25605ceb147c','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:57:05.7750Z'),('DEVICE_a45d67da-553e-461f-94a1-767421074f05','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:10:37.3810Z'),('DEVICE_a9763da5-3950-4dfd-b8a9-64c0165f9e54','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:47:24.3930Z'),('DEVICE_acde7e8e-b990-45c1-9aea-9a1fc0830c36','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:09:44.4040Z'),('DEVICE_b01acdfa-7aaa-403d-b1f5-fabd6fc453f2','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:10:48.9570Z'),('DEVICE_b289de04-e316-423c-83b7-def35442dca0','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:20:05.6820Z'),('DEVICE_b2c9c455-6de3-4b7c-9618-8a5b5f5c141c','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:10:50.7250Z'),('DEVICE_b4a8370e-31b2-4d26-9d4e-245c87a54818','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:39:26.3770Z'),('DEVICE_b9db3e7f-6c3f-4b48-9d3c-4096b429c434','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:06:52.7020Z'),('DEVICE_bae7b929-1530-4fe0-bd30-3b1aa0162083','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:34:17.4900Z'),('DEVICE_bcf71cfd-d2aa-457f-87b6-209e240db713','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:07:19.9160Z'),('DEVICE_bd1c4edf-df81-429a-92dc-aa7fe918e2f9','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','cDefUqhW78k:APA91bG6v85QEA1P4BYRBTNMsEUa5BW2OdFi4Zu9RLtK5CWUKLsnT0RcXv3cavw5pXymCOkBnz6sSCt0QG7ee9mZTJ0CjOH9UykcqDjZV_PXaINMeppkvs6cwvHWmbxn0wXdCbzsgCgR','ANDROID','2018-06-18T17:57:48.5420Z'),('DEVICE_bd7845fb-6d7b-4c7e-8ba3-fe03b27245e2','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:39:49.6100Z'),('DEVICE_c0182b69-b7e6-4c52-b0c2-c3d8e310b106','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T17:21:15.4360Z'),('DEVICE_c2985a97-9ae6-46bc-8ec9-5856ba28f0da','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T17:06:51.0380Z'),('DEVICE_c5e0a81a-14d0-4845-8004-2eec095f2295','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T13:46:02.6080Z'),('DEVICE_cb7e4d2d-8389-4666-a7c5-c3ead8579035','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:08:39.3690Z'),('DEVICE_cddb5dea-7761-4302-b685-aff5a41f94e6','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:47:25.2840Z'),('DEVICE_ce09a30f-dfe8-4763-bcfc-ca2595abab31','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T17:22:48.0450Z'),('DEVICE_d25e33d0-e586-4078-8623-6ceb7007604f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:24:16.1160Z'),('DEVICE_d35984a0-5026-49be-9c69-021c36a3897c','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:40:59.9000Z'),('DEVICE_d5bb8c2d-2445-4bce-97a8-297ac1b61e0d','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:58:46.2800Z'),('DEVICE_d71e59c3-0231-4ab6-942e-18dee12f093a','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T17:07:24.2680Z'),('DEVICE_d723ac18-1475-4b35-8c76-5cacb2b7313e','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:19:41.7200Z'),('DEVICE_d7ea8a68-d892-4d89-8c62-05132489479f','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T10:08:06.4220Z'),('DEVICE_da0d3708-7f53-4a41-8472-3c9577546689','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:01:33.4520Z'),('DEVICE_db0ab699-1ecd-4c67-9e7e-18af7fd05e46','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T16:13:33.2320Z'),('DEVICE_de9ebc8a-8332-4e8d-a76b-3785c54d1df1','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:02:01.8210Z'),('DEVICE_df57cbad-6173-4a19-a523-7604879d2843','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T11:10:03.8120Z'),('DEVICE_e1ad00e6-160d-4a84-951c-20ca45905386','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','cRWGTOBnctA:APA91bHC6UK8aWGpl5tMSDQURXbrV1_Ol0rl2bC0n0qCh0sWmsN3o9Xc6Ud1BczjrySUhqQ70JvR3BzaRbhHVI7o3YWnIfUuQcbTD9a_lzgpdVCg6ZuB-vEl43v3Y6LZBhnsk0dIWX8i','ANDROID','2018-06-19T11:24:42.7690Z'),('DEVICE_e7ed6d73-a135-4e98-a3df-3923f0afcaa8','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-19T09:54:48.9120Z'),('DEVICE_e916f039-c3ad-4fdb-b05d-12784a01f802','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T13:47:59.0490Z'),('DEVICE_e9cbc917-a31a-486c-822e-ac1976eae321','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T13:42:23.8980Z'),('DEVICE_ee8cadd0-6e7e-4fde-a5e2-cea6d9c7f95b','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T16:25:30.8650Z'),('DEVICE_f50da9dc-7f12-432a-bf64-4c6ceac2fb11','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T12:51:33.3040Z'),('DEVICE_f515d967-4380-4472-9acd-bf71851d6a56','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','fxfKCol10l4:APA91bGBl3UnKRz_ZNu0r3gATwQ__-E31iK4VoZh6kfSQAB_nmGmgq1C-VxWujza6ttFeOfxxqK-jRCp5BISoXpxXRcOhrHy6am7Is7fWwIgW0o5ZkWKoaUqnf4oHqw2BjYZcycXlj5L','ANDROID','2018-06-18T16:04:37.2910Z'),('DEVICE_fc3db318-6c86-4d7f-8c1f-4d58753ecd40','USER_20180613_4c64db54-dcd4-418c-a3a2-46aa7e5f13ec','eg5TeXaeZ34:APA91bFt7Cls4Bo6wUwR__E8FZnUryKtkG2x6ZMgcVpa1txQUfSYs-FQrzXLs8ASflvAcQymUGSTsyKWA7vQJ1aW6clHPbsJtYjm5FsIzHol0cQzM_1gv-0ViKP8KgTzKH1vxChEo8q5','ANDROID','2018-06-19T11:57:28.1690Z');
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
INSERT INTO `naber_bulletin` VALUES ('NABER_BULLETIN_35509dda-6544-4ebd-95c9-3e846aba4ba3','成為合作店家','想要透過NABER來銷售產品嗎？客人透過線上點餐功能，到場直接取餐免等待，讓「 不用等」成為你的店家口號。​如果你想進一步了解NABER對你的幫助，或是想了解更多資訊，請在登入頁面點擊右下角「 申請為店家」，或是透過下列Email與我們聯繫。','APPLY_OF_SELLER','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_3dfd10d0-8a42-47b6-9e78-93149856e32b','首頁','1.IOS即將上線，請稍待片刻。$split2.訂餐享紅利回饋，下次消費可折抵現金! (即將開放)','HOME','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_448167a5-c8ac-4876-9d99-71d0b07b4e6b','與我聯繫','與我聯繫','CONTACT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_468efa05-42ee-4327-8bdd-40a117a8dc20','訂餐步驟','訂餐步驟','TEACHING','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_65ff4e27-6195-482b-9137-f01c86ccc81c','關於我們','關於我們','ABOUT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_a805e1c2-a4dc-4ab6-8767-57cf6ffa00ca','常見問題','常見問題','FAQ','Y','2018-06-05T05:30:14.837Z');
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
INSERT INTO `order_info` VALUES ('ORDER_611c55dc-fd9a-4484-b169-694f0b6e5fa4','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T07:07:45.6350Z','2018-06-14T03:56:05.7150Z','100','10','2018-06-11T06:30:14.8317Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),('ORDER_f1469818-173f-4784-bc58-9ac64b0dbf90','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T07:11:31.9290Z','2018-06-13T07:11:31.9290Z','100','10','2018-06-11T06:30:14.8372Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y');
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_log`
--

LOCK TABLES `order_log` WRITE;
/*!40000 ALTER TABLE `order_log` DISABLE KEYS */;
INSERT INTO `order_log` VALUES (1,'ORDER_611c55dc-fd9a-4484-b169-694f0b6e5fa4','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T07:07:45.6350Z','2018-06-14T03:56:05.7150Z','100','10','2018-06-11T06:30:14.8317Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(8,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(9,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(10,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(11,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(12,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(13,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','PROCESSING','Y'),(14,'ORDER_9da9e8c6-ad37-4792-9c8b-22adb8d7bede','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T11:16:44.595Z','2018-06-12T11:16:44.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','CAN_FETCH','Y'),(15,'ORDER_1789aaab-ac94-4488-b047-d723ec7b6722','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T17:16:44.595Z','2018-06-12T17:16:44.595Z','100','10','2018-06-10T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','CAN_FETCH','Y'),(16,'ORDER_5832c72d-e113-49ac-b07f-900e7a5706a1','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T16:10:03.866Z','2018-06-12T16:10:03.866Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(17,'ORDER_fd47cd46-3f2e-4d5c-b336-a291a297786a','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T16:10:48.230Z','2018-06-12T16:10:48.230Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(18,'ORDER_4ab8da48-878f-4f57-a8ce-afc0f1ed5231','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T16:21:47.595Z','2018-06-12T16:21:47.595Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(19,'ORDER_dd5d04b1-6569-4d56-9b0c-870ad8552695','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T16:23:26.180Z','2018-06-12T16:23:26.180Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(20,'ORDER_bd23ebbb-c150-4309-97d6-091e7b9c72bb','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-12T16:25:37.337Z','2018-06-12T16:25:37.337Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(21,'ORDER_b3b55ee1-b83e-4f59-a8cc-c959c1ed8345','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T01:50:10.726Z','2018-06-13T01:50:10.726Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(22,'ORDER_0fc5d9c7-b1a3-4657-93b5-390e9f1ae908','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:36:56.413Z','2018-06-13T04:36:56.413Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(23,'ORDER_70f65407-d5cc-4aef-8de5-31f1dbb54028','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:37:27.584Z','2018-06-13T04:37:27.584Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(24,'ORDER_edbf5aec-b756-493c-bb6d-22a611f366fc','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:37:29.652Z','2018-06-13T04:37:29.652Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(25,'ORDER_770a84af-4e06-4b1e-8ac0-db8d7f69c9e6','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:37:31.521Z','2018-06-13T04:37:31.521Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(26,'ORDER_43965aef-3380-4471-a505-20f5845eaa5c','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:37:33.436Z','2018-06-13T04:37:33.436Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(27,'ORDER_cd17562d-cb25-44ec-b7f1-b6339cce0706','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:37:35.188Z','2018-06-13T04:37:35.188Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(28,'ORDER_f968a6ae-cf3f-4a7c-b3ae-4339830f802f','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:50:06.922Z','2018-06-13T04:50:06.922Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(29,'ORDER_adb999f8-f5dc-44b7-9d18-dad1941a23b0','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:51:11.920Z','2018-06-13T04:51:11.920Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(30,'ORDER_dab6c4c0-a395-4fbc-9fe7-3de4f13baefd','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T04:58:57.025Z','2018-06-13T04:58:57.025Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(31,'ORDER_37764d35-86f0-47e1-a572-1a1253b35dec','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T05:08:20.538Z','2018-06-13T05:08:20.538Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(32,'ORDER_61e6d8da-a5f3-47f1-8425-4a9263b1073b','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T05:08:53.176Z','2018-06-13T05:08:53.176Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-ba665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(33,'ORDER_22fab23c-1686-47a7-b0f1-b63b40650085','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T05:20:22.538Z','2018-06-13T05:20:22.538Z','100','10','2018-06-11T06:30:14.837Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y'),(35,'ORDER_f1469818-173f-4784-bc58-9ac64b0dbf90','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-13T07:11:31.9290Z','2018-06-13T07:11:31.9290Z','100','10','2018-06-11T06:30:14.8372Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','UNFINISH','Y');
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
INSERT INTO `restaurant_category_rel` VALUES ('RESTAURANT_CATEGORY_7591623f-7236-474f-9a6c-101e6d6c3938','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','TEXT','OPEN','Y','2018-06-14T08:46:13.8410Z'),('RESTAURANT_CATEGORY_875d564f-7ba2-4d09-a25f-eaddde716970','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','TEXT','OPEN','Y','2018-06-14T08:46:23.7100Z'),('RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','冷飲','OPEN','Y','2018-02-05T05:30:14.837Z'),('RESTAURANT_CATEGORY_db001826-9169-4230-a747-c6d9f8d0a582','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','TEXTsdsds','OPEN','Y','2018-06-14T09:00:55.4850Z');
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
INSERT INTO `restaurant_info` VALUES ('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','COCO1','飲料店','桃園市平鎮區文化街217號','09:30','17:30','[\"2018-06-15T03:19:45.887Z\"]','[{\"status\":\"OPEN\",\"date\":\"22:01-22:30\"},{\"status\":\"OPEN\",\"date\":\"22:31-23:00\"},{\"status\":\"OPEN\",\"date\":\"23:01-23:30\"},{\"status\":\"OPEN\",\"date\":\"23:31-24:00\"},{\"status\":\"OPEN\",\"date\":\"00:01-00:30\"},{\"status\":\"OPEN\",\"date\":\"00:31-01:00\"},{\"status\":\"OPEN\",\"date\":\"01:01-01:30\"}]','24.9543881','121.2009457','AAA','https://www.canada.ca/content/dam/pch/images/campaigns/canada-150/c150-logo.jpg',NULL,'Y','3','https://www.thelocal.it/userdata/images/article/69523836b0191608c41d640feead8da2be5462038d3409e1e3900fad039c7fc8.jpg','2018-02-05T05:30:14.837Z'),('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311d','DDD2','飲料','桃園市平鎮區文化街217號2號','22:00','04:00','[\"2014-10-27T08:09:30.914Z\",\"2014-11-27T08:09:30.914Z\",\"2018-06-07T08:09:30.914Z\"]','','24.9443881','121.2008457',NULL,'https://i.pinimg.com/736x/91/f2/71/91f2714f15f21600db913ed224616da7--unique-logo-design-name-logo-design.jpg',NULL,'Y','2','https://www.mcdonalds.com/content/dam/usa/documents/mcdelivery/mcdelivery_new11.jpg','2018-06-05T05:30:14.837Z'),('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba83123','DDDAA3','飲料店','樹林','12:30','20:00',NULL,'','25.0054291','121.423645',NULL,NULL,NULL,'Y','1','https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/3/1/8/4/144813-1-eng-GB/Front.jpg','2018-01-05T05:30:14.837Z');
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
INSERT INTO `seller_order_finish` VALUES ('ORDER_611c55dc-fd9a-4484-b169-694f0b6e5fa4','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','老闆加快裝帶','2018-06-14T03:39:57.9260Z','2018-06-14T03:39:57.9260Z','100','10','2018-06-11T06:30:14.8317Z','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}','FINISH','Y',NULL,NULL);
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
INSERT INTO `seller_registered` VALUES ('0987654321','a80b7c8e-b1fa-4c06-a86f-d56b0028237c','SELLER','桃園市龍潭區悅華路100號','SELLER','2018-06-04T15:39:44.2710Z','UNFINISHED');
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
INSERT INTO `user_order_log` VALUES ('ORDER_611c55dc-fd9a-4484-b169-694f0b6e5fa4','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c',NULL,NULL,'老闆加快裝帶','2018-06-13T07:07:45.6350Z','2018-06-14T03:56:05.7150Z','100','10','2018-06-11T06:30:14.8317Z','UNFINISH','Y','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}'),('ORDER_f1469818-173f-4784-bc58-9ac64b0dbf90','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','COCO1','桃園市平鎮區文化街217號','老闆加快裝帶','2018-06-13T07:11:31.9290Z','2018-06-13T07:11:31.9290Z','100','10','2018-06-11T06:30:14.8372Z','UNFINISH','Y','{\"orders\":[{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[],\"opts\":[],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"8分糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"}]}]}},{\"category_uuid\":\"RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5\",\"food_uuid\":\"FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3\",\"item\":{\"food_uuid\":\"\",\"food_name\":\"奶茶\",\"price\":\"45\",\"scopes\":[{\"name\":\"小杯\",\"price\":\"35\"}],\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"去冰\"}]}]}}]}');
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
INSERT INTO `verify_phone_log` VALUES ('5418eebf-f76d-4faf-9cdc-7c7046069181','0928297076','337886','2018-06-13T10:24:35.0790Z');
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

-- Dump completed on 2018-06-19 19:42:34
