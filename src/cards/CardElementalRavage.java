package cards;

public class CardElementalRavage extends CardElemental {

	public CardElementalRavage(int strength) {
		super(strength);
	}

	@Override
	protected String getFolder() {
		return super.getFolder() + "ravage/";
	}

}
