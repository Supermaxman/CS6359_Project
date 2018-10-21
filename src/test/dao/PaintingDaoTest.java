package test.dao;

import db.dao.impl.PaintingDaoImpl;
import domain.product.Painting;
import test.utils.TestUtils;

public class PaintingDaoTest extends AbstractProductCategoryDaoTest<Painting> {

	public PaintingDaoTest() {
		super(new PaintingDaoImpl());
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