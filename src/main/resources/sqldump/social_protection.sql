/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : social_protection

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-05-06 02:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES (0x165AFB2E3F154AD6B2EA5F7156819F9F, 'Ветераны Великой Отечественной войны,в том числе инвалиды Великой Отечественной войны');
INSERT INTO `categories` VALUES (0x261BB1C4334A42738323EA3BC7F9D084, 'Участники других боевых операций по защите Отечества');
INSERT INTO `categories` VALUES (0x72F3CFE360CB4B448358405C1A703777, 'Граждане, пострадавшие от последствий войн');
INSERT INTO `categories` VALUES (0xA8A69F89D7FA4036B52C5BF581D331F8, 'Участники ликвидации последствий катастрофы на Чернобыльской АЭС, других радиационных аварий');
INSERT INTO `categories` VALUES (0xD9B3C308352540569EFC7B52BD494C5F, 'Ветераны боевых действий на территории других государств, в том числе инвалиды боевых действий на территории других государств');

-- ----------------------------
-- Table structure for displacements
-- ----------------------------
DROP TABLE IF EXISTS `displacements`;
CREATE TABLE `displacements` (
  `uuid` binary(16) NOT NULL,
  `arrived_date` date DEFAULT NULL,
  `arrived_place` varchar(150) DEFAULT NULL,
  `decreased_date` date DEFAULT NULL,
  `decreased_place` varchar(150) DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `displacement_veteran_fk` (`veteran_uuid`),
  CONSTRAINT `FKmelq7urksn80f35t3hl8m5b7f` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `displacement_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of displacements
-- ----------------------------

-- ----------------------------
-- Table structure for districts
-- ----------------------------
DROP TABLE IF EXISTS `districts`;
CREATE TABLE `districts` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of districts
-- ----------------------------
INSERT INTO `districts` VALUES (0x14001B106CE74C59A8A9C008DC0FC633, 'Щучинский', '17');
INSERT INTO `districts` VALUES (0x200273DC50284D5DA417C2DC1985E02C, 'Сморгонский', '16');
INSERT INTO `districts` VALUES (0x5521F86DE25C4DB09010450F9E0B3A02, 'Кореличский', '8');
INSERT INTO `districts` VALUES (0x6F40CE4B2B8848C2A6CFAF3CFE08D6DE, 'Лидский', '9');
INSERT INTO `districts` VALUES (0x8C0F87F22B59461EA230FED52E14F169, 'Островецкий', '13');
INSERT INTO `districts` VALUES (0x8F732FB761EC46E19A2CE4376319D7BE, 'Берестовицкий', '1');
INSERT INTO `districts` VALUES (0x9D0D4E3DF41E44ED872996BA86940E98, 'Новогрудский', '11');
INSERT INTO `districts` VALUES (0xA4F77A8AF47449149BC43C3AD33B949C, 'Ивьевский', '7');
INSERT INTO `districts` VALUES (0xA95417EDD3944AE5A3177297D2CBF425, 'Вороновский', '3');
INSERT INTO `districts` VALUES (0xB362483093734F42AC421F4550A90677, 'Слонимский', '15');
INSERT INTO `districts` VALUES (0xC4A6E746D8644923A2A86C6C463234EC, 'Волковысский', '2');
INSERT INTO `districts` VALUES (0xD044E919E4F14BADB8D8F556F12F7C74, 'Дятловский', '5');
INSERT INTO `districts` VALUES (0xD36763B0F9634E1B96D60CDEFF545A3A, 'Ошмянский', '12');
INSERT INTO `districts` VALUES (0xD3C3748B1B544F6DAFD275E915484F72, 'Свислочский', '14');
INSERT INTO `districts` VALUES (0xE39939A338AC4C0FB2FFA8AA64152F1B, 'Мостовский', '10');
INSERT INTO `districts` VALUES (0xE600F13AFBFA4DA6BFB83855DC09920C, 'Зельвенский', '6');
INSERT INTO `districts` VALUES (0xE91B7AE97F614B1F93CB7142AF6C8429, 'Гродненский', '4');

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `series` varchar(50) DEFAULT NULL,
  `issued_by` varchar(150) DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `document_veteran_fk` (`veteran_uuid`),
  CONSTRAINT `FKp5ohnvk1hckptkca8oiqariyn` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `document_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------

-- ----------------------------
-- Table structure for familymembers
-- ----------------------------
DROP TABLE IF EXISTS `familymembers`;
CREATE TABLE `familymembers` (
  `uuid` binary(16) NOT NULL,
  `relation_degree` varchar(50) DEFAULT NULL,
  `full_name` varchar(150) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `phone_number` varchar(30) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `member_veteran_fk` (`veteran_uuid`),
  CONSTRAINT `FKa3yhaoj2n8c69y1n8oh4oweur` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `member_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of familymembers
-- ----------------------------

-- ----------------------------
-- Table structure for honors
-- ----------------------------
DROP TABLE IF EXISTS `honors`;
CREATE TABLE `honors` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of honors
-- ----------------------------

-- ----------------------------
-- Table structure for militaryterms
-- ----------------------------
DROP TABLE IF EXISTS `militaryterms`;
CREATE TABLE `militaryterms` (
  `uuid` binary(16) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `locality` varchar(50) DEFAULT NULL,
  `start_of_military_service` date DEFAULT NULL,
  `end_of_military_service` date DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `term_veteran_fk` (`veteran_uuid`),
  CONSTRAINT `FK2a7xk30vgbvjjgy9sq6krqy3l` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `term_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of militaryterms
-- ----------------------------

-- ----------------------------
-- Table structure for ranks
-- ----------------------------
DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ranks
-- ----------------------------

-- ----------------------------
-- Table structure for subcategories
-- ----------------------------
DROP TABLE IF EXISTS `subcategories`;
CREATE TABLE `subcategories` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `sub_category_fk` (`category_uuid`),
  CONSTRAINT `FK7ar6rfl7lq2o7u15byyxoo8jf` FOREIGN KEY (`category_uuid`) REFERENCES `categories` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sub_category_fk` FOREIGN KEY (`category_uuid`) REFERENCES `categories` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subcategories
-- ----------------------------
INSERT INTO `subcategories` VALUES (0x0A3C9824FFD44886A1EACEAA9AF20758, 'Члены семей, погибших (умерших) в годы ВОв', 0x72F3CFE360CB4B448358405C1A703777);
INSERT INTO `subcategories` VALUES (0x1707E156922842D99EC499E6654133DA, 'Ветераны боевых действий на территории других государств', 0xD9B3C308352540569EFC7B52BD494C5F);
INSERT INTO `subcategories` VALUES (0x1D3EC9D81FCC40C6864A8853D1C60CA6, 'Бывшие узники фашистских концлагерей', 0x72F3CFE360CB4B448358405C1A703777);
INSERT INTO `subcategories` VALUES (0x23CFD3ABA4EA40A4A9FF3E1B77327A96, 'Инвалиды боевых действий на территории других государств', 0xD9B3C308352540569EFC7B52BD494C5F);
INSERT INTO `subcategories` VALUES (0x27431E912F894D37901912F9645A5866, 'Инвалиды с детства (последствия военных действий)', 0x72F3CFE360CB4B448358405C1A703777);
INSERT INTO `subcategories` VALUES (0x4B586618324142FC8B52D04AB8422F29, 'Участники ликвидации последствий катастрофы на Чернобыльской АЭС', 0xA8A69F89D7FA4036B52C5BF581D331F8);
INSERT INTO `subcategories` VALUES (0x5DE607D9F672438BB8E699BF8B25BAFE, 'Участники ВОв', 0x165AFB2E3F154AD6B2EA5F7156819F9F);
INSERT INTO `subcategories` VALUES (0x94ACFDD7F25B49C7AF44C476D94E2939, 'Инвалиды ВОв', 0x165AFB2E3F154AD6B2EA5F7156819F9F);
INSERT INTO `subcategories` VALUES (0xB66694405B11458A9E2355414A01E13E, 'Члены семей военнослужащих, погибших в мирное время при исполнении обязанностей военной службы', 0xD9B3C308352540569EFC7B52BD494C5F);
INSERT INTO `subcategories` VALUES (0xBA690BC5B5804AF394E12A527D8F634C, 'Награжденные', 0x165AFB2E3F154AD6B2EA5F7156819F9F);
INSERT INTO `subcategories` VALUES (0xBAF87E50625143D2AAC055DE020A029A, 'Приравненные к ветеранам ВОв', 0x165AFB2E3F154AD6B2EA5F7156819F9F);
INSERT INTO `subcategories` VALUES (0xBFAEA39D678E4DA3AA406BE84945C7B3, 'Граждане, получившие инвалидность в период прохождения военной службы при исполнении обязанностей военной службы', 0xD9B3C308352540569EFC7B52BD494C5F);
INSERT INTO `subcategories` VALUES (0xD566BCD9739949FCAE441CC16ACADD55, 'Члены семей, умерших вследствие заболеваний, вызванных катастрофой на Чернобыльской АЭС, другими радиационными авариями', 0xA8A69F89D7FA4036B52C5BF581D331F8);
INSERT INTO `subcategories` VALUES (0xE800F41F27D64CA988BC86021D1FBDA7, 'Блокадники', 0x165AFB2E3F154AD6B2EA5F7156819F9F);
INSERT INTO `subcategories` VALUES (0xFD7ACDF9004247A5BB790857E1384748, 'Участники ликвидации других радиационных аварий', 0xA8A69F89D7FA4036B52C5BF581D331F8);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uuid` binary(16) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `district_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FKskiv80nh6wt5p86xuj6v4y8r4` (`district_uuid`),
  CONSTRAINT `FKskiv80nh6wt5p86xuj6v4y8r4` FOREIGN KEY (`district_uuid`) REFERENCES `districts` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (0x272D5AD7C868433CB233B1C73BD5C359, 'admin', 'admin', null);

-- ----------------------------
-- Table structure for veteranhonors
-- ----------------------------
DROP TABLE IF EXISTS `veteranhonors`;
CREATE TABLE `veteranhonors` (
  `uuid` binary(16) NOT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  `honor_uuid` binary(16) DEFAULT NULL,
  `decree` varchar(255) DEFAULT NULL,
  `date_of_receiving` date DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `honors_veteran_fk` (`veteran_uuid`),
  KEY `honors_honor_fk` (`honor_uuid`),
  CONSTRAINT `FK47ly0bnrsgos50xn6s4yfq5ti` FOREIGN KEY (`honor_uuid`) REFERENCES `honors` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKhao0hku12dfe0inm1klsl6cis` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `honors_honor_fk` FOREIGN KEY (`honor_uuid`) REFERENCES `honors` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `honors_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of veteranhonors
-- ----------------------------

-- ----------------------------
-- Table structure for veterans
-- ----------------------------
DROP TABLE IF EXISTS `veterans`;
CREATE TABLE `veterans` (
  `uuid` binary(16) NOT NULL,
  `first_name` varchar(50) DEFAULT '',
  `second_name` varchar(50) DEFAULT '',
  `middle_name` varchar(50) DEFAULT '',
  `rank_uuid` binary(16) DEFAULT NULL,
  `district_uuid` binary(16) DEFAULT NULL,
  `category_uuid` binary(16) DEFAULT NULL,
  `subcategory_uuid` binary(16) DEFAULT NULL,
  `case_number` varchar(100) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `subdivision` varchar(255) DEFAULT '',
  `regional_executive_committee` varchar(255) DEFAULT '',
  `village_executive_committee` varchar(255) DEFAULT '',
  `address` varchar(255) DEFAULT '',
  `registration_address` varchar(255) DEFAULT '',
  `phone_number` varchar(30) DEFAULT '',
  `marching_organization` varchar(255) DEFAULT '',
  `is_dead` tinyint(1) DEFAULT '0',
  `burial_place` varchar(255) DEFAULT '',
  `date_of_death` date DEFAULT NULL,
  `position` varchar(255) DEFAULT '',
  `photo` longtext,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `veterans_uuid_uindex` (`uuid`),
  KEY `veteran_category_fk` (`category_uuid`,`rank_uuid`) USING BTREE,
  KEY `veteran_rank_fk` (`rank_uuid`,`district_uuid`) USING BTREE,
  KEY `veteran_sub` (`subcategory_uuid`,`rank_uuid`) USING BTREE,
  KEY `veteran_district_fk` (`district_uuid`,`rank_uuid`) USING BTREE,
  CONSTRAINT `FKc5a3vv0u6uiudr0xxkiy9modb` FOREIGN KEY (`rank_uuid`) REFERENCES `ranks` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKiwsrnn8n0gekhl2tttyqarymj` FOREIGN KEY (`subcategory_uuid`) REFERENCES `subcategories` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKqmtlvkxsybyihhe0mf1i1ej5k` FOREIGN KEY (`district_uuid`) REFERENCES `districts` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKqpaglrriwuo92p630k0cxc3dd` FOREIGN KEY (`category_uuid`) REFERENCES `categories` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `veteran_category_fk` FOREIGN KEY (`category_uuid`) REFERENCES `categories` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `veteran_district_fk` FOREIGN KEY (`district_uuid`) REFERENCES `districts` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `veteran_rank_fk` FOREIGN KEY (`rank_uuid`) REFERENCES `ranks` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `veteran_sub` FOREIGN KEY (`subcategory_uuid`) REFERENCES `subcategories` (`uuid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of veterans
-- ----------------------------

-- ----------------------------
-- Table structure for veteranwounds
-- ----------------------------
DROP TABLE IF EXISTS `veteranwounds`;
CREATE TABLE `veteranwounds` (
  `uuid` binary(16) NOT NULL,
  `date` date DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  `disability_uuid` binary(16) DEFAULT NULL,
  `type_uuid` binary(16) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `wound_veteran_fk` (`veteran_uuid`),
  KEY `FK7lm715cjv7o51227m2qr221kq` (`disability_uuid`),
  KEY `FKar545h8a4iy6i6ru5pmuratwv` (`type_uuid`),
  CONSTRAINT `FK7lm715cjv7o51227m2qr221kq` FOREIGN KEY (`disability_uuid`) REFERENCES `wounddisabilities` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKar545h8a4iy6i6ru5pmuratwv` FOREIGN KEY (`type_uuid`) REFERENCES `woundtypes` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKlgq1cang2v6x66pxx5etwqt96` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of veteranwounds
-- ----------------------------

-- ----------------------------
-- Table structure for workplaces
-- ----------------------------
DROP TABLE IF EXISTS `workplaces`;
CREATE TABLE `workplaces` (
  `uuid` binary(16) NOT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `locality` varchar(150) DEFAULT NULL,
  `hr_number` varchar(50) DEFAULT NULL,
  `veteran_uuid` binary(16) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `workPlace_veteran_fk` (`veteran_uuid`),
  CONSTRAINT `FKi161y2nyj243ts44co8ddvqo5` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `workPlace_veteran_fk` FOREIGN KEY (`veteran_uuid`) REFERENCES `veterans` (`uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workplaces
-- ----------------------------

-- ----------------------------
-- Table structure for wounddisabilities
-- ----------------------------
DROP TABLE IF EXISTS `wounddisabilities`;
CREATE TABLE `wounddisabilities` (
  `uuid` binary(16) NOT NULL,
  `disability` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wounddisabilities
-- ----------------------------

-- ----------------------------
-- Table structure for woundtypes
-- ----------------------------
DROP TABLE IF EXISTS `woundtypes`;
CREATE TABLE `woundtypes` (
  `uuid` binary(16) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of woundtypes
-- ----------------------------
