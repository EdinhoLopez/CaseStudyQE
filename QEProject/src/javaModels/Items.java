package javaModels;

public class Items {
	
	private int itemID;
	private int categoryID;
	private int brandID;
	private double itemPrice;
	private String itemDescription;
	private boolean itemInstrument;
	private boolean itemEquipment;
	
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
	
	public boolean isItemEquipment() {
		return itemEquipment;
	}
	public void setItemEquipment(boolean itemEquipment) {
		this.itemEquipment = itemEquipment;
	}
	
	
	

}
