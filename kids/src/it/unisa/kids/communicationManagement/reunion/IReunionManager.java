package it.unisa.kids.communicationManagement.reunion;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;

public interface IReunionManager extends IManager
{
	abstract void insert(Reunion reunion) throws ErroreNeiDati, SQLException;
	abstract void delete(Reunion reunion) throws SQLException;
	abstract void modify(Reunion reunion)throws SQLException;
}
