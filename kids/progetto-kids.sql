-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generato il: Dic 11, 2012 alle 18:04
-- Versione del server: 5.5.27
-- Versione PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progetto-kids`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nickname` varchar(30) DEFAULT NULL,
  `Password` varchar(30) DEFAULT NULL,
  `Cognome` varchar(25) DEFAULT NULL,
  `Nome` varchar(25) DEFAULT NULL,
  `Email` varchar(35) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `Cellulare` varchar(20) DEFAULT NULL,
  `Fax` varchar(20) DEFAULT NULL,
  `DataNascita` date DEFAULT NULL,
  `ComuneNascita` varchar(20) DEFAULT NULL,
  `CodiceFiscale` char(16) DEFAULT NULL,
  `Cittadinanza` varchar(20) DEFAULT NULL,
  `ResidenzaIndirizzo` varchar(30) DEFAULT NULL,
  `ResidenzaComune` varchar(20) DEFAULT NULL,
  `ResidenzaProvincia` varchar(20) DEFAULT NULL,
  `ResidenzaCap` varchar(10) DEFAULT NULL,
  `DomicilioIndirizzo` varchar(30) DEFAULT NULL,
  `DomicilioComune` varchar(20) DEFAULT NULL,
  `DomicilioProvincia` varchar(20) DEFAULT NULL,
  `DomicilioCap` varchar(10) DEFAULT NULL,
  `TitoloStudio` varchar(30) DEFAULT NULL,
  `SituazioneFamiliare` varchar(15) DEFAULT NULL,
  `Reddito` double DEFAULT NULL,
  `TipologiaGenitore` enum('Studente','Tecnico amministrativo','Docente','Ricercatore','Determinato','Dottorando','Nothing') DEFAULT NULL,
  `DataScadenzaContratto` date DEFAULT NULL,
  `Facolta` varchar(50) DEFAULT NULL,
  `DataRegistrazione` date DEFAULT NULL,
  `TipologiaAccount` enum('Admin','Segreteria','Genitore','Educatore','Delegato scienze della formazione','Coordinatore Psicopedagogico','Responsabile Scientifico','Tirocinante','Responsabile Mensa','Delegato del rettore','Responsabile Asilo') DEFAULT NULL,
  `Matricola` char(11) DEFAULT NULL,
  `Stato` enum('Bozza','Inserito') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Nickname` (`Nickname`),
  UNIQUE KEY `Matricola` (`Matricola`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dump dei dati per la tabella `account`
--

INSERT INTO `account` (`Id`, `Nickname`, `Password`, `Cognome`, `Nome`, `Email`, `Telefono`, `Cellulare`, `Fax`, `DataNascita`, `ComuneNascita`, `CodiceFiscale`, `Cittadinanza`, `ResidenzaIndirizzo`, `ResidenzaComune`, `ResidenzaProvincia`, `ResidenzaCap`, `DomicilioIndirizzo`, `DomicilioComune`, `DomicilioProvincia`, `DomicilioCap`, `TitoloStudio`, `SituazioneFamiliare`, `Reddito`, `TipologiaGenitore`, `DataScadenzaContratto`, `Facolta`, `DataRegistrazione`, `TipologiaAccount`, `Matricola`, `Stato`) VALUES
(1, 's', 's', 'Super', 'Sajan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Segreteria', NULL, NULL),
(2, 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Admin', NULL, NULL),
(4, 'g', 'g', 'Landi', 'Pierluigi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Genitore', NULL, NULL),
(5, 'g2', 'g2', 'Poni', 'Carmela', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Genitore', NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `assegnazione`
--

CREATE TABLE IF NOT EXISTS `assegnazione` (
  `Classe` int(11) NOT NULL,
  `Educatore` int(11) NOT NULL,
  PRIMARY KEY (`Classe`,`Educatore`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `attivita`
--

CREATE TABLE IF NOT EXISTS `attivita` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(40) NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  `DataInizio` date NOT NULL,
  `DataFine` date NOT NULL,
  `IdClasse` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


-- --------------------------------------------------------

--
-- Struttura della tabella `attivita_giornaliera_per_sezione`
--

CREATE TABLE IF NOT EXISTS `attivita_giornaliera_per_sezione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdAttivita` int(11) NOT NULL,
  `IdEducatore` int(11) NOT NULL,
  `Note` varchar(1500) DEFAULT NULL,
  `IdClasse` int(11) NOT NULL,
  `Data` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;


-- --------------------------------------------------------

--
-- Struttura della tabella `attivita_tirocinante`
--

CREATE TABLE IF NOT EXISTS `attivita_tirocinante` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Nome` varchar(25) NOT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
  `Delegato` int(11) NOT NULL,
  `Tirocinante` int(11) NOT NULL,
  `Giudizio` varchar(200) ,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `classe`
