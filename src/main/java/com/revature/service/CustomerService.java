package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Transaction;
import com.revature.repository.CustomerDao;

public class CustomerService {

	private CustomerDao cdao;
	
	{
		cdao = new CustomerDao();
	}
	
	public List<Customer> getAllCustomers(){
	
		return cdao.findAll();
	}
	
	public Customer getById(int id) {
		return cdao.findById(id);
	}
	
	public Customer insert(String firstName, String lastName, String username, String password, int level) {
		
		Customer result = cdao.insert(new Customer(firstName,  lastName,  username,  password, level)); 
		return result;
	}
	
	public Customer login(String username, String password) {
		
		Customer result = cdao.login(username, password);
		return result;
	}



	public List<Account> getAccountsByCustomerId(int id){
		return cdao.getAccountsByCustomerId(id);
	}

	public int createBankAccount(int id, String type) {
		
		return cdao.createBankAccount(id , type);
	}
	
	public boolean checkUser(String username) {
		
		return cdao.checkUser(username);
	}

	public Customer createUser(String userName, String password, String firstName, String lastName) {
		return cdao.createUser(userName, password, firstName, lastName);
		
	}
	
	public List<Account> findPending() {
	
		return cdao.findPending();
	}

	

	public boolean approveAccountById(Integer employeeOptionInt) {
		
		return cdao.approveAccountById(employeeOptionInt);
	}

	public List<Account> getEligibleAccounts(int ID) {
		
		return cdao.getEligibleAccounts(ID);
	}



	public boolean makeDeposit(Float depositAmountFloat, Integer depositToID) {
		
		Float currentValue = cdao.currentBalanceByID(depositToID);
		Float newValue = currentValue + depositAmountFloat;
		cdao.createTransaction(depositToID, depositToID, depositAmountFloat, "DEPOSIT");

		return cdao.makeDeposit(newValue, depositToID);
		
	}

	public boolean makeWithdraw(Float withdrawFromFloat, Integer withdrawToInt) {
		Float currentValue = cdao.currentBalanceByID(withdrawToInt);
		Float newValue = currentValue - withdrawFromFloat;
		
		if(newValue >= 0) {
			cdao.createTransaction(withdrawToInt, withdrawToInt, withdrawFromFloat, "WITHDRAW");
			return cdao.withDraw(newValue, withdrawToInt);
		}else {
			return false;
		}
		
	}


public List<Transaction> getAllTransactions(){
	
	List<Transaction> transactions = new ArrayList<Transaction>();
	transactions = cdao.getAllTransactions();
	return transactions;
	
}
	
}
