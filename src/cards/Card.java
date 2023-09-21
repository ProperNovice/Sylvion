package cards;

import managers.Credentials;
import utils.ImageView;
import utils.Interfaces.IImageViewAble;
import utils.Logger;

public abstract class Card implements IImageViewAble {

	public Card() {

	}

	protected final void createCard() {

		new ImageView(getFilePath(), this);
		getImageView().setDimensions(Credentials.INSTANCE.dCard);

	}

	public final void print() {

		Logger.INSTANCE.logNewLine("*/");
		Logger.INSTANCE.logNewLine("printing card");
		Logger.INSTANCE.logNewLine(this.getClass().getSimpleName());

		printCredentials();

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.logNewLine("/*");

	}

	protected void printCredentials() {

	}

	protected abstract String getFilePath();

}
