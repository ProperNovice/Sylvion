package listCredentials;

import managers.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;

public class Deck extends ListCredentials {

	public Deck() {

		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.coordinatesList = Credentials.INSTANCE.cDeck;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.showListSize = true;

	}

}
