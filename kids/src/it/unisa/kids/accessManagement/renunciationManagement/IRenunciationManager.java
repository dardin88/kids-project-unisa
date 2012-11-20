package it.unisa.kids.accessManagement.renunciationManagement;

import java.sql.SQLException;
import java.util.List;

public interface IRenunciationManager {
	public Renunciation create(Renunciation pWaiver) throws SQLException;
	public Renunciation update(Renunciation pWaiver) throws SQLException;
	public Renunciation delete(Renunciation pWaiver) throws SQLException;
	List<Renunciation> search(Renunciation pWaiver) throws SQLException;
}
