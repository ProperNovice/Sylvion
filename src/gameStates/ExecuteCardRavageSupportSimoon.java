package gameStates;

import business.Model;

public class ExecuteCardRavageSupportSimoon extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Simoon";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportSimoon();
	}

}
