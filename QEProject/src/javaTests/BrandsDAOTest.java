package javaTests;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.Test;

import javaDAO.BrandsDAO;
import javaModels.Brands;

public class BrandsDAOTest {

	public static BrandsDAO testObject;
	public static Boolean connectedDB;
	
	@BeforeClass
	public static void setUpTestObject() {
		
		testObject = new BrandsDAO();
		
		//If the object returned by testConnection() is not null then connection successful
		if(Objects.nonNull(testObject.testConnection())) {
			
			connectedDB = true;
			
			//Test data inserted into the database used to test DAO methods
			try{
				
				testObject.registerBrandExcludeId(new Brands(1,"Fender","Great Company"));
				testObject.registerBrandExcludeId(new Brands(2,"Gibson","Authentic"));
				testObject.registerBrandExcludeId(new Brands(3,"Ibanez","Alright Com."));
				testObject.registerBrandExcludeId(new Brands(4,"ESP Guitars","Unknown"));
				testObject.registerBrandExcludeId(new Brands(5,"PRS Guitars","No Description"));
				
			}catch(Exception e) {
				
				System.out.println("Unable to insert test data into DB");
				
			}
			
			
			
		}
		else {
			
			connectedDB = false;
			
		}
		
	}
	
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
	
	
	
}
