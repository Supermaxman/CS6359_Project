package db.services.impl;

import db.dao.impl.SculptureDaoImpl;
import db.services.SculpturePersistenceService;
import domain.product.Sculpture;

public class SculpturePersistenceServiceImpl extends AbstractProductCategoryPersistenceService<Sculpture> implements SculpturePersistenceService {
	
	public SculpturePersistenceServiceImpl() {
		super(new SculptureDaoImpl());
	}
		
}
