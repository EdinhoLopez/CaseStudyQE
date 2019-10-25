package javaTests;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javaDAO.UsersDAO;
import javaModels.Users;



public class UsersDAOTest {

	public static UsersDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new UsersDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				testObject.registerUserIncludeId(new Users(1,"Steve10","Steve12345","Steve@gmail.com",0));
				testObject.registerUserIncludeId(new Users(2,"Maria20","Maria12345","Maria@gmail.com",0));
				testObject.registerUserIncludeId(new Users(3,"Angel30","Angel12345","Angel@gmail.com",0));
				testObject.registerUserIncludeId(new Users(4,"Brian40","Brian12345","Brian@gmail.com",1));
				testObject.registerUserIncludeId(new Users(5,"Chris50","Chris12345","Chris@gmail.com",1));
				
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	
	//Test the registerUser() method (Create Method)
	@Test
	public void testRegisterUser() {
			
			assumeTrue(connectedDB);
			
			Users testUsers = new Users(10,"TestUserName","TestPasword","TestEmail@test.com",0);
			
			try {
				
				//Insert the object into the database
				Integer testID = testObject.registerUserIncludeId(testUsers);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(10));
				
				//Is the object that was inserted in the database?
				assertNotNull(testObject.getUserById(10));
				
				//Make sure all information inserted in correct and consistent
				Users actualUser = testObject.getUserById(10);
				
				assertEquals(testUsers.getUserName(),actualUser.getUserName());
				assertEquals(testUsers.getUserPassword(),actualUser.getUserPassword());
				
				//Remove test data used during test
				testObject.removeUserById(10);
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllUsers() method (Retrieve Method)
	@Test
	public void testGetAllUsers() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Users object from database
				ArrayList<Users> actual = testObject.getAllUsers();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first User has the correct name
				assertThat(actual.get(0).getUserName(),is("Steve10"));
				
				//Make sure that the first User Password is correct
				assertThat(actual.get(0).getUserPassword(),is("Steve12345"));
				
				//Make sure that this User does not exist
				assertThat(actual,not(hasItems(new Users(5,"Marshall","*****","Fake@fake",0))));
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getUserById() method (Retrieve Method)
	@Test
	public void testGetUserById() {
		
		try {
			
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getUserById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getUserById(1).getUserName(),"Steve10");
		
		assertThat(testObject.getUserById(1).getUserPassword(),startsWith("Ste"));
		
		assertEquals(testObject.getUserById(1).getUserEmail(),"Steve@gmail.com");
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getUserById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetUserById()");
			
		}
		
	}
	
	//Test the getUserByName() method (Retrieve Method)
	@Test
	public void testGetUserByName() {
		
		try {
			//Make sure Null is not returned when given a valid Name
			assertNotNull(testObject.getUserByName("Maria20"));
			
			//Make sure the object obtained has the same info that was inserted
			assertEquals(testObject.getUserByName("Maria20").getUserName(),"Maria20");
			
			assertThat(testObject.getUserByName("Maria20").getUserPassword(),is("Maria12345"));
			
			assertEquals(testObject.getUserByName("Maria20").getUserEmail(),"Maria@gmail.com");
			
			//Make sure Null is returned when an invalid ID is provided
			assertNull(testObject.getUserByName("FakeUser69"));
			
			}catch(Exception e) {
				
				System.out.println("Unable to use GetUserByName()");
				
			}
		
	}
	
	//Test the updateBrand() method
	@Test
	public void testUpdateUser() {
			
			
			Users testBrands = new Users(3,"Angel30!!!","Angel54321","Angel@yahoo.com",0);
			
			try {
				//Make sure you are able to update the given Brand
				Boolean updatedUser = testObject.updateUsers(testBrands);
				
				//Make sure the user was actually updated
				assertTrue(updatedUser);
				
				//Make sure the updated Brand has the correct variables
				assertThat(testObject.getUserById(3).getUserName(),allOf(is("Angel30!!!"),not("Angel30")));
				
				assertThat(testObject.getUserById(3).getUserPassword(),allOf(is("Angel54321"),not("Angel12345.")));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeBrand() method
	@Test
	public void testRemoveUser() {
			
			try {
				
				//Test whether the Brand is removed correctly
				Boolean removedUser = testObject.removeUserById(5);
				
				assertTrue(removedUser);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getUserById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateUsers(new Users(5,"Fake","Info","rmation",1)));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerUserIncludeId(new Users(5,"Chris50","Chris12345","Chris@gmai.com",1));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
			for(Users out:testObject.getAllUsers()) {
				
				testObject.removeUserById(out.getUserID());
			
			}
			}
			catch(Exception e) {
				System.out.println("Unable to clear DB of test data");
			}
			
		}
}
