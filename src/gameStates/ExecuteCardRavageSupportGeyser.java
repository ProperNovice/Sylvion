package gameStates;

import business.Model;
import enums.EText;

public class ExecuteCardRavageSupportGeyser extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_GEYSER;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportGeyser();
	}

}
