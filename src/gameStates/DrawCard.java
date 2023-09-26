package gameStates;

import gameStatesDefault.GameState;
import managers.Model;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.drawCard();
		proceedToNextGameState();

	}

}
