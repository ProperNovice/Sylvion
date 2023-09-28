package gameStates;

import enums.EText;
import gameStatesDefault.GameState;
import utils.SelectImageViewManager;

public abstract class ExecuteCardRavageSupport extends GameState {

	@Override
	public final void execute() {

		getETextToShow().show();
		EText.CONTINUE.show();

	}

	@Override
	protected final void executeTextOption(EText eText) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		getFlow().addFirst(ResolveCardRavageSupport.class);
		executeCard().run();
		proceedToNextGameState();

	}

	protected abstract EText getETextToShow();

	protected abstract Runnable executeCard();

}
