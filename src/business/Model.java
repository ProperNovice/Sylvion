package business;

import cards.Card;
import cards.CardEdge;
import cards.CardElemental;
import cards.CardElementalBlazing;
import cards.CardRavage;
import cards.CardRavageSupport;
import cards.CardSylvan;
import cards.CardSylvanAnimal;
import cards.CardSylvanFountain;
import cards.CardSylvanTree;
import enums.ECardRavageSupport;
import enums.ECardSylvanAnimal;
import enums.EResolveOrder;
import gameStates.DrawCard;
import gameStates.ExecuteCardRavageSupportAcidLake;
import gameStates.ExecuteCardRavageSupportBlaze;
import gameStates.ExecuteCardRavageSupportGeyser;
import gameStates.ExecuteCardRavageSupportSimoon;
import gameStates.ExecuteCardRavageSupportStoneRain;
import gameStates.ExecuteCardRavageSupportTemporalTornado;
import gameStates.ExecuteCardSylvanAnimalDoves;
import gameStates.ExecuteCardSylvanAnimalElephant;
import gameStates.ExecuteCardSylvanAnimalFish;
import gameStates.ExecuteCardSylvanAnimalOwl;
import gameStates.ExecuteCardSylvanAnimalStag;
import gameStates.ExecuteCardSylvanAnimalWhale;
import gameStates.ExecuteGeyser;
import gameStates.ReturnTwoCardsFromHandToTopOfTheDeck;
import gameStatesDefault.EndGameLost;
import gameStatesDefault.GameState;
import interfaces.ICostAble;
import interfaces.IStrengthAble;
import utils.ArrayList;
import utils.Flow;
import utils.HashMap;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.ObjectPool;
import utils.SelectImageViewManager;
import utils.ShutDown;

public enum Model {

	INSTANCE;

	public Card cardToPlay = null;

	public void executeCardSylvanAnimalDoves() {

		for (Integer integer : ListsManager.INSTANCE.ravageStacks) {

			ArrayList<CardRavage> list = ListsManager.INSTANCE.ravageStacks.getValue(integer)
					.getArrayList();

			if (list.isEmpty())
				return;

			CardRavage cardRavage = list.removeFirst();
			cardRavage.getImageView().setVisible(false);

		}

	}

	public void executeCardSylvanAnimalFish() {

		for (int counter = 1; counter <= 3; counter++)
			ListsManager.INSTANCE.energy.getArrayList()
					.addLast(ObjectPool.INSTANCE.acquire(EnergyImageView.class));

		ListsManager.INSTANCE.energy.relocateImageViews();

	}

	public void executeCardSylvanAnimalStag() {

		int edgesToFlipUp = 2;
		int treesInBattlefield = 0;

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (card instanceof CardSylvanTree)
				treesInBattlefield++;

		}

		int treesToHeal = Math.max(edgesToFlipUp, treesInBattlefield);

		Logger.INSTANCE.logNewLine("trees to heal -> " + treesToHeal);

