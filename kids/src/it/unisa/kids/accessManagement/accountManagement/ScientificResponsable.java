package it.unisa.kids.accessManagement.accountManagement;

import it.unisa.kids.communicationManagement.programEducationalManagement.AnnualProject;
import it.unisa.kids.communicationManagement.programEducationalManagement.AnnualProjectSection;
import it.unisa.kids.communicationManagement.programEducationalManagement.CommentoBean;
import it.unisa.kids.communicationManagement.programEducationalManagement.IProgramEducational;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScientificResponsable extends Account implements IProgramEducational {

	public ScientificResponsable() {
		// TODO Auto-generated constructor stub
	}

	
	public void modifySubstantialProject(AnnualProject pProject)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void modifyLittleProject(AnnualProject pProject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
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

	
	public void approvedChangesProposed() throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void rejectChangedProposed() throws SQLException {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void insertComment(CommentoBean toAdd) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AnnualProjectSection getProgramEducational(int SectionId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insertProgramEducational(AnnualProjectSection pAnnual) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateProgramEducational(AnnualProjectSection toUpdate) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateProgramEducational(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int insertPathProject(AnnualProject project) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<AnnualProject> show() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
