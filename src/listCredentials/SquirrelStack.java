package listCredentials;

import business.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class SquirrelStack extends ListCredentials {

	public SquirrelStack() {

		super.coordinatesList = Credentials.INSTANCE.cSquirrelStack.clone();
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;

	}

}
