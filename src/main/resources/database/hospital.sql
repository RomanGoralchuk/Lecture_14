/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `hospital`;

CREATE TABLE IF NOT EXISTS `disease_treatment` (
  `input_number` int(11) NOT NULL,
  `start_treatment` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `end_treatment` date NOT NULL,
  `diagnosis` char(50) DEFAULT NULL,
  `result_treatment` char(50) NOT NULL DEFAULT 'alive',
  `doctor` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`input_number`),
  KEY `FK_disease_treatment_doctors` (`doctor`),
  CONSTRAINT `FK_disease_treatment_doctors` FOREIGN KEY (`doctor`) REFERENCES `doctors` (`doc_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_disease_treatment_registration_complaints` FOREIGN KEY (`input_number`) REFERENCES `registration_complaints` (`reg_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `disease_treatment` DISABLE KEYS */;
INSERT INTO `disease_treatment` (`input_number`, `start_treatment`, `end_treatment`, `diagnosis`, `result_treatment`, `doctor`) VALUES
	(1, '2021-10-18 00:25:37', '2021-10-20', 'alcohol', 'alive', 6);
/*!40000 ALTER TABLE `disease_treatment` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `doctors` (
  `doc_ID` int(11) NOT NULL AUTO_INCREMENT,
  `doc_name` char(50) NOT NULL,
  `doc_surname` char(50) NOT NULL,
  `doc_specialization` char(50) NOT NULL,
  PRIMARY KEY (`doc_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` (`doc_ID`, `doc_name`, `doc_surname`, `doc_specialization`) VALUES
	(1, 'Jimm', 'Morris', 'cardiologis'),
	(2, 'Albert', 'Einstein', 'proctologist'),
	(3, 'John', 'Elton', 'psihologist'),
	(4, 'Bradley', 'Pitt', 'surgeon'),
	(5, 'Dwayne', 'Johnson', 'surgeon'),
	(6, 'Abraham', 'Lincoln', 'urologist'),
	(7, 'Yuri', 'Zhivago', 'philosopher'),
	(8, 'Yuri', 'Zhivago', 'philosopher');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `patients` (
  `patient_ID` int(11) NOT NULL AUTO_INCREMENT,
  `patient_registration` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `patient_name` char(20) NOT NULL,
  `patient_surname` char(20) NOT NULL,
  `patient_sex` char(1) NOT NULL,
  `patient_birthday` date NOT NULL,
  PRIMARY KEY (`patient_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` (`patient_ID`, `patient_registration`, `patient_name`, `patient_surname`, `patient_sex`, `patient_birthday`) VALUES
	(1, '2021-10-17 23:05:32', 'James', 'Bukster', 'M', '1988-11-10'),
	(2, '2021-10-17 23:05:43', 'Jane', 'Bukster', 'F', '2004-05-15'),
	(3, '2021-10-17 23:19:19', 'Bob', 'Sponge', 'M', '2000-10-18'),
	(4, '2021-10-17 23:20:05', 'Patrik', 'SeaStar', 'M', '2001-11-18'),
	(17, '2021-10-24 22:41:05', 'Toto', 'Cutugno', 'M', '1968-09-04'),
	(18, '2021-10-24 22:41:05', 'Alice', 'Cooper', 'M', '1950-09-04'),
	(19, '2021-10-24 22:41:05', 'Lola', 'Brigitta', 'F', '1930-09-04'),
	(20, '2021-10-25 09:56:13', 'Larisa', 'Dolina', 'F', '1958-10-04'),
	(24, '2021-10-28 13:07:31', 'Mark', 'Antony', 'M', '0001-04-04'),
	(25, '2021-11-02 22:56:38', 'Lars', 'Trier', 'M', '1952-10-04'),
	(26, '2021-11-02 23:47:35', 'Jaga', 'Koshceeva', 'F', '1012-11-13'),
	(27, '2021-11-03 17:28:11', 'Cindy', 'Crawford', 'M', '1966-02-20'),
	(31, '2021-11-04 09:23:02', 'Cindy', 'Crawford', 'M', '1966-02-20');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `registration_complaints` (
  `reg_number` int(11) NOT NULL AUTO_INCREMENT,
  `pat_ID` int(11) NOT NULL,
  `date_input` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `complaints` char(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`reg_number`),
  KEY `FK_registration_complaints_patients` (`pat_ID`),
  CONSTRAINT `FK_registration_complaints_patients` FOREIGN KEY (`pat_ID`) REFERENCES `patients` (`patient_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*!40000 ALTER TABLE `registration_complaints` DISABLE KEYS */;
INSERT INTO `registration_complaints` (`reg_number`, `pat_ID`, `date_input`, `complaints`) VALUES
	(1, 1, '2021-10-17 23:02:27', 'headache'),
	(2, 1, '2021-10-17 23:26:55', 'asspain'),
	(3, 4, '2021-10-18 02:28:10', 'headache');
/*!40000 ALTER TABLE `registration_complaints` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
