package cards;

public abstract class CardSylvan extends Card {

	@Override
	protected String getFilePathFront() {
		return "sylvan/";
	}

	@Override
	protected final String getFilePathBack() {
		return "sylvan/back.jpg";
	}

}
