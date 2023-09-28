package gameStates;

import business.Model;
import enums.EText;

public class ExecuteCardRavageSupportStoneRain extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_STONE_RAIN;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportStoneRain();
	}

}
