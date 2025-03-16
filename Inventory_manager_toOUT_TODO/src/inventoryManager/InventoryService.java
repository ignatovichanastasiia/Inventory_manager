package inventoryManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

//clean

/***
 * CLASS INVENTORY SERVICE The `InventoryService` class is a class with static
 * methods that manage inventories. It contains all the data and can edit them.
 * The class is effectively closed for external access, and the only method 
 * that can be called from other classes is the method for initialization and the logical 
 * start of the inventory process - 'startInventoryService'. 
 * Various methods are interconnected to initialize the inventory for a product, 
 * track the scanning of a single product (one product can have multiple inventories), 
 * move to the next, edit the inventory, record the data in the inventory map, and generate the report. 
 * The result is written to a file with a timestamp.
 */
public class InventoryService {
	private static List<Inventory> inventories = new ArrayList<Inventory>();
	private static List<Product> inventoryList;
	private static Map<Product, Integer> inventoryMap;
	private static Scanner inventoryServiceScanner;

	/***
	 * The method for starting the inventory service. 
	 * Calling this method initializes the variables and starts the logic.
	 * The method parameter is a scanner from the main method.
	 * @param scanner
	 */
	public static void startInventoryService(Scanner scanner) {
		setInventoryServiceScanner(scanner);
		inventoryList = Stock.getProducts();
		startNewInventory();
		if (!inventories.isEmpty()) {
			endOfInventoryAndReport();
		}
	}

	/***
	 * The first part of the logic is the initialization of the inventory process. 
	 * The method takes the store's hash code from the client and passes it to initialize the inventory 
	 * for the product using the hash code. 
	 */
	private static void startNewInventory() {
		String productHash = getStringEntry(
				"To inventory a product, scan the product or enter the hash code manually. ('Q' for exit)");
		if (productHash.equalsIgnoreCase("Q")) {
			return;
		}
		Optional<Product> product = Stock.findProductByHashCode(productHash);
		if (product.isPresent()) {
			takeInventory(product.get());
		}
	}

	/***
	 * The method initializes the inventory for the selected product. 
	 * When the product changes, the next inventory is initialized. 
	 * The previous inventory is recorded in the inventory list. The list is not a set, 
	 * so one product can have multiple inventories. After the item counting is completed, 
	 * the quantities are summed up.
	 * @param product
	 */
	private static void takeInventory(Product product) {
		Inventory inventory = new Inventory(product);
		System.out.println("Scan the product one by one or enter the total quantity?");
		int answer = 0;
		while (answer != 1 || answer != 2) {
			try {
				answer = Integer
						.valueOf(getStringEntry("Enter one of the number: 1 - for scan, 2 - for enter quantity"));
			} catch (ClassCastException e) {
				System.out.println("Entry is not a right number: " + e.getMessage());
			}
		}
		switch (answer) {
		case 1:
			System.out.println("Scan the product one by one");
			inventWhile(inventory);
			System.out.println("Change product or exit?");
			if (!getStringEntry("'Y' for \"yes\"").equalsIgnoreCase("Y")) {
				inventWhile(inventory);
			} else {
				int answer2 = 0;
				while (answer2 != 1 || answer2 != 2 || answer2 != 3) {
					try {
						answer = Integer.valueOf(getStringEntry("Change product - 1, Exit - 2, Edit - 3"));
						if (answer == 1) {
							inventories.add(inventory);
							startNewInventory();
						} else if (answer == 3) {
							editInventories(inventory);
							startNewInventory();
						} else {
							return;
						}
					} catch (ClassCastException e) {
						System.out.println("Entry is not a right number: " + e.getMessage());
					}
				}
			}
			break;
		case 2:
			int quantity = 0;
			try {
				quantity = Integer.valueOf("Enter total quantity");
			} catch (ClassCastException e) {
				System.out.println("Enter is not a right number: " + e.getMessage());
			}
			inventory.addTotalProductQuantity(product.getProductShopHashCode(), quantity);
			break;
		}
	}

