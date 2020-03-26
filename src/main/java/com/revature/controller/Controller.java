package com.revature.controller;

import java.util.Scanner;

import com.revature.service.Service;

public class Controller {
	
	// initial value of 
	private static boolean loggedIn = false;
	Scanner scan = new Scanner(System.in);
	Service service = new Service();
	
	public static String homeOption;
	
	
	
	public Controller(){
		
		homeMenu();
		
		
	}
	
	public void homeMenu() {
		
		String option;
		do {
		 System.out.println("Hello Welcome to Hell's fargo!");
		 System.out.println("Long in: press 1");
		 System.out.println("Create Account: press 2");
		 System.out.println("Leave: press 3");
		 option = scan.nextLine();
		 
		 if(option.equals("1")) {
			 loginMenu();
		 }else if(option.equals("2")) {
			 createAccount();
		 }else if(option.equals("3")) {
			 System.out.println("three");
			 System.exit(0);
			 
		 }else {
			 System.out.println("Sorry, that is not a valid option. Please try again.");
		 }
		 
		 
		}while(!(option.equals("1") || option.equals("2") || option.equals("2")));
		
		
	}
	
	
	public void loginMenu() {
		
		 String userName;
		 String password;
		 boolean test;
		 
		 do {
			 System.out.flush();  
			 System.out.println("Please enter your credintials below:  ");
			 System.out.println("Please enter your Username:");
			 userName = scan.nextLine();
		 
			 System.out.println("Please enter you password:");
			 password = scan.nextLine();
		 
			 test = Service.checkUserAndPassword(userName, password);
		 
			 if(!test) {
				 String option;
				 do {
				 System.out.println("Sorry, but the username and/or password is incorrect. Press 1 to try again or press 2 to go back to the main menu.");
				  option = scan.nextLine();
				  if(option.equals("1")) {
					  
				  }else if(option.equals("2")) {
					  homeMenu();
				  }else {
					  System.out.println("Sorry that is not an option try again.");
				  }
				 
				
				 }while(!(option.equals("1") || option.equals("2")));
			 }
		 
		 }while(!test);
		 
	}
	
	public void createAccount() {
		
		 String userName; 
		 boolean test;
		 
		 do {
		 System.out.println("Please enter a Username:");
		 userName = scan.nextLine();
		 test = Service.checkUser(userName);
		 //if username does not exist  the user can proceed with creating an account
		 if(!test) {
			 
			 System.out.println("That username is available!! ");
			 System.out.println("Please enter a password:");
			 String password = scan.nextLine();
			 System.out.println("What is your firstName:");
			 String firstName = scan.nextLine();
			 System.out.println("What is your lastName");
			 String lastName = scan.nextLine();
			 Service.createAccount(userName, password, firstName, lastName);
			 
		 }else {
			 System.out.println("Sorry but that username is taken, please try another username.");
		 }
		 }while(test);
		 
	}
	
	
	
	
	
}
