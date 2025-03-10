package inventoryManager;

import java.util.Scanner;

public class Main {
	private static int program;

	public static void main(String[] args) {
		start();
		goProgram();
	}
	
	private static void start() {
//TODO test set of products and categories
//		предложить создать стандартный список категорий и подкатегорий
		Scanner scannerForInventory = new Scanner(System.in);
		
	}
	private static void menu(){
		System.out.println("""
				\nMenu:\t1-all products\t2-add product\t3-remove product\t4-sort products by price\t5-get category's product"
				+ "\nt-search by name\nt-products with stock below the threshold\nt-exit");
				""");
	}
	
	/***
	 * Мне нужно в меню:
	 * PRODUCT'S SEARCH
	 * Search product by name
	 * All products
	 * All products by category
	 * All products by subcategory
	 * 
	 * INVENTORY
	 * Add product's quantity one by one
	 * Add total quantity
	 * Compare
	 * 
	 * STOCK
	 * Receiving into the stock
	 * Write-off of from the stock
	 * 
	 * 
	 * PRODUCT'S SETTINGS
	 * Add type of product
	 * Remove type of product
	 * 
	 * CATEGORY'S SETTINGS
	 * All categories and subcategories
	 * Add category or subcategory
	 * Remove category
	 * Remove subcategory
	 * 
	 *
	 * 
	 * 
	 * 
	 */
	
	private static void goProgram() {
		do {
			menu();
			program = Integer.valueOf(Inventory.getStringName("Enter number of program: ").trim());
			switch(program) {
			case 1:
				Inventory.allProductsInfo();
				break;
			case 5:
				Inventory.findProductByCategory(Inventory.getStringName("Enter category:"));
				break;
			case 6:
				Inventory.findProductByName(Inventory.getStringName("Enter name of product you search: "));
				break;
			case 2:
				Inventory.addProduct();
				break;
			case 3:
				Inventory.removeProduct(Inventory.getStringName("Enter name of product you want to remove: "));
				break;
			case 4:
				Inventory.sortByPrice();
				Inventory.allProductsInfo();
				Inventory.sortProducts();
				break;
			
			case 7:
				Inventory.getLowStockProducts();
				break;
			case 0:
				Inventory.exit();
				break;
			}
		}while(program!=0);
	}
}



//Project 2: Inventory Management System  
//Objective: Implement an inventory system using various array and list operations. 
//Requirements: 
//1. Create a Product class with: 
//○ name (String) 
//○ category (String) 
//○ price (double) 
//○ stockQuantity (int) 
//○ Methods: 
//■ updateStock(int quantity): Increases/decreases stock. 
//■ getProductDetails(): Returns a formatted string. 


//2. Create an Inventory class with: 
//○ products (ArrayList of Product objects) 
//○ Methods: 
//■ addProduct(Product p): Adds a product. 
//■ removeProduct(String name): Removes a product. 
//■ searchByCategory(String category): Returns all products in a 
//category. 
//■ sortByPrice(boolean ascending): Sorts the products by price. 
//■ getLowStockProducts(int threshold): Returns products with 
//stock below the threshold. 
//3. Implementation Details: 
//○ Use array operations to manipulate stock levels. 
//○ Use sorting (Bubble Sort, Selection Sort, or Java’s built-in) to sort 
//products. 
//○ Allow the user to search, filter, and modify the inventory dynamically. 