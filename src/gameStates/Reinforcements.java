package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;

public class Reinforcements extends GameState {

	@Override
	public void execute() {

		EText.REINFORCEMENTS.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.reinforcements();
		proceedToNextGameState();

	}

}
