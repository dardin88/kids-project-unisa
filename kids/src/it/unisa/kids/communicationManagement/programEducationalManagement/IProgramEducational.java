package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.IManager;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProgramEducational extends IManager
{
        
        abstract void updateProgramEducational(AnnualProject pProject) throws SQLException;
        abstract int insertPathProject(AnnualProject project) throws SQLException;
        abstract ArrayList<AnnualProject> show() throws SQLException;
}
