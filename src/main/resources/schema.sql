CREATE TABLE IF NOT EXISTS `student_details` (
  `regId` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL,
  `dept` varchar(6) DEFAULT NULL,
  `college` varchar(45) DEFAULT NULL,
  `section` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`regId`),
  UNIQUE KEY `regId_UNIQUE` (`regId`)
);




CREATE TABLE IF NOT EXISTS Skills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    techstack VARCHAR(25) NOT NULL,
    category VARCHAR(25) NOT NULL,
    UNIQUE KEY uniq_tech_category (techstack, category)
);

CREATE TABLE IF NOT EXISTS student_skills (
    regId VARCHAR(15),
    skills_id INT,
    PRIMARY KEY (regId, skills_id),
    FOREIGN KEY (regId) REFERENCES student_details(regId) ON DELETE CASCADE,
    FOREIGN KEY (skills_id) REFERENCES Skills(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS `student`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(45) NOT NULL,
  `password` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`));


