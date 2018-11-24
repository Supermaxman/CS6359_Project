package test.services;

import db.services.impl.PaintingPersistenceServiceImpl;
import domain.product.Painting;
import test.utils.TestUtils;

public class PaintingPersistenceServiceTest extends AbstractProductCategoryPersistenceServiceTest<Painting> {

	public PaintingPersistenceServiceTest() {
		super(PaintingPersistenceServiceImpl.getInstance());
	}
	
	@Override
	protected Painting generate() throws Exception {
		return TestUtils.generatePainting();
	}

	@Override
	protected void modify(Painting prod) throws Exception {
		prod.setLength(2 * prod.getLength());
		prod.setWidth(2 * prod.getWidth());
		prod.setCanvasType("NEW CANVAS");
		prod.setPaintType("NEW PAINT");
	}
	
}
