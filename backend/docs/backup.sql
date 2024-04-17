-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: Paqueteria
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cliente` (
  `nit` int NOT NULL AUTO_INCREMENT,
  `idSistema` int DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `nombre` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`nit`),
  KEY `idSistema` (`idSistema`),
  CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`idSistema`) REFERENCES `Sistema` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=988 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES (123,1,1,'Rony'),(321,1,1,'Mauricio'),(456,1,1,'Alex'),(654,1,1,'Luther'),(789,1,1,'Daniel');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ColaEspera`
--

DROP TABLE IF EXISTS `ColaEspera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ColaEspera` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idPuntoDeControl` int DEFAULT NULL,
  `cantidadMaximaPaquetes` int DEFAULT NULL,
  `nombreCola` varchar(12) DEFAULT NULL,
  `idRuta` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombreCola` (`nombreCola`),
  KEY `idPuntoDeControl` (`idPuntoDeControl`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `ColaEspera_ibfk_1` FOREIGN KEY (`idPuntoDeControl`) REFERENCES `PuntoDeControl` (`id`),
  CONSTRAINT `ColaEspera_ibfk_2` FOREIGN KEY (`idRuta`) REFERENCES `Ruta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ColaEspera`
--

LOCK TABLES `ColaEspera` WRITE;
/*!40000 ALTER TABLE `ColaEspera` DISABLE KEYS */;
/*!40000 ALTER TABLE `ColaEspera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Destino`
--

DROP TABLE IF EXISTS `Destino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Destino` (
  `id` int NOT NULL,
  `cuota` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destino`
--

LOCK TABLES `Destino` WRITE;
/*!40000 ALTER TABLE `Destino` DISABLE KEYS */;
INSERT INTO `Destino` VALUES (1,23),(2,24),(3,25);
/*!40000 ALTER TABLE `Destino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Empleado`
--

DROP TABLE IF EXISTS `Empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Empleado` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSistema` int DEFAULT NULL,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(12) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `rol` int DEFAULT NULL,
  `nombre` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSistema` (`idSistema`),
  CONSTRAINT `Empleado_ibfk_1` FOREIGN KEY (`idSistema`) REFERENCES `Sistema` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empleado`
--

LOCK TABLES `Empleado` WRITE;
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
INSERT INTO `Empleado` VALUES (1,1,'admin1','admin1',1,1,'Administrador1'),(2,1,'admin2','admin2',1,1,'Administrador2'),(3,1,'admin3','admin3',1,1,'Administrador3'),(4,1,'operador1','operador1',1,2,'Operador1'),(5,1,'operador2','operador2',1,2,'Operador2'),(6,1,'operador3','operador3',1,2,'Operador3'),(7,1,'recepcion1','recepcion1',1,3,'Recepcionista1'),(8,1,'recepcion2','recepcion2',1,3,'Recepcionista2'),(9,1,'recepcion3','recepcion3',1,3,'Recepcionista3');
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Envio`
--

DROP TABLE IF EXISTS `Envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Envio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idSistema` int DEFAULT NULL,
  `idPaquete` int DEFAULT NULL,
  `idRuta` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idSistema` (`idSistema`),
  KEY `idPaquete` (`idPaquete`),
  KEY `idRuta` (`idRuta`),
  CONSTRAINT `Envio_ibfk_1` FOREIGN KEY (`idSistema`) REFERENCES `Sistema` (`id`),
  CONSTRAINT `Envio_ibfk_2` FOREIGN KEY (`idPaquete`) REFERENCES `Paquete` (`id`),
  CONSTRAINT `Envio_ibfk_3` FOREIGN KEY (`idRuta`) REFERENCES `Ruta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Envio`
--

LOCK TABLES `Envio` WRITE;
/*!40000 ALTER TABLE `Envio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Envio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Factura`
--

DROP TABLE IF EXISTS `Factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Factura` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nitCliente` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `precioEnvio` int DEFAULT NULL,
  `precioIngreso` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `nitCliente` (`nitCliente`),
  CONSTRAINT `Factura_ibfk_2` FOREIGN KEY (`nitCliente`) REFERENCES `Cliente` (`nit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Factura`
--

LOCK TABLES `Factura` WRITE;
/*!40000 ALTER TABLE `Factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `Factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paquete`
--

DROP TABLE IF EXISTS `Paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Paquete` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idRuta` int DEFAULT NULL,
  `nitCliente` int DEFAULT NULL,
  `idDestino` int DEFAULT NULL,
  `idPuntoDeControlActual` int DEFAULT NULL,
  `tiempo` int DEFAULT NULL,
  `peso` int DEFAULT NULL,
  `precioEnvio` int DEFAULT NULL,
  `ingresado` tinyint(1) DEFAULT NULL,
  `recogido` tinyint DEFAULT NULL,
  `precioPLibra` int DEFAULT NULL,
  `enRuta` tinyint DEFAULT '0',
  `precioIngreso` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idRuta` (`idRuta`),
  KEY `nitCliente` (`nitCliente`),
  KEY `idDestino` (`idDestino`),
  KEY `idPuntoDeControlActual` (`idPuntoDeControlActual`),
  CONSTRAINT `Paquete_ibfk_1` FOREIGN KEY (`idRuta`) REFERENCES `Ruta` (`id`),
  CONSTRAINT `Paquete_ibfk_2` FOREIGN KEY (`nitCliente`) REFERENCES `Cliente` (`nit`),
  CONSTRAINT `Paquete_ibfk_3` FOREIGN KEY (`idDestino`) REFERENCES `Destino` (`id`),
  CONSTRAINT `Paquete_ibfk_4` FOREIGN KEY (`idPuntoDeControlActual`) REFERENCES `PuntoDeControl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paquete`
--

LOCK TABLES `Paquete` WRITE;
/*!40000 ALTER TABLE `Paquete` DISABLE KEYS */;
INSERT INTO `Paquete` VALUES (1,NULL,123,1,NULL,0,12,0,0,0,34,0,0),(2,NULL,123,2,NULL,0,12,0,0,0,34,0,0),(3,NULL,123,2,NULL,0,12,0,0,0,34,0,0),(4,NULL,321,3,NULL,0,15,0,0,0,34,0,0),(5,NULL,321,3,NULL,0,12,0,0,0,34,0,0),(6,NULL,321,1,NULL,0,11,0,0,0,34,0,0),(7,NULL,456,1,NULL,0,14,0,0,0,34,0,0),(8,NULL,456,2,NULL,0,11,0,0,0,34,0,0),(9,NULL,456,2,NULL,0,18,0,0,0,34,0,0),(10,NULL,654,1,NULL,0,16,0,0,0,34,0,0),(11,NULL,654,1,NULL,0,15,0,0,0,34,0,0),(12,NULL,654,1,NULL,0,19,0,0,0,34,0,0),(13,NULL,789,3,NULL,0,11,0,0,0,34,0,0),(14,NULL,789,3,NULL,0,14,0,0,0,34,0,0),(15,NULL,789,3,NULL,0,18,0,0,0,34,0,0),(16,NULL,123,1,NULL,0,11,0,0,0,34,0,0),(17,NULL,123,1,NULL,0,11,0,0,0,34,0,0),(18,NULL,123,1,NULL,0,11,0,0,0,34,0,0),(19,NULL,123,3,NULL,0,11,0,0,0,34,0,0),(20,NULL,123,2,NULL,0,13,0,0,0,34,0,0),(21,NULL,321,1,NULL,0,11,0,0,0,34,0,0),(22,NULL,321,1,NULL,0,11,0,0,0,34,0,0),(23,NULL,321,1,NULL,0,11,0,0,0,34,0,0),(24,NULL,321,1,NULL,0,11,0,0,0,34,0,0),(25,NULL,321,2,NULL,0,11,0,0,0,34,0,0),(26,NULL,456,1,NULL,0,11,0,0,0,34,0,0),(27,NULL,456,1,NULL,0,11,0,0,0,34,0,0),(28,NULL,456,1,NULL,0,11,0,0,0,34,0,0),(29,NULL,456,3,NULL,0,11,0,0,0,34,0,0),(30,NULL,456,2,NULL,0,13,0,0,0,34,0,0),(31,NULL,654,1,NULL,0,11,0,0,0,34,0,0),(32,NULL,654,1,NULL,0,11,0,0,0,34,0,0),(33,NULL,654,1,NULL,0,11,0,0,0,34,0,0),(34,NULL,654,3,NULL,0,11,0,0,0,34,0,0),(35,NULL,654,2,NULL,0,13,0,0,0,34,0,0),(36,NULL,789,1,NULL,0,11,0,0,0,34,0,0),(37,NULL,789,1,NULL,0,11,0,0,0,34,0,0),(38,NULL,789,1,NULL,0,11,0,0,0,34,0,0),(39,NULL,789,3,NULL,0,11,0,0,0,34,0,0),(40,NULL,789,2,NULL,0,13,0,0,0,34,0,0);
/*!40000 ALTER TABLE `Paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PuntoDeControl`
--

DROP TABLE IF EXISTS `PuntoDeControl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PuntoDeControl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idRuta` int DEFAULT NULL,
  `idEmpleado` int DEFAULT NULL,
  `tarifaOperacion` int DEFAULT NULL,
  `libre` tinyint DEFAULT NULL,
  `cantidadMaximaPaquetes` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idRuta` (`idRuta`),
  KEY `idEmpleado` (`idEmpleado`),
  CONSTRAINT `PuntoDeControl_ibfk_1` FOREIGN KEY (`idRuta`) REFERENCES `Ruta` (`id`),
  CONSTRAINT `PuntoDeControl_ibfk_2` FOREIGN KEY (`idEmpleado`) REFERENCES `Empleado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PuntoDeControl`
--

LOCK TABLES `PuntoDeControl` WRITE;
/*!40000 ALTER TABLE `PuntoDeControl` DISABLE KEYS */;
INSERT INTO `PuntoDeControl` VALUES (1,1,4,12,0,5),(2,1,4,12,0,4),(3,1,5,12,0,5),(4,1,5,12,1,6),(5,2,6,12,0,5),(6,2,6,12,0,3),(7,2,4,12,0,3),(8,2,4,12,1,4),(9,3,5,12,0,4),(10,3,5,12,1,5),(11,3,6,12,1,5),(12,3,6,12,1,4),(13,4,4,12,0,5),(14,4,4,12,1,5),(15,4,5,12,1,4),(16,4,5,12,1,6),(17,5,6,12,0,4),(18,5,6,12,1,3),(19,5,4,12,1,5),(20,5,4,12,1,4);
/*!40000 ALTER TABLE `PuntoDeControl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ruta`
--

DROP TABLE IF EXISTS `Ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ruta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idDestino` int DEFAULT NULL,
  `activa` tinyint(1) DEFAULT NULL,
  `cantidadTotalPaquetes` int DEFAULT NULL,
  `concurrencia` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idDestino` (`idDestino`),
  CONSTRAINT `Ruta_ibfk_1` FOREIGN KEY (`idDestino`) REFERENCES `Destino` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ruta`
--

LOCK TABLES `Ruta` WRITE;
/*!40000 ALTER TABLE `Ruta` DISABLE KEYS */;
INSERT INTO `Ruta` VALUES (1,1,1,0,0),(2,2,1,0,0),(3,3,1,0,0),(4,2,1,0,0),(5,1,1,0,0);
/*!40000 ALTER TABLE `Ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sistema`
--

DROP TABLE IF EXISTS `Sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sistema` (
  `id` int NOT NULL AUTO_INCREMENT,
  `precioPLibra` int DEFAULT NULL,
  `tarifaOperacionGlobal` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sistema`
--

LOCK TABLES `Sistema` WRITE;
/*!40000 ALTER TABLE `Sistema` DISABLE KEYS */;
INSERT INTO `Sistema` VALUES (1,34,12);
/*!40000 ALTER TABLE `Sistema` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 16:53:41
