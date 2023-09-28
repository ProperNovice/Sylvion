package listCredentials;

import business.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;

public class DiscardPile extends ListCredentials {

	public DiscardPile() {

		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.coordinatesList = Credentials.INSTANCE.cDiscardPile;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.showListSize = true;

	}

}
