package cards;

import interfaces.IValue;
import utils.Logger;

public class CardEdge extends Card implements IValue {

	private int value;

	public CardEdge(int value) {

		this.value = value;

		super.createCard();

		String back = getFilePathBack();
		back += this.value;
		back += ".jpg";
		getImageView().setBack(back);

	}

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log("value -> " + this.value);

	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	protected String getFilePathFront() {
		return "edge/bloom.jpg";
	}

	@Override
	protected String getFilePathBack() {
		return "edge/desolated/";
	}

}
