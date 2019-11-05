-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Nov 05, 2019 at 09:08 PM
-- Server version: 10.3.13-MariaDB-1:10.3.13+maria~bionic
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `ecotoll`
--

-- --------------------------------------------------------

--
-- Table structure for table `autostrada`
--

CREATE TABLE `autostrada` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `tipo` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `autostrada`
--

INSERT INTO `autostrada` (`id`, `nome`, `tipo`) VALUES
(1, 'Autostrada del sole', 0),
(2, 'Autostrada A3', 0),
(3, 'La Verdemare', 1),
(4, 'Autostrada Azzurra', 0),
(5, 'Autostrada Brebemi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `biglietto`
--

CREATE TABLE `biglietto` (
  `targa` varchar(9) NOT NULL,
  `ingresso` int(11) NOT NULL,
  `carrello` tinyint(1) NOT NULL,
  `n_assi_carrello` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `casello`
--

CREATE TABLE `casello` (
  `id` int(11) NOT NULL,
  `autostrada` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `chilometro` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `casello`
--

INSERT INTO `casello` (`id`, `autostrada`, `nome`, `chilometro`) VALUES
(2, 1, 'Fiorenzuola', 74),
(3, 1, 'Lodi', 22.3),
(4, 1, 'Sasso Marconi', 210),
(5, 2, 'Ercolano', 8.5),
(6, 2, 'Portici', 8),
(7, 2, 'Angri', 29.7),
(8, 3, 'Ceva', 81),
(9, 3, 'Carmagnola', 13.1),
(10, 3, 'Millesimo', 97.1),
(11, 4, 'Lavagna', 41.1),
(12, 4, 'Cecina Nord', 251.6),
(13, 4, 'Recco', 22.8),
(14, 5, 'Castrezzato', 30.4),
(15, 5, 'Bariano', 40.1),
(16, 5, 'Milano', 62.1);

-- --------------------------------------------------------

--
-- Table structure for table `tariffa`
--

CREATE TABLE `tariffa` (
  `autostrada` int(11) NOT NULL,
  `tariffaA` float NOT NULL,
  `tariffaB` float NOT NULL,
  `tariffa3` float NOT NULL,
  `tariffa4` float NOT NULL,
  `tariffa5` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tariffa`
--

INSERT INTO `tariffa` (`autostrada`, `tariffaA`, `tariffaB`, `tariffa3`, `tariffa4`, `tariffa5`) VALUES
(1, 0.1, 0.2, 0.3, 0.4, 0.5),
(2, 0.2, 0.3, 0.4, 0.5, 0.6),
(3, 0.3, 0.4, 0.5, 0.6, 0.7),
(4, 0.4, 0.5, 0.6, 0.7, 0.8),
(5, 0.5, 0.6, 0.7, 0.8, 0.9);

-- --------------------------------------------------------

--
-- Table structure for table `veicolo`
--

CREATE TABLE `veicolo` (
  `targa` varchar(9) NOT NULL,
  `modello` varchar(20) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `altezza` float NOT NULL,
  `peso` int(11) NOT NULL,
  `anno` int(11) NOT NULL,
  `n_assi` int(11) NOT NULL,
  `euro` int(11) NOT NULL,
  `co2` int(11) DEFAULT NULL,
  `decibel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `veicolo`
--

INSERT INTO `veicolo` (`targa`, `modello`, `marca`, `altezza`, `peso`, `anno`, `n_assi`, `euro`, `co2`, `decibel`) VALUES
('AA000AA', 'kuga', 'ford', 1.6, 1600, 2014, 0, 0, NULL, NULL),
('AA001AA', 'panda', 'fiat', 1.63, 1300, 2010, 0, 0, NULL, NULL),
('AA011AA', 'q3', 'audi', 1.68, 1700, 2017, 0, 0, NULL, NULL),
('AA111AA', 'model s', 'tesla', 1.5, 2500, 2018, 0, 0, NULL, NULL),
('AA112AA', 'fiesta', 'ford', 1.58, 1500, 2000, 0, 0, NULL, NULL),
('AA122AA', 'tipo', 'fiat', 1.63, 1800, 2018, 0, 0, NULL, NULL),
('AA222AA', 'vitara', 'suzuki', 1.9, 2200, 1999, 0, 0, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autostrada`
--
ALTER TABLE `autostrada`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `biglietto`
--
ALTER TABLE `biglietto`
  ADD PRIMARY KEY (`targa`),
  ADD KEY `ingresso` (`ingresso`),
  ADD KEY `targa` (`targa`);

--
-- Indexes for table `casello`
--
ALTER TABLE `casello`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`autostrada`),
  ADD KEY `nome` (`nome`);

--
-- Indexes for table `tariffa`
--
ALTER TABLE `tariffa`
  ADD PRIMARY KEY (`autostrada`);

--
-- Indexes for table `veicolo`
--
ALTER TABLE `veicolo`
  ADD PRIMARY KEY (`targa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autostrada`
--
ALTER TABLE `autostrada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `casello`
--
ALTER TABLE `casello`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tariffa`
--
ALTER TABLE `tariffa`
  MODIFY `autostrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tariffa`
--
ALTER TABLE `tariffa`
  ADD CONSTRAINT `idautostrada` FOREIGN KEY (`autostrada`) REFERENCES `autostrada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;
