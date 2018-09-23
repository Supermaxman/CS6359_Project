package db.dao;

import domain.user.Inventory;

public interface InventoryDao {

	public Inventory getUserInventory(Integer userId);
	
	public int updateInventory(Inventory inv);
}
