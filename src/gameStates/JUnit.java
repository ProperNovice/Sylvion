package gameStates;

import cards.Card;
import cards.CardElemental;
import cards.CardSylvanFountain;
import gameStatesDefault.GameState;
import managers.ManagerCardPosition;
import model.CardPosition;
import utils.ShutDown;

public class JUnit extends GameState {

	@Override
	public void execute() {

		ManagerCardPosition.values();

		addCardsToCardPosition();

	}

	public void addCardsToCardPosition() {

		addCardsToPosition(0, 0, new CardSylvanFountain(2));
		addCardsToPosition(0, 1, new CardSylvanFountain(2));
		addCardsToPosition(0, 2, new CardSylvanFountain(2));
		addCardsToPosition(0, 3, new CardSylvanFountain(2));
		addCardsToPosition(3, 4, new CardSylvanFountain(2));
		addCardsToPosition(1, 3, new CardElemental(3));
		addCardsToPosition(2, 2, new CardElemental(1));

	}

	public void addCardsToPosition(int row, int column, Card card) {

		for (CardPosition cardPosition : ManagerCardPosition.INSTANCE.getCardPositions()) {

			if (row != cardPosition.getRow())
				continue;

			if (column != cardPosition.getColumn())
				continue;

			cardPosition.addCardRelocate(card);

			return;

		}

		ShutDown.INSTANCE.execute();

	}

}
