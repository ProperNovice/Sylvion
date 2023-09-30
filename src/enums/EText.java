package enums;

import utils.Enums.TextTypeEnum;
import utils.TextManager;

public enum EText {

	CANCEL("Cancel", TextTypeEnum.OPTION),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	START_GAME("Start game", TextTypeEnum.OPTION),
	VOID("", TextTypeEnum.INDICATOR),
	YOU_LOST("You lost", TextTypeEnum.INDICATOR),
	YOU_WON("You won", TextTypeEnum.INDICATOR),
	MOVE_ELEMENTALS("Move elementals", TextTypeEnum.INDICATOR),
	DRAW_CARD("Draw card", TextTypeEnum.INDICATOR),
	REVEAL_RAVAGE_CARDS("Reveal ravage cards", TextTypeEnum.INDICATOR),
	PLAY_HEDGEHOG("Play Hedgehog", TextTypeEnum.OPTION),
	CHOOSE_CARD("Choose card", TextTypeEnum.INDICATOR),
	CHOOSE_CARDS("Choose cards", TextTypeEnum.INDICATOR),
	RESOLVE_BLAZE("Resolve Blaze", TextTypeEnum.INDICATOR),
	RESOLVE_SIMOON("Resolve Simoon", TextTypeEnum.INDICATOR),
	RESOLVE_ACID_LAKE("Resolve Acid Lake", TextTypeEnum.INDICATOR),
	RESOLVE_STONE_RAIN("Resolve Stone Rain", TextTypeEnum.INDICATOR),
	RESOLVE_TEMPORAL_TORNADO("Resolve Temporal Tornado", TextTypeEnum.INDICATOR),
	RESOLVE_GEYSER("Resolve Geyser", TextTypeEnum.INDICATOR),
	REINFORCEMENTS("Reinforcements", TextTypeEnum.INDICATOR),

	;

	private TextTypeEnum textTypeEnum = null;
	private String string = null;

	private EText(String string, TextTypeEnum textTypeEnum) {

		this.string = string;
		this.textTypeEnum = textTypeEnum;

	}

	public void show() {
		TextManager.INSTANCE.showText(this, getString());
	}

	public void showAdditionally(String string) {
		TextManager.INSTANCE.showText(this, getString() + string);
	}

	public void showAdditionally(int integer) {
		showAdditionally(Integer.toString(integer));
	}

	public void showInstead(String string) {
		TextManager.INSTANCE.showText(this, string);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
