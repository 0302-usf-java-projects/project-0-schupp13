package com.revature.controller;



import com.revature.exception.LoginException;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Transaction;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.service.CustomerService;
import com.revature.service.Service;





public class Controller {

	

	public static final Logger logger = Logger.getLogger("Bank Application: ");
	Service service = new Service();
	List<Customer> list = new ArrayList<>();
	public static String homeOption;
	public static boolean loggedIn = false;

	public static Customer  customer = new Customer(); 
	Scanner scan = new Scanner(System.in);
	CustomerService customerService = new CustomerService();
	
	
	public Controller(){
		
	}
	
	public void homeMenu()  {
		logger.info("Program started - Main Menu");
		String option;
		do {
		 System.out.println("Hello, Welcome to Parker's Bank!");
		 System.out.println("Long in: press 1");
		 System.out.println("Create Account: press 2");
		 System.out.println("Leave the program: press 3");
		 option = scan.nextLine();
		 
		 if(!(option.equals("1") || option.equals("2") || option.equals("3"))) {
			 System.out.println("Sorry that is not an option. Please try again.");
		 }
		 
		}while(!(option.equals("1") || option.equals("2") || option.equals("3")));
		
		switch(option) {
		case "1":
			try {
				loginMenu();
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				logger.error("username or password was incorrrect. Main Menu.");
				
			}
			homeMenu();
			break;
		case "2":
			createUser();
			homeMenu();
			break;
		case "3":
			logger.info("End");
			System.exit(0);	
		default:
			System.out.printf("***************%nNot a valid entry, try again%n******************");
			homeMenu();
			
			
		}
	}
	
	public void  loginMenu() throws LoginException{
		
		 String username;
		 String password;
			
			Map<String, String> credentials = new HashMap<String, String>();
			logger.info("Program started - Customer is loggin - Login Menu");
		
			 System.out.println("Please enter your credintials below:  ");
			 
			 System.out.println("Please enter your Username:");
			 username = scan.nextLine();
			 credentials.put("username", username.trim());
			 
			 System.out.println("Please enter you password:");
			 password = scan.nextLine();
			 credentials.put("password", password.trim());
		 
			 customer = customerService.login(credentials.get("username"),credentials.get("password"));

			 if(customer == null) {
				 throw new LoginException();
			 }
		checkSecurityForMenu();
	}
	
	/**
	 * checks the user security level- 0 is a customer, 1 is a employee, 2 is a manger
	 */
	public void checkSecurityForMenu() {
		logger.info("Login was succelfull. Checking security level of user");
		setLogegdIn(true);

		if(customer.getLevel() == 0) {
			logger.info("User has customer level access");
			customerMenu();
		}else if (customer.getLevel() == 1) {
			logger.info("User had employee level access");
			employeeMenu();
		}else {
			logger.error("User does not have an accurate userlevel sending user back to the home menu.Look at user Record: "  + customer.getId());
			homeMenu();
		}
		
		
	}
	
	private void employeeMenu() {
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String username = customer.getUsername();
		String password = customer.getPassword();
		int id = customer.getId();
		String employeeOption;
		
		System.out.println("**********************************************");
		System.out.printf("Welcome %s %s! %n UserName: %s %n Password %s%n Employee ID: %d %n", 
				firstName , lastName, username, password, id);
		System.out.println("**********************************************");
		System.out.println("To View Pending Accounts: Press 1");
		System.out.println("To See A Transaction Log: Press 2");
		System.out.println("Logout: press 3");
		
		employeeOption = scan.nextLine();
		 switch(employeeOption) {
			case "1":
				reviewAccounts();
				employeeMenu();	
				break;
			case "2":
				viewTrans();
				employeeMenu();	
				break;
			case "3":
				logout();	
			default:
				System.out.println("Not an option. Please try again.");
				employeeMenu();
		 }
		 
	}

	private void viewTrans() {
		// TODO Auto-generated method stub
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = customerService.getAllTransactions();
		
		System.out.println();
		
		
		if(transactions.size() > 0) {
			
			for(Transaction transaction: transactions) {
				
				System.out.printf(" Transaction ID: " + transaction.getId() + " " + transaction.transType + " From Account: " + transaction.getFromAccount() + " To Account: " + transaction.getToAccount()  + " Amount: %.2f %n%n", transaction.getAmount());
			}
			
		}else {
			System.out.println("No Transactions");
		}

		
	}

