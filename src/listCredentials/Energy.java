package listCredentials;

import business.Credentials;
import utils.Enums.DirectionEnum;
import utils.Enums.RelocateTypeEnum;

public class Energy extends ListCredentials {

	public Energy() {

		super.coordinatesList = Credentials.INSTANCE.cEnergy;
		super.directionEnumHorizontal = DirectionEnum.LEFT;
		super.relocateTypeEnum = RelocateTypeEnum.TOP_RIGHT;

	}

}
