package gameStates;

import business.Model;
import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;
import model.CardPosition;
import utils.SelectImageViewManager;

public class ExecuteHedgehog extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.discardHedgehogCardFromHand();
		Model.INSTANCE.selectBattlefieldHedgehogTargets();

		EText.CHOOSE_CARD.show();

	}

	@Override
	protected void handleCardPressedBattlefield(Card card, CardPosition cardPosition) {

		if (!card.isSelected())
			return;

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();
		concealText();
		cardPosition.print();

		Model.INSTANCE.destroyCardBattlefield(cardPosition);

		getFlow().addFirst(PlayHedgehog.class);
		proceedToNextGameState();

	}

}
