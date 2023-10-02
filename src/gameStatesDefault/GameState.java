package gameStatesDefault;

import business.Battlefield;
import business.CardPosition;
import business.ListsManager;
import cards.Card;
import enums.EText;
import javafx.scene.input.KeyCode;
import utils.ArrayList;
import utils.Flow;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.SelectImageViewManager;
import utils.TextManager;

public abstract class GameState {

	public abstract void execute();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		TextManager.INSTANCE.concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		handleKeyPressed(keyCode);

		int keyCodeID = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		if (keyCodeID == -1)
			return;

		EText textEnumPressed = TextManager.INSTANCE.getTextEnumOptionPressed(keyCodeID);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void handleKeyPressed(KeyCode keyCode) {

	}

	protected void executeTextOption(EText eText) {

	}

	protected final ArrayList<Class<? extends GameState>> getFlow() {
		return Flow.INSTANCE.getFlow();
	}

	protected final void proceedToNextGameState() {
		Flow.INSTANCE.proceed();
	}

	protected final void executeGameState(Class<? extends GameState> gameState) {
		Flow.INSTANCE.executeGameState(gameState);
	}

	protected final void concealText() {
		TextManager.INSTANCE.concealText();
	}

	protected final ListsManager getListsManager() {
		return ListsManager.INSTANCE;
	}

	protected final SelectImageViewManager getSelectImageViewManager() {
		return SelectImageViewManager.INSTANCE;
	}

	public final void handleCardPressed(Card card) {

		if (ListsManager.INSTANCE.hand.getArrayList().contains(card))
			handleCardPressedHand(card);

		else
			for (CardPosition cardPosition : Battlefield.INSTANCE.getCardPositionsClone())
				if (cardPosition.containsCard())
					if (cardPosition.getCard().equals(card))
						handleCardPressedBattlefield(card, cardPosition);

	}

	public void handleCardPositionBattlefieldPressed(CardPosition cardPosition) {

	}

	public void handleCardPositionEdgesPressed() {

	}

	protected void handleCardPressedHand(Card card) {

	}

	protected void handleCardPressedBattlefield(Card card, CardPosition cardPosition) {

	}

}
