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

/***
 * CLASS INVENTORY SERVICE The `InventoryService` class is a class with static
 * methods that manage inventories. It contains all the data and can edit them.
 */
public class InventoryService {
	private static List<Inventory> inventories = new ArrayList<Inventory>();
	private static List<Product> inventoryList;
	private static Map<Product, Integer> inventoryMap;
	private static Scanner inventoryServiceScanner;

	public static void startInventoryServiceScanner(Scanner scanner) {
		setInventoryServiceScanner(scanner);
		startNewInventory();
		if (!inventoryList.isEmpty()) {
			endOfInventoryAndReport();
		}
	}

	public static void startNewInventory() {
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

	public static void takeInventory(Product product) {
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

	private static void inventWhile(Inventory inventory) {
		while (inventory.addProductQuantityOneByOne(getStringEntry("Scan hash-code or something else for exit"))) {
			System.out.println("+1");
		}
	}

	public static void editInventories(Inventory inventory) {
		System.out.println("Reset or change quantity?");
		if(getStringEntry("Entry 'R' for reset or other key for change quantity.").equalsIgnoreCase("R")) {
			inventory.resetProductQuantity(inventory.getInventoryProduct().getProductShopHashCode());
		}else {
			int qua = 0;
			try {
			qua = Integer.valueOf(getStringEntry("Enter quantity: "));
			}catch(ClassCastException e) {
				System.out.println("Entry is not a number: "+e.getMessage());
			}
			inventory.setInventoryProductCounter(qua);
		}
	}

	public static void endOfInventoryAndReport() {
		inventoryMap = new HashMap<Product, Integer>();
		for (int x = 0; x < inventories.size(); x++) {
			Inventory inv = inventories.get(x);
			if(!inventoryMap.containsKey(inv.getInventoryProduct())) {
			List<Inventory> inventoriesDubles = inventories.stream()
					.filter(in -> in.getInventoryProduct().equals(inv.getInventoryProduct())).toList();
			int productQuantity = inventoriesDubles.stream().mapToInt(Inventory::getInventoryProductCounter).sum();
			inventoryMap.put(inv.getInventoryProduct(), productQuantity);
			}
		}
		StringBuilder strB = new StringBuilder();
		for(Entry<Product, Integer> entry : inventoryMap.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            strB.append("Product: " + key + ", quantity: " + value);
            if(key.getProductStockQuantity()==value) {
            	strB.append(" - true\n");
            }else {
            	strB.append(" - false: stock quantity="+key.getProductStockQuantity()+"/n");
            }
        }
		System.out.println(strB.toString());
		if(writeInventoryReportToFile(strB.toString())) {
			System.out.println("The report is written to a file.");
		}
	}

	public static String getStringEntry(String str) {
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
	
	public static boolean writeInventoryReportToFile(String reportString) {
		File dir = new File("reports");
		File file = new File(dir, "report.txt");
		if(!dir.exists()) {
			dir.mkdir();
		}
		if(!file.exists()) {
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
		try {
			Writer wr = new FileWriter(file.getPath());
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

	public static void setInventoryServiceScanner(Scanner scanner) {
		InventoryService.inventoryServiceScanner = scanner;
	}

}
