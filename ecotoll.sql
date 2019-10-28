-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ott 28, 2019 alle 21:56
-- Versione del server: 5.7.17
-- Versione PHP: 7.1.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecotoll`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `autostrada`
--

CREATE TABLE `autostrada` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `tipo` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `autostrada`
--

INSERT INTO `autostrada` (`id`, `nome`, `tipo`) VALUES
(1, 'Autostrada del sole', 0),
(2, 'Autostrada A3', 0),
(3, 'La Verdemare', 1),
(4, 'Autostrada Azzurra', 0),
(5, 'Autostrada Brebemi', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `biglietto`
--

CREATE TABLE `biglietto` (
  `id` int(11) NOT NULL,
  `targa` varchar(9) NOT NULL,
  `ingresso` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `casello`
--

CREATE TABLE `casello` (
  `id` int(11) NOT NULL,
  `autostrada` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `chilometro` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `casello`
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
-- Struttura della tabella `tariffa`
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
-- Dump dei dati per la tabella `tariffa`
--

INSERT INTO `tariffa` (`autostrada`, `tariffaA`, `tariffaB`, `tariffa3`, `tariffa4`, `tariffa5`) VALUES
(1, 0.1, 0.2, 0.3, 0.4, 0.5),
(2, 0.2, 0.3, 0.4, 0.5, 0.6),
(3, 0.3, 0.4, 0.5, 0.6, 0.7),
(4, 0.4, 0.5, 0.6, 0.7, 0.8),
(5, 0.5, 0.6, 0.7, 0.8, 0.9);

-- --------------------------------------------------------

--
-- Struttura della tabella `veicolo`
--

CREATE TABLE `veicolo` (
  `targa` varchar(9) NOT NULL,
  `modello` varchar(20) NOT NULL,
  `marca` varchar(20) NOT NULL,
  `altezza` float NOT NULL,
  `peso` int(11) NOT NULL,
  `anno` int(11) NOT NULL,
  `n_assi` tinyint(4) NOT NULL,
  `carrello` tinyint(4) NOT NULL,
  `n_assi_carrello` tinyint(4) DEFAULT NULL,
  `emissioni_Co2` int(11) DEFAULT NULL,
  `emissioni_decibel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `veicolo`
--

INSERT INTO `veicolo` (`targa`, `modello`, `marca`, `altezza`, `peso`, `anno`, `n_assi`, `carrello`, `n_assi_carrello`, `emissioni_Co2`, `emissioni_decibel`) VALUES
('AA000AA', 'kuga', 'ford', 1.6, 1600, 2014, 2, 0, NULL, NULL, NULL),
('AA001AA', 'panda', 'fiat', 1.63, 1300, 2010, 2, 1, 1, NULL, NULL),
('AA011AA', 'q3', 'audi', 1.68, 1700, 2017, 2, 0, NULL, NULL, NULL),
('AA111AA', 'model s', 'tesla', 1.5, 2500, 2018, 2, 0, NULL, NULL, NULL),
('AA112AA', 'fiesta', 'ford', 1.58, 1500, 2000, 2, 0, NULL, NULL, NULL),
('AA122AA', 'tipo', 'fiat', 1.63, 1800, 2018, 2, 0, NULL, NULL, NULL),
('AA222AA', 'vitara', 'suzuki', 1.9, 2200, 1999, 2, 1, 1, NULL, NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autostrada`
--
ALTER TABLE `autostrada`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `biglietto`
--
ALTER TABLE `biglietto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `targa` (`targa`),
  ADD KEY `ingresso` (`ingresso`);

--
-- Indici per le tabelle `casello`
--
ALTER TABLE `casello`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`autostrada`),
  ADD KEY `nome` (`nome`);

--
-- Indici per le tabelle `tariffa`
--
ALTER TABLE `tariffa`
  ADD PRIMARY KEY (`autostrada`);

--
-- Indici per le tabelle `veicolo`
--
ALTER TABLE `veicolo`
  ADD PRIMARY KEY (`targa`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autostrada`
--
ALTER TABLE `autostrada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT per la tabella `biglietto`
--
ALTER TABLE `biglietto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `casello`
--
ALTER TABLE `casello`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT per la tabella `tariffa`
--
ALTER TABLE `tariffa`
  MODIFY `autostrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `tariffa`
--
ALTER TABLE `tariffa`
  ADD CONSTRAINT `idautostrada` FOREIGN KEY (`autostrada`) REFERENCES `autostrada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
