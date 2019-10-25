package javaModels;

public class Inventory {
	
	private int itemID;
	private int itemQuantity;
	
	public Inventory() {
		
	}
	
	public Inventory(int itemID, int itemQuantity) {
		super();
		this.itemID = itemID;
		this.itemQuantity = itemQuantity;
	}
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

}
