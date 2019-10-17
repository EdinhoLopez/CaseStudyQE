package javaModels;


public class Brands {
	
	public Brands() {
		
	}
	
	public Brands(int brandID, String brandName, String brandDescription) {
		super();
		this.brandID = brandID;
		this.brandName = brandName;
		this.brandDescription = brandDescription;
	}
	
	private int brandID;
	private String brandName;
	private String brandDescription;
	
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBrandDescription() {
		return brandDescription;
	}
	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}
	
	

}
