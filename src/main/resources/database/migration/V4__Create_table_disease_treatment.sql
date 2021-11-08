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