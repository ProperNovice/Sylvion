package cards;

public abstract class CardRavage extends Card {

	@Override
	protected String getFilePathFront() {
		return "ravage/";
	}

	@Override
	protected final String getFilePathBack() {
		return "ravage/back.jpg";
	}

}
