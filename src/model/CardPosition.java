package model;

import cards.Card;
import utils.Interfaces.ISelectCoordinatesAble;
import utils.Logger;
import utils.Vector2;

public class CardPosition implements ISelectCoordinatesAble {

	private int row, column;
	private Vector2 coordinatesCenter = null;
	private Card card = null;

	public CardPosition(int row, int column, Vector2 vector2) {

		this.row = row;
		this.column = column;
		this.coordinatesCenter = vector2;

	}

	public void addCardRelocate(Card card) {

		this.card = card;
		this.card.getImageView().relocateCenter(this.coordinatesCenter);

		printAction("adding", this.card);

	}

	public Card removeCard() {

		Card card = this.card;
		this.card = null;

		printAction("removing", card);

		return card;

	}

	private void printAction(String action, Card card) {

		Logger.INSTANCE.logNewLine("adding card to position -> " + this.row + " - " + this.column);
		card.print();

	}

	public boolean containsCard() {
		return this.card != null;
	}

	public Card getCard() {
		return this.card;
	}

	@Override
	public void handleMousePressedPrimary() {

		print();

	}

	public void print() {

		Logger.INSTANCE.logNewLine("*/");
		Logger.INSTANCE.logNewLine("printing card position");

		Logger.INSTANCE.log("row -> " + this.row);
		Logger.INSTANCE.log("column -> " + this.column);
		Logger.INSTANCE.newLine();

		Logger.INSTANCE.logNewLine("/*");

	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	@Override
	public Vector2 getCoordinatesCenter() {
		return this.coordinatesCenter;
	}

}
