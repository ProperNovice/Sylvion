package managers;

import cards.Card;
import cards.CardEdge;
import cards.CardElemental;
import cards.CardRavage;
import cards.CardSylvan;
import cards.CardSylvanFountain;
import gameStates.DrawCard;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.GameState;
import interfaces.IStrengthAble;
import model.CardPosition;
import utils.ArrayList;
import utils.Flow;
import utils.ListImageViewAbles;
import utils.Logger;

public enum Model {

	INSTANCE;

	public void revealRavageCards() {

		for (int counter = 0; counter <= 3; counter++) {

			ListImageViewAbles<CardRavage> ravageStack = ListsManager.INSTANCE.ravageStacks
					.getValue(counter);

			CardRavage cardRavage = ravageStack.getArrayList().removeFirst();
			cardRavage.getImageView().flipFront();

			CardPosition cardPosition = ManagerCardPosition.INSTANCE.getCardPosition(counter, 4);
			cardPosition.addCardRelocate(cardRavage);

		}

	}

	public void drawCard() {

		Card card = ListsManager.INSTANCE.deck.getArrayList().removeFirst();
		card.getImageView().flipFront();

		ListsManager.INSTANCE.hand.getArrayList().addLast(card);
		ListsManager.INSTANCE.hand.relocateImageViews();

	}

	private void damageTrees(CardElemental cardElemental) {

		Logger.INSTANCE.log("damaging trees");

		cardElemental.getImageView().setVisible(false);

		int strength = cardElemental.getStrength();

		Logger.INSTANCE.logNewLine(strength);

		for (CardEdge cardEdge : ListsManager.INSTANCE.edges) {

			if (strength == 0)
				break;

			if (cardEdge.getImageView().isFlippedBack())
				continue;

			cardEdge.getImageView().flipBack();
			strength--;

		}

		if (strength == 0)
			return;

		getFlow().clear();
		getFlow().addFirst(EndGameLost.class);

	}

	private void executeMovement(CardElemental cardElemental, CardPosition cardPosition) {

		// card position is empty

		if (!cardPosition.containsCard()) {

			cardPosition.addCardRelocate(cardElemental);
			return;

		}

		Card card = cardPosition.getCard();

		// card position contains strength able card

		if (card instanceof IStrengthAble) {

			IStrengthAble cardStrengthAble = (IStrengthAble) card;

			int cardElementalStrength = cardElemental.getStrength();
			int cardDefenceStrength = cardStrengthAble.getStrength();

			if (cardDefenceStrength > cardElementalStrength)
				cardElemental.getImageView().setVisible(false);

			else {

				CardSylvan cardSylvan = (CardSylvan) cardPosition.removeCard();

				ListsManager.INSTANCE.discardPile.getArrayList().addFirst(cardSylvan);
				ListsManager.INSTANCE.discardPile.relocateImageViews();

				if (cardSylvan instanceof CardSylvanFountain)
					getFlow().addFirst(DrawCard.class);

				if (cardDefenceStrength == cardElementalStrength)
					cardElemental.getImageView().setVisible(false);
				else
					cardPosition.addCardRelocate(cardElemental);

			}

			return;

		}

	}

	public void moveElementals() {

		// get card positions

		ArrayList<CardPosition> cardPositions = ManagerCardPosition.INSTANCE
				.getCardPositionsClone();

		// filter in elementals

		for (CardPosition cardPosition : cardPositions.clone()) {

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardElemental))
				continue;

			CardElemental cardElemental = (CardElemental) cardPosition.removeCard();

			// execute movement

			if (cardPosition.getColumn() > 0) {

				int rowCombat = cardPosition.getRow();
				int columnCombat = cardPosition.getColumn() - 1;
				CardPosition cardPositionCombat = ManagerCardPosition.INSTANCE
						.getCardPosition(rowCombat, columnCombat);
				executeMovement(cardElemental, cardPositionCombat);

				// execute damaging trees

			} else if (cardPosition.getColumn() == 0)
				damageTrees(cardElemental);

		}

	}

	private ArrayList<Class<? extends GameState>> getFlow() {
		return Flow.INSTANCE.getFlow();
	}

}
