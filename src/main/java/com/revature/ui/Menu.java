package com.revature.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.Service;

public class Menu {
	Scanner scan = new Scanner(System.in);
	
	public String homeMenu() {
		String option;
		do {
		 System.out.println("Hello Welcome to Hell's fargo!");
		 System.out.println("Long in: press 1");
		 System.out.println("Create Account: press 2");
		 System.out.println("Leave: press 3");
		 option = scan.nextLine();
		 
		 if(!(option.equals("1") || option.equals("2") || option.equals("2"))) {
			 System.out.println("Sorry that is not an option. Please try again.");
		 }
		 
		}while(!(option.equals("1") || option.equals("2") || option.equals("2")));
		return option;	
	}
	
	
	public Map<String, String> loginMenu(Map<String,String> credentials) {	

		 String username;
		 String password;
		 //Map<String, String> credentials = new HashMap<String, String>();
		 
			 System.out.println("Please enter your credintials below:  ");
			 
			 System.out.println("Please enter your Username:");
			 username = scan.nextLine();
			 credentials.put("username", username);
			 
			 System.out.println("Please enter you password:");
			 password = scan.nextLine();
			 credentials.put("password", password);
			 
			 return credentials;
	}
	
	
	
//	public void createAccount() {
//		
//		 String userName; 
//		 boolean test;
//		 do {
//		 System.out.println("Please enter a Username:");
//		 userName = scan.nextLine();
//		 test = Service.checkUser(userName, password);
//		 //if username does not exist  the user can proceed with creating an account
//		 if(!test) {
//			
//			 System.out.println("That username is available!! ");
//			 System.out.println("Please enter a password:");
//			 String password = scan.nextLine();
//			 System.out.println("What is your firstName:");
//			 String firstName = scan.nextLine();
//			 System.out.println("What is your lastName");
//			 String lastName = scan.nextLine();
//			 Service.createAccount(userName, password, firstName, lastName);
//			 
//			
//			 
//		 }else {
//			 System.out.println("Sorry but that username is taken, please try another username.");
//		 }
//		 }while(!test);
//		 
//	}
	
	public static void clearScreen() {  
	    
	    System.out.flush();  
	   }

	public String CustomerMenu(Customer customer, List<Account> customerAccounts) {
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String username = customer.getUsername();
		String password = customer.getPassword();
		
		clearScreen();
		System.out.println("**********************************************");
		System.out.printf("Welcome %s %s %n UserName: %s %n Password %s%n", 
				firstName , lastName, username, password);
		System.out.println("**********************************************");
		System.out.printf("Create Account: Press 1 %n");
		System.out.printf("Make a deposit: Press 2 %n");
		System.out.printf("Make a withdraw: Press 3 %n");
		System.out.printf("Apply for a loan: Press 4 %n");
		System.out.printf("Logout: Press 5%n");
		 String customerMenuOption = scan.nextLine();
	
		if (customerAccounts.size() > 0) {
			for(Account account: customerAccounts) {
				String pending = account.isApproved() ? "Approved" : "Pending";
				System.out.printf("%n%nAccount #: %d %n Account Type: %s%n Account Balance: %f%n Account Status: %s %n",
						account.getId(), account.getAccountType(), account.getBalance(), pending) ;
			}
		}else {
			System.out.println("Accounts: You have no Accounts");
		}
		

		 return customerMenuOption;
		
		
		
	}
}
