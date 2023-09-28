package gameStates;

import business.Model;
import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;
import utils.SelectImageViewManager;

public class ReturnTwoCardsFromHandToTopOfTheDeck extends GameState {

	private int cardsLeftToReturn = 2;

	@Override
	public void execute() {

		if (this.cardsLeftToReturn == 0 || getListsManager().hand.getArrayList().isEmpty()) {

			proceedToNextGameState();
			return;

		}

		EText.CHOOSE_CARD.show();

		for (Card card : getListsManager().hand)
			card.setSelected();

	}

	@Override
	protected void handleCardPressedHand(Card card) {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Model.INSTANCE.transferCardFromHandToTopOfTheDeck(card);

		this.cardsLeftToReturn--;
		execute();

	}

}
