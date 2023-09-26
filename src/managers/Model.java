package managers;

import cards.Card;
import cards.CardEdge;
import cards.CardElemental;
import cards.CardElementalBlazing;
import cards.CardRavage;
import cards.CardRavageSupport;
import cards.CardSylvan;
import cards.CardSylvanAnimal;
import cards.CardSylvanFountain;
import enums.ECardRavageSupport;
import enums.ECardSylvanAnimal;
import enums.EResolveOrder;
import gameStates.DrawCard;
import gameStates.ExecuteCardRavageSupportBlaze;
import gameStates.ExecuteCardRavageSupportSimoon;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.GameState;
import interfaces.IStrengthAble;
import model.CardPosition;
import model.ObjectPoolCardElementalBlazing;
import utils.ArrayList;
import utils.Flow;
import utils.HashMap;
import utils.ListImageViewAbles;
import utils.Logger;

public enum Model {

	INSTANCE;

	public void executeCardRavageSupportSimoon() {
		moveElementals();
	}

	public void executeCardRavageSupportBlaze() {

		// create upgrade map

		HashMap<Integer, Integer> hashMap = new HashMap<>();

		hashMap.put(0, 4);
		hashMap.put(1, 2);
		hashMap.put(2, 3);
		hashMap.put(3, 4);

		// get card positions with elementals

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardElemental))
				continue;

			if (card instanceof CardElementalBlazing)
				continue;

			cardPosition.removeCard();

			CardElemental cardElemental = (CardElemental) card;
			cardElemental.getImageView().setVisible(false);

			int strengthCardElemental = cardElemental.getStrength();
			int strengthCardBlazing = hashMap.getValue(strengthCardElemental);

			CardElementalBlazing cardElementalBlazing = ObjectPoolCardElementalBlazing.INSTANCE
					.getCardElementalBlazing(strengthCardBlazing);

			cardPosition.addCardRelocate(cardElementalBlazing);

		}

	}

	public void destroyCardRavageSupportResolving() {

		CardPosition cardPosition = getFirstCardRavageSupportToResolveInOrder();
		destroyCardBattlefield(cardPosition);

	}

	public void resolveCardRavageSupport() {

		CardPosition cardPosition = getFirstCardRavageSupportToResolveInOrder();

		if (cardPosition == null)
			return;

		// creating game states to resolve

		HashMap<ECardRavageSupport, Class<? extends GameState>> hashMap = new HashMap<>();

		hashMap.put(ECardRavageSupport.BLAZE, ExecuteCardRavageSupportBlaze.class);
		hashMap.put(ECardRavageSupport.SIMOON, ExecuteCardRavageSupportSimoon.class);

		// add game state

		CardRavageSupport cardRavageSupport = (CardRavageSupport) cardPosition.getCard();
		ECardRavageSupport eCardRavageSupport = cardRavageSupport.getECardRavageSupport();
		cardRavageSupport.setSelected();

		Flow.INSTANCE.getFlow().addFirst(hashMap.getValue(eCardRavageSupport));

	}

	private CardPosition getFirstCardRavageSupportToResolveInOrder() {

		// creating support cards to resolve

		HashMap<CardRavageSupport, CardPosition> hashMap = new HashMap<>();

		for (int counter = 0; counter <= 3; counter++) {

			CardPosition cardPosition = Battlefield.INSTANCE.getCardPosition(counter, 4);

			if (!cardPosition.containsCard())
				continue;

			CardRavage cardRavage = (CardRavage) cardPosition.getCard();

			if (!(cardRavage instanceof CardRavageSupport))
				continue;

			CardRavageSupport cardRavageSupport = (CardRavageSupport) cardRavage;

			hashMap.put(cardRavageSupport, cardPosition);

		}

		// choosing card to resolve

		for (EResolveOrder eResolveOrder : EResolveOrder.values()) {

			for (CardRavageSupport cardRavageSupport : hashMap) {

				if (!cardRavageSupport.getEResolveOrder().equals(eResolveOrder))
					continue;

				return hashMap.getValue(cardRavageSupport);

			}

		}

		return null;

	}

	public void destroyCardBattlefield(CardPosition cardPosition) {

		Card card = cardPosition.removeCard();
		card.getImageView().setVisible(false);

	}

	public void selectBattlefieldHedgehogTargets() {

		for (int counter = 0; counter <= 3; counter++) {

			CardPosition cardPosition = Battlefield.INSTANCE.getCardPosition(counter, 4);

			if (!cardPosition.containsCard())
				continue;

			cardPosition.getCard().setSelected();

		}

	}

	public void transferCardFromHandToDiscardPile(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.relocateImageViews();
		ListsManager.INSTANCE.discardPile.relocateImageViews();

	}

	public void discardHedgehogCardFromHand() {

		for (Card card : ListsManager.INSTANCE.hand) {

			if (!(card instanceof CardSylvanAnimal))
				continue;

			CardSylvanAnimal cardSylvanAnimal = (CardSylvanAnimal) card;
			ECardSylvanAnimal eCardSylvanAnimal = cardSylvanAnimal.getECardSylvanAnimal();

			if (!eCardSylvanAnimal.equals(ECardSylvanAnimal.HEDGEHOG))
				continue;

			transferCardFromHandToDiscardPile(card);
			return;

		}

	}

	public boolean handContainsCardSylvanHedgehog() {

		for (Card card : ListsManager.INSTANCE.hand) {

			if (!(card instanceof CardSylvanAnimal))
				continue;

			CardSylvanAnimal cardSylvanAnimal = (CardSylvanAnimal) card;
			ECardSylvanAnimal eCardSylvanAnimal = cardSylvanAnimal.getECardSylvanAnimal();

			if (!eCardSylvanAnimal.equals(ECardSylvanAnimal.HEDGEHOG))
				continue;

			return true;

		}

		return false;

	}

	public void revealRavageCards() {

		for (int counter = 0; counter <= 3; counter++) {

			ListImageViewAbles<CardRavage> ravageStack = ListsManager.INSTANCE.ravageStacks
					.getValue(counter);

			CardRavage cardRavage = ravageStack.getArrayList().removeFirst();
			cardRavage.getImageView().flipFront();

			CardPosition cardPosition = Battlefield.INSTANCE.getCardPosition(counter, 4);
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
					Flow.INSTANCE.getFlow().addFirst(DrawCard.class);

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

		ArrayList<CardPosition> cardPositions = Battlefield.INSTANCE.getCardPositionsClone();

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
				CardPosition cardPositionCombat = Battlefield.INSTANCE.getCardPosition(rowCombat,
						columnCombat);
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
