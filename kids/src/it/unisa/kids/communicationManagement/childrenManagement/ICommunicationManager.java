package it.unisa.kids.communicationManagement.childrenManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ICommunicationManager extends IManager{
	abstract void insertCommunication(Communication pCommunication) throws SQLException;
	abstract ArrayList<Communication> showCommunication()throws SQLException ;
	//abstract void solvedCommunication(Communication pCommunication)throws SQLException;
        abstract void solvedCommunication(int id, String solved)throws SQLException;
        abstract ArrayList<Communication> searchCommunication(String word)throws SQLException;
        abstract int getIdChild(String name, String surname) throws SQLException;
}
