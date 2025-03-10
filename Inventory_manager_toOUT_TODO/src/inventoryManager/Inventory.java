package inventoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory implements InventoryUpdatable{
	private static Scanner sc;
	private static Product pr;
	private static boolean done;
	private static boolean ascending;
	private static List <Product> products = new ArrayList<Product>();
	private static List<Category> category = new ArrayList<Category>();
	private List<Product> inventoryList;
	private Map<Product,Integer> inventoryMap;
	private Product inventoryProduct;
	private int counter;
	
	/***
	 * The class has both static and non-static methods. 
	 * Static methods are used for managing the product database and the main warehouse data. 
	 * The class object and non-static methods are used for conducting inventory 
	 * (reconciliation of the products in the store with the quantities in the warehouse). 
	 * Each subsequent check involves creating a new object of the class.
	 */
	public Inventory() {
		this.counter = 1;
		this.inventoryList = products;
		this.inventoryProduct = products.getFirst();
		this.inventoryMap = new HashMap<Product,Integer>();
		
	}

	@Override
	public boolean addProductQuantityOneByOne(String hashcode) {
//		if(inventoryProduct.)
		return false;
	}

	@Override
	public boolean addTotalProductQuantity(String hashcode, int totalQuantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reserProductQuantity(String hashcode) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void addProduct() {
		//String name, String category, double price, int stockQuantity
		System.out.println("Let's make a new product.");
		String name = getStringName("Enter name of product: ");
		String category = getStringName("Enter category number of category: ");
//		TODO
		Double price = (double)0;
		int stockQuantity = 0;
		try {
			price = Double.valueOf(getStringName("Enter price as number: ").trim());
			stockQuantity = Integer.valueOf(getStringName("Enter quantity as number: ").trim());
		}catch(Exception e) {
			System.out.println("This number is not correct");
			System.out.println(e.getMessage());	
		}
//		pr = new Product(name,category,price,stockQuantity);
		System.out.println("Product is added");
	}
	
	public static void removeProduct(String name){
		done = false;
		products.forEach(p -> {
			if(p.getName().equals(name)) {
				pr = p;
				done = true;
			}
		});
		products.remove(pr);
		System.out.println("Product "+pr.getName()+" is deleted");
		if(!done) {
			System.out.println("Product not found");
		}
	}
		
	public static void sortByPrice() {
		if(Inventory.getStringName("Do you want to sort products by ascending? Y/N: ").trim().equalsIgnoreCase("y")) {
			ascending = true;
		}else {
			ascending = false;
		}
		if(ascending) {
			products.sort((p1, p2) -> ((Double)p1.getPrice()).compareTo((Double)(p2.getPrice()))); 
		}else {
			products.sort((p1, p2) -> (-1)*((Double)p1.getPrice()).compareTo((Double)(p2.getPrice())));
		}
		System.out.println("Products are sorted");
	}
	
	public static void getLowStockProducts() {
		try {
			double threshold = Double.valueOf(Inventory.getStringName("Enter price: ").trim());
			done = false;
			products.forEach(p -> {
				if(p.getPrice()<threshold) {
					p.getProductDetails();
					done = true;
				}
			});
		}catch(Exception e) {
			e.getMessage();
		}
		if(!done) {
			System.out.println("Products not found");
		}
	}
	
	public static void sortProducts() {
		products.sort((p1, p2) -> p1.getName().compareTo(p2.getName())); 
	}
	
	public static void allProductsInfo() {
		products.forEach(p -> p.getProductDetails());
	}
	
	
	public static Product findProductByCategory(String category) {
		done = false;
		products.forEach(p -> {
			if(p.getCategory().equalsIgnoreCase(category)) {
				p.getProductDetails();
				done = true;
			}
		});
		if(!done) {
			System.out.println("Category not found");
		}
		return pr;
	}
	
	public static Product findProductByName(String productName) {
		done = false;
		pr = products.getFirst();
		products.forEach(p -> {
			if(p.getName().equalsIgnoreCase(productName)) {
				pr = p;
				System.out.println("Product found");
				done = true;
			}
		});
		if(!done) {
			System.out.println("Product not found");
		}
		pr.getProductDetails();
		return pr;
	}
	
	public static String getStringName(String str) {
		int x = 5;
		while(x>0) {
			System.out.println(str);
			String clientString = sc.nextLine();
			if(clientString.isBlank()) {
				System.out.println("This is not correct. You have attempts: "+(--x));
			}else {
				return clientString;
			}
		}
		return "Not real";
		
	}
	
	public boolean isCategory(String categoryName) {
		if (!categoryName.isBlank()) {
			}
//			if(getCategoryByName()) {
//			return(categories.contains(subcategoryName));
//		}
//			return false;
	}
	
	public static void exit(){
		System.out.println("Bye");
		sc.close();
	}
	
	/***
	 * 
	 * @param sc
	 */
	public static void startScanner(Scanner sc) {
		setSc(sc);
	}

	/***
	 * Getters and setters
	 *
	 */
	public static List<Product> getProducts() {
		return (List<Product>) products;
	}

	public static void setProducts(ArrayList<Product> products) {
		Inventory.products = products;
	}	


	public static void setSc(Scanner sc) {
		Inventory.sc = sc;
	}

	public static boolean isAscending() {
		return ascending;
	}

	public static void setAscending(boolean ascending) {
		Inventory.ascending = ascending;
	}

	public static List<Category> getCategory() {
		return category;
	}

	public static void setCategory(List<Category> category) {
		Inventory.category = category;
	}

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


	public static void setProducts(List<Product> products) {
		Inventory.products = products;
	}
	
	
}

