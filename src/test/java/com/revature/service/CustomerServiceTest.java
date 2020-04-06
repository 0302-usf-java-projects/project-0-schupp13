package com.revature.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.model.Customer;

public class CustomerServiceTest {
	
	@Test
	public void test() {
		System.out.println("This test ran");
	}

	 @Test
	  public void login() {
		 CustomerService service = new CustomerService();
	    Customer result = service.login("pooker", "password");
		   assertTrue(result.getFirstName().equals("Philip"));
		   
	  }
}
