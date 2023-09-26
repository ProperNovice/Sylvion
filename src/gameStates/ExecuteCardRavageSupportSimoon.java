package gameStates;

import enums.EText;
import managers.Model;

public class ExecuteCardRavageSupportSimoon extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_SIMOON;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportSimoon();
	}

}
