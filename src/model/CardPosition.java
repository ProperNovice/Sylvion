package model;

import utils.Interfaces.ISelectCoordinatesAble;
import utils.Logger;
import utils.Vector2;

public class CardPosition implements ISelectCoordinatesAble {

	private int row, column;
	private Vector2 coordinatesCenter = null;

	public CardPosition(int row, int column, Vector2 vector2) {

		this.row = row;
		this.column = column;
		this.coordinatesCenter = vector2;

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
