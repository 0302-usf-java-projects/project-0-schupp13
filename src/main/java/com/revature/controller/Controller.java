package com.revature.controller;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.service.Service;
import com.revature.ui.Menu;




public class Controller {
	

	
	Service service = new Service();
	CustomerService customerService = new CustomerService();
	List<Customer> list = new ArrayList<>();
	public static String homeOption;
	
	public static boolean logegdIn = false;
	
	public static Menu  menus = new Menu();




	public Controller(){
		
//		Customer two = cus.insert("Parker", "dopeboy", "999-555-4556", "pasjfj@jfsjd.com");
// 		
// 		if(two == null) {
// 			
// 			System.out.println("try again");
// 			
// 		}
		 
//		 System.out.println(two.getFirstName());
//		
//		 list = cus.getAllCustomers();
//		 for(Customer cust: list) {
//			 System.out.println(cust.getFirstName() + " " + cust.getLastName() + " " + cust.getEmail());
//		 }
//		 
//		 Customer c = cus.getById(1);
//		 
//		 System.out.println(c.getPhone());
		
		
		
		String homeOption = menus.homeMenu();
		
		if(homeOption.equals("1")) {
			Map<String, String> credentials = new HashMap<String, String>();
			Customer  customer = new Customer(); 
			do {
			
				 credentials = menus.loginMenu(credentials);
				 customer = customerService.login(credentials.get("username"),credentials.get("password"));
				 
				 if(customer == null) {
					 System.out.printf("Sorry that user name and/or password is incorrect. %n Please Try again. %n");	 
				 }
				 
			}while(customer == null);
			List<Account> customerAccounts = customerService.getAccountsByCustomerId(customer.getId());
			int securityLevel = customer.getLevel();
			
			if(securityLevel == 0) {
				menus.CustomerMenu(customer, customerAccounts);
			}
			
			
			
			
			
			
		}
		
	}
	
	
	public static boolean isLogegdIn() {
		return logegdIn;
	}



	public static void setLogegdIn(boolean logegdIn) {
		Controller.logegdIn = logegdIn;
	}
	
	

	

	
	
	
	
	
	
	
	
	
	
	
}
