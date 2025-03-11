package inventoryManager;

import java.util.ArrayList;
import java.util.List;

//clean 

/***
 * This class represents a product category in the store network. To simplify
 * classification and eliminate potential errors in the database, categories and
 * subcategories have been standardized. However, categories and subcategories
 * can be adjusted and tailored to fit specific needs. To facilitate the
 * process, below are the standard categories and subcategories for the retail
 * network.
 * 
 * To work with the category class, you need to create a category and specify a
 * subcategory, or add a subcategory to an existing category. Please note that a
 * duplicate category or subcategory will not be created.
 * 
 * In retail food networks, several standard categories and subcategories of
 * products are typically identified. Here are some of them:
 * 
 * 1. **Food Products:** - **Vegetables and fruits:** fresh, frozen, canned. -
 * **Meat and meat products:** fresh meat, sausages, semi-finished products. -
 * **Fish and seafood:** fresh, frozen, canned. - **Dairy products:** milk,
 * yogurt, cheese, butter. - **Bakery products:** bread, rolls, croissants. -
 * **Confectionery products:** chocolate, cookies, candies. - **Cereals and
 * pasta:** rice, buckwheat, pasta, oatmeal. - **Grocery:** sugar, salt, spices,
 * flour, canned goods. - **Beverages:** juices, water, soft drinks, tea,
 * coffee.
 * 
 * 2. **Frozen Products:** - **Frozen vegetables and fruits.** - **Frozen meat
 * and fish.** - **Semi-finished products and pizza.** - **Desserts and ice
 * cream.**
 * 
 * 3. **Health and Dietetic Products:** - **Gluten-free products.** -
 * **Vegetarian and vegan products.** - **Diet products: low-calorie, low-carb,
 * etc.** - **Organic products, bio products.**
 * 
 * 4. **Alcoholic Beverages:** - **Beer.** - **Wine.** - **Strong drinks:**
 * vodka, brandy, whiskey, liqueurs.
 * 
 * 5. **Non-alcoholic Beverages:** - **Mineral waters and soda.** - **Juices and
 * nectar.** - **Tea and coffee.** - **Energy drinks.**
 * 
 * 6. **Household Chemicals and Household Products:** - **Cleaning and washing
 * products.** - **Laundry and cleaning supplies.** - **Kitchen supplies:**
 * sponges, bags, wraps.
 * 
 * 7. **Cosmetics and Hygiene Products:** - **Face and body cosmetics.** -
 * **Shampoos, conditioners.** - **Hygiene products:** toothpaste, soap, shower
 * gels.
 * 
 * 8. **Baby Products:** - **Baby food.** - **Diapers and wet wipes.** - **Baby
 * cosmetics and hygiene products.** - **Toys and baby accessories.**
 * 
 * 9. **Pet Products:** - **Pet food.** - **Toys and accessories for pets.** -
 * **Pet care products.**
 * 
 * These categories and subcategories may vary depending on the specific store
 * network and region.
 */

public class Category {
	private String categoryName;
	private List<String> subcategories;

	/***
	 * Constructor: takes name of product's category. An empty list (ArrayList) is
	 * automatically created. It will store the subcategories of the given category,
	 * and they can be added through the class method. 
	 * If the category is already exist, new category not added to list of categories.
	 * 
	 * @param categoryName
	 */
	public Category(String categoryName) {
		this.categoryName = categoryName;
		this.subcategories = new ArrayList<String>();
		if(!Stock.getCategories().contains(this)) {
			List<Category> categories = Stock.getCategories();
			categories.add(this);
			Stock.setCategories(categories);
		}
	}

	/***
	 * The method takes the name of the subcategory. If the name is not empty 
	 * and such a subcategory is not already in the list, it adds the name to the list.
	 * 
	 * @param subcategoryName
	 * @return true of false. It is a status: whether the operation was successful or not
	 */
	public boolean addSubcategories(String subcategoryName) {
		if (!subcategoryName.isBlank()) {
			if (!subcategories.contains(subcategoryName)) {
				return subcategories.add(subcategoryName);
			}
		}
		return false;
	}
	
	/***
	 * The method takes the name of the subcategory. If the name is not empty 
	 * and such a subcategory is in the list, it memoves the name from the list.
	 * 
	 * @param subcategoryName
	 * @return true of false. It is a status: whether the operation was successful or not
	 */
	public boolean removeSubcategories(String subcategoryName) {
		if (!subcategoryName.isBlank()) {
			if (subcategories.contains(subcategoryName)) {
				return subcategories.remove(subcategoryName);
			}
		}
		return false;
	}
	
	/***
	 * The method takes the name of the subcategory and checks if such a subcategory exists 
	 * in the given category. It returns true if it exists, and false if it does not.
	 * 
	 * @param subcategoryName
	 * @return true or false - the String is a subcategory, or not.
	 */
	public boolean isSubcategory(String subcategoryName) {
		if (!subcategoryName.isBlank()) {
			return(subcategories.contains(subcategoryName));
		}
			return false;
	}
	
	/***
	 * Getters and setters
	 * 
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}

}
