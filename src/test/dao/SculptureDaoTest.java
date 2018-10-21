package test.dao;

import db.dao.impl.SculptureDaoImpl;
import domain.product.Sculpture;
import test.utils.TestUtils;

public class SculptureDaoTest extends AbstractProductCategoryDaoTest<Sculpture> {

	public SculptureDaoTest() {
		super(new SculptureDaoImpl());
	}

	@Override
	protected Sculpture generate() throws Exception {
		return TestUtils.generateSculpture();
	}

	@Override
	protected void modify(Sculpture prod) throws Exception {
		prod.setLength(2 * prod.getLength());
		prod.setWidth(2 * prod.getWidth());
		prod.setHeight(2 * prod.getHeight());
		prod.setWeight(2 * prod.getWeight());
		prod.setMaterial("NEW MATERIAL");
	}
		
}