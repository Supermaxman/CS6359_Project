package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DbManager;
import db.dao.CartDao;
import db.dao.CategoryDao;
import db.dao.DaoException;
import db.dao.InventoryDao;
import db.dao.PaintingDao;
import db.dao.ProductDao;
import db.dao.impl.CartDaoImpl;
import db.dao.impl.CategoryDaoImpl;
import db.dao.impl.InventoryDaoImpl;
import db.dao.impl.PaintingDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.services.PaintingPersistenceService;
import domain.product.Category;
import domain.product.Painting;
import domain.product.Product;

public class PaintingPersistenceServiceImpl implements PaintingPersistenceService {

	private DbManager db = new DbManager();
	private InventoryDao inventoryDao = new InventoryDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private PaintingDao paintDao = new PaintingDaoImpl();
	private CategoryDao catDao = new CategoryDaoImpl();

	@Override
	public void create(Painting painting, Integer invnId) throws SQLException, DaoException {
		Connection connection = db.getConnection();

		try {
			connection.setAutoCommit(false);

			prodDao.create(connection, painting);
			paintDao.create(connection, painting);
			inventoryDao.addProduct(connection, painting.getProdId(), invnId);

			connection.commit();
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			if (connection != null) {
				connection.setAutoCommit(true);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}
	}

	@Override
	public List<Painting> retrieveAll() throws SQLException, DaoException {
		Connection connection = db.getConnection();

		try {
			connection.setAutoCommit(false);

			List<Painting> paintings = paintDao.retrieveAll(connection);
			for (Product prod : paintings) {
				Category prodCat = catDao.retrieveByProduct(connection, prod.getProdId());
				prod.setCategory(prodCat);
			}
			connection.commit();
			return paintings;
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			if (connection != null) {
				connection.setAutoCommit(true);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}

	}

	@Override
	public Painting retrieve(Integer prodId) throws SQLException, DaoException {
		Connection connection = db.getConnection();

		try {
			connection.setAutoCommit(false);

			Painting painting = paintDao.retrieve(connection, prodId);
			Category prodCat = catDao.retrieveByProduct(connection, painting.getProdId());
			painting.setCategory(prodCat);
			
			connection.commit();
			return painting;
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			if (connection != null) {
				connection.setAutoCommit(true);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}
	}

	@Override
	public int update(Painting product) throws SQLException, DaoException {
		Connection connection = db.getConnection();

		try {
			connection.setAutoCommit(false);

			int count = paintDao.update(connection, product);
			int prodCount = prodDao.update(connection, product);
			
			if (prodCount != count) {
				throw new DaoException("Unable to update Product!");
			}
			
			connection.commit();
			return count;
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			if (connection != null) {
				connection.setAutoCommit(true);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}
	}

	@Override
	public int delete(Painting product) throws SQLException, DaoException {
		Connection connection = db.getConnection();

		try {
			connection.setAutoCommit(false);
			
			cartDao.removeProductFromAllCarts(connection, product.getProdId());
			// TODO consider moving this to inventoryDao from prodDao.
			Integer invnId = prodDao.retrieveInventoryId(connection, product);
			inventoryDao.removeProduct(connection, product.getProdId(), invnId);
			
			int count = paintDao.delete(connection, product);
			int prodCount = prodDao.delete(connection, product);
			
			if (prodCount != count) {
				throw new DaoException("Unable to update Product!");
			}
			
			connection.commit();
			return count;
		} catch (Exception ex) {
			connection.rollback();
			throw ex;
		} finally {
			if (connection != null) {
				connection.setAutoCommit(true);
				if (!connection.isClosed()) {
					connection.close();
				}
			}
		}
	}

}
