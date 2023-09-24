package cards;

import interfaces.ICostAble;
import interfaces.IStrengthAble;
import interfaces.IVitalityPoints;
import utils.Logger;

public class CardSylvanTree extends CardSylvan
		implements ICostAble, IStrengthAble, IVitalityPoints {

	private int cost, strength, vitalityPoints;

	public CardSylvanTree(int cost) {

		this.cost = cost;
		this.strength = 0;
		this.vitalityPoints = this.cost + 1;

		super.createCard();

	}

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log("cost -> " + this.cost);
		Logger.INSTANCE.log("strength -> " + this.strength);
		Logger.INSTANCE.log("vitality points -> " + this.vitalityPoints);

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
	public int getVitalityPoints() {
		return this.vitalityPoints;
	}

	@Override
	protected String getFilePathFront() {
		return super.getFilePathFront() + "tree/" + this.cost + ".jpg";
	}

}
