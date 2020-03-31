package com.revature.repository;

import java.util.List;

public interface DaoContract<T, I> {


	List<T> findAll();
	T findById(I id);
	T findByBoolean(boolean b);
	T findByAString(String s);
	T updateByID(T t);
	T insert(T t);
	
	
	
	
}
