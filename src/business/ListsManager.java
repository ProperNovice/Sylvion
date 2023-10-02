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
import listCredentials.SquirrelStack;
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
	public HashMap<Integer, ListImageViewAbles<CardRavage>> ravageStacks, squirrelsStack;

	public void instantiate() {

		this.deck = new ListImageViewAbles<>(Deck.class);
		this.discardPile = new ListImageViewAbles<>(DiscardPile.class);
		this.hand = new ListImageViewAbles<>(Hand.class);
		this.edges = new ListImageViewAbles<>(Edges.class);
		this.energy = new ListImageViewAbles<>(Energy.class);
		this.ravageStacks = new HashMap<>();
		this.squirrelsStack = new HashMap<>();

		// ravage & squirrels stacks

		for (int counter = 0; counter <= 3; counter++) {

			ListImageViewAbles<CardRavage> listRavageStack = new ListImageViewAbles<>(
					RavageStack.class);
			ListImageViewAbles<CardRavage> listSquirrelStack = new ListImageViewAbles<>(
					SquirrelStack.class);

			listRavageStack.getListCredentials().coordinatesList.y += counter
					* Credentials.INSTANCE.dCard.y;
			listRavageStack.getListCredentials().coordinatesList.y += counter
					* Credentials.INSTANCE.dGapBetweenComponents.y;

			listSquirrelStack.getListCredentials().coordinatesList.y += counter
					* Credentials.INSTANCE.dCard.y;
			listSquirrelStack.getListCredentials().coordinatesList.y += counter
					* Credentials.INSTANCE.dGapBetweenComponents.y;

			this.ravageStacks.put(counter, listRavageStack);
			this.squirrelsStack.put(counter, listRavageStack);

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
