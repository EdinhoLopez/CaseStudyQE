package javaModels;

public class Users {
	
	private int userID;
	private String userName;
	private String userPassword;
	private String userEmail;
	private int userAdmin;
	
	public Users() {
		
		
	}
	
	public Users(int userID, String userName, String userPassword, String userEmail, int userAdmin) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userAdmin = userAdmin;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public int getUserAdmin() {
		return userAdmin;
	}
	public void setUserAdmin(int userAdmit) {
		this.userAdmin = userAdmit;
	}
	
	
	
	
	

}
