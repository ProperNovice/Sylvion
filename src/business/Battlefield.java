package business;

import cards.Card;
import utils.ArrayList;
import utils.ShutDown;
import utils.Vector2;

public enum Battlefield {

	INSTANCE;

	private ArrayList<CardPosition> list = new ArrayList<>();

	private Battlefield() {
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

	public CardPosition getCardPosition(int row, int column) {

		CardPosition cardPosition = null;

		for (CardPosition cardPositionTemp : this.list)
			if (cardPositionTemp.getRow() != row)
				continue;
			else if (cardPositionTemp.getColumn() != column)
				continue;
			else
				cardPosition = cardPositionTemp;

		return cardPosition;

	}

	public ArrayList<CardPosition> getCardPositionsClone() {
		return this.list.clone();
	}

	public CardPosition getCardPositionContainingCard(Card card) {

		for (CardPosition cardPosition : this.list)
			if (cardPosition.containsCard())
				if (cardPosition.getCard().equals(card))
					return cardPosition;

		ShutDown.INSTANCE.execute();

		return null;

	}

}
