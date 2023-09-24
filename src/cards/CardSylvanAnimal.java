package cards;

import enums.ECardSylvanAnimal;
import interfaces.IAnimal;
import interfaces.ICostAble;
import utils.Logger;

public class CardSylvanAnimal extends CardSylvan implements ICostAble, IAnimal {

	private ECardSylvanAnimal eCardSylvanAnimal = null;

	public CardSylvanAnimal(ECardSylvanAnimal eCardSylvanAnimal) {

		this.eCardSylvanAnimal = eCardSylvanAnimal;

		super.createCard();

	}

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log(this.eCardSylvanAnimal.toString().toLowerCase());
		Logger.INSTANCE.log("cost -> " + getCost());

	}

	@Override
	public int getCost() {
		return this.eCardSylvanAnimal.getCost();
	}

	@Override
	public ECardSylvanAnimal getECardSylvanAnimal() {
		return this.eCardSylvanAnimal;
	}

	@Override
	protected String getFilePathFront() {
		return super.getFilePathFront() + "animal/" + this.eCardSylvanAnimal.toString().toLowerCase()
				+ ".jpg";
	}

}
