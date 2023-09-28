package gameStates;

import business.Model;
import enums.EText;

public class ExecuteCardRavageSupportAcidLake extends ExecuteCardRavageSupport {

	@Override
	protected EText getETextToShow() {
		return EText.RESOLVE_ACID_LAKE;
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportAcidLake();
	}

}
