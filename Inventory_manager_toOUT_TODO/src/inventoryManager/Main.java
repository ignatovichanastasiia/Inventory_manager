package inventoryManager;

import java.util.Scanner;

/***
 * CLASS MAIN(system)
 * To start the system, you need to initialize the scanner for the Stock and
 * Main classes. This is done in the Start method. The call to this method
 * should be the first in the Main method. --- At the beginning, the program
 * will offer to create a standard list of categories and subcategories for the
 * product network (the list is described in the Category class). You can skip
 * this step and manually enter all categories and subcategories. The menu is
 * divided into categories: Inventory, Stock (Warehouse), Product Settings,
 * Category Settings.
 * 
 * **Inventory** is responsible for recalculating the existing products in the
 * store. It can work with manual input or the scanner, counting the scanned
 * product by its hash code. Then, in the inventory results, a report on
 * discrepancies in quantities with the warehouse data is provided. **Stock
 * (Warehouse)** handles incoming stock data (receiving), product write-offs by
 * quantity, and sales (deductions). Stock supports changing product quantities,
 * searching for products by various parameters, and adding promotional
 * discounts. **Product Settings** supports adding and removing products
 * (working with the database and hash codes) and editing product information.
 * **Category Settings** allows adding categories and subcategories. Note: To
 * delete a category or subcategory, no product should be associated with it.
 * You can add the standard set of categories and subcategories for the product
 * network (described in the Category class).
 * 
 */
public class Main {
	private static Scanner scanner;
	private static int program;

	/***
	 * Для начала работы системы нужно инициализироать сканнер для класса Stock. Это
	 * происходит в методе Start. Обращение к этому методу должно стоять первым в
	 * методе Main. В начале работы программа предлагает создать стандартный список
	 * категорий и подкатегорий для продуктовой сети (список в описании класса
	 * Категория). Можно не подтверждать это действие и ввести все категории и
	 * подкатегории вручную. --- 
	 * Меню разделено на категории: Инвенторизация, Сток
	 * (Склад), Настройки продуктов, Настройки категорий. Инвенторизация отвечает за
	 * пересчет имеющейся продукции в магазине, может работать с ручного ввода или
	 * со сканером, считая сканированный продукт по хэш-коду. Далее в итогах
	 * инвенторизации, предоставляется отчет по несоответствиям количества с данными
	 * склада. Сток или склад - это данные с приема на склад (поступлении), списание
	 * продукта по количеству, продажи (отчисление). Сток поддерживает изменение
	 * количества продуктов, поиск продукта по разным параметрам, добавление
	 * акционных скидок. Настройки продуктов поддерживает добавление и удаление
	 * продукта (работа с базой и хэш-кодами), редактирование информации. Настройки
	 * категорий и подкатегорий. Меню предоставляет возможность добавить категорию и
	 * подкатегорию. Внимание, для того, чтобы удалить категорию или подкатегорию,
	 * ни один продукт не должен относиться к ней. Добавить можно стандартный набор
	 * категорий и подкатегорий для продуктовой сети (описан в классе категория).
	 */

	public static void main(String[] args) {
		start();
//		goProgram();
	}

	private static void start() {
		scanner = new Scanner(System.in);
		Stock.startStockScanner(scanner);
		if (Stock.getCategories().size() == 0) {
			instructionFirst();
			createDefCategories();
			categoriesInfo();
		}
	}

	private static void createDefCategories() {
		System.out.println("Would you like to create the standard list of categories and subcategories?\n");
		String answer = getStringName("Enter 'Y' for \"yes\" or eny other for \"no\"");
		if (answer.trim().contains("Y") || answer.trim().contains("y")) {
			Stock.createDefaultCategories();
		}
	}

	private static void categoriesInfo() {
		System.out.println("Existing Categories: " + Stock.getCategories().toString()
				+ "\nEdit categories?(Note: You will not be able to delete a category or "
				+ "subcategory if any product is associated with it.)");
		String answer = getStringName("Enter 'Y' for \"yes\" or eny other for \"no\"");
		if (answer.trim().contains("Y") || answer.trim().contains("y")) {
//			TODO
		}
	}

	Would you
	like to
	proceed with
	creating products?

	1.
	Select a
	product category.2.
	Select a
	product subcategory.3.
	Create a
	product by
	using its
	universal barcode
	from the packaging,name,brand,category,subcategory,
	and price.(Note:
	Changing product
	parameters without
	administrator access
	is not possible.)""");}
//TODO test set of products and categories
//		предложить создать стандартный список категорий и подкатегорий
	Scanner scannerForStock = new Scanner(System.in);Stock.startScanner(scannerForStock);

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

	public static String getStringName(String str) {
		int x = 5;
		while (x > 0) {
			System.out.println(str);
			String clientString = scanner.nextLine();
			if (clientString.isBlank()) {
				System.out.println("This is not correct. You have attempts: " + (--x));
			} else {
				return clientString;
			}
		}
		return "Not real";

	}

	private static void instructionFirst() {
		System.out.println("""
				Let's begin the program setup. To add products,
				you first need to create a product category and a product subcategory.
				A product can only be created after these steps.\n
				You can enter all categories and subcategories manually, one by one,
				or adjust the existing list in the program.\n
						""");
	}

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
