package cards;

import interfaces.ICostAble;
import interfaces.IStrengthAble;
import utils.Logger;

public class CardSylvanFountain extends CardSylvan implements ICostAble, IStrengthAble {

	private int cost, strength;

	public CardSylvanFountain(int cost) {

		this.cost = cost;
		this.strength = this.cost + 1;

		super.createCard();

	}

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log("cost -> " + this.cost);
		Logger.INSTANCE.log("strength -> " + this.strength);

	}

	@Override
	public int getStrength() {
		return this.strength;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	protected String getFilePathFront() {
		return super.getFilePathFront() + "fountain/" + this.cost + ".jpg";
	}

}
