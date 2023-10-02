package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;

public class ExecuteCardSylvanAnimalSquirrels extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		EText.RESOLVE.showAdditionally("Squirrels");
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		proceedToNextGameState();

	}

}
