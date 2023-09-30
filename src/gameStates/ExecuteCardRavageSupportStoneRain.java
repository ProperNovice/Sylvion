package gameStates;

import business.Model;

public class ExecuteCardRavageSupportStoneRain extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Stone Rain";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportStoneRain();
	}

}
