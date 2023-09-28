package gameStates;

import business.Model;
import enums.EText;

public class ExecuteCardRavageSupportTemporalTornado extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_TEMPORAL_TORNADO;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportTemporalTornado();
	}

}
