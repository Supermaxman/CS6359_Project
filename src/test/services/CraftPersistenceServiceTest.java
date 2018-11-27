package test.services;

import db.services.impl.CraftPersistenceServiceImpl;
import domain.product.Craft;
import test.utils.TestUtils;

public class CraftPersistenceServiceTest  extends AbstractProductCategoryPersistenceServiceTest<Craft> {

	public CraftPersistenceServiceTest() {
		super(CraftPersistenceServiceImpl.getInstance());
	}
	
	@Override
	protected Craft generate() throws Exception {
		return TestUtils.generateCraft();
	}

	@Override
	protected void modify(Craft prod) throws Exception {
		prod.setHeight(2 * prod.getHeight());
		prod.setLength(2 * prod.getLength());
		prod.setWidth(2 * prod.getWidth());
		prod.setUsage("NEW USAGE");
	}
	
}