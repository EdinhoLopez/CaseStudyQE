package javaTests;


import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javaDAO.OrderDAO;
import javaDAO.UsersDAO;
import javaModels.Order;
import javaModels.Users;

public class OrderDAOTest {
	
	public static OrderDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new OrderDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				//I need to have some Users in my Users table in order for Order to be accessible
				UsersDAO insertPrequesites = new UsersDAO();
				
				insertPrequesites.registerUserIncludeId(new Users(1,"Steve10","Steve12345","Steve@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(2,"Maria20","Maria12345","Maria@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(3,"Angel30","Angel12345","Angel@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(4,"Brian40","Brian12345","Brian@gmail.com",1));
				insertPrequesites.registerUserIncludeId(new Users(5,"Chris50","Chris12345","Chris@gmail.com",1));
				
				
				//Insert UserDetails now that I actually have users in my database
				testObject.registerOrderIncludeId(new Order(1,1,LocalDate.now(),50.0,"Shipped"));
				testObject.registerOrderIncludeId(new Order(2,1,LocalDate.now(),100.0,"Delivered"));
				testObject.registerOrderIncludeId(new Order(3,2,LocalDate.now(),50.0,"Not Shipped"));
				testObject.registerOrderIncludeId(new Order(4,2,LocalDate.now(),50.0,"Delivered"));
				testObject.registerOrderIncludeId(new Order(5,3,LocalDate.now(),50.0,"Shipped"));
				
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	
	//Test the registerOrder() method (Create Method)
	@Test
	public void testRegisterOrder() {
			
			assumeTrue(connectedDB);
			
			Order testOrder = new Order(10,1,LocalDate.now(),1000.0,"Delivered");
			
			try {
					
				//Insert the object into the database
				Integer testID = testObject.registerOrderIncludeId(testOrder);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(10));
				
				//Is the object that was inserted in the database?
				assertNotNull(testObject.getOrderById(10));
				
				//Make sure all information inserted in correct and consistent
				Order actualOrder = testObject.getOrderById(10);
				
				assertEquals(testOrder.getOrderID(),actualOrder.getOrderID());
				assertEquals(testOrder.getOrderDate(),actualOrder.getOrderDate());
				
				//Remove test data used during test
				testObject.removeOrderById(10);
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllOrders() method (Retrieve Method)
	@Test
	public void testGetAllOrders() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Users object from database
				ArrayList<Order> actual = testObject.getAllOrders();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first Order total is correct
				assertThat(actual.get(0).getOrderTotal(),is(50.0));
				
				//Make sure that the first OrderDate is correct
				assertEquals(actual.get(0).getOrderDate(),LocalDate.now());
				
				//Make sure that this User does not exist
				assertThat(actual,not(hasItems(new Order(100,100,LocalDate.now(),100.0,"Delivered"))));
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getOrderById() method (Retrieve Method)
	@Test
	public void testOrderById() {
		
		try {
			
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getOrderById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getOrderById(1).getUserID(),1);
		
		assertThat(testObject.getOrderById(1).getOrderID(),is(1));
		
		assertEquals(testObject.getOrderById(1).getOrderStatus(),"Shipped");
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getOrderById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetOrderById()");
			
		}
		
	}
	
	//Test the updateOrder() method
	@Test
	public void testUpdateOrder() {
			
			
			Order testOrder = new Order(2,3,LocalDate.now(),200.0,"Shipped");
			
			try {
				//Make sure you are able to update the given Order
				Boolean updatedOrder = testObject.updateOrder(testOrder);
				
				//Make sure the Order was actually updated
				assertTrue(updatedOrder);
				
				//Make sure the updated Order has the correct variables
				assertThat(testObject.getOrderById(2).getUserID(),allOf(is(3),not(1)));
				
				assertThat(testObject.getOrderById(2).getOrderTotal(),allOf(is(200.0),not(100.0)));
				
				assertThat(testObject.getOrderById(2).getOrderStatus(),is("Shipped"));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeOrder() method
	@Test
	public void testRemoveUserDetails() {
			
			try {
				
				//Test whether the UserDetails is removed correctly
				Boolean removedUserD = testObject.removeOrderById(5);
				
				assertTrue(removedUserD);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getOrderById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateOrder(new Order(5,3,LocalDate.now(),1000.0,"rmation")));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerOrderIncludeId(new Order(5,3,LocalDate.now(),50.0,"Shipped"));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
				
			//Removed initial orders inserted
			for(Order out:testObject.getAllOrders()) {
				
				testObject.removeOrderById(out.getOrderID());
			
			}
			
			//Remove the initial Order objects we inserted
			UsersDAO prequesite = new UsersDAO();
			
			for(Users out:prequesite.getAllUsers()) {
				
				prequesite.removeUserById(out.getUserID());
			
			}
			
			
			}
			catch(Exception e) {
				System.out.println("Unable to clear DB of test data");
			}
			
		}
	

}
