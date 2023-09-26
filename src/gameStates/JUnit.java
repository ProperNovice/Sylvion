package gameStates;

import cards.Card;
import cards.CardEdge;
import cards.CardElemental;
import cards.CardElementalBlazing;
import cards.CardElementalRavage;
import cards.CardRavage;
import cards.CardRavageSupport;
import cards.CardSylvanAnimal;
import cards.CardSylvanFountain;
import enums.ECardRavageSupport;
import enums.ECardSylvanAnimal;
import gameStatesDefault.GameState;
import managers.ListsManager;
import managers.ManagerCardPosition;
import managers.RevealRavageCards;
import model.CardPosition;
import utils.ArrayList;
import utils.Flow;
import utils.ListImageViewAbles;
import utils.ShutDown;

public class JUnit extends GameState {

	@Override
	public void execute() {

		for (int counter = 1; counter <= 12; counter++)
			getListsManager().edges.getArrayList().addLast(new CardEdge(1));

		getListsManager().edges.relocateImageViews();

		ManagerCardPosition.values();

		addCardsToRavageStacks();
		addCardsToCardPosition();
		addCardsToDeck();
		addCardsToHand();
		damageTrees(1);

		Flow.INSTANCE.executeGameState(RevealRavageCards.class);

	}

	public void addCardsToHand() {

		ArrayList<Card> hand = getListsManager().hand.getArrayList();

		hand.addLast(new CardSylvanFountain(2));
		hand.addLast(new CardSylvanAnimal(ECardSylvanAnimal.ELEPHANT));
		hand.addLast(new CardSylvanFountain(1));
		hand.addLast(new CardSylvanFountain(1));
		hand.addLast(new CardSylvanAnimal(ECardSylvanAnimal.OWL));
		hand.addLast(new CardSylvanAnimal(ECardSylvanAnimal.STAG));

		getListsManager().hand.relocateImageViews();

	}

	public void addCardsToDeck() {

		ArrayList<Card> deck = getListsManager().deck.getArrayList();

		deck.addLast(new CardSylvanFountain(2));
		deck.addLast(new CardSylvanAnimal(ECardSylvanAnimal.ELEPHANT));
		deck.addLast(new CardSylvanFountain(1));
		deck.addLast(new CardSylvanFountain(1));

		getListsManager().deck.relocateImageViews();

		for (Card card : getListsManager().deck)
			card.getImageView().flipBack();

	}

	public void addCardsToCardPosition() {

		addCardsToPosition(0, 0, new CardSylvanFountain(2));
		addCardsToPosition(0, 1, new CardSylvanFountain(2));
		addCardsToPosition(0, 2, new CardSylvanFountain(2));
		addCardsToPosition(0, 3, new CardSylvanFountain(2));
//		addCardsToPosition(0, 4, new CardElementalBlazing(4));

		addCardsToPosition(1, 0, new CardElemental(3));
		addCardsToPosition(1, 2, new CardSylvanFountain(3));
		addCardsToPosition(1, 3, new CardElemental(3));

		addCardsToPosition(2, 0, new CardElemental(0));
		addCardsToPosition(2, 1, new CardSylvanFountain(0));
		addCardsToPosition(2, 2, new CardElemental(1));

		addCardsToPosition(3, 0, new CardElemental(2));

	}

	public void addCardsToRavageStacks() {

		getListsManager().ravageStacks.getValue(0).getArrayList().addLast(new CardElemental(0));
		getListsManager().ravageStacks.getValue(0).getArrayList().addLast(new CardElemental(2));

		getListsManager().ravageStacks.getValue(1).getArrayList().addLast(new CardElemental(1));
		getListsManager().ravageStacks.getValue(1).getArrayList()
				.addLast(new CardRavageSupport(ECardRavageSupport.BLAZE));

		getListsManager().ravageStacks.getValue(2).getArrayList()
				.addLast(new CardElementalBlazing(4));
		getListsManager().ravageStacks.getValue(2).getArrayList()
				.addLast(new CardElementalBlazing(4));

		getListsManager().ravageStacks.getValue(3).getArrayList()
				.addLast(new CardElementalRavage(1));
		getListsManager().ravageStacks.getValue(3).getArrayList()
				.addLast(new CardRavageSupport(ECardRavageSupport.SIMOON));

		for (int counter = 0; counter <= 3; counter++) {

			ListImageViewAbles<CardRavage> list = getListsManager().ravageStacks.getValue(counter);

			list.relocateImageViews();

			for (CardRavage cardRavage : list)
				cardRavage.getImageView().flipBack();

		}

	}

	public void addCardsToPosition(int row, int column, Card card) {

		for (CardPosition cardPosition : ManagerCardPosition.INSTANCE.getCardPositionsClone()) {

			if (row != cardPosition.getRow())
				continue;

			if (column != cardPosition.getColumn())
				continue;

			cardPosition.addCardRelocate(card);

			return;

		}

		ShutDown.INSTANCE.execute();

	}

	public void damageTrees(int strength) {

		for (CardEdge cardEdge : ListsManager.INSTANCE.edges) {

			if (strength == 0)
				break;

			if (cardEdge.getImageView().isFlippedBack())
				continue;

			cardEdge.getImageView().flipBack();
			strength--;

		}

	}

}
