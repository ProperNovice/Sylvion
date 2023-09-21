package cards;

import enums.ECardRavageSupport;
import enums.EResolveOrder;
import interfaces.IResolveOrder;
import interfaces.ISupport;
import utils.Logger;

public class CardRavageSupport extends CardRavage implements ISupport, IResolveOrder {

	private ECardRavageSupport eCardRavageSupport = null;

	public CardRavageSupport(ECardRavageSupport eCardRavageSupport) {

		this.eCardRavageSupport = eCardRavageSupport;

		super.createCard();

	}

	@Override
	protected void printCredentials() {

		Logger.INSTANCE.log(this.eCardRavageSupport.toString().toLowerCase());
		Logger.INSTANCE.log("resolve order -> " + getEResolveOrder().toString().toLowerCase());

	}

	@Override
	public ECardRavageSupport getECardRavageSupport() {
		return this.eCardRavageSupport;
	}

	@Override
	public EResolveOrder getEResolveOrder() {
		return this.eCardRavageSupport.getEResolveOrder();
	}

	@Override
	protected String getFilePath() {
		return super.getFilePath() + "support/" + eCardRavageSupport.toString().toLowerCase()
				+ ".jpg";
	}

}
