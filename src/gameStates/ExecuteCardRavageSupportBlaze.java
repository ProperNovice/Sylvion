package gameStates;

import business.Model;
import enums.EText;

public class ExecuteCardRavageSupportBlaze extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_BLAZE;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportBlaze();
	}

}
