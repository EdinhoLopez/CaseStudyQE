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
import javaDAO.InventoryDAO;
import javaDAO.ItemsDAO;
import javaModels.Brands;
import javaModels.Category;
import javaModels.Inventory;
import javaModels.Items;

public class InventoryDAOTest {
	
	public static InventoryDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new InventoryDAO();
		
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
				
				//I need to have some Items in my Items table in order for Inventory to work
				ItemsDAO insertItemsPrequesites = new ItemsDAO();
				
				insertItemsPrequesites.registerItemIncludeId(new Items(1,1,1,250.0,"Epiphone Gibson SG 2012",true));
				insertItemsPrequesites.registerItemIncludeId(new Items(2,1,2,70.0,"Squire Fender 2007",true));
				insertItemsPrequesites.registerItemIncludeId(new Items(3,3,1,500.0,"AmpX Fender 2002",false));
				insertItemsPrequesites.registerItemIncludeId(new Items(4,4,2,100.0,"NeX Gibson Microphone",false));
				insertItemsPrequesites.registerItemIncludeId(new Items(5,2,2,200.0,"BassX Gibson 2002",true));
				insertItemsPrequesites.registerItemIncludeId(new Items(6,3,2,15.0,"Crate 15w amp",false));
				
				//I need to insert some data into Inventory in order to test my DAO methods
				
				InventoryDAO insertInventoryprequesite = new InventoryDAO();
				
				insertInventoryprequesite.registerInventoryIncludeId(new Inventory(1,2));
				insertInventoryprequesite.registerInventoryIncludeId(new Inventory(2,5));
				insertInventoryprequesite.registerInventoryIncludeId(new Inventory(3,1));
				insertInventoryprequesite.registerInventoryIncludeId(new Inventory(4,5));
				insertInventoryprequesite.registerInventoryIncludeId(new Inventory(5,0));
				
				
				
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
	public void testRegisterInventory() {
			
			assumeTrue(connectedDB);
			
			Inventory testInventory = new Inventory(6,6);
			
			try {
				
				//Insert the object into the database
				Integer testID = testObject.registerInventoryIncludeId(testInventory);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(6));
				
				//Is the object that was inserted in the database?
				assertNotNull(testObject.getInventoryById(6));
				
				//Make sure all information inserted in correct and consistent
				Inventory actualInventory = testObject.getInventoryById(6);
				
				assertEquals(testInventory.getItemQuantity(),actualInventory.getItemQuantity());
				assertEquals(testInventory.getItemID(),actualInventory.getItemID());
				
				//Remove test data used during test
				testObject.removeInventoryById(6);
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllInventory() method (Retrieve Method)
	@Test
	public void testGetAllInventory() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Inventory object from database
				ArrayList<Inventory> actual = testObject.getAllInventory();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first Inventory has the correct quantity
				assertThat(actual.get(0).getItemQuantity(),is(2));
				
				//Make sure that the first Inventory is correct
				assertThat(actual.get(0).getItemID(),is(1));
				
				//Make sure that this Inventory does not exist
				assertThat(actual,not(hasItems(new Inventory(6,100))));
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getInventoryById() method (Retrieve Method)
	@Test
	public void testGetInventoryById() {
		
		try {
			
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getInventoryById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getInventoryById(1).getItemQuantity(),2);
		
		assertThat(testObject.getInventoryById(1).getItemID(),is(1));
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getInventoryById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetUserById()");
			
		}
		
	}
	
	//Test the updateInventory() method
	@Test
	public void testUpdateInventory() {
			
			
			Inventory testInventory = new Inventory(3,3);
			
			try {
				//Make sure you are able to update the given Brand
				Boolean updatedInventory = testObject.updateInventory(testInventory);
				
				//Make sure the Inventory was actually updated
				assertTrue(updatedInventory);
				
				//Make sure the updated Inventory has the correct variables
				assertThat(testObject.getInventoryById(3).getItemQuantity(),allOf(is(3),not(1)));
				
				assertThat(testObject.getInventoryById(3).getItemID(),is(3));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeInventory() method
	@Test
	public void testRemoveInventory() {
			
			try {
				
				//Test whether the UserDetails is removed correctly
				Boolean removedInventory = testObject.removeInventoryById(5);
				
				assertTrue(removedInventory);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getInventoryById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateInventory(new Inventory(5,10)));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerInventoryIncludeId(new Inventory(5,0));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
				
				//Remove all Inventory objects inserted for testing
				
				for(Inventory out:testObject.getAllInventory()) {
					
					testObject.removeInventoryById(out.getItemID());
					
				}
				
				//Removed initial items inserted
				ItemsDAO preItems = new ItemsDAO();
				
				for(Items out:preItems.getAllItems()) {
				
					preItems.removeItemById(out.getItemID());
			
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
