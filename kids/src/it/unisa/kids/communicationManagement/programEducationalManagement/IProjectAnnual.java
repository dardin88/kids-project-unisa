package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProjectAnnual extends IProgramEducational
{
	abstract void insertProjectAnnual(AnnualProject pAnnualProject) throws SQLException;
	abstract ArrayList<AnnualProject> showProjectAnnual();
	

}
