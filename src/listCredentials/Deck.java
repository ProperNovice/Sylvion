package listCredentials;

import managers.Credentials;
import utils.Enums.RearrangeTypeEnum;

public class Deck extends ListCredentials {

	public Deck() {

		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;
		super.coordinatesList = Credentials.INSTANCE.cDeck;

	}

}
