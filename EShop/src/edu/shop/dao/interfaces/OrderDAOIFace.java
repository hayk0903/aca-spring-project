package edu.shop.dao.interfaces;

import java.util.List;

import edu.shop.entity.Order;

public interface OrderDAOIFace {
	List<Order> getOrders();
	Order getByID(long id);
	List<Order> getByStatus(String status);
	void setStatus(long id, String status);
	List<Order> getByUserID(long id);
	Order makeOrder(Order order);
}
