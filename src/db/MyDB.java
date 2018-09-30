package db;
/**
 * 
 * @author mehra
 * MYSQL database schema :coursedatabase
 * user :coursedatabase_admin
 * pass :Test1234
 * 
 */
public interface MyDB {

	String USER="root";
	String PASS="9440257142";
	String CONN_URL="jdbc:mysql://127.0.0.1:3306/artkart";
	
	
}
/**
-- MySQL Script generated by MySQL Workbench
-- Mon Sep 17 01:32:56 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
-- -----------------------------------------------------
-- Schema artkart
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema artkart
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `artkart` DEFAULT CHARACTER SET utf8 ;
USE `artkart` ;
-- -----------------------------------------------------
-- Table `artkart`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`User` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `User_userId_UNIQUE` (`userId` ASC),
  UNIQUE INDEX `User_username_UNIQUE` (`username` ASC));
-- -----------------------------------------------------
-- Table `artkart`.`CreditCard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`CreditCard` (
  `cardId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `number` VARCHAR(19) NOT NULL,
  `expDate` DATETIME NOT NULL,
  `ccv` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`cardId`),
  UNIQUE INDEX `CreditCard_cardId_UNIQUE` (`cardId` ASC),
  INDEX `CreditCard_userId_idx` (`userId` ASC),
  CONSTRAINT `fk_CreditCard_User_userId`
    FOREIGN KEY (`userId`)
    REFERENCES `artkart`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`Cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`Cart` (
  `cartId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  PRIMARY KEY (`cartId`),
  INDEX `Cart_userId_idx` (`userId` ASC),
  UNIQUE INDEX `Cart_cartId_UNIQUE` (`cartId` ASC),
  CONSTRAINT `fk_Cart_User_userId`
    FOREIGN KEY (`userId`)
    REFERENCES `artkart`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`Inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`Inventory` (
  `invnId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  PRIMARY KEY (`invnId`),
  INDEX `Inventory_id_idx` (`userId` ASC),
  UNIQUE INDEX `Inventory_invnId_UNIQUE` (`invnId` ASC),
  CONSTRAINT `fk_Inventory_User_userId`
    FOREIGN KEY (`userId`)
    REFERENCES `artkart`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`Product` (
  `prodId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(2000) NULL,
  `price` DOUBLE NOT NULL,
  `isSold` TINYINT NOT NULL,
  PRIMARY KEY (`prodId`),
  UNIQUE INDEX `Product_prodId_UNIQUE` (`prodId` ASC));
-- -----------------------------------------------------
-- Table `artkart`.`InventoryProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`InventoryProduct` (
  `invnId` INT NOT NULL,
  `prodId` INT NOT NULL,
  PRIMARY KEY (`invnId`, `prodId`),
  INDEX `InventoryProduct_prodId_idx` (`prodId` ASC),
  CONSTRAINT `fk_InventoryProduct_Inventory_invnId`
    FOREIGN KEY (`invnId`)
    REFERENCES `artkart`.`Inventory` (`invnId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InventoryProduct_Product_prodId`
    FOREIGN KEY (`prodId`)
    REFERENCES `artkart`.`Product` (`prodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`CartProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`CartProduct` (
  `cartId` INT NOT NULL,
  `prodId` INT NOT NULL,
  PRIMARY KEY (`cartId`, `prodId`),
  INDEX `CartProduct_prodId_idx` (`prodId` ASC),
  CONSTRAINT `fk_CartProduct_Cart_cartId`
    FOREIGN KEY (`cartId`)
    REFERENCES `artkart`.`Cart` (`cartId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CartProduct_Product_prodId`
    FOREIGN KEY (`prodId`)
    REFERENCES `artkart`.`Product` (`prodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`Transaction` (
  `trxnId` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`trxnId`),
  UNIQUE INDEX `Transaction_trxnId_UNIQUE` (`trxnId` ASC),
  INDEX `Transaction_userId_idx` (`userId` ASC),
  CONSTRAINT `fk_Transaction_User_userId`
    FOREIGN KEY (`userId`)
    REFERENCES `artkart`.`User` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`TransactionProduct`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`TransactionProduct` (
  `trxnId` INT NOT NULL,
  `prodId` INT NOT NULL,
  PRIMARY KEY (`trxnId`, `prodId`),
  INDEX `TransactionProduct_prodId_idx` (`prodId` ASC),
  CONSTRAINT `fk_TransactionProduct_Transaction_txnId`
    FOREIGN KEY (`trxnId`)
    REFERENCES `artkart`.`Transaction` (`trxnId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TransactionProduct_Product_prodId`
    FOREIGN KEY (`prodId`)
    REFERENCES `artkart`.`Product` (`prodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `artkart`.`Painting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artkart`.`Painting` (
  `prodId` INT NOT NULL,
  `canvasType` VARCHAR(255) NOT NULL,
  `paintType` VARCHAR(255) NOT NULL,
  `length` DOUBLE NOT NULL,
  `width` DOUBLE NOT NULL,
  PRIMARY KEY (`prodId`),
  INDEX `Painting_prodId_idx` (`prodId` ASC),
  CONSTRAINT `fk_Painting_Product_prodId`
    FOREIGN KEY (`prodId`)
    REFERENCES `artkart`.`Product` (`prodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



jdbc:mysql://127.0.0.1:3306/artkart?user=coursedatabase_admin

*/
