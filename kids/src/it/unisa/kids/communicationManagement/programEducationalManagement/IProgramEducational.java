package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProgramEducational extends IManager
{
        
	abstract void notifyExpirationPlan(AnnualProject pProject) throws SQLException;
	abstract ArrayList<String> showNotifyExpirationPlan(AnnualProject pProject) throws SQLException;
        abstract void insertComment(CommentoBean toAdd) throws SQLException;
        abstract AnnualProjectSection getProgramEducational(int SectionId) throws SQLException;
        abstract void insertProgramEducational(AnnualProjectSection pAnnual  ) throws SQLException;
        abstract void updateProgramEducational(AnnualProjectSection toUpdate) throws SQLException;
}