	private void reviewAccounts() {
		String employeeOption;
		boolean update;
	
		do {
		System.out.println("**********************************************");
		System.out.printf("%s %s, please approve accounts by entering the account number below.%n Or enter 'back' to go back to the employee menu.%n", 
				customer.getFirstName() , customer.getLastName());
		System.out.println("**********************************************");
		
		List<Account> pendingAccounts = customerService.findPending();
		 List<Integer> accountInList = new ArrayList<Integer>(); 
		 if (pendingAccounts.size() > 0) {
			
				for(Account account: pendingAccounts) {
					accountInList.add(account.getId());
					Customer customerInLoop = customerService.getById(account.getCustomerId());
					
					System.out.printf("Customer Name: %s %s %nAccount #: %d %n Account Type: %s%n Account Balance: %.2f%n%n",
					customerInLoop.getFirstName(), customerInLoop.getLastName(), account.getId(), account.getAccountType(), account.getBalance()) ;
					System.out.println("-------------------------------------------------");
				}
				
		
			}else {
				System.out.println("Accounts: No Accounts to update - enter 'back' to go to employee menu");
			}
		 
		 System.out.println("Enter Here: ");
	
		 employeeOption = scan.nextLine();
		 try {
			 Integer employeeOptionInt = Integer.parseInt(employeeOption);
			 update = accountInList.contains(employeeOptionInt);
			 if(update)  {
				 
				 
				boolean updated =  customerService.approveAccountById(employeeOptionInt);
				if(updated) {
					System.out.printf("Account %s has been updated. %n", employeeOption);
				}
			 }
		 }catch(NumberFormatException e) {
			 
		 }
	
		}while(!(employeeOption.equalsIgnoreCase("back")) );
		
	}

	public void createUser() {
		
		String firstName;
		String lastName;
		String userName; 
		String password;
		boolean test;
		 
		 
		 do {
		 System.out.println("Please enter a Username:");
		 userName = scan.nextLine();
		 test = customerService.checkUser(userName.trim());
		 //if username does not exist  the user can proceed with creating an account
		 if(!test) {
			
			 System.out.println("That username is available!! ");
			 System.out.println("Please enter a password:");
			 password = scan.nextLine();
			 System.out.println("What is your firstName:");
			 firstName = scan.nextLine();
			 System.out.println("What is your lastName");
			 lastName = scan.nextLine();
			 customer = customerService.createUser(userName.trim(), password.trim(), firstName.trim(), lastName.trim());
			 
			customerMenu();
			 
		 }else {
			 System.out.println("Sorry but that username is taken, please try another username.");
		 }
		 }while(test);
		 
	}
	
	public void createBankAccount(){
		String bankOption;
		int flag =0;
		do {
		 System.out.println("What type of bank account would you like to open");
		 System.out.println("Checking: press 1");
		 System.out.println("Savings: prsss 2");
		 System.out.println("Back to User Page: prsss 3");
		
		  bankOption = scan.nextLine();
		
		 
		 if(bankOption.equals("1")) {
			 flag = customerService.createBankAccount(customer.getId(), "checking");
	
		 }else if(bankOption.equals("2")) {
			 flag = customerService.createBankAccount(customer.getId(), "savings");
		
		 }else if(bankOption.equals("3")) {
			
			 flag=1;
		 }else {
			 System.out.println("Sorry that is not a valid option please try agin.");
			 flag = -1;
		 }
		 
		}while(flag < 0);
		 
	
	}
	
	public void customerMenu() {
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String username = customer.getUsername();
		String password = customer.getPassword();
		
		 List<Account> customerAccounts = customerService.getAccountsByCustomerId(customer.getId());
		System.out.println("**********************************************");
		System.out.printf("Welcome %s %s! %n UserName: %s %n Password %s%n%n", 
				firstName , lastName, username, password);
	

		if (customerAccounts.size() > 0) {
			for(Account account: customerAccounts) {
				String pending = account.isApproved() ? "Active" : "Pending";

				System.out.printf("Account #: %d %n Account Type: %s%n Account Balance: %.2f%n Account Status: %s%n",
				account.getId(), account.getAccountType(), account.getBalance(), pending) ;
				System.out.println("-------------------------------------------------");
			}
		}else {
			System.out.println("Accounts: You currently have no accounts.");
		}
		
		System.out.printf("%nCreate Account: Press 1 %n");
		System.out.printf("Make a deposit: Press 2 %n");
		System.out.printf("Make a withdraw: Press 3 %n");
	
		System.out.printf("Logout: Press 4%n");
		
		 String customerMenuOption = scan.nextLine();
		 
		 switch(customerMenuOption) {
			case "1":
				createBankAccount();
				customerMenu();	
				break;
			case "2":
				makeDeposit();
				customerMenu();	
				break;
			case "3":
				makeWithdraw();
				customerMenu();	
			case "4":
				logout();
			default:
				System.out.printf("%n***************%nNot a valid entry, try again%n******************%n");
				customerMenu();}
		 };
	


