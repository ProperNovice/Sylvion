package gameStates;

import business.Model;
import enums.EText;

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
