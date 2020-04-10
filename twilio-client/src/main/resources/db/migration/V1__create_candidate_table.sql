-- MySQL Workbench Forward Engineering

/*SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';*/

-- -----------------------------------------------------
-- Schema votes
-- -----------------------------------------------------
/*CREATE SCHEMA IF NOT EXISTS `votes` DEFAULT CHARACTER SET utf8 ;
USE `votes` ;*/

-- -----------------------------------------------------
-- Table `votes`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `votes`.`candidate` (
  `candidate_id` VARCHAR(36) NOT NULL,
  `candidate_name` VARCHAR(45) NOT NULL,
  `total_vote_count` INT(5) NOT NULL,
  PRIMARY KEY (`candidate_id`),
  UNIQUE INDEX `candidate_id_UNIQUE` (`candidate_id` ASC),
  UNIQUE INDEX `candidate_name_UNIQUE` (`candidate_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `votes`.`vote`
-- -----------------------------------------------------
/*CREATE TABLE IF NOT EXISTS `votes`.`vote` (
  `vote_id` VARCHAR(36) NOT NULL,
  `candidate` VARCHAR(36) NOT NULL,
  `total_vote_count` INT(5) NOT NULL,
  PRIMARY KEY (`vote_id`),
  UNIQUE INDEX `vote_id_UNIQUE` (`vote_id` ASC),
  INDEX `candidate_id_idx` (`candidate` ASC),
  CONSTRAINT `candidate_id`
    FOREIGN KEY (`candidate`)
    REFERENCES `votes`.`candidate` (`candidate_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;*/

