package gameStates;

import business.Model;

public class ExecuteCardRavageSupportAcidLake extends ExecuteCardRavageSupport {

	@Override
	protected String getAdditionalTextToShow() {
		return "Acid Lake";
	}

	@Override
	protected Runnable executeCard() {
		return () -> Model.INSTANCE.executeCardRavageSupportAcidLake();
	}

}
