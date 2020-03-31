package com.revature.service;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.Customer;
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
	
	
}
