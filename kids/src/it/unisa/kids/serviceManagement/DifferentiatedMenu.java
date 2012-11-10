package it.unisa.kids.serviceManagement;

public class DifferentiatedMenu {

	private String id;
	private String motivation;
	private String first;
	private String second;
	private String sideDish;
	private String fruit;
	private String childInscriptionId;

	public DifferentiatedMenu() {

	}

	public DifferentiatedMenu(String id, String motivation, String first,
			String second, String sideDish, String fruit,
			String childInscriptionId) {
		setId(id);
		setMotivation(motivation);
		setFirst(first);
		setSecond(second);
		setSideDish(sideDish);
		setFruit(fruit);
		setChildInscriptionId(childInscriptionId);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getSideDish() {
		return sideDish;
	}

	public void setSideDish(String sideDish) {
		this.sideDish = sideDish;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
	}

	public String getChildInscriptionId() {
		return childInscriptionId;
	}

	public void setChildInscriptionId(String childInscriptionId) {
		this.childInscriptionId = childInscriptionId;
	}
}
