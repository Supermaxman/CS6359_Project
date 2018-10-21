package test.dao;

import db.dao.impl.CraftDaoImpl;
import domain.product.Craft;
import test.utils.TestUtils;

public class CraftDaoTest extends AbstractProductCategoryDaoTest<Craft> {

	public CraftDaoTest() {
		super(new CraftDaoImpl());
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
