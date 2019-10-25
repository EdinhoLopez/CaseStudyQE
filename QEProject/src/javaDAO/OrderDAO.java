package javaDAO;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javaDatabase.MariaDBConnection;

import javaModels.Order;


public class OrderDAO {
	
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
		public ArrayList<Order> getAllOrders() throws SQLException {
			// Declare variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Order u = null;
			ArrayList<Order> orderList = null;

			// Assign query string to a variable
			String qString = "select * from `order`";

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
				orderList = new ArrayList<Order>();
				// Read the ResultSet instance
				while (rs.next()) {
					// Each iteration creates a new user
					u = new Order();
					// Assign columns/fields to related fields in the User object
					// 1,2 and 3 represent column numbers/positions
					u.setOrderID(rs.getInt(1));
					u.setUserID(rs.getInt(2));
					u.setOrderDate(rs.getDate(3).toLocalDate());
					u.setOrderTotal(rs.getDouble(4));
					u.setOrderStatus(rs.getString(5));
					// Add the user to the list
					orderList.add(u);
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
			return orderList;
		} // End of getAllUsers method	

		//Creates a Brand object into the database
		public Integer registerOrderIncludeId(Order inputOrder) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into `order`(orderID, userID, orderDate,orderTotal,orderStatus) values (?,?,?,?,?)";
			
		    int ID = inputOrder.getOrderID();
		    String[] COL = {"orderID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        stmt.setInt(1, inputOrder.getOrderID());
		        stmt.setInt(2, inputOrder.getUserID());
		        
		        stmt.setDate(3, java.sql.Date.valueOf( inputOrder.getOrderDate()));
		        stmt.setDouble(4, inputOrder.getOrderTotal());
		        stmt.setString(5, inputOrder.getOrderStatus());
		        
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

		public Integer registerOrderExcludeId(Order inputOrder) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into `order` (userID,orderDate,orderTotal,orderStatus) values (?,?,?,?)";
			
		    int ID = inputOrder.getUserID();
		    String[] COL = {"orderID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        
		        stmt.setInt(1, inputOrder.getUserID());
		        stmt.setDate(2, java.sql.Date.valueOf( inputOrder.getOrderDate()));
		        stmt.setDouble(3, inputOrder.getOrderTotal());
		        stmt.setString(4, inputOrder.getOrderStatus());
		        
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
		public Order getOrderById(int orderId) throws ClassNotFoundException, IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Order u = null;
			
			// Assign query string to variable
			String qString = "select * from `order` where orderId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(qString);
				
				// Set query parameters (?)
				stmt.setInt(1, orderId); // user_id if from String parameter passed to method
				
				// Run query and assign to ResultSet
				rs = stmt.executeQuery();
				
				// Retrieve ResultSet and assign to new User
				if (rs.next()) {
					u = new Order();
					u.setOrderID(rs.getInt(1));
					u.setUserID(rs.getInt(2));
					u.setOrderDate(rs.getDate(3).toLocalDate());
					u.setOrderTotal(rs.getDouble(4));
					u.setOrderStatus(rs.getString(5));
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
		
		//Updates a Brands object from the DB through a given Brands object
		public Boolean updateOrder(Order u) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign update string to variable
			String updateString = "update `order` "
					+ "set userID = ?, orderDate = ?, orderTotal = ?, orderStatus = ? "
					+ "where orderId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(updateString);
				
				// Set query parameters (?)
				stmt.setInt(1, u.getUserID());
				stmt.setDate(2, java.sql.Date.valueOf( u.getOrderDate()));
				stmt.setDouble(3, u.getOrderTotal());
				stmt.setString(4, u.getOrderStatus());
				stmt.setInt(5, u.getOrderID());
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
		public Boolean removeOrderById(int orderId) throws IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign delete string to variable
			String deleteString = "delete from `order` where orderId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(deleteString);
				
				// Set query parameters (?)
				stmt.setInt(1, orderId);
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
		
		
}
