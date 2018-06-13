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
  `is_login` varchar(1) NOT NULL DEFAULT '0',
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
INSERT INTO `account_info` VALUES ('0987654321','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','test_seller','a123456','evan.wang@melonltd.com.tw','桃園市平鎮區文化街217號','SELLERS',NULL,'0','MANAGE','Y','1','2018-06-07T19:32:00.3300Z',NULL,NULL,'1988/04/06','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c');
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
INSERT INTO `category_food_rel` VALUES ('FOOD_6d17a836-a9f8-4316-b8f8-4303a00b39af','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','黑糖珍珠奶茶','45','https://media-01.creema.net/user/1286435/exhibits/3077929/0_8beae16fd09790255c5295dc379eeddb_583x585.jpg','jpg','{\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-02-05T05:30:14.837Z'),('FOOD_c4e512d6-ef5d-4a10-a2db-eb991c304cf3','RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','珍珠奶茶','35','https://media-01.creema.net/user/1286435/exhibits/3077929/0_8beae16fd09790255c5295dc379eeddb_583x585.jpg','jpg','{\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}','OPEN','Y','2018-02-05T05:30:14.837Z');
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
INSERT INTO `mobile_device` VALUES ('DEVICE_2829a589-1946-49d7-ac11-558c95ad8167','USER_20180606_727b6449-4092-4a01-b841-182921ada5f8','test token','NUKNOWN','2018-06-07T19:32:00.3300Z');
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
INSERT INTO `naber_bulletin` VALUES ('NABER_BULLETIN_35509dda-6544-4ebd-95c9-3e846aba4ba3','成為合作店家','成為合作店家','APPLY_OF_SELLER','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_3dfd10d0-8a42-47b6-9e78-93149856e32b','首頁','首頁','HOME','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_448167a5-c8ac-4876-9d99-71d0b07b4e6b','與我聯繫','與我聯繫','CONTACT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_468efa05-42ee-4327-8bdd-40a117a8dc20','訂餐步驟','訂餐步驟','TEACHING','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_65ff4e27-6195-482b-9137-f01c86ccc81c','關於我們','關於我們','ABOUT_US','Y','2018-06-05T05:30:14.837Z'),('NABER_BULLETIN_a805e1c2-a4dc-4ab6-8767-57cf6ffa00ca','常見問題','常見問題','FAQ','Y','2018-06-05T05:30:14.837Z');
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
INSERT INTO `restaurant_category_rel` VALUES ('RESTAURANT_CATEGORY_07c0a17d-9fdb-4b24-8051-675c9927724b','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','蛋糕','CLOSE','Y','2018-02-05T05:30:14.837Z'),('RESTAURANT_CATEGORY_2e7412d6-3106-432c-88b9-7d7817cc8857','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','熟食','OPEN','Y','2018-02-05T05:30:14.837Z'),('RESTAURANT_CATEGORY_72ae2a8f-33fe-483c-96e5-4ef0a10a6e9d','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','甜品','OPEN','N','2018-02-05T05:30:14.837Z'),('RESTAURANT_CATEGORY_90666dc9-8f0f-4d46-b996-a74a665fb5a5','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','冷飲','OPEN','Y','2018-02-05T05:30:14.837Z'),('RESTAURANT_CATEGORY_c0d17f24-7b7f-4e13-addd-c2990f4354f8','RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','熱飲','OPEN','Y','2018-02-05T05:30:14.837Z');
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
INSERT INTO `restaurant_info` VALUES ('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311c','COCO1','飲料店','桃園市平鎮區文化街217號','09:30','17:30','[\"2018-06-08T03:19:45.887Z\",\"2018-07-08T01:19:45.887Z\",\"2018-08-08T03:11:45.887Z\"]','[{\"status\":\"Y\",\"date\":\"09:01-09:30\"},{\"status\":\"Y\",\"date\":\"09:31-10:00\"},{\"status\":\"Y\",\"date\":\"10:01-10:30\"},{\"status\":\"Y\",\"date\":\"10:31-11:00\"},{\"status\":\"Y\",\"date\":\"11:01-11:30\"},{\"status\":\"Y\",\"date\":\"11:31-12:00\"},{\"status\":\"N\",\"date\":\"12:01-12:30\"},{\"status\":\"N\",\"date\":\"12:31-13:00\"},{\"status\":\"Y\",\"date\":\"13:01-13:30\"},{\"status\":\"Y\",\"date\":\"13:31-14:00\"},{\"status\":\"Y\",\"date\":\"14:01-14:30\"},{\"status\":\"Y\",\"date\":\"14:31-15:00\"},{\"status\":\"Y\",\"date\":\"15:01-15:30\"},{\"status\":\"Y\",\"date\":\"15:31-16:00\"},{\"status\":\"Y\",\"date\":\"16:01-16:30\"},{\"status\":\"Y\",\"date\":\"16:31-17:00\"},{\"status\":\"Y\",\"date\":\"17:01-17:30\"}]','24.9543881','121.2009457',NULL,'https://www.canada.ca/content/dam/pch/images/campaigns/canada-150/c150-logo.jpg',NULL,'Y','3','https://www.thelocal.it/userdata/images/article/69523836b0191608c41d640feead8da2be5462038d3409e1e3900fad039c7fc8.jpg','2018-02-05T05:30:14.837Z'),('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba8311d','DDD2','飲料','桃園市平鎮區文化街217號2號','22:00','04:00','[\"2014-10-27T08:09:30.914Z\",\"2014-11-27T08:09:30.914Z\",\"2018-06-07T08:09:30.914Z\"]','','24.9443881','121.2008457',NULL,'https://i.pinimg.com/736x/91/f2/71/91f2714f15f21600db913ed224616da7--unique-logo-design-name-logo-design.jpg',NULL,'Y','2','https://www.mcdonalds.com/content/dam/usa/documents/mcdelivery/mcdelivery_new11.jpg','2018-06-05T05:30:14.837Z'),('RESTAURANT_4045aaa1-380c-4f6d-a44f-6b411ba83123','DDDAA3','飲料店','樹林','12:30','20:00',NULL,'','25.0054291','121.423645',NULL,NULL,NULL,'Y','1','https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/3/1/8/4/144813-1-eng-GB/Front.jpg','2018-01-05T05:30:14.837Z');
/*!40000 ALTER TABLE `restaurant_info` ENABLE KEYS */;
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
INSERT INTO `verify_phone_log` VALUES ('e14700b3-7293-431c-ac41-745762838db8','0928297076','333372','2018-06-03T07:03:26.3820Z');
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
/*!50003 DROP PROCEDURE IF EXISTS `get_mobile_token_limit_one` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`melonltd_admin`@`localhost` PROCEDURE `get_mobile_token_limit_one`(in_account_uuid VARCHAR(200))
BEGIN
	SELECT *  FROM  mobile_device WHERE  account_uuid = in_account_uuid ORDER BY create_date DESC LIMIT 1 ;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-08 17:31:49
