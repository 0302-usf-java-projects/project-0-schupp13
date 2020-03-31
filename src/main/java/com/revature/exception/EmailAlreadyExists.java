package com.revature.exception;

public class EmailAlreadyExists extends Exception{
	
	public EmailAlreadyExists(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 

}
