package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;

public class ExecuteCardSylvanAnimalStag extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		EText.RESOLVE.showAdditionally("Stag");
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.executeCardSylvanAnimalStag();
		proceedToNextGameState();

	}

}
