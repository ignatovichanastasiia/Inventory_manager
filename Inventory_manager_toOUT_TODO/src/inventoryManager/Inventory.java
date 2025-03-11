package inventoryManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//clean

public class Inventory implements InventoryUpdatable{
	private static int inventoryCounter = 0;
	private List<Product> inventoryList;
	private Map<Product,Integer> inventoryMap;
	private Product inventoryProduct;
	private int inventoryProductCounter;
	private String inventoryName;
	
	/***
	 * CLASS INVENTORY 
	 *The class object and non-static methods are used for conducting inventory 
	 *(reconciliation of products in the store with the quantities in the warehouse). 
	 *Each subsequent check involves creating a new object of the class. The product to be 
	 *inventoried is selected, and if the hash code matches, its quantity is increased by one 
	 *(scanning from the shelves). In case of an error, the result can be reset, 
	 *or the product quantity can be manually specified. When the product is changed, 
	 *an inventory number is assigned, and if necessary, its result can be edited later. 
	 *The inventory process does not alter warehouse data; it allows for re-scanning items or 
	 *manually specifying the product quantity to compare with warehouse data, identifying shortages or 
	 *unaccounted items.
	 */
	
	
	/***
	 * The constructor takes no parameters for initialization. In the constructor, a new list is created 
	 * from the already existing products, and the first product in the list becomes the item to be checked. 
	 * If the incoming (scanned) hash code belongs to the checked product, the quantity of the product in 
	 * the map is increased by one. 
	 */
	public Inventory() {
		this.inventoryName = genID();
		this.inventoryList = Stock.getProducts();
		this.inventoryProduct = Stock.getProducts().getFirst();
		this.inventoryProductCounter = 0;
		this.inventoryMap = new HashMap<Product,Integer>();
	}

	/***
	 * The method takes a hash code as a parameter. 
	 * If the hash code matches the selected product's hash code, the product 
	 * quantity is increased by 1, and the method returns `true`. If it does not match, 
	 * the method returns `false`.
	 */
	@Override
	public boolean addProductQuantityOneByOne(String hashcode) {
		if(inventoryProduct.getProductShopHashCode().equals(hashcode)) {
			inventoryProductCounter++;
			return true;
		}
		return false;
	}

	/***
	 * The method takes the product's hash code and the final quantity as parameters. 
	 * The method allows manually entering the final product quantity for the inventory. 
	 * It returns `true` if the operation is successful, or `false` if it is not.
	 * @return boolean
	 */
	@Override
	public boolean addTotalProductQuantity(String hashcode, int totalQuantity) {
		if(!hashcode.isBlank() && totalQuantity>=0 && (inventoryProduct.getProductShopHashCode().equals(hashcode))){
			inventoryMap.put(inventoryProduct, inventoryProductCounter);
		}
		return false;
	}

	/***
	 * The method takes the hash code of the product to be checked as a parameter. 
	 * The product quantity for the inventory is reset to zero.
	 * It returns `true` if the operation is successful, or `false` if it is not.
	 * @param hashcode
	 * @return boolean (is successful or not)
	 */
	@Override
	public boolean resetProductQuantity(String hashcode) {
		if(!hashcode.isBlank() && (inventoryProduct.getProductShopHashCode().equals(hashcode))) {
			inventoryMap.replace(inventoryProduct, 0);
			return true;
		}
		return false;
	}
	
	/**
	 * Utility method generates ID
	 * 
	 * @return ID
	 */
	private static String genID() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return "inv"+now.format(formatter)+inventoryCounter++;
	}
	/***
	 * Getters and setters
	 *
	 */
	
	public List<Product> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Product> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public Map<Product, Integer> getInventoryMap() {
		return inventoryMap;
	}

	public void setInventoryMap(Map<Product, Integer> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}

	public Product getInventoryProduct() {
		return inventoryProduct;
	}

	public void setInventoryProduct(Product inventoryProduct) {
		this.inventoryProduct = inventoryProduct;
	}

	public int getInventoryProductCounter() {
		return inventoryProductCounter;
	}

	public void setInventoryProductCounter(int inventoryProductCounter) {
		this.inventoryProductCounter = inventoryProductCounter;
	}

	public String getInventoryName() {
		return inventoryName;
	}	
}

