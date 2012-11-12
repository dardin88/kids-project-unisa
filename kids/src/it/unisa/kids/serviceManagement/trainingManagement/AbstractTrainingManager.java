package it.unisa.kids.serviceManagement.trainingManagement;


public abstract class AbstractTrainingManager {
	protected ITrainingManager imp;
	
	public abstract ITrainingManager getTrainingManagerImplementor();
}
