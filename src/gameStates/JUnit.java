package gameStates;

import cards.CardElementalRavage;
import gameStatesDefault.GameState;

public class JUnit extends GameState {

	@Override
	public void execute() {

		new CardElementalRavage(3).print();

	}

}
