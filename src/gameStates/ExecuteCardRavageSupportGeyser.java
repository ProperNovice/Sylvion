package gameStates;

import business.Model;

public class ExecuteCardRavageSupportGeyser extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Geyser";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportGeyser();
	}

}
