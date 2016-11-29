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

import edu.shop.dao.interfaces.OrderDAOIFace;
import edu.shop.entity.Order;


public class OrderDAO extends JdbcDaoSupport implements OrderDAOIFace{

	@Override
	public List<Order> getOrders() {
		String sqlSelect = "SELECT * FROM orders";
        List<Order> orderList = getJdbcTemplate().query(sqlSelect, new OrderRowMapper());
         return orderList;
	}

	@Override
	public Order getByID(long id) {
		String query = "SELECT * FROM orders where order_id = ?";
        Order order = getJdbcTemplate().queryForObject(
				query, 
				new OrderRowMapper(), id);
         return order;
	}
	
	@Transactional
	@Override
	public Order makeOrder(final Order order) {
		final String insertSql = "Insert Into orders (product_id, user_id, status, price, delivery_date, geolat, geolong) "
				+ "values (?,?,?,?,?,?,?)";

		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, order.getProductID());
				ps.setLong(2, order.getUserID());
				ps.setString(3, order.getStatus());
				ps.setDouble(4, order.getPrice());
				java.sql.Timestamp sqlDate = new java.sql.Timestamp(order.getDelivaryDate().getTime());
				ps.setTimestamp(5, sqlDate);
				ps.setDouble(6, order.getGeolat());
				ps.setDouble(7, order.getGeolong());
				
				return ps;
			}

		}, keyHolder);
		order.setOrderID(keyHolder.getKey().longValue());
		
		
		return order;		
	}

	@Override
	public List<Order> getByUserID(long id) {
		String sqlSelect = "SELECT * FROM orders where user_id = ?";
        List<Order> orderList = getJdbcTemplate().query(sqlSelect, new OrderRowMapper(), id);
         return orderList;
	}

	@Override
	public List<Order> getByStatus(String status) {
		String sqlSelect = "SELECT * FROM orders where status = ?";
        List<Order> orderList = getJdbcTemplate().query(sqlSelect, new OrderRowMapper(), status);
         return orderList;
	}

	@Override
	public void setStatus(long id, String status) {
		getJdbcTemplate().update(
                "update orders set status = ? where order_id = ?", 
                status, id);
		
		
	}
	

}

class OrderRowMapper implements RowMapper<Order> {
	 
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Order order = new Order();
    	order.setOrderID(rs.getLong("order_id"));
    	order.setProductID(rs.getLong("product_id"));
    	order.setUserID(rs.getLong("user_id"));
    	order.setStatus(rs.getString("status"));
    	order.setPrice(rs.getDouble("price"));
    	order.setDelivaryDate(rs.getDate("delivery_date"));
    	order.setGeolat(rs.getDouble("geolat"));
    	order.setGeolong(rs.getDouble("geolong"));
    	
        return order;
    }
     
}