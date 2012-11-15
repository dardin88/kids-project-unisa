package it.unisa.kids.serviceManagement.trainingManagement;

import it.unisa.kids.common.AbstractManager;


public class RefinedAbstractTrainingManager extends AbstractManager<ITrainingManager>{

	public ITrainingManager getManagerImplementor() {
		this.imp=JDBCTrainingManager.getInstance();
		return this.imp;
	}

}
