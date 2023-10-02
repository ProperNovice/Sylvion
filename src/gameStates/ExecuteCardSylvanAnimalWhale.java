package gameStates;

import business.Battlefield;
import business.CardPosition;
import business.CardPositionEdges;
import business.Model;
import cards.Card;
import cards.CardElemental;
import enums.EText;
import gameStatesDefault.GameState;
import utils.ArrayList;
import utils.SelectImageViewManager;

public class ExecuteCardSylvanAnimalWhale extends GameState {

	private CardElemental cardElemental = null;
	private ArrayList<CardPosition> listElementals = new ArrayList<>();
	private EText eTextShowing = null;

	@Override
	public void execute() {

		createListElemental();

		Model.INSTANCE.transferCardFromHandToDiscardPile(Model.INSTANCE.cardToPlay);

		for (CardPosition cardPosition : this.listElementals)
			cardPosition.setSelected();

		EText.RESOLVE.showAdditionally("Whale");
		EText.CHOOSE_ELEMENTAL.show();

		this.eTextShowing = EText.CHOOSE_ELEMENTAL;

	}

	@Override
	public void handleCardPositionBattlefieldPressed(CardPosition cardPosition) {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		if (this.eTextShowing.equals(EText.CHOOSE_ELEMENTAL))
			chooseElemental(cardPosition);

		else if (this.eTextShowing.equals(EText.CHOOSE_CARD_POSITION))
			chooseCardPosition(cardPosition);

	}

	@Override
	public void handleCardPositionEdgesPressed() {

		concealText();
		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		this.cardElemental.getImageView().setVisible(false);

		int strength = this.cardElemental.getStrength();
		Model.INSTANCE.damageTrees(strength);

		proceedToNextGameState();

	}

	private void chooseElemental(CardPosition cardPositionElemental) {

		this.cardElemental = (CardElemental) cardPositionElemental.removeCard();

		EText.RESOLVE.showAdditionally("Whale");
		EText.CHOOSE_CARD_POSITION.show();

		this.eTextShowing = EText.CHOOSE_CARD_POSITION;

		int rowElemental = cardPositionElemental.getRow();
		int columnElemental = cardPositionElemental.getColumn();

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			int column = cardPosition.getColumn();

			if (column == 4)
				continue;

			int row = cardPosition.getRow();

			int distance = 0;

			distance += Math.abs(rowElemental - row);
			distance += Math.abs(columnElemental - column);

			if (distance == 0)
				continue;

			if (distance > 3)
				continue;

			if (!cardPosition.containsCard()) {

				cardPosition.setSelected();
				continue;

			}

			Card card = cardPosition.getCard();

			if (card instanceof CardElemental)
				continue;

			cardPosition.setSelected();

		}

		if (columnElemental <= 2)
			CardPositionEdges.INSTANCE.setSelected();

	}

	private void chooseCardPosition(CardPosition cardPosition) {

		Model.INSTANCE.executeMovement(this.cardElemental, cardPosition);
		proceedToNextGameState();

	}

	private void createListElemental() {

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			if (cardPosition.getColumn() == 4)
				continue;

			else if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (card instanceof CardElemental)
				this.listElementals.addLast(cardPosition);

		}

	}

}
