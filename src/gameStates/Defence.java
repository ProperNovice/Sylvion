package gameStates;

import business.Model;
import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;
import utils.SelectImageViewManager;

public class Defence extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.defenceSelectPlayableCards();

		if (SelectImageViewManager.INSTANCE.getSelectedImageViewAbles().isEmpty()) {

			proceedToNextGameState();
			return;

		}

		EText.CHOOSE_CARD.show();

	}

	@Override
	protected void handleCardPressedHand(Card card) {

		if (!SelectImageViewManager.INSTANCE.getSelectedImageViewAbles().contains(card))
			return;

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Model.INSTANCE.cardToPlay = card;

		getFlow().addFirst(PayForTheCardToPlay.class);
		proceedToNextGameState();

	}

}
