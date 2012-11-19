package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDailyActivitySection extends IProgramEducational 
{
	abstract void insertDailyActivitySection(DailyActivitySection pProject) throws SQLException;
	abstract ArrayList<DailyActivitySection> showDailyActivitySection() throws SQLException;

}
