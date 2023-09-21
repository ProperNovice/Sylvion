package gameStates;

import enums.EText;
import gameStatesDefault.GameState;

public class StartGame extends GameState {

	@Override
	public void execute() {

		EText.START_GAME.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

	}

}
