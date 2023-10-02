package business;

import enums.ELayerZ;
import utils.Background;
import utils.HashMap;

public enum LayerZBackground {

	INSTANCE;

	private HashMap<ELayerZ, Background> hashMap = new HashMap<>();

	private LayerZBackground() {

	}

	public void setVisible(ELayerZ eLayerZ, boolean value) {

		if (!this.hashMap.containsKey(eLayerZ))
			this.hashMap.put(eLayerZ, new Background(eLayerZ));

		Background background = this.hashMap.getValue(eLayerZ);
		background.getImageView().setVisible(value);
		background.getImageView().toFront();

	}

}
