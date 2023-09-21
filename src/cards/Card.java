package cards;

import utils.Interfaces.IImageViewAble;

public abstract class Card implements IImageViewAble {

	public Card() {

	}

	protected abstract String getFileName();

}
