package gameStates;

import business.Model;
import cards.Card;
import enums.EText;
import gameStatesDefault.GameState;
import interfaces.ICostAble;
import utils.Interfaces.IImageViewAble;
import utils.Logger;
import utils.SelectImageViewManager;

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

		if (this.costRemainingToBePaid > 0) {

			handleETextToShow();
			return;

		}

		Model.INSTANCE.resolveCardPlayed();
		proceedToNextGameState();

	}

	@Override
	protected void executeTextOption(EText eText) {

		for (IImageViewAble imageViewAble : SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles()) {

			Card card = (Card) imageViewAble;
			Model.INSTANCE.transferCardFromHandToDiscardPile(card);

		}

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Model.INSTANCE.resolveCardPlayed();
		proceedToNextGameState();

	}

	@Override
	protected void handleCardPressedHand(Card card) {

		if (card.equals(Model.INSTANCE.cardToPlay))
			return;

		card.reverseSelectImageView();
		handleETextToShow();

	}

	private void handleETextToShow() {

		concealText();

		EText.CHOOSE_CARDS.showAdditionally(" to pay");

		if (this.costRemainingToBePaid == SelectImageViewManager.INSTANCE
				.getSelectedImageViewAbles().size())
			EText.CONTINUE.show();
		else
			EText.VOID.show();

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
