package gameStates;

import gameStatesDefault.GameState;
import managers.Model;

public class ResolveCardRavageSupport extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.resolveCardRavageSupport();
		proceedToNextGameState();

	}

}
