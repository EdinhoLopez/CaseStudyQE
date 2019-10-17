package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;

import javaModels.Brands;

public class BrandsDAO {
	
	//Connection and statement that will be used in each method
	Connection con = null;
	Statement stm = null;
	
	//Returns an ArrayList filled with Brands objects from database
	public ArrayList<Brands> getAllBrands() throws SQLException {
		// Declare variables
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Brands u = null;
		ArrayList<Brands> brandList = null;

		// Assign query string to a variable
		String qString = "select * from brands";

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
			brandList = new ArrayList<Brands>();
			// Read the ResultSet instance
			while (rs.next()) {
				// Each iteration creates a new user
				u = new Brands();
				// Assign columns/fields to related fields in the User object
				// 1,2 and 3 represent column numbers/positions
				u.setBrandID(rs.getInt(1));
				u.setBrandName(rs.getString(2));
				u.setBrandDescription(rs.getString(3));
				// Add the user to the list
				brandList.add(u);
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
		return brandList;
	} // End of getAllUsers method	

	//Creates a Brand object into the database
	public Integer registerBrand(Brands inputBrand) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// Assign insert statement string to variable
		String insertString = "insert into brands (brandId, brandName, brandDescription) values (?,?,?)";
		
	    int ID = inputBrand.getBrandID();
	    String[] COL = {"brandId"};
	    
	    MariaDBConnection mysql = new MariaDBConnection();
	    
	    try
	    {
	        conn = mysql.getConnection();
	        stmt = conn.prepareStatement(insertString, COL);
	        
	        stmt.setInt(1, inputBrand.getBrandID());
	        stmt.setString(2, inputBrand.getBrandName());
	        stmt.setString(3, inputBrand.getBrandDescription());
	        
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
	public Brands getBrandById(int brandId) throws ClassNotFoundException, IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Brands u = null;
		
		// Assign query string to variable
		String qString = "select * from brands where brandId = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mysql = new MariaDBConnection();
		
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(qString);
			
			// Set query parameters (?)
			stmt.setInt(1, brandId); // user_id if from String parameter passed to method
			
			// Run query and assign to ResultSet
			rs = stmt.executeQuery();
			
			// Retrieve ResultSet and assign to new User
			if (rs.next()) {
				u = new Brands();
				u.setBrandID(rs.getInt(1));
				u.setBrandName(rs.getString(2));
				u.setBrandDescription(rs.getString(3));
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
	public Brands getBrandByName(String brandName) throws ClassNotFoundException, IOException, SQLException {
	 // Declare variables
	 Connection conn = null;
	 PreparedStatement stmt = null;
	 ResultSet rs = null;
	 Brands u = null;
	 
	 // Assign query string to variable
	 String qString = "select * from brands where brandName = ?";
	 
	 // Create MySqlConnection class instance
	 MariaDBConnection mysql = new MariaDBConnection();
	 
	 // Begin try/catch block to query the database
	 try
	 {
	 	// Connect to database and assign query string to PreparedStatement object
	 	conn = mysql.getConnection();
	 	stmt = conn.prepareStatement(qString);
	 	
	 	// Set query parameters (?)
	 	stmt.setString(1, brandName); 
	 	
	 	// Run query and assign to ResultSet
	 	rs = stmt.executeQuery();
	 	
	 	// Retrieve ResultSet and assign to new User
	 	if (rs.next()) {
	 		u = new Brands();
	 		u.setBrandID(rs.getInt(1));
	 		u.setBrandName(rs.getString(2));
	 		u.setBrandDescription(rs.getString(3));
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
	public Boolean updateBrand(Brands u) throws SQLException, ClassNotFoundException, IOException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign update string to variable
		String updateString = "update brands "
				+ "set brandName = ?, brandDescription = ? "
				+ "where brandId = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mysql = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(updateString);
			
			// Set query parameters (?)
			stmt.setString(1, u.getBrandName());
			stmt.setString(2, u.getBrandDescription());
			stmt.setInt(3, u.getBrandID());
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
	public Boolean removeBrand(int brandId) throws IOException, SQLException {
		// Declare variables
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer updateResult = null;
		
		// Assign delete string to variable
		String deleteString = "delete from brands where brandId = ?";
		
		// Create MySqlConnection class instance
		MariaDBConnection mysql = new MariaDBConnection();
		// Begin try/catch block to query the database
		try
		{
			// Connect to database and assign query string to PreparedStatement object
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(deleteString);
			
			// Set query parameters (?)
			stmt.setInt(1, brandId);
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

	public static void main(String[]args) {
		
		
		
	}

}
