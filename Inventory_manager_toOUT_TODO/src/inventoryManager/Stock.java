package inventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/***
 * CLASS STOCK Static methods of the class are used for managing the product
 * database and the main warehouse data.
 */
public class Stock {
	private static Scanner sc;
	private static boolean ascending;
	private static List<Product> products = new ArrayList<Product>();
	private static List<Category> categories = new ArrayList<Category>();

	/***
	 * PRODUCT SERVICE METHODS
	 */

	/***
	 * Method creates product. Method get entries from user and added new product.
	 * It returns `true` if the operation is successful, or `false` if it is not.
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
	 * 
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
		// TODO
	}

	/***
	 * FIND PRODUCT METHODS
	 */

	/***
	 * The method searches for products by category. It takes a category name as a
	 * parameter, and if the name matches the category name, the products are
	 * filtered by category and collected into a list to be returned by the method.
	 * 
	 * @param categoryName
	 * @return list of products
	 */
	public static List<Product> findProductsByCategory(String categoryName) {
		Optional<Category> category = Stock.findCategoryByName(categoryName);
		List<Product> productsByCategory = new ArrayList<Product>();
		if (category.isPresent()) {
			if (products.size() != 0) {
				productsByCategory = products.stream().filter(pr -> pr.getProductCategory().equals(category.get()))
						.toList();
			}
		}
		return productsByCategory;
	}

	/***
	 * The method searches for products by subcategory. It takes a subcategory name
	 * as a parameter, and if the name matches the subcategory name, the products
	 * are filtered by subcategory and collected into a list to be returned by the
	 * method.
	 * 
	 * @param subcategoryName
	 * @return list of products
	 */
	public static List<Product> findProductsBySubcategory(String subcategoryName) {
		List<Product> listProductWithCat = new ArrayList<Product>();
		List<Product> listProductWithEntrySubcategory = new ArrayList<Product>();
		Optional<Category> categoryOpt = categories.stream().filter(cat -> cat.isSubcategory(subcategoryName)).findFirst();
		if(categoryOpt.isPresent()) {
			listProductWithCat = findProductsByCategory(categoryOpt.get().getCategoryName());
		}
		listProductWithEntrySubcategory = listProductWithCat.stream().filter(pr -> pr.getProductSubcategory().equals(subcategoryName)).toList();
		return listProductWithEntrySubcategory;
	}

	/***
	 * This method takes as a parameter product's name and return list of products. 
	 * @param productName
	 * @return list of products with the same name
	 */
	public static List<Product> findProductByName(String productName) {
		List<Product> product = new ArrayList<Product>();
		if (products.size() != 0) {
			product = products.stream().filter(pr -> pr.getProductName().equals(productName)).toList();
		}
		return product;
	}

	/***
	 * This method takes as a parameter product's hash-code and return Optional of Product. 
	 * Hash-code is an uncle parameter for Product.
	 * @param productHashCode
	 * @return Optional of searched product 
	 */
	public static Optional<Product> findProductByHashCode(String productHashCode) {
		Optional<Product> product = null;
		if (products.size() != 0) {
			product = products.stream().filter(pr -> pr.getProductShopHashCode().equals(productHashCode)).findFirst();
		}
		return product;
	}
	
	/***
	 * This method takes as a parameter product's brand's hash-code (from box) and return List of Products. 
	 * Brand's hash-code is not an uncle parameter for Product. If price or other parameter is not the same, 
	 * Product is not the same Object.
	 * @param product universal HashCode (from brand)
	 * @return list of searched products
	 */
	public static List<Product> findProductByUnHashCode(String productUnHashCode) {
		List<Product> productsByUnHash = new ArrayList<Product>();
		if (products.size() != 0) {
			productsByUnHash = products.stream().filter(pr -> pr.getProductUNHashCode().equals(productUnHashCode))
					.toList();
		}
	return productsByUnHash;
	}
	
	/***
	 * The method searches for products by brand. It takes a brand name as a
	 * parameter, and if the name matches the brand, the products are
	 * filtered by brand and collected into a list to be returned by the method.
	 * 
	 * @param brandName
	 * @return list of products
	 */
	public static List<Product> findProductsByBrand(String brand) {
		List<Product> productsByBrand = new ArrayList<Product>();
			if (products.size() != 0) {
				productsByBrand = products.stream().filter(pr -> pr.getProductBrand().equals(brand))
						.toList();
			}
		return productsByBrand;
	}

	/***
	 * SORT PRODUCT METHODS
	 */

	public static void sortProductsByPrice() {
		if (Stock.getStringEntry("Do you want to sort products by ascending? Enter 'Y' for \"yes\": ").trim()
				.equalsIgnoreCase("y")) {
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
		// логика по которой предложить уже имеющиеся категории с похожими именами
		return new Category(categoryName);
	}

	public static boolean removeCategory() {
//		TODO
		return false;
	}

	public static Optional<Category> findCategoryByName(String categoryName) {
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

	public static void createDefaultCategories() {
//		TODO
	}

	/***
	 * A utility method for reading data entered by the client in the console.
	 */
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

	public static String categoriesToString() {
		StringBuilder strBu = new StringBuilder();
		strBu.append("Categories:\n");
		if (categories.size() != 0) {
			categories.forEach(cat -> {
				strBu.append("\t" + (categories.indexOf(cat) + 1) + " -> " + cat + ";\n");
				strBu.append("subcategories: ");
				List<String> subcategories = cat.getSubcategories();
				if (cat.getSubcategories().size() != 0) {
					subcategories.forEach(sub -> {
						strBu.append("\t" + (subcategories.indexOf(sub) + 1) + " -> " + sub + "; ");
					});
				} else {
					strBu.append("nothing.");
				}
			});
		} else {
			strBu.append("nothing.");
		}
		return strBu.toString();
	}

	public static String inventoriesToString() {
//		TODO
		return null;
	}

//	TODO
	public static String productsToString() {
		return null;
	}

	/***
	 * Getters and setters
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
