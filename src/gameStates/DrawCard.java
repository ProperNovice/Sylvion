package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import managers.Model;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		EText.DRAW_CARD.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.drawCard();
		proceedToNextGameState();

	}

}
