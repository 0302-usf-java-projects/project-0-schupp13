package com.revature.model;

import java.util.Objects;

public class Customer {
	
	private int id;
	private String firstName =null;
	private String lastName = null;
	private String username = null;
	private String password = null;
	private int level;
	
	
	public Customer() {
		super();
	}

	
	/**
	 * This constructor is used for creating customers
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 */
	public Customer(String firstName, String lastName, String username, String password, int level) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.level = level;
	}
	
	

	/**
	 * this constructor is used fetch data
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param email
	 */
	public Customer(int id, String firstName, String lastName, String username, String password, int level) {
		this(firstName,lastName,  username,  password, level);
		this.id = id;
	}






	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	

	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, level, password, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& level == other.level && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

}
