package listCredentials;

import business.Credentials;

public class Hand extends ListCredentials {

	public Hand() {

		super.coordinatesList = Credentials.INSTANCE.cHand;
		super.objectsPerRow = 5;

	}

}
