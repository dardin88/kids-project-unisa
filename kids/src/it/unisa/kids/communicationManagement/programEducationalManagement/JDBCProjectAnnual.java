package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCProjectAnnual implements IProjectAnnual
{

	@Override
	public void modifySubstantialProject(AnnualProject pProject)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyLittleProject(AnnualProject pProject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String approveProject(AnnualProject pProject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyExpirationPlan(AnnualProject pProject)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> showNotifyExpirationPlan(AnnualProject pProject)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approvedChangesProposed() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectChangedProposed() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertProjectAnnual(AnnualProject pAnnualProject)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<AnnualProject> showProjectAnnual() {
		// TODO Auto-generated method stub
		return null;
	}

}
