package db.services;

import java.sql.SQLException;

import db.dao.DaoException;
import domain.product.Painting;

public interface PaintingPersistenceService {
	
	public void create(Painting painting, Integer invnId) throws SQLException, DaoException;
	
}
