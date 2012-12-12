package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IRenunciationManager extends IManager{
	public boolean insert(Renunciation pRenunciation) throws SQLException;
	public boolean update(Renunciation pRenunciation) throws SQLException;
	public boolean delete(Renunciation pRenunciation) throws SQLException;
	List<Renunciation> search(Renunciation pRenunciation) throws SQLException;
}
