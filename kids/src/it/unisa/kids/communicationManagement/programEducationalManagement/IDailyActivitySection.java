package it.unisa.kids.communicationManagement.programEducationalManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IDailyActivitySection 
{
	abstract void insertDailyActivitySection(DailyActivitySection pProject) throws SQLException;
	abstract DailyActivitySection showDailyActivitySection(GregorianCalendar data, int sectionId) throws SQLException;
        

}
