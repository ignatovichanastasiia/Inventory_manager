package inventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***
 * CLASS STOCK
 * Static methods of the class are used for managing the product database and the main
 * warehouse data.
 */
public class Stock {
	private static Scanner sc;
	private static Product pr;
	private static boolean done;
	private static boolean ascending;
	private static List<Product> products = new ArrayList<Product>();
	private static List<Category> categories = new ArrayList<Category>();
	private static List<Inventory> inventories = new ArrayList<Inventory>();
	
	/***
	 * Method get entries from user and added new product. It returns `true` if the
	 * operation is successful, or `false` if it is not.
	 * 
	 * @return boolean (is successful or not)
	 */
	public static Product addProduct() {
		// String productUNHashCode, String productName, String productCategory, String
		// productSubcategory, double productPrice
		System.out.println("Let's make a new product.");
		String hash = getStringName("Enter or scan universal hash-code: ");
		String name = getStringName("Enter name of product: ");
		String brand = getStringName("Enter brand of product: ");
		String category = getStringName("Enter number of category: ");
		String subcategory = getStringName("Enter number of subcategory: ");
		Double price = (double) 0;
		try {
			price = Double.valueOf(getStringName("Enter price as number: ").trim());
		} catch (Exception e) {
			System.out.println("This number is not correct: " + e.getMessage());
		}
		return new Product(hash, name, brand, category, subcategory, price);

	}

	public static boolean setQuantity(Product product) {
		try {
			int stockQuantity = Integer.valueOf(getStringName("Enter quantity as number: ").trim());
			product.setProductStockQuantity(stockQuantity);
			return true;
		} catch (Exception e) {
			System.out.println("This number is not correct: " + e.getMessage());
		}
		return false;
	}

	public static void removeProduct(Product product) {
		products.remove(product);
		System.out.println(
				"Product " + product.getProductShopHashCode() + " (" + product.getProductName() + ") " + " is deleted");
	}

	public static void sortByPrice() {
		if (Stock.getStringName("Do you want to sort products by ascending? Y/N: ").trim().equalsIgnoreCase("y")) {
			ascending = true;
		} else {
			ascending = false;
		}
		if (ascending) {
			products.sort((p1, p2) -> ((Double) p1.getProductPrice()).compareTo((Double) (p2.getProductPrice())));
		} else {
			products.sort(
					(p1, p2) -> (-1) * ((Double) p1.getProductPrice()).compareTo((Double) (p2.getProductPrice())));
		}
		System.out.println("Products are sorted");
	}

	public static void getLowStockProducts() {
		try {
			double threshold = Double.valueOf(Stock.getStringName("Enter price: ").trim());
			done = false;
			products.forEach(p -> {
				if (p.getProductPrice() < threshold) {
					p.toString();
					done = true;
				}
			});
		} catch (Exception e) {
			e.getMessage();
		}
		if (!done) {
			System.out.println("Products not found");
		}
	}

	public static void sortProducts() {
		products.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
	}

	public static void allProductsInfo() {
		products.forEach(p -> p.toString());
	}

	public static Product findProductByCategory(String category) {
		done = false;
		products.forEach(p -> {
			if (p.getProductCategory().equalsIgnoreCase(category)) {
				p.toString();
				done = true;
			}
		});
		if (!done) {
			System.out.println("Category not found");
		}
		return pr;
	}

	public static Product findProductByName(String productName) {
		done = false;
		pr = products.getFirst();
		products.forEach(p -> {
			if (p.getProductName().equalsIgnoreCase(productName)) {
				pr = p;
				System.out.println("Product found");
				done = true;
			}
		});
		if (!done) {
			System.out.println("Product not found");
		}
		pr.toString();
		return pr;
	}

	public static String getStringName(String str) {
		int x = 5;
		while (x > 0) {
			System.out.println(str);
			String clientString = sc.nextLine();
			if (clientString.isBlank()) {
				System.out.println("This is not correct. You have attempts: " + (--x));
			} else {
				return clientString;
			}
		}
		return "Not real";

	}

	public boolean isCategory(Category category) {
		return (categories.contains(category));
	}

	public static void exit() {
		System.out.println("Bye");
		sc.close();
	}

	/***
	 * 
	 * @param sc
	 */
	public static void startStockScanner(Scanner sc) {
		setSc(sc);
	}
	
	public static void createDefaultCategories(){
//		TODO
	}
	
	public static String categoriesToString(){
		StringBuilder strBu = new StringBuilder();
		strBu.append("Categories:\n");
		if(categories.size()!=0) {
		categories.forEach(cat -> {
			strBu.append("\t"+(categories.indexOf(cat)+1)+" -> "+cat+";\n");
				strBu.append("subcategories: ");
				List<String> subcategories = cat.getSubcategories();
				if(cat.getSubcategories().size()!=0) {
				subcategories.forEach(sub -> {
					strBu.append("\t"+(subcategories.indexOf(sub)+1)+" -> "+sub+"; ");
				});
				}else {
					strBu.append("nothing.");
				}
		});
		}else {
			strBu.append("nothing.");
		}
		return strBu.toString();
	}
	
	public static String inventoriesToString(){
//		TODO
		return null;
	}

	/***
	 * Getters and setters
	 * 
	 */
	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		Stock.sc = sc;
	}

	public static boolean isAscending() {
		return ascending;
	}

	public static void setAscending(boolean ascending) {
		Stock.ascending = ascending;
	}

	public static List<Product> getProducts() {
		return products;
	}

	public static void setProducts(List<Product> products) {
		Stock.products = products;
	}

	public static List<Category> getCategories() {
		return categories;
	}

	public static void setCategories(List<Category> categories) {
		Stock.categories = categories;
	}

	public static List<Inventory> getInventories() {
		return inventories;
	}

	public static void setInventories(List<Inventory> inventories) {
		Stock.inventories = inventories;
	}

}
