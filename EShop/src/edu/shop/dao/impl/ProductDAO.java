package edu.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import edu.shop.dao.interfaces.ProductDAOIFace;
import edu.shop.entity.Product;


public class ProductDAO extends JdbcDaoSupport implements ProductDAOIFace {

	@Override
	public List<Product> getProducts() {
		String sqlSelect = "SELECT * FROM product";
        List<Product> productList = getJdbcTemplate().query(sqlSelect, new ProductRowMapper());
         return productList;
	}

	

	@Override
	public List<Product> getAvailable() {
		String query = "Select * from product where available='yes' and quantity > 0";
		List<Product> productList = getJdbcTemplate().query(query, new ProductRowMapper());
		return productList;
	}

	
	@Override
	public Product getById(long productID) {
		String query = "Select * from product where product_id=?";
		Product product = getJdbcTemplate().queryForObject(
				query, 
				new ProductRowMapper(), productID);
		
		return product;
	}



	@Override
	@Transactional
	public void updateQuantity(long productID, int quant) {
		getJdbcTemplate().update(
                "update product set quantity = ? where product_id = ?", 
                quant, productID);
	}



	@Override
	@Transactional
	public void changeStatus(long productID, String status) {
		getJdbcTemplate().update(
                "update product set available = ? where product_id = ?", 
                status, productID);
		
	}



	@Override
	public Product addProduct(Product product) {
		final String insertSql = "Insert Into product (type, price, quantity, available) "
				+ "values (?,?,?,?)";

		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, product.getType());
				ps.setDouble(2, product.getPrice());
				ps.setInt(3, product.getQuantity());
				ps.setString(4, product.getAvailable());
				return ps;
			}

		}, keyHolder);
		product.setProductID(keyHolder.getKey().longValue());
		return product;
	} 

}
class ProductRowMapper implements RowMapper<Product> {
	 
    public Product mapRow(ResultSet result, int rowNum) throws SQLException {
    	Product product = new Product();
    	product.setAvailable(result.getString("available"));
    	product.setPrice(result.getDouble("price"));
    	product.setProductID(result.getLong("product_id"));
    	product.setQuantity(result.getInt("quantity"));
    	product.setType(result.getString("type"));
        return product;
    }
     
}

