package inventoryManager;

import java.util.Objects;

public class Product {
	private String productUNHashCode;
	private String productShopHashCode;
	private String productName; 
	private String productCategory;
	private String productSubcategory;
	private double productPrice;
	private boolean productSale;
	private double productSalePrice;
	private int productStockQuantity; 
	
	/***
	 * The class constructor takes the following parameters: 
	 * a universal product hash code, the product name, its category, its subcategory, 
	 * and the price. The price is the regular price of the product without any discounts. 
	 * The discounted price is entered through a separate method. The method generates an 
	 * internal product hash code for the store. The product quantity in the warehouse is 
	 * entered using a separate method, and by default, the quantity is set to 0 when the 
	 * product is created.
	 * 
	 * @param productUNHashCode
	 * @param productName
	 * @param productCategory
	 * @param productSubcategory
	 * @param productPrice
	 *
	 */
	
public Product(String productUNHashCode, String productName, String productCategory, String productSubcategory,
			double productPrice) {
		this.productUNHashCode = productUNHashCode;
		this.productShopHashCode = generateShopHashCode();
		this.productName = productName;
		this.productCategory = productCategory;
		this.productSubcategory = productSubcategory;
		this.productPrice = productPrice;
		this.productSale = productSale;
		this.productSalePrice = productPrice;
		this.productStockQuantity = 0;
		Stock.getProducts().add(this);
	}
	

	/***
	 * This method works with the quantity of products in the stock(warehouse). 
	 * It takes the quantity of product and adds it to the existing quantity (receipt into the warehouse).
	 * The quantity of goods must always be a positive number or zero.
	 * @param quantity
	 * @return boolean: it returns `true` if the operation is successful, or `false` if it is not.
	 */
	public boolean productStockQuantityPlusQuantity(int quantity) {
		if((productStockQuantity+quantity)>=0) {
			this.productStockQuantity += quantity;
			return true;
		}
		return false;
	}
	
	/***
	 * It is used in the class constructor.
	 * This method generates a hash code for use within the store (or store network). 
	 * Each product must have such a hash code for inventory purposes.
	 * @return String with hash-code
	 */
	private String generateShopHashCode() {
		 return String.valueOf(hashCode());
	}

	/***
	 * Getters and setters
	 * 
	 */
	public String getProductShopHashCode() {
		return productShopHashCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSubcategory() {
		return productSubcategory;
	}

	public void setProductSubcategory(String productSubcategory) {
		this.productSubcategory = productSubcategory;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public boolean isProductSale() {
		return productSale;
	}

	public void setProductSale(boolean productSale) {
		this.productSale = productSale;
	}

	public double getProductSalePrice() {
		return productSalePrice;
	}

	public void setProductSalePrice(double productSalePrice) {
		this.productSalePrice = productSalePrice;
	}

	public int getProductStockQuantity() {
		return productStockQuantity;
	}

	public void setProductStockQuantity(int productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	} 

	@Override
	public int hashCode() {
		return Objects.hash(productCategory, productShopHashCode, productName, productSubcategory, productUNHashCode);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productCategory, other.productCategory)
				&& Objects.equals(productShopHashCode, other.productShopHashCode)
				&& Objects.equals(productName, other.productName)
				&& Objects.equals(productSubcategory, other.productSubcategory)
				&& Objects.equals(productUNHashCode, other.productUNHashCode);
	}	
}
