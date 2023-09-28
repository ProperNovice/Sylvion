package gameStates;

import business.Model;
import gameStatesDefault.GameState;

public class ResolveCardRavageSupport extends GameState {

	@Override
	public void execute() {

		Model.INSTANCE.resolveCardRavageSupport();
		proceedToNextGameState();

	}

}
