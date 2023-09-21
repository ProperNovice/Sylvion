package cards;

public class CardElementalBlazing extends CardElemental {

	public CardElementalBlazing(int strength) {
		super(strength);
	}

	@Override
	protected String getFolder() {
		return super.getFolder() + "blazing/";
	}

}
