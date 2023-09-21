package gameStates;

import cards.CardSylvanAnimal;
import enums.ECardSylvanAnimal;
import gameStatesDefault.GameState;

public class JUnit extends GameState {

	@Override
	public void execute() {

		new CardSylvanAnimal(ECardSylvanAnimal.WHALE).print();

	}

}
