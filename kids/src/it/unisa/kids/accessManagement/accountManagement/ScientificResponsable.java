package it.unisa.kids.accessManagement.accountManagement;

import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.kids.communicationManagement.programEducationalManagement.AnnualProject;
import it.unisa.kids.communicationManagement.programEducationalManagement.IProgramEducational;

public class ScientificResponsable extends Account implements IProgramEducational {

	public ScientificResponsable() {
		// TODO Auto-generated constructor stub
	}

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

}
