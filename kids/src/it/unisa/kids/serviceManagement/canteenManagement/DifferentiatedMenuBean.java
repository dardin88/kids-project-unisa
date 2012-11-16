package it.unisa.kids.serviceManagement.canteenManagement;

import java.io.Serializable;

public class DifferentiatedMenuBean implements Serializable {

	private int id;
	private String motivation;
	private String first;
	private String second;
	private String sideDish;
	private String fruit;
	private int childInscriptionId;

	public DifferentiatedMenuBean() {

	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int pId) {
		this.id = pId;
	}

	public String getMotivation() {
		return this.motivation;
	}

	public void setMotivation(String pMotivation) {
		this.motivation = pMotivation;
	}

	public String getFirst() {
		return this.first;
	}

	public void setFirst(String pFirst) {
		this.first = pFirst;
	}

	public String getSecond() {
		return this.second;
	}

	public void setSecond(String pSecond) {
		this.second = pSecond;
	}

	public String getSideDish() {
		return this.sideDish;
	}

	public void setSideDish(String pSideDish) {
		this.sideDish = pSideDish;
	}

	public String getFruit() {
		return this.fruit;
	}

	public void setFruit(String pFruit) {
		this.fruit = pFruit;
	}

	public int getChildInscriptionId() {
		return this.childInscriptionId;
	}

	public void setChildInscriptionId(int pChildInscriptionId) {
		this.childInscriptionId = pChildInscriptionId;
	}
}
