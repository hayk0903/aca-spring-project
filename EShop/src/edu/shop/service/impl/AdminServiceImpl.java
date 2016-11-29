package edu.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.shop.dao.impl.AccountDao;
import edu.shop.dao.impl.OrderDAO;
import edu.shop.dao.interfaces.ProductDAOIFace;
import edu.shop.dao.interfaces.UserDAOIFace;
import edu.shop.entity.Account;
import edu.shop.entity.Order;
import edu.shop.entity.Product;
import edu.shop.entity.Storage;
import edu.shop.entity.User;
import edu.shop.service.AdminService;
import edu.shop.userapp.AdminApp;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserDAOIFace userDAO;

	@Autowired
	private AccountDao accountDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAOIFace productDAO;

	@Autowired
	private Storage storage;

	@Override
	public List<Order> getOrdered() {
		List<Order> orders = orderDAO.getByStatus("ordered");
		return orders;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	@Override
	public AdminApp login(String email, String password) throws Exception {
		User user = userDAO.getByEmail(email);
		if (!(user.getRoleID() == 1))
			throw new Exception("Wrong email or password.Try again.");

		Account account = accountDAO.getByUserID(user.getId());
		if (password.equals(account.getPassword())) {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			accountDAO.updateLastLoginDate(account.getAccountID(), sqlDate);

			AdminApp adminApp = new AdminApp();
			adminApp.setAdmin(user);
			adminApp.setAccount(account);
			return adminApp;
		} else {
			throw new Exception("Wrong email or password.Try again.");
		}
	}

	@Override
	public void setStatus(List<Order> orders) {
		for (Order e : orders) {
			e.setStatus("delivered");
			orderDAO.setStatus(e.getOrderID(), "delivered");
		}

	}

	@Override
	public void setQuantity(long productID, int quant) {
		productDAO.updateQuantity(productID, quant);

	}

	@Override
	public Product addProduct(Product product) {
		return productDAO.addProduct(product);
	}

	@Override
	public void changeStatus(long productID, String status) {
		productDAO.changeStatus(productID, status);

	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

}
