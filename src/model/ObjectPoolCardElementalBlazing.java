package model;

import cards.CardElementalBlazing;
import utils.ArrayList;
import utils.HashMap;

public enum ObjectPoolCardElementalBlazing {

	INSTANCE;

	private HashMap<Integer, ArrayList<CardElementalBlazing>> hashMap = new HashMap<>();

	private ObjectPoolCardElementalBlazing() {

		for (int counter = 2; counter <= 4; counter++)
			this.hashMap.put(counter, new ArrayList<>());

	}

	public CardElementalBlazing getCardElementalBlazing(int strength) {

		CardElementalBlazing cardElementalBlazing = null;

		ArrayList<CardElementalBlazing> list = this.hashMap.getValue(strength);

		for (CardElementalBlazing cardElementalBlazingTemp : list) {

			if (cardElementalBlazingTemp.getImageView().isVisible())
				continue;

			cardElementalBlazing = cardElementalBlazingTemp;
			break;

		}

		if (cardElementalBlazing == null) {

			cardElementalBlazing = new CardElementalBlazing(strength);
			list.addLast(cardElementalBlazing);

		}

		return cardElementalBlazing;

	}

}
