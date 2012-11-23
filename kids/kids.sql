-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generato il: Nov 10, 2012 alle 13:52
-- Versione del server: 5.5.25a
-- Versione PHP: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kids`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nickname` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Cognome` varchar(25) NOT NULL,
  `Nome` varchar(25) NOT NULL,
  `Email` varchar(35) NOT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Cellulare` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `DataNascita` date NOT NULL,
  `ComuneNascita` varchar(20) NOT NULL,
  `CodiceFiscale` char(16) NOT NULL,
  `Cittadinanza` varchar(20) NOT NULL,
  `ResidenzaIndirizzo` varchar(30) NOT NULL,
  `ResidenzaComune` varchar(20) NOT NULL,
  `ResidenzaProvincia` varchar(20) NOT NULL,
  `ResidenzaCap` varchar(10) NOT NULL,
  `DomicilioIndirizzo` varchar(30) NOT NULL,
  `DomicilioComune` varchar(20) NOT NULL,
  `DomicilioProvincia` varchar(20) NOT NULL,
  `DomicilioCap` varchar(10) NOT NULL,
  `TitoloStudio` varchar(30) DEFAULT NULL,
  `SituazioneFamiliare` varchar(15) DEFAULT NULL,
  `Reddito` double DEFAULT NULL,
  `TipologiaAccount` enum('Admin','Delegato Ufficio','Genitore','Educatore','Delegato scienze della formazione','Coordinatore Psicopedagogico','Responsabile Scientifico') NOT NULL,
  `TipologiaGenitore` enum('Studente','Tecnico amministrativo','Docente','Ricercatore','Determinato','Dottorando') DEFAULT NULL,
  `DataScadenzaContratto` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `attivita`
--

CREATE TABLE IF NOT EXISTS `attivita` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Periodo` varchar(20) NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `attivita_giornaliera_per_sezione`
--

CREATE TABLE IF NOT EXISTS `attivita_giornaliera_per_sezione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdAttivita` int(11) NOT NULL,
  `IdEducatore` int(11) NOT NULL,
  `Note` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `attivita_tirocinante`
--

CREATE TABLE IF NOT EXISTS `attivita_tirocinante` (
  `Data` date NOT NULL DEFAULT '0000-00-00',
  `Nome` varchar(25) NOT NULL DEFAULT '',
  `Descrizione` varchar(200) DEFAULT NULL,
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
  `Delegato` int(11) NOT NULL,
  PRIMARY KEY (`Data`,`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `commento`
--

CREATE TABLE IF NOT EXISTS `commento` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `TipoModifica` varchar(40) NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  `IdAnnuale` int(11) NOT NULL,
  `IdSezione` int(11) NOT NULL,
  `IdAutore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `comunicazioni`
--

CREATE TABLE IF NOT EXISTS `comunicazioni` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` int(11) NOT NULL,
  `IdEducatore` int(11) NOT NULL,
  `IdBambino` int(11) NOT NULL,
  `Contenuto` varchar(500) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `esito`
--

CREATE TABLE IF NOT EXISTS `esito` (
  `Iscrizione` int(11) NOT NULL,
  `Graduatoria` int(11) NOT NULL,
  `Esito` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `graduatoria`
--

CREATE TABLE IF NOT EXISTS `graduatoria` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `DataScadenza` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `iscrizionebambino`
--

CREATE TABLE IF NOT EXISTS `iscrizionebambino` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Cognome` varchar(25) NOT NULL,
  `Nome` varchar(25) NOT NULL,
  `DataNascita` date NOT NULL,
  `ComuneNascita` varchar(20) NOT NULL,
  `CodiceFiscale` char(16) NOT NULL,
  `Cittadinanza` varchar(20) NOT NULL,
  `FasciaUtenza` enum('full time','part time pomeridiana','part time mattutina') NOT NULL,
  `DataIscrizione` date NOT NULL,
  `Malattia` text,
  `FaseDellIscrizione` enum('registrata','confermata','accettata','eliminata','rinunciata','ricorso') NOT NULL DEFAULT 'registrata',
  `AccountGenitore` int(11) NOT NULL,
  `Classe` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `menu_differenziato`
--

CREATE TABLE IF NOT EXISTS `menu_differenziato` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Motivazione` varchar(200) NOT NULL,
  `Primo` varchar(200) DEFAULT NULL,
  `Secondo` varchar(200) DEFAULT NULL,
  `Contorno` varchar(200) DEFAULT NULL,
  `Frutta` varchar(200) DEFAULT NULL,
  `Iscrizione` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(40) NOT NULL,
  `Contenuto` varchar(200) NOT NULL,
  `Data` date NOT NULL,
  `IdAutore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `pagamento`
--

CREATE TABLE IF NOT EXISTS `pagamento` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DataScadenza` date NOT NULL,
  `Addebito` tinyint(1) DEFAULT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Sconto` varchar(10) DEFAULT NULL,
  `DescrizioneSconto` varchar(200) DEFAULT NULL,
  `Importo` double NOT NULL,
  `ImportoDovuto` double NOT NULL,
  `Effettuato` tinyint(1) NOT NULL,
  `Conto` varchar(30) NOT NULL,
  `Beneficiario` varchar(30) NOT NULL,
  `AccountGenitore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipa`
--

CREATE TABLE IF NOT EXISTS `partecipa` (
  `GiornoAttivita` date NOT NULL DEFAULT '0000-00-00',
  `NomeAttivita` varchar(25) NOT NULL DEFAULT '',
  `Tirocinante` char(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`GiornoAttivita`,`NomeAttivita`,`Tirocinante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `partecipazione`
--

CREATE TABLE IF NOT EXISTS `partecipazione` (
  `IdBambino` int(11) NOT NULL DEFAULT '0',
  `IdAttivitaGiornaliera` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdBambino`,`IdAttivitaGiornaliera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `progetto_annuale`
--

CREATE TABLE IF NOT EXISTS `progetto_annuale` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(40) NOT NULL,
  `Tema` varchar(200) NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  `AnnoApplicazione` varchar(10) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `progetto_sezione`
--

CREATE TABLE IF NOT EXISTS `progetto_sezione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(40) NOT NULL,
  `Sezione` varchar(2) NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  `IdAnnuale` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta`
--

CREATE TABLE IF NOT EXISTS `richiesta` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Ora` time NOT NULL,
  `AccountGenitore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `ricorso`
--

CREATE TABLE IF NOT EXISTS `ricorso` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Motivo` text NOT NULL,
  `Valutazione` tinyint(1) NOT NULL,
  `Iscrizione` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `rimborso`
--

CREATE TABLE IF NOT EXISTS `rimborso` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Importo` double NOT NULL,
  `Genitore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `rinunce`
--

CREATE TABLE IF NOT EXISTS `rinunce` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Conferma` tinyint(1) NOT NULL,
  `Iscrizione` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `riunione`
--

CREATE TABLE IF NOT EXISTS `riunione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Luogo` varchar(40) NOT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `tirocinante`
--

CREATE TABLE IF NOT EXISTS `tirocinante` (
  `Matricola` char(10) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `DataNascita` date NOT NULL,
  `CittaNascita` varchar(25) NOT NULL,
  `CittaResidenza` varchar(25) NOT NULL,
  `Indirizzo` varchar(40) NOT NULL,
  `CAP` int(11) NOT NULL,
  `NumeroTelefenico` int(11) DEFAULT NULL,
  `AccountDelegato` int(11) NOT NULL,
  PRIMARY KEY (`Matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
