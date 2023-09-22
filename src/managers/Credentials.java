package managers;

import utils.Enums.RearrangeTypeEnum;
import utils.Vector2;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Sylvion", numbersImageViewColor = "black";
	public boolean colliderVisibility = true;
	public double gapBetweenBorders = 25, textHeight = 50, selectEventHandlerAbleDimension = 100,
			animationStep = 4;
	public Vector2 dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast;
	public Vector2 cTextPanel, cImageViewIndicator;
	public RearrangeTypeEnum rearrangeTypeEnumText = RearrangeTypeEnum.LINEAR;

	public Vector2 cCardPosition;
	public Vector2 dCard;

	private Credentials() {

		double x = 0, y = 0;

		this.dFrame = new Vector2(2560 - 4 - 636, 1368 - 2);
		this.dGapBetweenComponents = new Vector2(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		// c text panel

		this.cTextPanel = new Vector2(x, y);

		// c image view indicator

		x = this.gapBetweenBorders;
		y = this.gapBetweenBorders;
		this.cImageViewIndicator = new Vector2(x, y);

		// d card

		x = 213;
		y = 326;
		this.dCard = new Vector2(x, y);

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

	}

}
