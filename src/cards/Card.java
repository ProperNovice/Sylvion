package cards;

import business.Credentials;
import utils.Flow;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.Logger;

public abstract class Card implements IImageViewAble {

	public Card() {

	}

	protected final void createCard() {

		new ImageView(getFilePathFront(), this);
		getImageView().setDimensions(Credentials.INSTANCE.dCard);
		getImageView().setBack(getFilePathBack());

	}

	public final void print() {

		Logger.INSTANCE.logNewLine("*/");
		Logger.INSTANCE.logNewLine("printing card");
		Logger.INSTANCE.logNewLine(this.getClass().getSimpleName());

		printCredentials();

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("/*");

	}

	@Override
	public void handleMousePressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleCardPressed(this);
	}

	protected void printCredentials() {

	}

	protected abstract String getFilePathFront();

	protected abstract String getFilePathBack();

}
