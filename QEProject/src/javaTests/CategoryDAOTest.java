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

import javaDAO.CategoryDAO;
import javaModels.Category;


public class CategoryDAOTest {

	public static CategoryDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new CategoryDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				testObject.registerCategory(new Category(1,"Guitars","Electric & Acoustic"));
				testObject.registerCategory(new Category(2,"Basses","Electric basses"));
				testObject.registerCategory(new Category(3,"Drums","Sets of drums"));
				testObject.registerCategory(new Category(4,"Keyboards","Pianos and keyboards"));
				testObject.registerCategory(new Category(5,"Pedals","Guitar pedal effects"));
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	//Test the registerCategory() method (Create Method)
	@Test
	public void testRegisterCategory() {
			
			assumeTrue(connectedDB);
			
			Category testCategory = new Category(10,"TestingCategory","TestingDescription");
			
			try {
				
				//Insert the object into the database
				Integer testID = testObject.registerCategory(testCategory);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(10));
				
				//was the object inserted into the database?
				assertNotNull(testObject.getCategoryById(10));
				
				//Make sure all information inserted in correct and consistent
				Category actualCategory = testObject.getCategoryById(10);
				
				assertEquals(testCategory.getCategoryName(),actualCategory.getCategoryName());
				assertEquals(testCategory.getCategoryDescription(),actualCategory.getCategoryDescription());
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllCategory() method (Retrieve Method)
	@Test
	public void testGetAllCategory() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Category object from database
				ArrayList<Category> actual = testObject.getAllCategory();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first Category has the correct name
				assertThat(actual.get(0).getCategoryName(),is("Guitars"));
				
				//Make sure that the first Category description is correct
				assertThat(actual.get(0).getCategoryDescription(),is("Electric & Acoustic"));
				
				//Make sure that this Brand does not exist
				assertThat(actual,not(hasItems(new Category(5,"Marshall","*****"))));
				
				//Make sure that the ArrayList is not null
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getCategoryById() method (Retrieve Method)
	@Test
	public void testGetCategoryById() {
		
		try {
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getCategoryById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getCategoryById(1).getCategoryName(),"Guitars");
		
		assertThat(testObject.getCategoryById(1).getCategoryDescription(),startsWith("Ele"));
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getCategoryById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetCategoryById()");
			
		}
		
	}
	
	//Test the getCategoryByName() method (Retrieve Method)
	@Test
	public void testGetCategoryByName() {
		
		try {
			//Make sure Null is not returned when given a valid Name
			assertNotNull(testObject.getCategoryByName("Keyboards"));
			
			//Make sure the object obtained has the same info that was inserted
			assertEquals(testObject.getCategoryByName("Keyboards").getCategoryName(),"Keyboards");
			
			assertThat(testObject.getCategoryByName("Keyboards").getCategoryDescription(),startsWith("Pian"));
			
			//Make sure Null is returned when an invalid ID is provided
			assertNull(testObject.getCategoryByName("FalseCompany"));
			
			}catch(Exception e) {
				
				System.out.println("Unable to use GetCategoryById()");
				
			}
		
	}
	
	//Test the updateCategory() method
	@Test
	public void testUpdateCategory() {
			
			
			Category testCategory = new Category(3,"Drums!!!","Full sets of drums");
			
			try {
				//Make sure you are able to update the given Category
				Boolean updatedCategory = testObject.updateCategory(testCategory);
				
				//Make sure the user was actually updated
				assertTrue(updatedCategory);
				
				//Make sure the updated Brand has the correct variables
				assertThat(testObject.getCategoryById(3).getCategoryName(),allOf(is("Drums!!!"),not("Drums")));
				
				assertThat(testObject.getCategoryById(3).getCategoryDescription(),allOf(is("Full sets of drums"),not("Sets of drums")));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeCategory() method
	@Test
	public void testRemoveCategory() {
			
			try {
				
				//Test whether the Brand is removed correctly
				Boolean removedCategory = testObject.removeCategoryById(5);
				
				assertTrue(removedCategory);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getCategoryById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateCategory(new Category(5,"FalseInfo","NotInfo")));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerCategory(new Category(5,"Pedals","Guitar pedal effects"));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
			for(Category out:testObject.getAllCategory()) {
				
				testObject.removeCategoryById(out.getCategoryID());
			
			}
			}
			catch(Exception e) {
				System.out.println("Unable to clear DB of test data");
			}
			
		}
}
