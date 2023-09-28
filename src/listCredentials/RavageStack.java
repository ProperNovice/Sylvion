package listCredentials;

import business.Credentials;
import utils.Enums.LayerZListEnum;
import utils.Enums.RearrangeTypeEnum;
import utils.Enums.RelocateTypeEnum;

public class RavageStack extends ListCredentials {

	public RavageStack() {

		super.coordinatesList = Credentials.INSTANCE.cRavageStack.clone();
		super.relocateTypeEnum = RelocateTypeEnum.CENTER;
		super.layerZListEnum = LayerZListEnum.TO_FRONT_FIRST_IMAGEVIEW;
		super.rearrangeTypeEnum = RearrangeTypeEnum.STATIC;

	}

}
