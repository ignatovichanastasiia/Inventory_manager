package inventoryManager;

public interface InventoryUpdatable {

	boolean addProductQuantityOneByOne(String hashcode);
	boolean addTotalProductQuantity(String hashcode, int totalQuantity);
	boolean resetProductQuantity(String hashcode);
	
}
