package enums;

public enum ECardSylvanAnimal {

	DOVES(1), ELEPHANT(1), FISH(1), HEDGEHOG(0), OWL(1), SQUIRRELS(1), STAG(0), WHALE(0);

	private int cost;

	private ECardSylvanAnimal(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return this.cost;
	}

}
