-- MySQL Script generated by MySQL Workbench
-- 06/10/16 09:12:42
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema search_on_role
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema search_on_role
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `search_on_role` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `search_on_role` ;

-- -----------------------------------------------------
-- Table `search_on_role`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `search_on_role`.`usuario` ;

CREATE TABLE IF NOT EXISTS `search_on_role`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(100) NULL COMMENT '',
  `email` VARCHAR(100) NULL COMMENT '',
  `senha` VARCHAR(32) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `logincol_UNIQUE` (`senha` ASC)  COMMENT '',
  UNIQUE INDEX `logincol_UNIQUE` (`email` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `search_on_role`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `search_on_role`.`roles` ;

CREATE TABLE IF NOT EXISTS `search_on_role`.`roles` (
  `idrole` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `preco` FLOAT NULL COMMENT '',
  `data` VARCHAR(60) NULL COMMENT '',
  `local` VARCHAR(100) NULL COMMENT '',
  `descricao` VARCHAR(500) NULL COMMENT '',
  `origID` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idrole`)  COMMENT '',
  INDEX `fk_roles_login_idx` (`origID` ASC)  COMMENT '',
  CONSTRAINT `fk_roles_login`
    FOREIGN KEY (`origID`)
    REFERENCES `search_on_role`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `search_on_role`.`confirmar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `search_on_role`.`confirmar` ;

CREATE TABLE IF NOT EXISTS `search_on_role`.`confirmar` (
  `idUsuario` INT NOT NULL COMMENT '',
  `idRole` INT NOT NULL COMMENT '',
  PRIMARY KEY (`idUsuario`, `idRole`)  COMMENT '',
  INDEX `fk_role_idx` (`idRole` ASC)  COMMENT '',
  CONSTRAINT `fk_usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `search_on_role`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role`
    FOREIGN KEY (`idRole`)
    REFERENCES `search_on_role`.`roles` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
