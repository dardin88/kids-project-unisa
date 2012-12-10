package it.unisa.kids.communicationManagement.meeting;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;

public interface IReunionManager extends IManager
{
	abstract void insert(Reunion reunion) throws ErroreNeiDati, SQLException;
	abstract void delete(Reunion reunion) throws SQLException;
	abstract void update(Reunion reunion)throws SQLException;
}
