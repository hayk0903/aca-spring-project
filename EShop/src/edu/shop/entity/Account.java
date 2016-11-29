package edu.shop.entity;

import java.util.Date;

public class Account {
	private long accountID;
	private double balance;
	private long userID;
	private Date dateCreated;
	private Date lastLoginDate;
	private String password;
	
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void fillBalance(double ammount){
		this.balance += ammount;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", userID=" + userID + ", dateCreated="
				+ dateCreated + ", lastLoginDate=" + lastLoginDate + ", password=" + password + "]";
	}
	
	

}
