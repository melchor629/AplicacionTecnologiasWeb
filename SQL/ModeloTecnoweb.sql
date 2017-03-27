-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tecnoweb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tecnoweb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tecnoweb` DEFAULT CHARACTER SET utf8 ;
USE `tecnoweb` ;

-- -----------------------------------------------------
-- Table `tecnoweb`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `Apellidos` VARCHAR(100) NOT NULL,
  `Twitter` VARCHAR(45) NULL,
  `Instagram` VARCHAR(45) NULL,
  `Web` VARCHAR(45) NULL,
  `Foto` VARCHAR(255) NULL,
  `NombreUsuario` VARCHAR(45) NOT NULL,
  `Contraseña` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`Estudios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`Estudios` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`Estudios` (
  `IdUsuario` INT NOT NULL,
  `Fecha_Comienzo` DATETIME NOT NULL,
  `Fecha_Finalizacion` DATETIME NOT NULL,
  `Descripcion` VARCHAR(255) NULL,
  `Ubicacion` VARCHAR(455) NULL,
  PRIMARY KEY (`IdUsuario`),
  CONSTRAINT `Usuario`
    FOREIGN KEY (`IdUsuario`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`Aficiones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`Aficiones` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`Aficiones` (
  `idUsuario` INT NOT NULL,
  `Nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`ExperienciaLaboral`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`ExperienciaLaboral` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`ExperienciaLaboral` (
  `idUsuario` INT NOT NULL,
  `Fecha_Comienzo` DATETIME NOT NULL,
  `Fecha_Finalizacion` DATETIME NULL,
  `Empresa` VARCHAR(45) NOT NULL,
  `Puesto` VARCHAR(45) NOT NULL,
  `Web_Empresa` VARCHAR(45) NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT `Usuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`Contactos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`Contactos` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`Contactos` (
  `idUsuario` INT NOT NULL,
  `Amigo` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `UsuarioAmigo_idx` (`Amigo` ASC),
  CONSTRAINT `UsuarioPK`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UsuarioAmigo`
    FOREIGN KEY (`Amigo`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`PeticionAmistad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`PeticionAmistad` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`PeticionAmistad` (
  `idEmisor` INT NOT NULL,
  `idReceptor` INT NOT NULL,
  `Mensaje` VARCHAR(499) NULL,
  PRIMARY KEY (`idEmisor`, `idReceptor`),
  INDEX `UsuarioReceptor_idx` (`idReceptor` ASC),
  CONSTRAINT `Usuario`
    FOREIGN KEY (`idEmisor`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UsuarioReceptor`
    FOREIGN KEY (`idReceptor`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tecnoweb`.`Mensaje`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tecnoweb`.`Mensaje` ;

CREATE TABLE IF NOT EXISTS `tecnoweb`.`Mensaje` (
  `idEmisor` INT NOT NULL,
  `idReceptor` INT NOT NULL,
  `Texto` VARCHAR(999) NOT NULL,
  `Leido` TINYINT(1) NOT NULL,
  `Titulo` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idEmisor`, `idReceptor`),
  INDEX `UsuarioReceptor_idx` (`idReceptor` ASC),
  CONSTRAINT `Usuario`
    FOREIGN KEY (`idEmisor`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UsuarioReceptor`
    FOREIGN KEY (`idReceptor`)
    REFERENCES `tecnoweb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
