-- MySQL Script generated by MySQL Workbench
-- 06/06/16 12:01:52
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`login` ;

CREATE TABLE IF NOT EXISTS `mydb`.`login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `senha` VARCHAR(32) NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `logincol_UNIQUE` (`senha` ASC),
  UNIQUE INDEX `logincol_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`roles` ;

CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `idroles` INT NOT NULL AUTO_INCREMENT,
  `preco` FLOAT NULL,
  `data` VARCHAR(60) NULL,
  `local` VARCHAR(100) NULL,
  `descricao` VARCHAR(500) NULL,
  `origID` INT NOT NULL,
  PRIMARY KEY (`idroles`),
  INDEX `fk_roles_login_idx` (`origID` ASC),
  CONSTRAINT `fk_roles_login`
    FOREIGN KEY (`origID`)
    REFERENCES `mydb`.`login` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
