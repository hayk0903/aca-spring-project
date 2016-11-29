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

import edu.shop.dao.interfaces.UserDAOIFace;
import edu.shop.entity.User;


public class UserDao extends JdbcDaoSupport implements UserDAOIFace {
	
	
	

	@Override
	public List<User> getUsers() {
		String sqlSelect = "SELECT * FROM user";
        List<User> userList = getJdbcTemplate().query(sqlSelect, new UserRowMapper());
         return userList;
	}

	@Override
	public User getById(long id) {
		String query = "Select * from user where user_id=?";
		User user = getJdbcTemplate().queryForObject(
				query, 
				new UserRowMapper(), id);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		try{
		String query = "Select * from user where email=?";
		User user = getJdbcTemplate().queryForObject(
				query, 
				new UserRowMapper(), email);
		return user;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	@Transactional
	public User createUser(final User user) {
		final String insertSql = "Insert Into user(Role_id, First_name, Last_name, geolat, geolong, email)"
				+ "values (?,?,?,?,?,?)";

		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, 2L);
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setDouble(4, user.getGeolat());
				ps.setDouble(5, user.getGeolong());
				ps.setString(6, user.getEmail());
				
				return ps;
			}

		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());
		
		
		return user;		
	}

}
class UserRowMapper implements RowMapper<User> {
	 
    public User mapRow(ResultSet result, int rowNum) throws SQLException {
    	User user = new User();
    	user.setId(result.getLong("user_id"));
    	user.setRoleID(result.getLong("role_id"));
        user.setFirstName(result.getString("first_name"));
        user.setLastName(result.getString("last_name"));
        user.setGeolat(result.getFloat("geolat"));
        user.setGeolong(result.getFloat("geolong"));
        user.setEmail(result.getString("email")); 
        return user;
    }
     
}

