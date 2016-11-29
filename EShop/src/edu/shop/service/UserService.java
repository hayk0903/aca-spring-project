package edu.shop.service;

import java.util.List;

import edu.shop.entity.Account;
import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.entity.User;
import edu.shop.userapp.UserApp;

public interface UserService {

	void register(UserApp userPart, String password);
	boolean userExists(String email);
	Account updateBalance(long userId, double ammount);
	List<Order> viewHistory(long userId);
	List<Product> viewAvailableProducts();
	Order makeOrder(long userId, long productID, String delivaryDate) throws Exception;
	UserApp login(String email, String password) throws Exception;
	UserApp signUp(User user, String password);
	UserApp makeOrder(UserApp userApp, long productID, String delivaryDate) throws Exception;

}
