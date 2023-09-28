package gameStates;

import business.Model;
import enums.EText;
import gameStatesDefault.GameState;
import utils.Logger;

public class PlayHedgehog extends GameState {

	@Override
	public void execute() {

		if (Model.INSTANCE.handContainsCardSylvanHedgehog()) {

			Logger.INSTANCE.logNewLine("hand contains card hedgehog");

			EText.PLAY_HEDGEHOG.show();
			EText.CANCEL.show();

		} else {

			Logger.INSTANCE.logNewLine("hand doen't contain card hedgehog");

			proceedToNextGameState();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case PLAY_HEDGEHOG:
			getFlow().addFirst(ExecuteHedgehog.class);
			break;

		case CANCEL:
			break;

		default:
			break;

		}

		proceedToNextGameState();

	}

}
