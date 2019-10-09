SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DBAutostrada
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DBAutostrada
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DBAutostrada` DEFAULT CHARACTER SET utf8 ;
USE `DBAutostrada` ;

-- -----------------------------------------------------
-- Table `DBAutostrada`.`autostrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBAutostrada`.`autostrada` (
  `idautostrada` INT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo` TINYINT NOT NULL,
  PRIMARY KEY (`idautostrada`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBAutostrada`.`casello`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBAutostrada`.`casello` (
  `idcasello` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `chilometro` FLOAT NOT NULL,
  `idautostrada` INT NOT NULL,
  PRIMARY KEY (`idcasello`),
  INDEX `fk_casello_autostrada1_idx` (`idautostrada` ASC) VISIBLE,
  CONSTRAINT `fk_casello_autostrada1`
    FOREIGN KEY (`idautostrada`)
    REFERENCES `DBAutostrada`.`autostrada` (`idautostrada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBAutostrada`.`biglietto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBAutostrada`.`biglietto` (
  `idbiglietto` INT NOT NULL AUTO_INCREMENT,
  `targa_veicolo` VARCHAR(9) NOT NULL,
  `idcasello_ingresso` INT NOT NULL,
  PRIMARY KEY (`idbiglietto`),
  INDEX `fk_biglietto_casello1_idx` (`idcasello_ingresso` ASC) VISIBLE,
  CONSTRAINT `fk_biglietto_casello1`
    FOREIGN KEY (`idcasello_ingresso`)
    REFERENCES `DBAutostrada`.`casello` (`idcasello`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBAutostrada`.`tariffa_autostradale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBAutostrada`.`tariffa_autostradale` (
  `idautostrada` INT NOT NULL AUTO_INCREMENT,
  `tariffaA` FLOAT NOT NULL,
  `tariffaB` FLOAT NOT NULL,
  `tariffa1` FLOAT NOT NULL,
  `tariifa2` FLOAT NOT NULL,
  `tariifa4` FLOAT NOT NULL,
  PRIMARY KEY (`idautostrada`),
  CONSTRAINT `idautostrada`
    FOREIGN KEY (`idautostrada`)
    REFERENCES `DBAutostrada`.`autostrada` (`idautostrada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBAutostrada`.`veicolo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBAutostrada`.`veicolo` (
  `altezza` INT NOT NULL,
  `n_assi` TINYINT NOT NULL,
  `carrello` TINYINT NOT NULL,
  `n_assi_carrello` TINYINT NOT NULL,
  `targa` VARCHAR(9) NOT NULL,
  `modello` VARCHAR(20) NOT NULL,
  `marca` VARCHAR(20) NOT NULL,
  `anno` INT NOT NULL,
  `peso` INT NOT NULL,
  PRIMARY KEY (`targa`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;