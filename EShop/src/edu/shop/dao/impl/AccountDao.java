package edu.shop.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import edu.shop.dao.interfaces.AccountDAOIFace;
import edu.shop.entity.Account;


public class AccountDao extends JdbcDaoSupport implements AccountDAOIFace {

	@Override
	public List<Account> getAccounts() {
		String sqlSelect = "SELECT * FROM account";
        List<Account> accountList = getJdbcTemplate().query(sqlSelect, new AccountRowMapper());
         return accountList;
	}

	@Override
	public Account getByUserID(long userID) {
		String query = "Select * from account where user_id=?";
		Account account = getJdbcTemplate().queryForObject(
				query, 
				new AccountRowMapper(), userID);
		return account;
	}
	
	@Override
	@Transactional
	public Account createAccount(final Account account) {
		
		final Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0); 
		
		final String insertSql = "Insert Into account(balance, user_id, date_created, last_login_date, password)"
				+ "values (?,?,?,?,?)";

		final KeyHolder keyHolder = new GeneratedKeyHolder();

		getJdbcTemplate().update(new PreparedStatementCreator() {
			

			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertSql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setDouble(1, account.getBalance());
				ps.setLong(2, account.getUserID());
				ps.setDate(3, (Date) account.getDateCreated());
				ps.setDate(4, null);
				ps.setString(5, account.getPassword());
				
				return ps;
			}

		}, keyHolder);
		account.setAccountID(keyHolder.getKey().longValue());
		
		
		return account;		
	}

	@Override
	public Account updateBalance(long id, double ammount) {
		getJdbcTemplate().update(
                "update account set balance = ? where account_id = ?", 
                ammount, id);
		return getByID(id);
	}

	@Override
	public void updateLastLoginDate(long id, java.util.Date today) {
		getJdbcTemplate().update(
                "update account set last_login_date = ? where account_id = ?", 
                today, id);
		
	}

	@Override
	public Account getByID(long  id) {
		String query = "Select * from account where account_id=?";
		Account account = getJdbcTemplate().queryForObject(
				query, 
				new AccountRowMapper(), id);
		return account;
	}

}
	

class AccountRowMapper implements RowMapper<Account> {
	 
    public Account mapRow(ResultSet result, int rowNum) throws SQLException {
    	Account account = new Account();
    	account.setAccountID(result.getLong("account_id"));
    	account.setBalance(result.getDouble("balance"));
    	account.setUserID(result.getLong("user_id"));
    	account.setDateCreated(result.getDate("date_created"));
    	account.setLastLoginDate(result.getDate("last_login_date"));
    	account.setPassword(result.getString("password"));
    	
        return account;
    }
   
     
}
