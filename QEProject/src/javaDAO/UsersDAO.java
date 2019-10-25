package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;

import javaModels.Users;

public class UsersDAO {
	
	//Connection and statement that will be used in each method
		Connection con = null;
		Statement stm = null;
		

		//Determines whether a connection to the DB is possible		
		public Connection testConnection() {
					
			//Creates object from MariaDBConnection so you can use its getConnection Method
			MariaDBConnection mariadbConnection = new MariaDBConnection();
					
			try {
						
				//Variable "con" is assigned a connection to the Database
				con = mariadbConnection.getConnection();
				System.out.println("Connected!!!!!");
				return con;
						
						
			}
			catch(Exception e) {
									
				System.out.println("Connection failed to MariaDb");
				return null;
						
			}
			finally {
						
				if(con!=null) {
							
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
				}
						
			}
					
		}
		
		//Returns an ArrayList filled with Brands objects from database
		public ArrayList<Users> getAllUsers() throws SQLException {
			// Declare variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Users u = null;
			ArrayList<Users> userList = null;

			// Assign query string to a variable
			String qString = "select * from users";

			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();

			// Begin try/catch block to query the database
			try
			{
				// Connect to database
				conn = mysql.getConnection();
				// If the connection fails the application won't make it to this point
				
				// Create Statement instance/object
				stmt = conn.createStatement();
			
				// Run query and assign to the ResultSet instance
				rs = stmt.executeQuery(qString);
				//Create list to hold User objects
				userList = new ArrayList<Users>();
				// Read the ResultSet instance
				while (rs.next()) {
					// Each iteration creates a new user
					u = new Users();
					// Assign columns/fields to related fields in the User object
					// 1,2 and 3 represent column numbers/positions
					u.setUserID(rs.getInt(1));
					u.setUserName(rs.getString(2));
					u.setUserPassword(rs.getString(3));
					u.setUserEmail(rs.getString(4));
					u.setUserAdmin(rs.getInt(5));
					// Add the user to the list
					userList.add(u);
					// Repeat until rs.next() returns false (i.e., end of ResultSet)
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return userList;
		} // End of getAllUsers method	

		//Creates a Brand object into the database
		public Integer registerUserIncludeId(Users inputUser) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into users (userID, userName, userPassword,userEmail,userAdmin) values (?,?,?,?,?)";
			
		    int ID = inputUser.getUserID();
		    String[] COL = {"userID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        stmt.setInt(1, inputUser.getUserID());
		        stmt.setString(2, inputUser.getUserName());
		        stmt.setString(3, inputUser.getUserPassword());
		        stmt.setString(4, inputUser.getUserEmail());
		        stmt.setInt(5, inputUser.getUserAdmin());
		        
		        stmt.executeUpdate();
		        
		        rs = stmt.getGeneratedKeys();
		        if(rs != null && rs.next()) {
		            ID = rs.getInt(1);
		        }
		        System.out.println(ID);
		    }
		    catch (SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		    
			return ID;
		} // End of registerBrand() method

		public Integer registerUserExcludeId(Users inputUser) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into users (userName, userPassword,userEmail,userAdmin) values (?,?,?,?)";
			
		    int ID = inputUser.getUserID();
		    String[] COL = {"userID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        
		        stmt.setString(1, inputUser.getUserName());
		        stmt.setString(2, inputUser.getUserPassword());
		        stmt.setString(3, inputUser.getUserEmail());
		        stmt.setInt(4, inputUser.getUserAdmin());
		        
		        stmt.executeUpdate();
		        
		        rs = stmt.getGeneratedKeys();
		        if(rs != null && rs.next()) {
		            ID = rs.getInt(1);
		        }
		        System.out.println(ID);
		    }
		    catch (SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		    
			return ID;
		} // End of registerBrand() method
		
		//Retrieves a Brands object from the DB through an ID provided
		public Users getUserById(int userId) throws ClassNotFoundException, IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Users u = null;
			
			// Assign query string to variable
			String qString = "select * from users where userId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(qString);
				
				// Set query parameters (?)
				stmt.setInt(1, userId); // user_id if from String parameter passed to method
				
				// Run query and assign to ResultSet
				rs = stmt.executeQuery();
				
				// Retrieve ResultSet and assign to new User
				if (rs.next()) {
					u = new Users();
					u.setUserID(rs.getInt(1));
					u.setUserName(rs.getString(2));
					u.setUserPassword(rs.getString(3));
					u.setUserEmail(rs.getString(4));
					u.setUserAdmin(rs.getInt(5));
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return u;
		} // End of getBrandById() method
		
		//Retrieves a Brands object from the DB through a Name provided
		public Users getUserByName(String userName) throws ClassNotFoundException, IOException, SQLException {
		 // Declare variables
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 Users u = null;
		 
		 // Assign query string to variable
		 String qString = "select * from users where userName = ?";
		 
		 // Create MySqlConnection class instance
		 MariaDBConnection mysql = new MariaDBConnection();
		 
		 // Begin try/catch block to query the database
		 try
		 {
		 	// Connect to database and assign query string to PreparedStatement object
		 	conn = mysql.getConnection();
		 	stmt = conn.prepareStatement(qString);
		 	
		 	// Set query parameters (?)
		 	stmt.setString(1, userName); 
		 	
		 	// Run query and assign to ResultSet
		 	rs = stmt.executeQuery();
		 	
		 	// Retrieve ResultSet and assign to new User
		 	if (rs.next()) {
		 		u = new Users();
		 		u.setUserID(rs.getInt(1));
		 		u.setUserName(rs.getString(2));
		 		u.setUserPassword(rs.getString(3));
		 		u.setUserEmail(rs.getString(4));
		 		u.setUserAdmin(rs.getInt(5));
		 	}
		 }
		 catch (ClassNotFoundException | IOException | SQLException e)
		 {
		 	System.out.println("Error: " + e.getMessage());
		 	e.getStackTrace();
		 }
		 finally
		 {
		 	if (rs != null) {
		 		rs.close();
		 	}
		 	if (stmt != null) {
		 		stmt.close();
		 	}
		 	if (conn != null) {
		 		conn.close();
		 	}
		 }
		 return u;
			} // End of getBrandByName() method
			
		//Updates a Brands object from the DB through a given Brands object
		public Boolean updateUsers(Users u) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign update string to variable
			String updateString = "update users "
					+ "set userName = ?, userPassword = ?, userEmail = ?, userAdmin = ? "
					+ "where userId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(updateString);
				
				// Set query parameters (?)
				stmt.setString(1, u.getUserName());
				stmt.setString(2, u.getUserPassword());
				stmt.setString(3, u.getUserEmail());
				stmt.setInt(4, u.getUserAdmin());
				stmt.setInt(5, u.getUserID());
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of updateBrand() method
		
		//Deletes a Brands object from the DB through an ID provided
		public Boolean removeUserById(int userId) throws IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign delete string to variable
			String deleteString = "delete from users where userId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(deleteString);
				
				// Set query parameters (?)
				stmt.setInt(1, userId);
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of removeBrand() method

		//Deletes a Brands object from the DB through a name provided
		public Boolean removeUserByName(String userName) {
			
			
			try {
				//Uses existing method to get Brand to be deleted
				Users deletedUser = getUserByName(userName);
				
				//Uses existing method to delete brand and returns outcome.
				return removeUserById(deletedUser.getUserID());
				
			}
			catch(Exception e) {
				
				System.out.println("Unable to delete User");
				return false;
				
			}
			
		}
	
		
}
