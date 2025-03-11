package inventoryManager;

import java.util.Scanner;

public class Main {
	private static int program;

	public static void main(String[] args) {
		start();
//		goProgram();
	}

	private static void start() {
//TODO test set of products and categories
//		предложить создать стандартный список категорий и подкатегорий
		Scanner scannerForInventory = new Scanner(System.in);

	}

	private static void menu() {
		System.out.println(
				"""
						\nMenu:\t1-all products\t2-add product\t3-remove product\t4-sort products by price\t5-get category's product"
						+ "\nt-search by name\nt-products with stock below the threshold\nt-exit");
						""");
	}

	/***
	 * Мне нужно в меню: PRODUCT'S SEARCH Search product by name All products All
	 * products by category All products by subcategory
	 * 
	 * INVENTORY Add product's quantity one by one Add total quantity Compare
	 * 
	 * STOCK Receiving into the stock Write-off of from the stock
	 * 
	 * 
	 * PRODUCT'S SETTINGS Add type of product Remove type of product
	 * 
	 * CATEGORY'S SETTINGS All categories and subcategories Add category or
	 * subcategory Remove category Remove subcategory
	 * 
	 *
	 * 
	 * 
	 * 
	 */

//	private static void goProgram() {
//		do {
//			menu();
//			program = Integer.valueOf(Inventory.getStringName("Enter number of program: ").trim());
//			switch(program) {
//			case 1:
//				Inventory.allProductsInfo();
//				break;
//			case 5:
//				Inventory.findProductByCategory(Inventory.getStringName("Enter category:"));
//				break;
//			case 6:
//				Inventory.findProductByName(Inventory.getStringName("Enter name of product you search: "));
//				break;
//			case 2:
//				Inventory.addProduct();
//				break;
//			case 3:
//				Inventory.removeProduct(Inventory.getStringName("Enter name of product you want to remove: "));
//				break;
//			case 4:
//				Inventory.sortByPrice();
//				Inventory.allProductsInfo();
//				Inventory.sortProducts();
//				break;
//			
//			case 7:
//				Inventory.getLowStockProducts();
//				break;
//			case 0:
//				Inventory.exit();
//				break;
//			}
//		}while(program!=0);
//	}
}

