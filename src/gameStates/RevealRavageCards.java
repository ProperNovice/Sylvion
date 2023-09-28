package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;

public class RevealRavageCards extends GameState {

	@Override
	public void execute() {

		EText.REVEAL_RAVAGE_CARDS.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.revealRavageCards();
		proceedToNextGameState();

	}

}
