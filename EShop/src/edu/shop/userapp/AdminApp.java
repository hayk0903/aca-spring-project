package edu.shop.userapp;

import java.util.List;

import edu.shop.entity.Account;
import edu.shop.entity.Order;
import edu.shop.entity.Storage;
import edu.shop.entity.User;


public class AdminApp {
	private User admin;
	private  Account account;
	private Storage storage;
	private List<Order> orders;
	
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	
}