--

CREATE TABLE IF NOT EXISTS `classe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(15) NOT NULL,
  `Stato` enum('bozza','sottomessa','accettata','revisione') DEFAULT 'bozza',
  `StatoProgetto` enum('Bozza','Sottomessa','RichiestaMod','AccettaRett', 'AccettaScient') DEFAULT 'Bozza',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `commento`
--

CREATE TABLE IF NOT EXISTS `commento` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Contenuto` varchar(1500) NOT NULL,
  `IdAnnuale` int(11) NOT NULL,
  `IdSezione` int(11) NOT NULL,
  `IdAutore` int(11) NOT NULL,
  `Ora` time NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `comunicazioni`
--

CREATE TABLE IF NOT EXISTS `comunicazioni` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdEducatore` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Descrizione` varchar(2000) NOT NULL,
  `idBambino` int(11) NOT NULL,
  `Tipo` enum('Salute','Bisogno') DEFAULT 'Salute',
  `Risolto` enum('Si','No') DEFAULT 'No',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `convocazione`
--

CREATE TABLE IF NOT EXISTS `convocazione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
  `NomeAttivita` varchar(50) NOT NULL,
  `Confermato` tinyint(1) DEFAULT '0',
  `IdDelegato` int(11) NOT NULL,
  `IdTirocinante` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `criteripesati`
--

CREATE TABLE IF NOT EXISTS `criteripesati` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(40) NOT NULL,
  `TabellaDbDelCampo` varchar(20) NOT NULL,
  `CampoDb` varchar(20) NOT NULL,
  `Operando` varchar(5) NOT NULL,
  `Condizione` varchar(20) NOT NULL,
  `Peso` double NOT NULL,
  `Abilitato` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `criteripesati`
--

INSERT INTO `criteripesati` (`Id`, `Descrizione`, `TabellaDbDelCampo`, `CampoDb`, `Operando`, `Condizione`, `Peso`, `Abilitato`) VALUES
(1, 'Nati prima del 2009 (esempio)', 'iscrizionebambino', 'DataNascita', '<', '2009-01-01', -100, 1),
(2, 'Reddito inferiore a 1.000 (esempio)', 'account', 'Reddito', '<', '1000', 10, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `esito`
--

CREATE TABLE IF NOT EXISTS `esito` (
  `Graduatoria` int(11) NOT NULL,
  `Iscrizione` int(11) NOT NULL,
  `Punteggio` double DEFAULT '0',
  `Esito` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Iscrizione`,`Graduatoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `esito`
--

INSERT INTO `esito` (`Graduatoria`, `Iscrizione`, `Punteggio`, `Esito`) VALUES
(9, 1, 0, 1),
(10, 2, 0, 1),
(11, 3, 0, 1),
(12, 5, 0, 0),
(15, 5, 0, 0),
(15, 6, 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `graduatoria`
--

CREATE TABLE IF NOT EXISTS `graduatoria` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) DEFAULT NULL,
  `Data` date NOT NULL,
  `Stato` enum('bozza','provvisoria','definitiva') DEFAULT 'bozza',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `graduatoria`
--

INSERT INTO `graduatoria` (`Id`, `Nome`, `Data`, `Stato`) VALUES
(9, 'modificata', '2012-12-12', 'definitiva'),
(10, 'senior', '2012-12-12', 'provvisoria'),
(11, 'nuova', '2012-12-13', 'bozza'),
(12, 'nuovad', '2012-12-14', 'bozza'),
(15, 'prova', '2012-12-21', 'definitiva');

-- --------------------------------------------------------

--
-- Struttura della tabella `iscrizionebambino`
--

CREATE TABLE IF NOT EXISTS `iscrizionebambino` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AccountGenitore` int(11) NOT NULL,
  `DataIscrizione` date NOT NULL,
  `FaseDellIscrizione` enum('bozza','sottomessa','ricevuta','eliminata','ricorso','accettata','completata','validata','rinunciata') NOT NULL DEFAULT 'bozza',
  `Cognome` varchar(25) DEFAULT NULL,
  `Nome` varchar(25) DEFAULT NULL,
  `DataNascita` date DEFAULT NULL,
  `ComuneNascita` varchar(20) DEFAULT NULL,
  `CodiceFiscale` char(16) DEFAULT NULL,
  `Cittadinanza` varchar(20) DEFAULT NULL,
  `FasciaUtenza` enum('','full_time','part_time_pomeridiana','part_time_mattutina') DEFAULT NULL,
  `Malattie` text,
  `Vaccinazioni` text,
  `DichiarazioneDellaPrivacy` text,
  `NoteAggiuntive` text,
  `IsSetMalattie` enum('si','no','in_parte') DEFAULT 'no',
  `IsSetVaccinazioni` enum('si','no','in_parte') DEFAULT 'no',
  `IsSetDichiarazioneDellaPrivacy` enum('si','no','in_parte') DEFAULT 'no',
  `Classe` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dump dei dati per la tabella `iscrizionebambino`