	/***
	 * Loop for an inventory
	 * @param inventory
	 */
	private static void inventWhile(Inventory inventory) {
		while (inventory.addProductQuantityOneByOne(getStringEntry("Scan hash-code or something else for exit"))) {
			System.out.println("+1");
		}
	}

	/***
	 * The method takes as a parameter the inventory that needs to be edited. 
	 * The method changes the quantity of the scanned product (to the entered number or zero (reset).
	 * @param inventory
	 */
	private static void editInventories(Inventory inventory) {
		System.out.println("Reset or change quantity?");
		if (getStringEntry("Entry 'R' for reset or other key for change quantity.").equalsIgnoreCase("R")) {
			inventory.resetProductQuantity(inventory.getInventoryProduct().getProductShopHashCode());
		} else {
			int qua = 0;
			try {
				qua = Integer.valueOf(getStringEntry("Enter quantity: "));
			} catch (ClassCastException e) {
				System.out.println("Entry is not a number: " + e.getMessage());
			}
			inventory.setInventoryProductCounter(qua);
		}
	}

	/***
	 * The method creates a set of products, summing up the quantities from all inventories. 
	 * The set is recorded in the inventory map. It is then printed from the map and written to a file.
	 */
	private static void endOfInventoryAndReport() {
		inventoryMap = new HashMap<Product, Integer>();
		for (int x = 0; x < inventories.size(); x++) {
			Inventory inv = inventories.get(x);
			if (!inventoryMap.containsKey(inv.getInventoryProduct())) {
				List<Inventory> inventoriesDubles = inventories.stream()
						.filter(in -> in.getInventoryProduct().equals(inv.getInventoryProduct())).toList();
				int productQuantity = inventoriesDubles.stream().mapToInt(Inventory::getInventoryProductCounter).sum();
				inventoryMap.put(inv.getInventoryProduct(), productQuantity);
			}
		}
		StringBuilder strB = new StringBuilder();
		for (Entry<Product, Integer> entry : inventoryMap.entrySet()) {
			Product key = entry.getKey();
			Integer value = entry.getValue();
			strB.append("Product: " + key + ", quantity: " + value);
			if (key.getProductStockQuantity() == value) {
				strB.append(" - true\n");
			} else {
				strB.append(" - false: stock quantity=" + key.getProductStockQuantity() + "/n");
			}
		}
		System.out.println(strB.toString());
		if (writeInventoryReportToFile(strB.toString())) {
			System.out.println("The report is written to a file.");
		}
	}

	/***
	 * A utility method for reading data entered by the client in the console.
	 */
	private static String getStringEntry(String str) {
		int x = 5;
		while (x > 0) {
			System.out.println(str);
			String clientString = inventoryServiceScanner.nextLine();
			if (clientString.isBlank()) {
				System.out.println("This is not correct. You have attempts: " + (--x));
			} else {
				return clientString;
			}
		}
		return "Not real";
	}

	/***
	 * The method writes the inventory results to a file.
	 * @param reportString
	 * @return boolean (true, if all done)
	 */
	private static boolean writeInventoryReportToFile(String reportString) {
		File dir = new File("reports");
		File file = new File(dir, "report.txt");
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Problem with creating file: ");
				e.printStackTrace();
			}
		}
		StringBuilder strBu = new StringBuilder();
		LocalDateTime lcdt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		strBu.append("\n");
		String formattedDateTime = lcdt.format(formatter);
		strBu.append(formattedDateTime);
		strBu.append(reportString);
		strBu.append("\n");
		try (Writer wr = new FileWriter(file.getPath())) {
			wr.write(reportString);
			return true;
		} catch (IOException e) {
			System.out.println("Problem with writing file: ");
			e.printStackTrace();
		}
		return false;
	}

	/***
	 * Getters and setters
	 */

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

	private static void setInventoryServiceScanner(Scanner scanner) {
		InventoryService.inventoryServiceScanner = scanner;
	}

}
