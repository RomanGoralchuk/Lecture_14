CREATE TABLE IF NOT EXISTS `patients` (
  `patient_ID` int(11) NOT NULL AUTO_INCREMENT,
  `patient_registration` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `patient_name` char(20) NOT NULL,
  `patient_surname` char(20) NOT NULL,
  `patient_sex` char(1) NOT NULL,
  `patient_birthday` date NOT NULL,
  PRIMARY KEY (`patient_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;