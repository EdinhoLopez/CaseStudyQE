package javaModels;
import java.time.LocalDate;

public class UserDetails {
	
	private int userID;
	private String userFName;
	private String userLName;
	private LocalDate dateBirth;
	private String userAddress;
	
	public UserDetails() {
		
	}
	
	public UserDetails(int userID, String userFName, String userLName, LocalDate dateBirth, String userAddress) {
		super();
		this.userID = userID;
		this.userFName = userFName;
		this.userLName = userLName;
		this.dateBirth = dateBirth;
		this.userAddress = userAddress;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUserFName() {
		return userFName;
	}
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	
	public LocalDate getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

}