		healTrees(treesToHeal);

	}

	public void executeCardSylvanAnimalOwl() {
		getFlow().addFirst(DrawCard.class, 3);
	}

	public void resolveCardPlayed() {

		Class<? extends GameState> classToResolve = null;

		// handle card animal

		if (this.cardToPlay instanceof CardSylvanAnimal) {

			CardSylvanAnimal cardSylvanAnimal = (CardSylvanAnimal) this.cardToPlay;
			ECardSylvanAnimal eCardSylvanAnimal = cardSylvanAnimal.getECardSylvanAnimal();

			switch (eCardSylvanAnimal) {

			case DOVES:
				classToResolve = ExecuteCardSylvanAnimalDoves.class;
				break;

			case ELEPHANT:
				classToResolve = ExecuteCardSylvanAnimalElephant.class;
				break;

			case FISH:
				classToResolve = ExecuteCardSylvanAnimalFish.class;
				break;

			case OWL:
				classToResolve = ExecuteCardSylvanAnimalOwl.class;
				break;

			case SQUIRRELS:
				break;

			case STAG:
				classToResolve = ExecuteCardSylvanAnimalStag.class;
				break;

			case WHALE:
				classToResolve = ExecuteCardSylvanAnimalWhale.class;
				break;

			default:
				ShutDown.INSTANCE.execute();
				break;

			}

		} else if (this.cardToPlay instanceof CardRavageSupport) {

			CardRavageSupport cardRavageSupport = (CardRavageSupport) this.cardToPlay;
			ECardRavageSupport eCardRavageSupport = cardRavageSupport.getECardRavageSupport();

			if (eCardRavageSupport.equals(ECardRavageSupport.GEYSER))
				classToResolve = ExecuteGeyser.class;

		}

		getFlow().addFirst(classToResolve);

	}

	public void defenceSelectPlayableCards() {

		// playable cards hand

		int energyAvailableToSpend = 0;
		energyAvailableToSpend += ListsManager.INSTANCE.energy.getArrayList().size();
		energyAvailableToSpend += ListsManager.INSTANCE.hand.getArrayList().size() - 1;

		for (Card card : ListsManager.INSTANCE.hand) {

			if (!(card instanceof ICostAble))
				continue;

			if (card instanceof CardSylvanAnimal) {

				CardSylvanAnimal cardSylvanAnimal = (CardSylvanAnimal) card;
				ECardSylvanAnimal eCardSylvanAnimal = cardSylvanAnimal.getECardSylvanAnimal();

				if (eCardSylvanAnimal.equals(ECardSylvanAnimal.HEDGEHOG))
					continue;

				else if (eCardSylvanAnimal.equals(ECardSylvanAnimal.ELEPHANT)
						|| eCardSylvanAnimal.equals(ECardSylvanAnimal.WHALE)) {

					boolean cardElementalFound = false;

					for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

						if (!cardPosition.containsCard())
							continue;

						Card cardTemp = cardPosition.getCard();

						if (!(cardTemp instanceof CardElemental))
							continue;

						cardElementalFound = true;

					}

					if (!cardElementalFound)
						continue;

				}

			}

			ICostAble iCostAble = (ICostAble) card;
			int cardCost = iCostAble.getCost();

			if (cardCost > energyAvailableToSpend)
				continue;

			SelectImageViewManager.INSTANCE.addSelectImageViewAble(card);

		}

		// geyser in battlefield

		for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone()) {

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardRavageSupport))
				continue;

			CardRavageSupport cardRavageSupport = (CardRavageSupport) card;
			ECardRavageSupport eCardRavageSupport = cardRavageSupport.getECardRavageSupport();

			if (eCardRavageSupport.equals(ECardRavageSupport.GEYSER))
				cardRavageSupport.setSelected();

		}

		Logger.INSTANCE.logNewLine("playable cards -> "
				+ SelectImageViewManager.INSTANCE.getSelectedImageViewAbles().size());

	}

	public void reinforcements() {

		for (int counter = 1; counter <= 3; counter++)
			Flow.INSTANCE.getFlow().addFirst(DrawCard.class);

	}

	public void executeCardRavageSupportGeyser() {

		CardPosition cardPosition = getFirstCardRavageSupportToResolveInOrder();
		Card card = cardPosition.removeCard();

		addCardInBattlefieldFurthestAway(card, cardPosition);

	}

	public void executeCardRavageSupportTemporalTornado() {

		CardPosition cardPositionRavageSupport = getFirstCardRavageSupportToResolveInOrder();
		destroyCardRavageSupportResolving();

		ArrayList<Card> list = new ArrayList<>();

		int row = cardPositionRavageSupport.getRow();

		for (int column = 0; column <= 3; column++) {

			CardPosition cardPosition = Battlefield.INSTANCE.getCardPosition(row, column);

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardSylvanFountain) && !(card instanceof CardSylvanTree))
				continue;

			list.addLast(cardPosition.removeCard());

		}

		if (!list.isEmpty()) {

			ListsManager.INSTANCE.hand.getArrayList().addAllLast(list);
			ListsManager.INSTANCE.hand.relocateImageViews();

		} else
			Flow.INSTANCE.getFlow().addFirst(ReturnTwoCardsFromHandToTopOfTheDeck.class);

	}

	public void executeCardRavageSupportStoneRain() {

		CardPosition cardPositionRavageSupport = getFirstCardRavageSupportToResolveInOrder();
		destroyCardRavageSupportResolving();

		int row = cardPositionRavageSupport.getRow();

		for (int column = 3; column >= 0; column--) {

			CardPosition cardPosition = Battlefield.INSTANCE.getCardPosition(row, column);

			if (!cardPosition.containsCard())
				continue;

			Card card = cardPosition.getCard();

			if (!(card instanceof CardSylvanFountain) && !(card instanceof CardSylvanTree))
				continue;

			ListsManager.INSTANCE.discardPile.getArrayList().addFirst(card);
			ListsManager.INSTANCE.discardPile.relocateImageViews();

			Flow.INSTANCE.getFlow().addFirst(DrawCard.class);

			return;

		}

		damageTrees(1);

	}

	public void executeCardRavageSupportAcidLake() {

		CardPosition cardPosition = getFirstCardRavageSupportToResolveInOrder();
		Card card = cardPosition.removeCard();

		addCardInBattlefieldFurthestAway(card, cardPosition);

	}

	public void executeCardRavageSupportSimoon() {

		destroyCardRavageSupportResolving();
		moveElementals();

	}

	public void executeCardRavageSupportBlaze() {

		destroyCardRavageSupportResolving();

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
		hashMap.put(ECardRavageSupport.ACID_LAKE, ExecuteCardRavageSupportAcidLake.class);
		hashMap.put(ECardRavageSupport.STONE_RAIN, ExecuteCardRavageSupportStoneRain.class);
		hashMap.put(ECardRavageSupport.TEMPORAL_TORNADO,
				ExecuteCardRavageSupportTemporalTornado.class);
		hashMap.put(ECardRavageSupport.GEYSER, ExecuteCardRavageSupportGeyser.class);

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

	public void transferCardFromHandToTopOfTheDeck(Card card) {

		ListsManager.INSTANCE.hand.getArrayList().remove(card);
		ListsManager.INSTANCE.deck.getArrayList().addFirst(card);

		ListsManager.INSTANCE.hand.relocateImageViews();
		ListsManager.INSTANCE.deck.relocateImageViews();

		card.getImageView().flipBack();

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

	private void healTrees(int value) {

		Logger.INSTANCE.log("healing trees");
		Logger.INSTANCE.logNewLine(value);

		ArrayList<CardEdge> edges = ListsManager.INSTANCE.edges.getArrayList().clone();
		edges.reverse();

		for (CardEdge cardEdge : edges) {

			if (value == 0)
				break;

			if (cardEdge.getImageView().isFlippedFront())
				continue;

			cardEdge.getImageView().flipFront();
			value--;

		}

	}

	public void damageTrees(int strength) {

		Logger.INSTANCE.log("damaging trees");
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

	public void executeMovement(CardElemental cardElemental, CardPosition cardPosition) {

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

		} else {

			CardRavageSupport cardRavageSupport = (CardRavageSupport) card;

			if (cardRavageSupport.getECardRavageSupport().equals(ECardRavageSupport.ACID_LAKE)) {

				int column = cardPosition.getColumn();

				if (column == 0) {

					cardElemental.getImageView().setVisible(false);
					damageTrees(cardElemental.getStrength());

				} else {

					int row = cardPosition.getRow();
					cardPosition = Battlefield.INSTANCE.getCardPosition(row, column - 1);
					executeMovement(cardElemental, cardPosition);

				}

			} else if (cardRavageSupport.getECardRavageSupport()
					.equals(ECardRavageSupport.GEYSER)) {

				cardPosition.removeCard().getImageView().setVisible(false);
				cardElemental.getImageView().setVisible(false);

			}

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

			} else if (cardPosition.getColumn() == 0) {

				cardElemental.getImageView().setVisible(false);
				damageTrees(cardElemental.getStrength());

			}

		}

	}

	private void addCardInBattlefieldFurthestAway(Card card, CardPosition cardPosition) {

		int row = cardPosition.getRow();

		for (int column = 0; column <= 3; column++) {

			CardPosition cardPositionTemp = Battlefield.INSTANCE.getCardPosition(row, column);

			if (cardPositionTemp.containsCard())
				continue;

			cardPositionTemp.addCardRelocate(card);

			return;

		}

		card.getImageView().setVisible(false);

	}

	private ArrayList<Class<? extends GameState>> getFlow() {
		return Flow.INSTANCE.getFlow();
	}

}
