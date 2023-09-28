package gameStates;

import business.Model;
import gameStatesDefault.GameState;

public class DrawCard extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.drawCard();
		proceedToNextGameState();

	}

}
