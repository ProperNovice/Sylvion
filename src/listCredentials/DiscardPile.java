package listCredentials;

import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;

public class DiscardPile extends ListCredentials {

	public DiscardPile() {

		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.coordinatesList = Credentials.INSTANCE.cDiscardPile;

	}

}