	private void makeWithdraw() {
		System.out.println("List Eligible Accounts: ");
		List<Account>EligibileAccount = customerService.getEligibleAccounts(customer.getId());
		 List<Integer> accountInList = new ArrayList<Integer>(); 
		 boolean flag = false;
		 do {
		if(EligibileAccount.size() > 0) {
			for(Account account: EligibileAccount) {
				accountInList.add(account.getId());
				String pending = account.isApproved() ? "Active" : "Pending";
				
				System.out.printf("Account #: %d %n Account Type: %s%n Account Balance: %.2f%n Account Status: %s%n",
				account.getId(), account.getAccountType(), account.getBalance(), pending) ;
				System.out.println("-------------------------------------------------");
				
			}
		}else {
			System.out.println("Accounts: You currently have no accounts to make a withdraw from.");
			customerMenu();
		}
		
		try {
			System.out.println("What account would you like to make a withdraw From? Enter Account #");
			 String withdrawFrom = scan.nextLine();
			System.out.println("How much would like to deposit?");
			 String withdrawAmount = scan.nextLine();
			 Float withdrawFromFloat = Float.parseFloat(withdrawAmount);
			 Integer withdrawToInt = Integer.parseInt(withdrawFrom);
			 if(accountInList.contains(withdrawToInt)) {
			
				 flag = customerService.makeWithdraw(withdrawFromFloat, withdrawToInt);
				 if(flag == false) {
					 System.out.println("Insuficent funds. ");
				 }
				 
			 }else {
				 System.out.println("Not a valid entry. try again");
			 }

			
		}catch(NumberFormatException e) {
			 
			logger.error("Not a valid entry. try again");
			//System.out.println("Not a valid entry. try again");
			makeDeposit();
		}

		 }while(flag == false);
		
	}

	private void makeDeposit() {
		System.out.println("List Eligible Accounts: ");
		List<Account>EligibileAccount = customerService.getEligibleAccounts(customer.getId());
		 List<Integer> accountInList = new ArrayList<Integer>(); 
		 boolean flag = false;
		 do {
		if(EligibileAccount.size() > 0) {
			for(Account account: EligibileAccount) {
				accountInList.add(account.getId());
				String pending = account.isApproved() ? "Active" : "Pending";
				
				System.out.printf("Account #: %d %n Account Type: %s%n Account Balance: %.2f%n Account Status: %s%n",
				account.getId(), account.getAccountType(), account.getBalance(), pending) ;
				System.out.println("-------------------------------------------------");
				
			}
		}else {
			System.out.println("Accounts: You currently have no accounts to make a deposit to.");
			customerMenu();
		}
		
		try {
			System.out.println("What account would you like to make a deposit to? Enter Account #");
			 String depositTo = scan.nextLine();
			System.out.println("How much would like to deposit?");
			 String depositAmount = scan.nextLine();
			 Float depositAmountFloat = Float.parseFloat(depositAmount);
			 Integer depositToInt = Integer.parseInt(depositTo);
			 if(accountInList.contains(depositToInt)) {
			
				 flag = customerService.makeDeposit(depositAmountFloat, depositToInt);
				
				 if(flag == false) {
					 System.out.println("transaction did not go through. ");
				 }
				 
			 }else {
				 System.out.println("Not a valid entry. try again");
			 }

			
		}catch(NumberFormatException e) {
			 
		 
			System.out.println("Not a valid entry. try again");
			makeDeposit();
		}

		 }while(flag == false);
		 
		
		
	}

	public void logout() {
		
		setLogegdIn(false);
		
		System.out.printf("%n****User: %s has logged out****%n%n", customer.getUsername());
		customer = null;
		homeMenu();
		
	}
	
	public static boolean isLoggedIn() {
		return loggedIn;
	}



	public static void setLogegdIn(boolean loggedIn) {
		Controller.loggedIn = loggedIn;
	}
	
	

	

	
	
	
	
	
	
	
	
	
	
	
}
