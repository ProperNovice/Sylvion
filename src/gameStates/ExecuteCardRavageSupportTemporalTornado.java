package gameStates;

import business.Model;

public class ExecuteCardRavageSupportTemporalTornado extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Temporal Tornado";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportTemporalTornado();
	}

}
