package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IProjectAnnualSection extends IProgramEducational
{
	abstract void insertProjectAnnualSection(AnnualProjectSection pProject) throws SQLException;
	abstract ArrayList<AnnualProjectSection> showProjectAnnualSection();
        abstract 

}
