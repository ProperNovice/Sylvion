package gameStates;

import business.Model;
import gameStatesDefault.GameState;
import interfaces.ICostAble;
import utils.Logger;

public class PayForTheCardToPlay extends GameState {

	private int costRemainingToBePaid;

	@Override
	public void execute() {

		ICostAble iCostAble = (ICostAble) Model.INSTANCE.cardToPlay;
		this.costRemainingToBePaid = iCostAble.getCost();

		logEnergy("before");
		adjustCostWithEnergy();
		logEnergy("after");

		Logger.INSTANCE.newLine();

	}

	private void adjustCostWithEnergy() {

		if (this.costRemainingToBePaid == 0)
			return;

		if (getListsManager().energy.getArrayList().size() == 0)
			return;

		this.costRemainingToBePaid--;
		getListsManager().energy.getArrayList().removeLast().getImageView().setVisible(false);

		adjustCostWithEnergy();

	}

	private void logEnergy(String string) {

		Logger.INSTANCE.log("card cost " + string + " applying energy:");
		Logger.INSTANCE.log(this.costRemainingToBePaid);

	}

}
