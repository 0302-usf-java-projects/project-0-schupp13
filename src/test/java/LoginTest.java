//Canonical JUnit import statement:
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.CustomerService;
public class LoginTest {
	
	private static CustomerService service;
	
	@Before
	public void setUp() {
		service = new CustomerService();	
	
	}
	
	
	  @After
	  public void tearDown() {
		  service = null;
	    System.out.println("After is running!");
	  }
	  
	  @Test
	  public void login() {
	    Customer result = service.login("pooker", "password");
	  
		   assertTrue(result.getFirstName() == "Philip");
		   
	  }
	  
	  @Test
	  public void testCustomerByID() {
		  Customer result1 = service.login("pooker", "password");
		  List<Account> result = service.getAccountsByCustomerId(result1.getId());
	   
		   assertTrue(result.size() == 1);
		   
	  }
	  
	  @Test
	  public void logout() {
		  
	  }

}
