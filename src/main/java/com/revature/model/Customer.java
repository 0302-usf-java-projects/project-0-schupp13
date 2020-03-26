package com.revature.model;

import java.util.ArrayList;

public class Customer {
	
	private String firstName =null;
	private String lastName = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String phone = null;	
	private Double balance = null;
	
	
	Customer(String username, String password,  String firstName, String lastName, String phone, String email, Double balance){
		
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setEmail(email);
		setBalance(balance);
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * This function allows a customer to make a Deposit into their bank account
	 * @param Amount
	 */
	public void makeDeposit(Double Amount) {
		
	}
	
	/**
	 * This function allows a user to make a Withdraw from their account
	 * @param Amount
	 */
	public void makeWithdraw(Double Amount) {
			
	}
	
	
	/**
	 * 
	 * fetches all transactions made by the user
	 * @return ArrayList
	 */
	public ArrayList<String> getTransactions() {
		
		ArrayList<String> transactions = new ArrayList<String>();
		return transactions;
	}


}
