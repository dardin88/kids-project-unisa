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
	
	
	public void insert(MenuBean pMenu) throws SQLException;
	public void update(MenuBean pMenu) throws SQLException;
	public void delete(MenuBean pMenu) throws SQLException;
	
	public List<MenuBean> search(MenuBean pMenu) throws SQLException;
	public List<MenuBean> getMenuList() throws SQLException;
}
