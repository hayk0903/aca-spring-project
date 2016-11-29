package edu.shop.dao.interfaces;

import java.util.List;

import edu.shop.entity.Product;

public interface ProductDAOIFace {
	Product addProduct(Product product);
	List<Product> getProducts();
	List<Product> getAvailable();
	Product getById(long productID);
	void updateQuantity(long productID, int quant);
	void changeStatus(long productID, String status);

}
