package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;

public class ExecuteCardSylvanAnimalOwl extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		EText.RESOLVE.showAdditionally("Owl");
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.executeCardSylvanAnimalOwl();
		proceedToNextGameState();

	}

}
