package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import managers.Model;

public class MoveElementals extends GameState {

	@Override
	public void execute() {

		EText.MOVE_ELEMENTALS.show();
		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.moveElementals();
		proceedToNextGameState();

	}

}
