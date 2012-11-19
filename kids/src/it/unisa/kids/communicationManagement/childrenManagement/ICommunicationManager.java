package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICommunicationManager extends IManager{
	abstract void insertCommunication(Communication pCommunication) throws SQLException;
	abstract ArrayList<Communication> showCommunication()throws SQLException ;
	abstract void deleteCommunication(Communication pCommunication) throws SQLException;
	abstract void modifyCommunication(Communication pCommunication)throws SQLException;
}