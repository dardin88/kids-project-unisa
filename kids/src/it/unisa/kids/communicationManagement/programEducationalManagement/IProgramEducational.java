package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProgramEducational extends IManager
{
	abstract void modifySubstantialProject(AnnualProject pProject) throws SQLException;
	abstract void modifyLittleProject(AnnualProject pProject) throws SQLException;
	abstract String approveProject(AnnualProject pProject) throws SQLException;
	abstract void notifyExpirationPlan(AnnualProject pProject) throws SQLException;
	abstract ArrayList<String> showNotifyExpirationPlan(AnnualProject pProject) throws SQLException;
	abstract void approvedChangesProposed() throws SQLException;
	abstract void rejectChangedProposed() throws SQLException;

    abstract void insertComment(CommentoBean toAdd) throws SQLException;

}
