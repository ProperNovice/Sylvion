package business;

import cards.Card;
import cards.CardEdge;
import cards.CardRavage;
import listCredentials.Deck;
import listCredentials.DiscardPile;
import listCredentials.Edges;
import listCredentials.Energy;
import listCredentials.Hand;
import listCredentials.RavageStack;
import model.EnergyImageView;
import utils.ArrayList;
import utils.HashMap;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;

public enum ListsManager {

	INSTANCE;

	public final ArrayList<ListImageViewAbles<IImageViewAble>> lists = new ArrayList<ListImageViewAbles<IImageViewAble>>();
	public ListImageViewAbles<Card> deck, discardPile, hand;
	public ListImageViewAbles<CardEdge> edges;
	public ListImageViewAbles<EnergyImageView> energy;
	public HashMap<Integer, ListImageViewAbles<CardRavage>> ravageStacks = new HashMap<>();

	public void instantiate() {

		this.deck = new ListImageViewAbles<>(Deck.class);
		this.discardPile = new ListImageViewAbles<>(DiscardPile.class);
		this.hand = new ListImageViewAbles<>(Hand.class);
		this.edges = new ListImageViewAbles<>(Edges.class);
		this.energy = new ListImageViewAbles<>(Energy.class);

		// ravage stacks

		for (int counter = 0; counter <= 3; counter++) {

			ListImageViewAbles<CardRavage> list = new ListImageViewAbles<>(RavageStack.class);

			list.getListCredentials().coordinatesList.y += counter * Credentials.INSTANCE.dCard.y;
			list.getListCredentials().coordinatesList.y += counter
					* Credentials.INSTANCE.dGapBetweenComponents.y;

			this.ravageStacks.put(counter, list);

		}

	}

	public void saveListsOriginal() {

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().saveOriginal();

	}

	public void loadListsOriginal() {

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().clear();

		for (ListImageViewAbles<IImageViewAble> list : this.lists)
			list.getArrayList().loadOriginal();

	}

}
