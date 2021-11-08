CREATE TABLE IF NOT EXISTS `registration_complaints` (
  `reg_number` int(11) NOT NULL AUTO_INCREMENT,
  `pat_ID` int(11) NOT NULL,
  `date_input` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `complaints` char(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`reg_number`),
  KEY `FK_registration_complaints_patients` (`pat_ID`),
  CONSTRAINT `FK_registration_complaints_patients` FOREIGN KEY (`pat_ID`) REFERENCES `patients` (`patient_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;