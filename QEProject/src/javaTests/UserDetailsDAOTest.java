package javaTests;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
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

import javaDAO.UserDetailsDAO;
import javaDAO.UsersDAO;
import javaModels.UserDetails;
import javaModels.Users;

public class UserDetailsDAOTest {
	
	public static UserDetailsDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new UserDetailsDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				//I need to have some Users in my Users table in order for UserDetails to be accessible
				UsersDAO insertPrequesites = new UsersDAO();
				
				insertPrequesites.registerUserIncludeId(new Users(1,"Steve10","Steve12345","Steve@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(2,"Maria20","Maria12345","Maria@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(3,"Angel30","Angel12345","Angel@gmail.com",0));
				insertPrequesites.registerUserIncludeId(new Users(4,"Brian40","Brian12345","Brian@gmail.com",1));
				insertPrequesites.registerUserIncludeId(new Users(5,"Chris50","Chris12345","Chris@gmail.com",1));
				
				
				//Insert UserDetails now that I actually have users in my database
				testObject.registerUserDetails(new UserDetails(1,"Steve","Stevenson",LocalDate.now(),"9090 Street Dr"));
				testObject.registerUserDetails(new UserDetails(2,"Maria","Lopez",LocalDate.now(),"8080 Calle Dr"));
				testObject.registerUserDetails(new UserDetails(3,"Angel","Arcan",LocalDate.now(),"7070 Pass Dr"));
				testObject.registerUserDetails(new UserDetails(4,"Brian","Guy",LocalDate.now(),"6060 Mega Str"));
				testObject.registerUserDetails(new UserDetails(5,"Chris","Done",LocalDate.now(),"5050 Face Str"));
				
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	
	//Test the registerUserDetails() method (Create Method)
	@Test
	public void testRegisterUserDetails() {
			
			assumeTrue(connectedDB);
			
			Users testUsers = new Users(10,"TestUserName","TestPasword","TestEmail@test.com",0);
			
			UserDetails testUsersDetails = new UserDetails(10,"TestFName","TestLName",LocalDate.now(),"FakeStreet");
			
			try {
				
				//Insert User to database as a prequesite to inserting UserDetails
				UsersDAO prequesite = new UsersDAO();
				prequesite.registerUserIncludeId(testUsers);
				
				//Insert the object into the database
				Integer testID = testObject.registerUserDetails(testUsersDetails);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(10));
				
				//Is the object that was inserted in the database?
				assertNotNull(testObject.getUserDetailsById(10));
				
				//Make sure all information inserted in correct and consistent
				UserDetails actualUserDetails = testObject.getUserDetailsById(10);
				
				assertEquals(testUsersDetails.getUserFName(),actualUserDetails.getUserFName());
				assertEquals(testUsersDetails.getUserLName(),actualUserDetails.getUserLName());
				
				//Remove test data used during test
				testObject.removeDetailsById(10);
				prequesite.removeUserById(10);
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllUserDetails() method (Retrieve Method)
	@Test
	public void testGetAllUserDetails() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Users object from database
				ArrayList<UserDetails> actual = testObject.getAllUserDetails();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first User has the correct name
				assertThat(actual.get(0).getUserFName(),is("Steve"));
				
				//Make sure that the first User Password is correct
				assertThat(actual.get(0).getUserLName(),is("Stevenson"));
				
				//Make sure that this User does not exist
				assertThat(actual,not(hasItems(new UserDetails(100,"Marshall","*****",LocalDate.now(),"FakeStreet"))));
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getUserDetailsById() method (Retrieve Method)
	@Test
	public void testGetUserDetailsById() {
		
		try {
			
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getUserDetailsById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getUserDetailsById(1).getUserFName(),"Steve");
		
		assertThat(testObject.getUserDetailsById(1).getUserLName(),startsWith("Ste"));
		
		assertEquals(testObject.getUserDetailsById(1).getUserAddress(),"9090 Street Dr");
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getUserDetailsById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetUserById()");
			
		}
		
	}
	
	//Test the updateUserDetails() method
	@Test
	public void testUpdateUserDetails() {
			
			
			UserDetails testDetails = new UserDetails(3,"Miachelangelo","DaVi",LocalDate.now(),"9090 Real Str.");
			
			try {
				//Make sure you are able to update the given Brand
				Boolean updatedUserD = testObject.updateUserDetails(testDetails);
				
				//Make sure the user was actually updated
				assertTrue(updatedUserD);
				
				//Make sure the updated Brand has the correct variables
				assertThat(testObject.getUserDetailsById(3).getUserFName(),allOf(is("Miachelangelo"),not("Angel")));
				
				assertThat(testObject.getUserDetailsById(3).getUserLName(),allOf(is("DaVi"),not("Arcan")));
				
				assertThat(testObject.getUserDetailsById(3).getUserAddress(),is("9090 Real Str."));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeBrand() method
	@Test
	public void testRemoveUserDetails() {
			
			try {
				
				//Test whether the UserDetails is removed correctly
				Boolean removedUserD = testObject.removeDetailsById(5);
				
				assertTrue(removedUserD);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getUserDetailsById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateUserDetails(new UserDetails(5,"Fake","Info",LocalDate.now(),"rmation")));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerUserDetails(new UserDetails(5,"Chris","Done",LocalDate.now(),"5050 Face Str"));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
				
			//Removed initial userDetails inserted
			for(UserDetails out:testObject.getAllUserDetails()) {
				
				testObject.removeDetailsById(out.getUserID());
			
			}
			
			//Remove the initial User objects we inserted
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
