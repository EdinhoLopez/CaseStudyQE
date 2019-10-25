package javaModels;

public class Items {
	
	private int itemID;
	private int categoryID;
	private int brandID;
	private double itemPrice;
	private String itemDescription;
	private Boolean itemInstrument;
	
	public Items() {
		
	}
	
	public Items(int itemID, int categoryID, int brandID, double itemPrice, String itemDescription,
			Boolean itemInstrument) {
		super();
		this.itemID = itemID;
		this.categoryID = categoryID;
		this.brandID = brandID;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.itemInstrument = itemInstrument;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	public boolean isItemInstrument() {
		return itemInstrument;
	}
	
	public void setItemInstrument(boolean itemInstrument) {
		this.itemInstrument = itemInstrument;
	}
	
	
	
	

}
