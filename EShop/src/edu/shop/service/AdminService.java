package edu.shop.service;

import java.util.List;

import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.userapp.AdminApp;

public interface AdminService {
	
	List<Product> getProducts();
	List<Order> getOrdered();
	void setStatus(List<Order> orders);
	AdminApp login(String email, String password) throws Exception;
	Product addProduct(Product product);
	void changeStatus(long productID, String status);
	void setQuantity(long productID, int quant);
}
