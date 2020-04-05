package com.revature.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.revature.config.ConnectionUtil;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.model.Transaction;



public class CustomerDao implements DaoContract<Customer, Integer> {

	@Override
	public Customer findById(Integer id) {
		
		try(Connection conn = ConnectionUtil.connect()){
		String sql = "select * from \"Customer\" where \"Id\" = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Customer findByBoolean(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByAString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer>findAll() {
		// TODO Auto-generated method stub
	
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from \"Customer\"";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Customer> list = new ArrayList<>();
			
			while(rs.next()) {
			
				list.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			return list;
		}catch(SQLException e){
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer updateByID(Customer t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer insert(Customer t)  {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into \"Customer\" (\"FirstName\", \"LastName\", \"Username\", \"Password\")"
					+ " values (?,?,?,?,?) RETURNING \"Id\" ";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, t.getFirstName());
			ps.setString(2, t.getLastName());
			ps.setString(3, t.getUsername());
			ps.setString(4, t.getPassword());
			ps.setInt(5, 0);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return findById(rs.getInt(1));
		}catch(PSQLException e) {
			System.out.println("Sorry that email already exists.");
			
		}catch (SQLException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	public Customer login(String username, String password) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from \"Customer\" where \"Username\" = ? and \"Password\" = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return findById(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		return null;
	}

	public List<Account> getAccountsByCustomerId(int id) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionUtil.connect()){
			List<Account> customerAccounts = new ArrayList<Account>();

			String sql = "select * from \"Account\" where \"CustomerId\" = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customerAccounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5)));
			}
			return customerAccounts;
			
		}catch (SQLException e) {
			return null;
			
		}
	}

	public int createBankAccount(int id, String type) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into \"Account\" (\"CustomerId\", \"AccountType\") values (? , ?) RETURNING \"Id\" " ;

			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setInt(1, id);
			ps.setString(2, type);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
			
		}
		return -1;
	}

	public boolean checkUser(String username) {
		// TODO Auto-generated method stub
		
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from \"Customer\" where \"Username\" = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public Customer createUser(String userName, String password, String firstName, String lastName) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into \"Customer\" values(default, ? , ?, ?,?, default) RETURNING \"Id\"" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, userName);
			ps.setString(4, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return findById(rs.getInt(1));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<Account> findPending() {
		
		try(Connection conn = ConnectionUtil.connect()){
			List<Account> customerAccounts = new ArrayList<Account>();

			String sql = "select * from \"Account\" where \"Approved\" = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, false);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customerAccounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5)));
			}
			return customerAccounts;
			
		}catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
			return null;
			
		}
			
		
		}

	public boolean approveAccountById(Integer employeeOptionInt) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "update \"Account\" set \"Approved\" = true where \"Id\" = ?  RETURNING \"Approved\"" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employeeOptionInt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getBoolean(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public List<Account> getEligibleAccounts(int ID) {
		try(Connection conn = ConnectionUtil.connect()){
			List<Account> customerAccounts = new ArrayList<Account>();

			String sql = "select * from \"Account\" where \"Approved\"  = true and  \"CustomerId\" = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,ID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				customerAccounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getBoolean(5)));
			}
			return customerAccounts;
			
		}catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
			return null;
			
		}
	}
	
	public void createTransaction(Integer fromAccount, Integer toAccount, Float amount, String type) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into \"Transactions\" values (default, ?, ? , ?, ?);" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setInt(2, fromAccount);
			ps.setInt(3, toAccount);
			ps.setFloat(4, amount);
			ps.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	public boolean makeDeposit(Float depositAmountFloat, Integer depositToInt) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "update \"Account\" set \"Balance\" = ? where \"Id\"  = ? RETURNING \"Approved\";" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, depositAmountFloat);
			ps.setInt(2, depositToInt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				return rs.getBoolean(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
		
	}

	public Float currentBalanceByID(Integer ID) {
		try(Connection conn = ConnectionUtil.connect()){

			String sql = "select \"Balance\" from \"Account\" where \"Id\"  = ?" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setFloat(1,ID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getFloat(1);
			}
			return null;
			
		}catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	public List<Transaction> getAllTransactions(){
		try(Connection conn = ConnectionUtil.connect()){
			List <Transaction> transactions = new ArrayList <Transaction>();

			String sql = "select * from \"Transactions\"" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				transactions.add(new Transaction(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getFloat(5)));
			}
			return transactions;
			
		}catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
			return null;
		}
	
		
		
	}

	public boolean withDraw(Float newValue, Integer withdrawToInt) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "update \"Account\" set \"Balance\" = ? where \"Id\"  = ? RETURNING \"Approved\";" ;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, newValue);
			ps.setInt(2, withdrawToInt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				return rs.getBoolean(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}


	
	




}
