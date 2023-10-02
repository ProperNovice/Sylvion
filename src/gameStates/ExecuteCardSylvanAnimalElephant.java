package gameStates;

import business.Battlefield;
import business.CardPosition;
import business.Model;
import cards.Card;
import cards.CardElemental;
import enums.EText;
import gameStatesDefault.GameState;

public class ExecuteCardSylvanAnimalElephant extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		EText.RESOLVE.showAdditionally("Elephant");
		EText.CHOOSE_CARD.show();

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardElemental))
				continue;

			card.setSelected();

		}

	}

	@Override
	protected void handleCardPressedBattlefield(Card card, CardPosition cardPosition) {

		if (!card.isSelected())
			return;

		concealText();
		getSelectImageViewManager().releaseSelectImageViews();

		Model.INSTANCE.destroyCardBattlefield(cardPosition);
		proceedToNextGameState();

	}

}
