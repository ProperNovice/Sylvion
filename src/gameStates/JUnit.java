package gameStates;

import cards.Card;
import cards.CardEdge;
import cards.CardElemental;
import cards.CardElementalBlazing;
import cards.CardRavage;
import cards.CardRavageSupport;
import cards.CardSylvanAnimal;
import cards.CardSylvanFountain;
import enums.ECardRavageSupport;
import enums.ECardSylvanAnimal;
import gameStatesDefault.GameState;
import managers.ManagerCardPosition;
import model.CardPosition;
import utils.ArrayList;
import utils.ShutDown;

public class JUnit extends GameState {

	@Override
	public void execute() {

		for (int counter = 1; counter <= 12; counter++)
			getListsManager().edges.getArrayList().addLast(new CardEdge(1));

		getListsManager().edges.relocateImageViews();

		ManagerCardPosition.values();

		addCardsToCardPosition();
		addCardsToRavageStacks();
		addCardsToDeck();
		addCardsToHand();

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
		addCardsToPosition(1, 3, new CardElemental(3));
		addCardsToPosition(2, 2, new CardElemental(1));

	}

	public void addCardsToRavageStacks() {

		ArrayList<CardRavage> list = new ArrayList<>();

		list.addLast(new CardElemental(0));
		list.addLast(new CardRavageSupport(ECardRavageSupport.BLAZE));
		list.addLast(new CardElementalBlazing(4));
		list.addLast(new CardRavageSupport(ECardRavageSupport.SIMOON));

		for (int counter = 0; counter <= 3; counter++) {

			getListsManager().ravageStacks.getValue(counter).getArrayList()
					.addLast(list.get(counter));

			getListsManager().ravageStacks.getValue(counter).relocateImageViews();

		}

	}

	public void addCardsToPosition(int row, int column, Card card) {

		for (CardPosition cardPosition : ManagerCardPosition.INSTANCE.getCardPositions()) {

			if (row != cardPosition.getRow())
				continue;

			if (column != cardPosition.getColumn())
				continue;

			cardPosition.addCardRelocate(card);

			return;

		}

		ShutDown.INSTANCE.execute();

	}

}
