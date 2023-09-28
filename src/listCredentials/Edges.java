package listCredentials;

import business.Credentials;
import utils.Enums.RelocateTypeEnum;

public class Edges extends ListCredentials {

	public Edges() {

		super.coordinatesList = Credentials.INSTANCE.cEdges;
		super.objectsPerRow = 1;
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.gapBetweenComponents.y = Credentials.INSTANCE.gapBetweenEdges;

	}

}
