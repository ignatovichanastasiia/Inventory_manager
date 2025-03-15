package inventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryService {
	private static List<Inventory> inventories = new ArrayList<Inventory>();
	private static List<Product> inventoryList;
	private static Map<Product,Integer> inventoryMap;
	
	
	public List<Product> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Product> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public static List<Inventory> getInventories() {
		return inventories;
	}

	public static void setInventories(List<Inventory> inventories) {
		InventoryService.inventories = inventories;
	}

	public static Map<Product, Integer> getInventoryMap() {
		return inventoryMap;
	}

	public static void setInventoryMap(Map<Product, Integer> inventoryMap) {
		InventoryService.inventoryMap = inventoryMap;
	}	
}