--

INSERT INTO `iscrizionebambino` (`Id`, `AccountGenitore`, `DataIscrizione`, `FaseDellIscrizione`, `Cognome`, `Nome`, `DataNascita`, `ComuneNascita`, `CodiceFiscale`, `Cittadinanza`, `FasciaUtenza`, `Malattie`, `Vaccinazioni`, `DichiarazioneDellaPrivacy`, `NoteAggiuntive`, `IsSetMalattie`, `IsSetVaccinazioni`, `IsSetDichiarazioneDellaPrivacy`, `Classe`) VALUES
(1, 4, '2012-12-12', 'rinunciata', 'Landi', 'Giovanni', '2011-07-14', 'Baronissi', 'LNDGVN00T03H703D', 'Italiana', 'full_time', '', '', 'si', NULL, 'no', 'no', 'si', 0),
(2, 4, '2012-12-12', 'validata', 'Landi', 'Veronica', '2010-06-07', 'Baronissi', 'LNDVRN10T05H704E', 'Italiana', 'part_time_mattutina', '', 'Tetano', 'si', NULL, 'in_parte', 'si', 'si', 0),
(3, 5, '2012-12-12', 'validata', 'Poni', 'Little', '2011-12-18', 'Fisciano', 'ponilittle180120', 'Italiana', 'part_time_pomeridiana', 'Nessuna', 'Vaccinazioni di base', 'si', NULL, 'no', 'no', 'no', 0),
(4, 5, '2012-12-12', 'validata', 'Poni', 'Conny', '2009-08-16', 'Fisciano', '20090916poniconn', 'Italiana', 'part_time_pomeridiana', '', '', '', NULL, 'no', 'no', 'no', 0),
(5, 4, '2012-12-14', 'ricorso', 'Citro', 'Alfredo', '2008-09-03', 'salerno', 'CTRLRD00T03H703D', 'italiana', 'full_time', '', '', '', NULL, NULL, NULL, NULL, 0),
(6, 4, '2012-12-18', 'ricevuta', 'prova', 'prova', '2012-12-11', 'stampa', 'stampa', 'stampa', 'full_time', '', '', '', NULL, NULL, NULL, NULL, 0),
(7, 4, '2012-12-19', 'sottomessa', 'afad', 'sdfsdg', '2012-12-13', 'afda', 'sdgf', 'sdgsd', 'full_time', '', '', '', NULL, NULL, NULL, NULL, 0),
(10, 4, '2012-12-21', 'eliminata', 'bozza', 'bozza', '2012-12-04', 'bozza', 'bozza', 'bozza', 'full_time', '', '', '', NULL, NULL, NULL, NULL, 0),
(11, 4, '2012-12-21', 'eliminata', 'bozza', 'bozza', '2012-12-07', 'bozza', 'bozza', 'bozza', 'full_time', '', '', '', NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `menu_mensa`
--

CREATE TABLE IF NOT EXISTS `menu_mensa` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` enum('Giornaliero','Differenziato') DEFAULT NULL,
  `Data` date NOT NULL,
  `Primo` varchar(200) DEFAULT NULL,
  `Secondo` varchar(200) DEFAULT NULL,
  `Contorno` varchar(200) DEFAULT NULL,
  `Frutta` varchar(200) DEFAULT NULL,
  `IscrizioneBambinoId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(40) NOT NULL,
  `contenuto` varchar(5000) DEFAULT NULL,
  `Data` date NOT NULL,
  `IdAutore` int(11) NOT NULL,
  `Titolo` varchar(20) DEFAULT NULL,
  `Ora` time DEFAULT NULL,
  `Allegato` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `pagamento`
--

CREATE TABLE IF NOT EXISTS `pagamento` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DataScadenza` date NOT NULL,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Sconto` double DEFAULT '0',
  `DescrizioneSconto` varchar(200) DEFAULT NULL,
  `Importo` double NOT NULL,
  `Effettuato` tinyint(1) NOT NULL DEFAULT '0',
  `Conto` varchar(30) DEFAULT NULL,
  `Beneficiario` varchar(30) NOT NULL,
  `AccountGenitore` int(11) NOT NULL,
  `CodiceRicevuta` varchar(50) DEFAULT NULL,
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
  `Path` varchar(40) NOT NULL,
  `Stato` varchar(50) NOT NULL,
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
  `Scadenza` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `questionario`
--

CREATE TABLE IF NOT EXISTS `questionario` (
  `idQuestionario` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idQuestionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `questionariocompilato`
--

CREATE TABLE IF NOT EXISTS `questionariocompilato` (
  `idQuestionario` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `compilato` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `registro_scolastico`
--

CREATE TABLE IF NOT EXISTS `registro_scolastico` (
  `IdBambino` int(11) NOT NULL DEFAULT '0',
  `IdAttivitaGiornaliera` int(11) NOT NULL DEFAULT '0',
  `Stato` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdBambino`,`IdAttivitaGiornaliera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_modifica_orari_servizio`
--

CREATE TABLE IF NOT EXISTS `richiesta_modifica_orari_servizio` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Motivazione` varchar(2000) DEFAULT NULL,
  `FasciaUtenza` enum('full_time','part_time_pomeridiana','part_time_mattutina') NOT NULL,
  `IdBambino` int(11) NOT NULL,
  `AccountGenitore` int(11) NOT NULL,
  `Stato` enum('Sottomessa','Accettata','Rifiutata','Validata') DEFAULT 'Sottomessa',
  `Valutazione` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_orari_servizio`
--

CREATE TABLE IF NOT EXISTS `richiesta_orari_servizio` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoServizio` varchar(200) NOT NULL,
  `Confermato` int(1) DEFAULT '0',
  `GiornoModifica` varchar(20) NOT NULL,
  `AccountGenitore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_pasto`
--

CREATE TABLE IF NOT EXISTS `richiesta_pasto` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Data` date NOT NULL,
  `Soddisfatta` tinyint(1) NOT NULL DEFAULT '0',
  `AccountGenitore` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `richiesta_tirocinanti`
--

CREATE TABLE IF NOT EXISTS `richiesta_tirocinanti` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NumeroTirocinanti` int(11) NOT NULL,
  `Delegato` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Attivita` varchar(30) NOT NULL DEFAULT '',
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
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
  `Valutazione` enum('rifiutato','accettato','davalutare') NOT NULL DEFAULT 'davalutare',
  `Iscrizione` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `ricorso`
--

INSERT INTO `ricorso` (`Id`, `Data`, `Motivo`, `Valutazione`, `Iscrizione`) VALUES
(4, '2012-12-21', 'provo', 'davalutare', 5),
(5, '2012-12-21', 'perchè ancora non è stata accettata?', 'rifiutato', 5),
(6, '2012-12-21', 'perchè?', 'rifiutato', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `rimborso`
--

CREATE TABLE IF NOT EXISTS `rimborso` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(200) DEFAULT NULL,
  `Importo` double NOT NULL DEFAULT '0',
  `Genitore` int(11) NOT NULL,
  `Effettuato` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struttura della tabella `rinunce`
--

CREATE TABLE IF NOT EXISTS `rinunce` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdIscrizione` int(11) NOT NULL,
  `Data` date NOT NULL,
  `Motivazione` varchar(50),
  `Conferma` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dump dei dati per la tabella `rinunce`
--

INSERT INTO `rinunce` (`Id`, `IdIscrizione`, `Data`, `Motivazione`, `Conferma`) VALUES
(14, 1, '2012-12-16', 'Trasferimento in altro asilo', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `riunione`
--

CREATE TABLE IF NOT EXISTS `riunione` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Descrizione` varchar(500) DEFAULT NULL,
  `Data` date NOT NULL,
  `Titolo` varchar(200) DEFAULT NULL,
  `OraInizio` time DEFAULT NULL,
  `OraFine` time DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
  `Stato` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

INSERT INTO `news` (`Id`,`Tipo`,`contenuto`,`Data`,`IdAutore`,`Ora`) VALUES (1,'OrarioDiServizio','','2012-12-13',1,'00:00:00');
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
