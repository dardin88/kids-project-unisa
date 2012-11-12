package it.unisa.kids.serviceManagement.trainingManagement;


public class RefinedAbstractTrainingManager extends AbstractTrainingManager{

	public ITrainingManager getTrainingManagerImplementor() {
		this.imp=JDBCTrainingManager.getInstance();
		return imp;
	}

}
