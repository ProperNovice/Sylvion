package enums;

public enum ECardRavageSupport {

	ACID_LAKE(EResolveOrder.B), BLAZE(EResolveOrder.C), DEMOBILIZATION(EResolveOrder.B),
	DESICCATION(EResolveOrder.A), GEYSER(EResolveOrder.A), SIMOON(EResolveOrder.D),
	STONE_RAIN(EResolveOrder.C), TEMPORAL_TORNADO(EResolveOrder.B);

	private EResolveOrder eResolveOrder = null;

	private ECardRavageSupport(EResolveOrder eResolveOrder) {
		this.eResolveOrder = eResolveOrder;
	}

	public EResolveOrder getEResolveOrder() {
		return this.eResolveOrder;
	}

}
