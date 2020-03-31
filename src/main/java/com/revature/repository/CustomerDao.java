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
			rs.next();
			return findById(rs.getInt(1));
		}catch (SQLException e) {
			return null;
			
		}
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

	




}
