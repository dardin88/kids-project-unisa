package it.unisa.kids.serviceManagement.canteenManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.List;

public interface ICanteenManager extends IManager {

	public void insert(DifferentiatedMenuBean pDiffMenu) throws SQLException;
	public void update(DifferentiatedMenuBean pDiffMenu) throws SQLException;
	public void delete(DifferentiatedMenuBean pDiffMenu) throws SQLException;
	
	public List<DifferentiatedMenuBean> search(DifferentiatedMenuBean pDiffMenu)
			throws SQLException;
	public List<DifferentiatedMenuBean> getDiffMenuList() throws SQLException;
}
