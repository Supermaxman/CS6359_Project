package test.services;

import db.services.impl.SculpturePersistenceServiceImpl;
import domain.product.Sculpture;
import test.utils.TestUtils;

public class SculpturePersistenceServiceTest  extends AbstractProductCategoryPersistenceServiceTest<Sculpture> {

	public SculpturePersistenceServiceTest() {
		super(new SculpturePersistenceServiceImpl());
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