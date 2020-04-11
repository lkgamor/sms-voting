-- -----------------------------------------------------
-- Table `votes`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `votes`.`candidate` (
  `candidate_id` VARCHAR(36) NOT NULL,
  `candidate_name` VARCHAR(50) NOT NULL,
  `candidate_image` BLOB,
  `total_vote_count` INT(5) NOT NULL,
  PRIMARY KEY (`candidate_id`),
  UNIQUE INDEX `candidate_id_UNIQUE` (`candidate_id` ASC),
  UNIQUE INDEX `candidate_name_UNIQUE` (`candidate_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

