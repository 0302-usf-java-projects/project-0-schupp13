package com.revature.model;

import java.util.ArrayList;
import java.util.Objects;

public class Account {
	
	private int id;
	private int customerId;
	private String accountType;
	private float balance;
	private boolean approved;
	
	public Account(int id, int customerId, String accountType, float balance, boolean approved){
		setId(id);
		setCustomerId(customerId);
		setAccountType(accountType);
		setBalance(balance);
		setApproved(approved);
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	

	
	@Override
	public int hashCode() {
		return Objects.hash(accountType, approved, balance, customerId, id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountType, other.accountType) && approved == other.approved
				&& Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance)
				&& customerId == other.customerId && id == other.id;
	}



	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", accountType=" + accountType + ", balance="
				+ balance + ", approved=" + approved + "]";
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
