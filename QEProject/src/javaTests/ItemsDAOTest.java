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
import java.util.ArrayList;
import java.util.Objects;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javaDAO.BrandsDAO;
import javaDAO.CategoryDAO;
import javaDAO.ItemsDAO;
import javaModels.Brands;
import javaModels.Category;
import javaModels.Items;

public class ItemsDAOTest {
	
	public static ItemsDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new ItemsDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				//I need to have some Categories in my Categories table in order for Items to be accessible
				CategoryDAO insertCategoryPrequesites = new CategoryDAO();
				
				insertCategoryPrequesites.registerCategory(new Category(1,"Guitars","Electric and Acoustic guitars"));
				insertCategoryPrequesites.registerCategory(new Category(2,"Basses","Electric and Acoustic basses"));
				insertCategoryPrequesites.registerCategory(new Category(3,"Amps","Electric amplifiers"));
				insertCategoryPrequesites.registerCategory(new Category(4,"Microphones","Mics for all instruments"));
				
				//I need to have some Brands in my Brands table in order for Items to be accessible
				BrandsDAO insertBrandPrequesites = new BrandsDAO();
				
				insertBrandPrequesites.registerBrandIncludeId(new Brands(1,"Gibson","Authentic"));
				insertBrandPrequesites.registerBrandIncludeId(new Brands(2,"Fender","Great Company"));
				
				//I need to have some Items in my Items table in order to test the DAO's methods
				ItemsDAO insertItemsPrequesites = new ItemsDAO();
				
				//Constructor for Items is (int itemID, int categoryID, int brandID, double itemPrice, String itemDescription,Boolean itemInstrument)
				
				insertItemsPrequesites.registerItemIncludeId(new Items(1,1,1,250.0,"Epiphone Gibson SG 2012",true));
				insertItemsPrequesites.registerItemIncludeId(new Items(2,1,2,70.0,"Squire Fender 2007",true));
				insertItemsPrequesites.registerItemIncludeId(new Items(3,3,1,500.0,"AmpX Fender 2002",false));
				insertItemsPrequesites.registerItemIncludeId(new Items(4,4,2,100.0,"NeX Gibson Microphone",false));
				insertItemsPrequesites.registerItemIncludeId(new Items(5,2,2,200.0,"BassX Gibson 2002",true));
					
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	//Test the registerItem() method (Create Method)
	@Test
	public void testRegisterItem() {
				
				assumeTrue(connectedDB);
				
				Items testItems = new Items(10,1,1,200.0,"TestItem",true);
				
				try {
					
					//Insert the object into the database
					Integer testID = testObject.registerItemIncludeId(testItems);
					
					//Does the method return the correct ID assigned to the User?
					assertThat(testID, equalTo(10));
					
					//Is the object that was inserted in the database?
					assertNotNull(testObject.getItemById(10));
					
					//Make sure all information inserted in correct and consistent
					Items actualItem = testObject.getItemById(10);
					
					assertEquals(testItems.getCategoryID(),actualItem.getCategoryID());
					assertEquals(testItems.getItemDescription(),actualItem.getItemDescription());
					
					//Remove test data used during test
					testObject.removeItemById(10);
					
				} catch (Exception e) {
					
					System.out.println("Unable to insert User into DB");
					
				}
				
			}

	//Tests getAllItems() method (Retrieve Method)
	@Test
	public void testGetAllItems() {
				
				assumeTrue(connectedDB);
				
				try {
					
					//Get all Items object from database
					ArrayList<Items> actual = testObject.getAllItems();
					
					//Make sure there only 5 objects stored
					assertThat(actual.size(),is(5));
					
					//Makes sure the first Item has the correct Description
					assertThat(actual.get(0).getItemDescription(),is("Epiphone Gibson SG 2012"));
					
					//Make sure that the first Items' Category Id is correct
					assertThat(actual.get(0).getCategoryID(),is(1));
					
					//Make sure that this Item does not exist
					assertThat(actual,not(hasItems(new Items(100,10,10,1000.0,"FakeItem",true))));
					
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
				}
				
			}
			
	//Test the getItemById() method (Retrieve Method)
	@Test
	public void testGetItemById() {
			
			try {
				
			//Make sure Null is not returned when given a valid ID
			assertNotNull(testObject.getItemById(1));
			
			//Make sure the object obtained has the same info that was inserted
			assertEquals(testObject.getItemById(1).getItemDescription(),"Epiphone Gibson SG 2012");
			
			assertThat(testObject.getItemById(1).getBrandID(),is(1));
			
			assertThat(testObject.getItemById(1).getItemPrice(),is(250.0));
			
			//Make sure Null is returned when an invalid ID is provided
			assertNull(testObject.getItemById(100));
			
			}catch(Exception e) {
				
				System.out.println("Unable to use GetItemById()");
				
			}
			
		}
		
	//Test the updateItem() method
	@Test
	public void testUpdateItem() {
				
				
				Items testItem = new Items(3,1,1,10.0,"Not Description",true);
				
				try {
					//Make sure you are able to update the given Brand
					Boolean updatedItem = testObject.updateItem(testItem);
					
					//Make sure the user was actually updated
					assertTrue(updatedItem);
					
					//Make sure the updated Brand has the correct variables
					assertThat(testObject.getItemById(3).getCategoryID(),allOf(is(1),not(3)));
					
					assertThat(testObject.getItemById(3).getItemPrice(),allOf(is(10.0),not(500.0)));
					
					assertThat(testObject.getItemById(3).getItemDescription(),is("Not Description"));
					
				} catch (Exception e) {
					
					System.out.println("Unable to use Update method");
					
				}
				
				
			}

	//Test the removeItem() method
	@Test
	public void testRemoveItem() {
				
				try {
					
					//Test whether the UserDetails is removed correctly
					Boolean removedItem = testObject.removeItemById(5);
					
					assertTrue(removedItem);
					
					//Test that the object is no longer in the DB
					assertNull(testObject.getItemById(5));
					
					//Test that you cannot update a removed object
					assertFalse(testObject.updateItem(new Items(5,1,1,10000.0,"FakeInfo",true)));
					
					
					//Re-insert object to prevent failure from other tests
					testObject.registerItemIncludeId(new Items(5,2,2,200.0,"BassX Gibson 2002",true));
					
					
				} catch (Exception e) {
					
					System.out.println("Unable to use Remove method");
					
				}
			}
		
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
				
				try {
					
					//Removed initial items inserted
					
					for(Items out:testObject.getAllItems()) {
					
						testObject.removeItemById(out.getItemID());
				
					}
					
					//Remove initial Brands inserted
					
					BrandsDAO preBrands = new BrandsDAO();
					
					for(Brands out:preBrands.getAllBrands()) {
						
						preBrands.removeBrandById(out.getBrandID());
					}
					
					//Remove initial Categories inserted
					
					CategoryDAO preCate = new CategoryDAO();
					
					for(Category out:preCate.getAllCategory()) {
						
						preCate.removeCategoryById(out.getCategoryID());
					}
				
				}
				catch(Exception e) {
					System.out.println("Unable to clear DB of test data");
				}
				
			}
	
	
	
}
