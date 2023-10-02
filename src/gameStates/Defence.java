package gameStates;

import business.CardPosition;
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

		EText.CHOOSE_CARD.showAdditionally(" to play");
		EText.PASS.show();

	}

	@Override
	protected void handleCardPressedHand(Card card) {

		if (!SelectImageViewManager.INSTANCE.getSelectedImageViewAbles().contains(card))
			return;

		getFlow().addFirst(PayForTheCardToPlay.class);
		handleCardPressedCredentials(card);

		proceedToNextGameState();

	}

	@Override
	protected void handleCardPressedBattlefield(Card card, CardPosition cardPosition) {

		if (!SelectImageViewManager.INSTANCE.getSelectedImageViewAbles().contains(card))
			return;

		handleCardPressedCredentials(card);
		Model.INSTANCE.resolveCardPlayed();

		proceedToNextGameState();

	}

	private void handleCardPressedCredentials(Card card) {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		Model.INSTANCE.cardToPlay = card;

	}

}
