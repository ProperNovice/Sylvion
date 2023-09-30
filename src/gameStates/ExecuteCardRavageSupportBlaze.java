package gameStates;

import business.Model;

public class ExecuteCardRavageSupportBlaze extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Blaze";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportBlaze();
	}

}
