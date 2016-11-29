package edu.shop.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.shop.dao.impl.AccountDao;
import edu.shop.dao.impl.OrderDAO;
import edu.shop.dao.interfaces.ProductDAOIFace;
import edu.shop.dao.interfaces.UserDAOIFace;
import edu.shop.entity.Account;
import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.entity.User;
import edu.shop.service.UserService;
import edu.shop.userapp.UserApp;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAOIFace userDAO;

	@Autowired
	private AccountDao accountDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAOIFace productDAO;

	@Transactional
	@Override
	public void register(UserApp userPart, String password) {
		User user = userDAO.createUser(userPart.getUser());

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		Account account = new Account();
		account.setBalance(0);
		account.setUserID(user.getId());
		account.setDateCreated(sqlDate);
		account.setPassword(password);

		account = accountDAO.createAccount(account);

		userPart.setUser(user);
		userPart.setAccount(account);
	}

	@Transactional
	@Override
	public UserApp signUp(User user, String password) {
		user = userDAO.createUser(user);

		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		Account account = new Account();
		account.setBalance(0);
		account.setUserID(user.getId());
		account.setDateCreated(sqlDate);
		account.setPassword(password);
		account = accountDAO.createAccount(account);
		UserApp userApp = new UserApp();

		userApp.setUser(user);
		userApp.setAccount(account);
		return userApp;
	}

	@Transactional
	@Override
	public UserApp login(String email, String password) throws Exception {
		User user = userDAO.getByEmail(email);
		Account account = accountDAO.getByUserID(user.getId());
		if ((user.getRoleID() == 1))
			throw new Exception("Wrong email or password.Try again.");

		if (password.equals(account.getPassword())) {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			accountDAO.updateLastLoginDate(account.getAccountID(), sqlDate);

			UserApp up = new UserApp();
			up.setUser(user);
			up.setAccount(account);

			return up;
		} else {
			throw new Exception("Wrong email or password.Try again.");
		}

	}

	@Transactional
	@Override
	public Account updateBalance(long userId, double ammount) {
		Account account = accountDAO.getByUserID(userId);
		account.fillBalance(ammount);
		accountDAO.updateBalance(account.getAccountID(), account.getBalance());
		return accountDAO.getByUserID(userId);
	}

	@Override
	public List<Order> viewHistory(long userId) {
		List<Order> orderList = orderDAO.getByUserID(userId);
		return orderList;
	}

	@Override
	public List<Product> viewAvailableProducts() {
		return productDAO.getAvailable();
	}

	@Transactional
	@Override
	public Order makeOrder(long userId, long productID, String delivaryDate) throws Exception {
		Product product = productDAO.getById(productID);
		User user = userDAO.getById(userId);
		Order order = new Order();
		Account account = accountDAO.getByUserID(userId);
		if (account.getBalance() < product.getPrice()) {
			throw new Exception("Insufficient balance");
		} else {
			order.setProductID(productID);
			order.setUserID(userId);
			order.setStatus("ordered");
			order.setPrice(product.getPrice());
			order.setGeolat(user.getGeolat());
			order.setGeolong(user.getGeolong());

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date;
			try {
				date = sdf.parse(delivaryDate);
				order.setDelivaryDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			updateBalance(userId, -product.getPrice());
			order = orderDAO.makeOrder(order);
			System.out.println("Order confirmed.");
			return order;
		}

	}

	@Transactional
	@Override
	public UserApp makeOrder(UserApp userApp, long productID, String delivaryDate) throws Exception {
		Product product = productDAO.getById(productID);
		User user = userDAO.getById(userApp.getUser().getId());
		Order order = new Order();
		Account account = accountDAO.getByUserID(userApp.getUser().getId());
		if (account.getBalance() < product.getPrice()) {
			throw new Exception("Insufficient balance");
		} else {
			order.setProductID(productID);
			order.setUserID(userApp.getUser().getId());
			order.setStatus("ordered");
			order.setPrice(product.getPrice());
			order.setGeolat(user.getGeolat());
			order.setGeolong(user.getGeolong());

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date;
			try {
				date = sdf.parse(delivaryDate);
				order.setDelivaryDate(date);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			userApp.setAccount(updateBalance(userApp.getUser().getId(), -product.getPrice()));
			productDAO.updateQuantity(productID, product.getQuantity() - 1);
			order = orderDAO.makeOrder(order);

			return userApp;
		}

	}

	@Override
	public boolean userExists(String email) {
		User user = userDAO.getByEmail(email);
		return !(user == null);
	}

	

}
