package it.unisa.kids.accessManagement.renunciationManagement;

import it.unisa.kids.common.IManager;
import java.sql.SQLException;
import java.util.List;

public interface IRenunciationManager extends IManager{
	public void insert (Renunciation pRenunciation) throws SQLException;
	public void update(Renunciation pRenunciation) throws SQLException;
	public void delete(Renunciation pRenunciation) throws SQLException;
	List<Renunciation> search(Renunciation pRenunciation) throws SQLException;
}
