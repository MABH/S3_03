-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema floristeria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema floristeria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `floristeria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `floristeria` ;

-- -----------------------------------------------------
-- Table `floristeria`.`Floristeries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `floristeria`.`Floristeries` (
  `id_floristeria` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_floristeria`),
  INDEX `id_floristeries` (`id_floristeria` ASC) INVISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `floristeria`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `floristeria`.`Tickets` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `floristeria_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_ticket`),
  INDEX `id_ticket` (`id_ticket` ASC) VISIBLE,
  INDEX `fk_Tickets_Floristeries1_idx` (`floristeria_id` ASC) VISIBLE,
  CONSTRAINT `fk_Tickets_Floristeries1`
    FOREIGN KEY (`floristeria_id`)
    REFERENCES `floristeria`.`Floristeries` (`id_floristeria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `floristeria`.`Flors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `floristeria`.`Flors` (
  `id_flors` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `preu` FLOAT NOT NULL,
  `ticket_id` INT NULL,
  `floristeria_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id_flors`),
  INDEX `id_flors_UNIQUE` (`id_flors` ASC) VISIBLE,
  INDEX `fk_Flors_Tickets1_idx` (`ticket_id` ASC) VISIBLE,
  INDEX `fk_Flors_Floristeries1_idx` (`floristeria_id` ASC) VISIBLE,
  CONSTRAINT `fk_Flors_Tickets1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `floristeria`.`Tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Flors_Floristeries1`
    FOREIGN KEY (`floristeria_id`)
    REFERENCES `floristeria`.`Floristeries` (`id_floristeria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `floristeria`.`Decoracions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `floristeria`.`Decoracions` (
  `id_decoracio` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `material` ENUM('PLASTIC', 'FUSTA') NOT NULL,
  `preu` FLOAT NOT NULL,
  `ticket_id` INT NULL,
  `floristeria_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id_decoracio`),
  INDEX `id_decoracio` (`id_decoracio` ASC) INVISIBLE,
  INDEX `fk_Decoracions_Tickets1_idx` (`ticket_id` ASC) VISIBLE,
  INDEX `fk_Decoracions_Floristeries1_idx` (`floristeria_id` ASC) VISIBLE,
  CONSTRAINT `fk_Decoracions_Tickets1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `floristeria`.`Tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Decoracions_Floristeries1`
    FOREIGN KEY (`floristeria_id`)
    REFERENCES `floristeria`.`Floristeries` (`id_floristeria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `floristeria`.`Arbres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `floristeria`.`Arbres` (
  `id_arbre` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `alçada` FLOAT NOT NULL,
  `preu` FLOAT NOT NULL,
  `ticket_id` INT NULL,
  `floristeria_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id_arbre`),
  INDEX `id_arbre` (`id_arbre` ASC) INVISIBLE,
  INDEX `fk_Arbres_Tickets1_idx` (`ticket_id` ASC) VISIBLE,
  INDEX `fk_Arbres_Floristeries1_idx` (`floristeria_id` ASC) VISIBLE,
  CONSTRAINT `fk_Arbres_Tickets1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `floristeria`.`Tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Arbres_Floristeries1`
    FOREIGN KEY (`floristeria_id`)
    REFERENCES `floristeria`.`Floristeries` (`id_floristeria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
