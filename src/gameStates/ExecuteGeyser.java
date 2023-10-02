package gameStates;

import business.Battlefield;
import business.CardPosition;
import business.Model;
import cards.CardElemental;
import enums.EText;
import gameStatesDefault.GameState;
import utils.ArrayList;
import utils.HashMap;

public class ExecuteGeyser extends GameState {

	private HashMap<EText, ArrayList<CardPosition>> hashMap = new HashMap<>();
	private CardPosition cardPositionGeyser = null;

	@Override
	public void execute() {

		this.cardPositionGeyser = Battlefield.INSTANCE
				.getCardPositionContainingCard(Model.INSTANCE.cardToPlay);

		createHashMap();

		if (this.hashMap.isEmpty()) {

			Model.INSTANCE.destroyCardBattlefield(this.cardPositionGeyser);
			proceedToNextGameState();
			return;

		}

		Model.INSTANCE.cardToPlay.setSelected();
		EText.ERUPT_GEYSER.show();

		for (EText eText : this.hashMap)
			eText.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Model.INSTANCE.cardToPlay.reverseSelectImageView();
		Model.INSTANCE.destroyCardBattlefield(this.cardPositionGeyser);

		ArrayList<CardPosition> list = this.hashMap.getValue(eText);

		for (CardPosition cardPosition : list)
			Model.INSTANCE.destroyCardBattlefield(cardPosition);

		proceedToNextGameState();

	}

	private void createHashMap() {

		ArrayList<EText> list = new ArrayList<>();

		list.addAllLast(EText.UP);
		list.addAllLast(EText.DOWN);
		list.addAllLast(EText.LEFT);
		list.addAllLast(EText.RIGHT);

		for (EText eText : list) {

			this.hashMap.put(eText, new ArrayList<>());

			addCardPosition(eText, this.cardPositionGeyser);
			filterCardPositions(eText);

			if (this.hashMap.getValue(eText).isEmpty())
				this.hashMap.removeKey(eText);

		}

	}

	private void filterCardPositions(EText eText) {

		ArrayList<CardPosition> list = this.hashMap.getValue(eText);

		for (CardPosition cardPosition : list.clone())

			if (cardPosition.containsCard() && cardPosition.getCard() instanceof CardElemental)
				continue;

			else
				list.remove(cardPosition);

	}

	private void addCardPosition(EText eText, CardPosition cardPositionOld) {

		int row = cardPositionOld.getRow();
		int column = cardPositionOld.getColumn();

		switch (eText) {

		case UP:
			row--;
			break;

		case DOWN:
			row++;
			break;

		case LEFT:
			column--;
			break;

		case RIGHT:
			column++;
			break;

		default:
			break;

		}

		if (row < 0 || column < 0 || row > 3 || column > 3)
			return;

		CardPosition cardPositionNew = Battlefield.INSTANCE.getCardPosition(row, column);
		this.hashMap.getValue(eText).addLast(cardPositionNew);

		addCardPosition(eText, cardPositionNew);

	}

}
