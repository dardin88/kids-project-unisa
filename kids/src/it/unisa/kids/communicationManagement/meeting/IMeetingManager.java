package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;

public interface IMeetingManager extends IManager
{
	abstract void insert(Meeting reunion) throws SQLException;
	abstract void delete(Meeting reunion) throws SQLException;
	abstract void update(Meeting reunion)throws SQLException;
}
