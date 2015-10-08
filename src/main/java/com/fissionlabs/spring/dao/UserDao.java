package com.fissionlabs.spring.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fissionlabs.spring.model.User;



public interface UserDao {
	
public void addUser(User u);
	
	public User getById(int id);
	
	public void deleteUser(int id);
	
	public void updateUser(int id,String name,String password);
	
	public List<User> listOfAllUsers();
	

}
