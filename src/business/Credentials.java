package business;

import utils.Enums.RearrangeTypeEnum;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Sylvion", numbersImageViewColor = "black";
	public boolean colliderVisibility = true;
	public double stagePixesOnTheLeft = 180 - 180, gapBetweenBorders = 25, textHeight = 50,
			selectEventHandlerAbleDimension = 100, animationStep = 4;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel, cImageViewIndicator;
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.PIVOT;

	public Vector2 dCard, dEnergy;
	public Vector2 cCardPosition, cDeck, cDiscardPile, cHand, cEdges, cRavageStack, cEnergy,
			cCardPositionEdges, cSquirrelStack;
	public double gapBetweenEdges;

	private Credentials() {

		double x = 0, y = 0;

		// d card

		x = 213;
		y = 326;
		this.dCard = new Vector2(x, y);

		// gaps

		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		// frame

		x = 0;
		x += 2 * this.gapBetweenBorders;
		x += 11 * this.dCard.x;
		x += 10 * this.dGapBetweenComponents.x;

		this.dFrame = new Vector2(2560 - 4 - 636 + 636, 1368 - 2);
		this.dFrame = new Vector2(x, 1368 - 2);

		// c image view indicator

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.cImageViewIndicator = new Vector2(x, y);

		// select event handler dimension

		this.selectEventHandlerAbleDimension = this.dCard.x / 2;

		// d card position

		x = this.dFrame.x;
		x -= this.gapBetweenBorders;
		x -= 5 * this.dCard.x;
		x -= 4 * this.dGapBetweenComponents.x;
		x += this.dCard.x / 2;
		y = this.gapBetweenBorders;
		y += this.dCard.y / 2;
		this.cCardPosition = new Vector2(x, y);

		// c discard pile

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.cDiscardPile = new Vector2(x, y);

		// c deck

		x = this.cDiscardPile.x;
		x += this.dCard.x;
		x += this.dGapBetweenComponents.x;
		y = this.cDiscardPile.y;
		this.cDeck = new Vector2(x, y);

		// c hand

		x = this.gapBetweenBorders;
		y = this.cDiscardPile.y;
		y += this.dCard.y;
		y += this.dGapBetweenComponents.y;
		this.cHand = new Vector2(x, y);

		// c edges

		x = this.cCardPosition.x;
		x -= this.dCard.x;
		x -= this.dGapBetweenComponents.x;
		y = this.cCardPosition.y;
		this.cEdges = new Vector2(x, y);

		// gap between edges

		y = this.dFrame.y;
		y -= 2 * this.gapBetweenBorders;
		y -= this.dCard.y;
		y /= 11;
		this.gapBetweenEdges = y;

		// c ravage stack

		x = this.dFrame.x;
		x -= this.gapBetweenBorders;
		x -= this.dCard.x / 2;
		y = this.cCardPosition.y;
		this.cRavageStack = new Vector2(x, y);
		this.gapBetweenEdges = y;

		// c text panel

		x = this.cDeck.x;
		x += this.dCard.x;
		x += this.dGapBetweenComponents.x;
		y = this.cDeck.y;
		y += this.dCard.y / 2;

		this.cTextPanel = new Vector2(x, y);

		// d energy

		x = 43;
		y = 66;
		this.dEnergy = new Vector2(x, y);

		// c energy

		x = this.cEdges.x;
		x -= this.dCard.x / 2;
		x -= this.dGapBetweenComponents.x;
		y = this.gapBetweenBorders;
		this.cEnergy = new Vector2(x, y);

		// c card position edges

		x = this.cEdges.x;
		y = this.dFrame.y;
		y /= 2;
		this.cCardPositionEdges = new Vector2(x, y);

		// c squirrel stack

		x = this.cRavageStack.x;
		x -= this.dCard.x;
		x -= this.dGapBetweenComponents.x;
		y = this.cRavageStack.y;
		this.cSquirrelStack = new Vector2(x, y);

	}

}
