package db.services.impl;

import db.dao.impl.PaintingDaoImpl;
import db.services.PaintingPersistenceService;
import domain.product.Painting;

public class PaintingPersistenceServiceImpl extends AbstractProductCategoryPersistenceService<Painting> implements PaintingPersistenceService {
	
	public PaintingPersistenceServiceImpl() {
		super(new PaintingDaoImpl());
	}
		
}
