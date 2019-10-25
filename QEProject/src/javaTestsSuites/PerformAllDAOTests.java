package javaTestsSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import javaTests.BrandsDAOTest;
import javaTests.CategoryDAOTest;
import javaTests.InventoryDAOTest;
import javaTests.ItemsDAOTest;
import javaTests.OrderDAOTest;
import javaTests.UserDetailsDAOTest;
import javaTests.UsersDAOTest;

@RunWith(Suite.class)
@SuiteClasses({ BrandsDAOTest.class,
				CategoryDAOTest.class,
				InventoryDAOTest.class,
				ItemsDAOTest.class,
				OrderDAOTest.class,
				UserDetailsDAOTest.class,
				UsersDAOTest.class})

public class PerformAllDAOTests {

}
