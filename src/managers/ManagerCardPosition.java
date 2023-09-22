package managers;

import model.CardPosition;
import utils.ArrayList;
import utils.Vector2;

public enum ManagerCardPosition {

	INSTANCE;

	private ArrayList<CardPosition> list = new ArrayList<>();

	private ManagerCardPosition() {
		createCardPositions();
	}

	private void createCardPositions() {

		for (int row = 0; row <= 3; row++) {

			for (int column = 0; column <= 4; column++) {

				Vector2 vector2 = Credentials.INSTANCE.cCardPosition.clone();

				vector2.addX(column * Credentials.INSTANCE.dCard.x);
				vector2.addX(column * Credentials.INSTANCE.dGapBetweenComponents.x);

				vector2.addY(row * Credentials.INSTANCE.dCard.y);
				vector2.addY(row * Credentials.INSTANCE.dGapBetweenComponents.y);

				this.list.addLast(new CardPosition(row, column, vector2));

			}

		}

	}

	public ArrayList<CardPosition> getCardPositions() {
		return this.list;
	}

}
