package business;

import utils.Flow;
import utils.Interfaces.ISelectCoordinatesAble;
import utils.Vector2;

public enum CardPositionEdges implements ISelectCoordinatesAble {

	INSTANCE;

	@Override
	public void handleMousePressedPrimary() {
		Flow.INSTANCE.getGameStateCurrent().handleCardPositionEdgesPressed();
	}

	@Override
	public Vector2 getCoordinatesCenter() {
		return Credentials.INSTANCE.cCardPositionEdges;
	}

}
