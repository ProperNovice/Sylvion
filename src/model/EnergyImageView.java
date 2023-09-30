package model;

import utils.ImageView;
import utils.Interfaces.IImageViewAble;

public class EnergyImageView implements IImageViewAble {

	public EnergyImageView() {

		String filePath = "energy.png";
		new ImageView(filePath, this);

	}

}
