-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: gymfit
-- ------------------------------------------------------
-- Server version	5.7.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ejercicio`
--
CREATE DATABASE gymfit;
USE gymfit;
DROP TABLE IF EXISTS `ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejercicio` (
  `idEjercicio` int(11) NOT NULL AUTO_INCREMENT,
  `cargaMax` decimal(5,2) NOT NULL,
  `totalRepeticiones` int(3) NOT NULL,
  `idEntrenamiento` int(11) DEFAULT NULL,
  `idTipoEjercicio` int(11) NOT NULL,
  PRIMARY KEY (`idEjercicio`),
  KEY `fk_ejercicio_entrenamiento1_idx` (`idEntrenamiento`),
  KEY `fk_ejercicio_tipo_ejercicio1_idx` (`idTipoEjercicio`),
  CONSTRAINT `fk_ejercicio_tipo_ejercicio1` FOREIGN KEY (`idTipoEjercicio`) REFERENCES `tipo_ejercicio` (`idTipoEjercicio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejercicio`
--

LOCK TABLES `ejercicio` WRITE;
/*!40000 ALTER TABLE `ejercicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrenamiento`
--

DROP TABLE IF EXISTS `entrenamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entrenamiento` (
  `idEntrenamiento` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEntreno` varchar(128) NOT NULL,
  `fecha` date NOT NULL,
  `notas` varchar(256) DEFAULT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEntrenamiento`),
  KEY `fk_entrenamiento_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_entrenamiento_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenamiento`
--

LOCK TABLES `entrenamiento` WRITE;
/*!40000 ALTER TABLE `entrenamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrenamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `peso`
--

DROP TABLE IF EXISTS `peso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `peso` (
  `pesoReg` decimal(4,1) NOT NULL,
  `fecha` date NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`fecha`,`idUsuario`),
  KEY `fk_peso_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_peso_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `peso`
--

LOCK TABLES `peso` WRITE;
/*!40000 ALTER TABLE `peso` DISABLE KEYS */;
/*!40000 ALTER TABLE `peso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombreRol` varchar(32) NOT NULL,
  `descRol` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROL_REGISTRADO','Rol para los usuarios registrados'),(2,'ROL_ADMIN','Rol para los usuarios administradores');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_ejercicio`
--

DROP TABLE IF EXISTS `tipo_ejercicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_ejercicio` (
  `idTipoEjercicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEjercicio` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `descEjercicio` varchar(128) COLLATE utf8_spanish_ci DEFAULT NULL,
  `rutaImgEjercicio` varchar(64) COLLATE utf8_spanish_ci DEFAULT NULL,
  `urlVideo` varchar(512) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idTipoEjercicio`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_ejercicio`
--

LOCK TABLES `tipo_ejercicio` WRITE;
/*!40000 ALTER TABLE `tipo_ejercicio` DISABLE KEYS */;
INSERT INTO `tipo_ejercicio` VALUES (1,'Press banca plano con barra','Ejercicio realizado con barra para trabajar la zona del pectoral mayor en conjunto','planobarra.jpg','https://www.youtube.com/watch?v=0BzdD55kELs'),(2,'Press banca declinado con barra','Ejercicio realizado con barra para trabajar la zona del pectoral inferior','declinadobarra.jpg','https://www.youtube.com/watch?v=tEspGkqoik4'),(3,'Aperturas planas con mancuernas','Ejercicio realizado con mancuernas para trabajar la zona del pectoral mayor','aperturasplanasmancuernas.jpg','https://www.youtube.com/watch?v=xyHdY99F640'),(4,'Remo con barra','Ejercicio de remo con barra para la zona dorsal','remobarra.png','https://www.youtube.com/watch?v=YfNpoC5FuXk'),(5,'Sentadillas','Ejercicio para el tren inferior','sentadilla.jpg','https://www.youtube.com/watch?v=QMFHCvmX1Y8'),(6,'Press Militar','Ejercicio para hombros con barra o mancuernas','pressmilitar.jpg','https://www.youtube.com/watch?v=j_Buh54Sb-w'),(7,'Peso Muerto','Ejercicio para tren inferior con barra','pesomuerto.png','https://www.youtube.com/watch?v=7_Wm0YGQinw');
/*!40000 ALTER TABLE `tipo_ejercicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(32) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(64) COLLATE utf8_spanish_ci NOT NULL,
  `edad` int(3) DEFAULT NULL,
  `altura` int(3) DEFAULT NULL,
  `rutaFoto` varchar(64) CHARACTER SET utf8 DEFAULT 'user-icon.png',
  `idRol` int(11) NOT NULL,
  `fechaRegistro` date NOT NULL,
  `nombreApellido` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_usuario_rol_idx` (`idRol`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-12  9:33:42
