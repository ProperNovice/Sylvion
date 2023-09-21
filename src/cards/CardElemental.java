package cards;

import interfaces.IStrengthAble;
import utils.Logger;

public class CardElemental extends CardRavage implements IStrengthAble {

	private int strength;

	public CardElemental(int strength) {

		this.strength = strength;

		super.createCard();

	}

	@Override
	protected final void printCredentials() {

		Logger.INSTANCE.log("strength -> " + this.strength);

	}

	@Override
	public final int getStrength() {
		return this.strength;
	}

	@Override
	protected final String getFilePath() {
		return super.getFilePath() + getFolder() + this.strength + ".jpg";
	}

	protected String getFolder() {
		return "elemental/";
	}

}
