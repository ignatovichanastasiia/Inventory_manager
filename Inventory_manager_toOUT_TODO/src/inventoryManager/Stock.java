package inventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	
	/***
	 * PRODUCT SERVICE METHODS
	 */
	
	/***
	 * Method creates product.
	 * Method get entries from user and added new product. It returns `true` if the
	 * operation is successful, or `false` if it is not.
	 * 
	 * @return boolean (is successful or not)
	 */
	public static Product addProduct() {
		// String productUNHashCode, String productName, String productCategory, String
		// productSubcategory, double productPrice
		System.out.println("Let's make a new product.");
		String hash = getStringEntry("Enter or scan universal hash-code: ");
		String name = getStringEntry("Enter name of product: ");
		String brand = getStringEntry("Enter brand of product: ");
		String category = getStringEntry("Enter number of category: ");
		String subcategory = getStringEntry("Enter number of subcategory: ");
		Double price = (double) 0;
		try {
			price = Double.valueOf(getStringEntry("Enter price as number: ").trim());
		} catch (Exception e) {
			System.out.println("This number is not correct: " + e.getMessage());
		}
		return new Product(hash, name, brand, category, subcategory, price);

	}

	/***
	 * Method set product's quantity. Default quantity of a new product is zero.
	 * @param product
	 * @return
	 */
	public static boolean setProductQuantity(Product product) {
		try {
			int stockQuantity = Integer.valueOf(getStringEntry("Enter quantity as number: ").trim());
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
	
	public static void doProductSale() {
//		TODO
	}
	
	public static void doProductsMarkdown() {
//		TODO
	}
	
	public static void doPartProductsMarkdown() {
		//TODO
	}
	
	/***
	 * FIND PRODUCT METHODS
	 */
	
	public static List<Product> findProductsByCategory(String categoryName) {
		List<Product> listProductsWithEntryCategory = new ArrayList<Product>();
		Optional<Category> category = findCategoryByName(categoryName);
//		TODO
		done = false;
		products.forEach(p -> {
			if (p.getProductCategory().equals(category)) {
				p.toString();
				done = true;
			}
		});
		if (!done) {
			System.out.println("Category not found");
		}
		return listProductsWithEntryCategory;
	}
	
	public static List<Product> findProductsBySubcategory(String subcategoryName){
		List <Product> listProductWithEntrySubcategory = new ArrayList<Product>();
//		TODO
		return listProductWithEntrySubcategory;
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
	

	/***
	 * SORT PRODUCT METHODS
	 */
	
	
	public static void sortProductsByPrice() {
		if (Stock.getStringEntry("Do you want to sort products by ascending? Enter 'Y' for \"yes\": ").trim().equalsIgnoreCase("y")) {
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
		System.out.println("Products are sorted by price");
	}

	public static void getLowStockProducts() {
		try {
			double threshold = Double.valueOf(Stock.getStringEntry("Enter price: ").trim());
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

	public static void sortProductsByName() {
		products.sort((p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
		System.out.println("Products are sorted by name");
	}

	/***
	 * CATEGORY SERVICE METHODS
	 */

	public static Category addCategory() {
//		TODO
		String categoryName = getStringEntry("Enter name of new Category");
		//логика по которой предложить уже имеющиеся категории с похожими именами
		return new Category(categoryName);
	}
	
	public static boolean removeCategory() {
//		TODO
		return false;
	}

	public static Optional<Category> findCategoryByName(String categoryName){
		Optional<Category> cat = Optional.of(null);
//		TODO
		return cat;
		
	}
	
	public static Optional<Category> findCategoryByIndex(int index) {
		Optional<Category> cat = Optional.of(null);
//		TODO
		return cat;
	}
	
	public static void sortCategoryByName() {
//		TODO
	}

	public static void editCategoryName() {
//		TODO
	}
	
	public static void editCategoryListSubcategory() {
//		TODO
//		Добавить, удалить, переименовать
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

	public static String getStringEntry(String str) {
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
	
//	TODO
	public static String productsToString() {
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

}
