package gameStates;

import business.CardPosition;
import business.Model;
import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;

public class ExecuteCardSylvanAnimalDoves extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		EText.RESOLVE.showAdditionally("Doves");
		EText.CONTINUE.show();

	}

	@Override
	protected void handleCardPressedBattlefield(Card card, CardPosition cardPosition) {

		Model.INSTANCE.executeCardSylvanAnimalDoves();
		proceedToNextGameState();

	}

}
