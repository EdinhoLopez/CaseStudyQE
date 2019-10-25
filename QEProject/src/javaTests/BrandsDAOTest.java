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
import java.util.ArrayList;
import java.util.Objects;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javaDAO.BrandsDAO;

import javaModels.Brands;

public class BrandsDAOTest {

	public static BrandsDAO testObject;
	public static Boolean connectedDB;
	
	//Fill the database with the test data used for the test cases
	//Test data used for these test cases can be found here!
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new BrandsDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				testObject.registerBrandIncludeId(new Brands(1,"Fender","Great Company"));
				testObject.registerBrandIncludeId(new Brands(2,"Gibson","Authentic"));
				testObject.registerBrandIncludeId(new Brands(3,"Ibanez","Alright Com."));
				testObject.registerBrandIncludeId(new Brands(4,"ESP Guitars","Unknown"));
				testObject.registerBrandIncludeId(new Brands(5,"PRS Guitars","No Description"));
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
	
	//Test the registerBrand() method (Create Method)
	@Test
	public void testRegisterBrand() {
			
			assumeTrue(connectedDB);
			
			Brands testBrand = new Brands(10,"TestingBrand","TestingDescription");
			
			try {
				
				//Insert the object into the database
				Integer testID = testObject.registerBrandIncludeId(testBrand);
				
				//Does the method return the correct ID assigned to the User?
				assertThat(testID, equalTo(10));
				
				//Is the object that was inserted in the database?
				assertNotNull(testObject.getBrandById(10));
				
				//Make sure all information inserted in correct and consistent
				Brands actualBrand = testObject.getBrandById(10);
				
				assertEquals(testBrand.getBrandName(),actualBrand.getBrandName());
				assertEquals(testBrand.getBrandDescription(),actualBrand.getBrandDescription());
				
			} catch (Exception e) {
				
				System.out.println("Unable to insert User into DB");
				
			}
			
		}

	//Tests getAllBrands() method (Retrieve Method)
	@Test
	public void testGetAllBrands() {
			
			assumeTrue(connectedDB);
			
			try {
				
				//Get all Brands object from database
				ArrayList<Brands> actual = testObject.getAllBrands();
				
				//Make sure there only 5 objects stored
				assertThat(actual.size(),is(5));
				
				//Makes sure the first Brand has the correct name
				assertThat(actual.get(0).getBrandName(),is("Fender"));
				
				//Make sure that the last Brand description is correct
				assertThat(actual.get(4).getBrandDescription(),is("No Description"));
				
				//Make sure that this Brand does not exist
				assertThat(actual,not(hasItems(new Brands(5,"Marshall","*****"))));
				
				//Make sure that the ArrayList is not null
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	//Test the getBrandById() method (Retrieve Method)
	@Test
	public void testGetBrandById() {
		
		try {
		//Make sure Null is not returned when given a valid ID
		assertNotNull(testObject.getBrandById(1));
		
		//Make sure the object obtained has the same info that was inserted
		assertEquals(testObject.getBrandById(1).getBrandName(),"Fender");
		
		assertThat(testObject.getBrandById(1).getBrandDescription(),startsWith("Gre"));
		
		//Make sure Null is returned when an invalid ID is provided
		assertNull(testObject.getBrandById(100));
		
		}catch(Exception e) {
			
			System.out.println("Unable to use GetBrandById()");
			
		}
		
	}
	
	//Test the getBrandByName() method (Retrieve Method)
	@Test
	public void testGetBrandByName() {
		
		try {
			//Make sure Null is not returned when given a valid Name
			assertNotNull(testObject.getBrandByName("Gibson"));
			
			//Make sure the object obtained has the same info that was inserted
			assertEquals(testObject.getBrandByName("Gibson").getBrandName(),"Gibson");
			
			assertThat(testObject.getBrandByName("Gibson").getBrandDescription(),startsWith("Auth"));
			
			//Make sure Null is returned when an invalid ID is provided
			assertNull(testObject.getBrandByName("FalseCompany"));
			
			}catch(Exception e) {
				
				System.out.println("Unable to use GetBrandById()");
				
			}
		
	}
	
	//Test the updateBrand() method
	@Test
	public void testUpdateBrand() {
			
			
			Brands testBrands = new Brands(3,"Ibanez!!!","Alright Company");
			
			try {
				//Make sure you are able to update the given Brand
				Boolean updatedBrand = testObject.updateBrand(testBrands);
				
				//Make sure the user was actually updated
				assertTrue(updatedBrand);
				
				//Make sure the updated Brand has the correct variables
				assertThat(testObject.getBrandById(3).getBrandName(),allOf(is("Ibanez!!!"),not("Ibanez")));
				
				assertThat(testObject.getBrandById(3).getBrandDescription(),allOf(is("Alright Company"),not("Alright Com.")));
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Update method");
				
			}
			
			
		}

	//Test the removeBrand() method
	@Test
	public void testRemoveBrand() {
			
			try {
				
				//Test whether the Brand is removed correctly
				Boolean removedBrand = testObject.removeBrandById(5);
				
				assertTrue(removedBrand);
				
				//Test that the object is no longer in the DB
				assertNull(testObject.getBrandById(5));
				
				//Test that you cannot update a removed object
				assertFalse(testObject.updateBrand(new Brands(5,"PRS Guitars","NotInfo")));
				
				
				//Re-insert object to prevent failure from other tests
				testObject.registerBrandIncludeId(new Brands(5,"PRS Guitars","No Description"));
				
				
			} catch (Exception e) {
				
				System.out.println("Unable to use Remove method");
				
			}
		}
	
	//Clears database of all test data
	@AfterClass
	public static void clearTable() {
			
			try {
			for(Brands out:testObject.getAllBrands()) {
				
				testObject.removeBrandById(out.getBrandID());
			
			}
			}
			catch(Exception e) {
				System.out.println("Unable to clear DB of test data");
			}
			
		}
}
