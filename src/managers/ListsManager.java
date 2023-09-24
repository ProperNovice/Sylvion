package managers;

import cards.Card;
import listCredentials.Deck;
import listCredentials.DiscardPile;
import listCredentials.Hand;
import utils.ArrayList;
import utils.Interfaces.IImageViewAble;
import utils.ListImageViewAbles;

public enum ListsManager {

	INSTANCE;

	public final ArrayList<ListImageViewAbles<IImageViewAble>> lists = new ArrayList<ListImageViewAbles<IImageViewAble>>();
	public ListImageViewAbles<Card> deck, discardPile, hand;

	public void instantiate() {

		this.deck = new ListImageViewAbles<>(Deck.class);
		this.discardPile = new ListImageViewAbles<>(DiscardPile.class);
		this.hand = new ListImageViewAbles<>(Hand.class);

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
