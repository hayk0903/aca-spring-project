package edu.shop.dao.interfaces;

import java.util.List;

import edu.shop.entity.User;


public interface UserDAOIFace  {
	List<User> getUsers();
	User getById(long id);
	User getByEmail(String email);
	
	User createUser(User user);
	
	

}
