package edu.shop.entity;

public class User {
	private long id;
	private long roleID;
	private String firstName;
	private String lastName;
	private double geolat;
	private double geolong;
	private String email;
	private Account account;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getGeolat() {
		return geolat;
	}
	public void setGeolat(double geolat) {
		this.geolat = geolat;
	}
	public double getGeolong() {
		return geolong;
	}
	public void setGeolong(double geolong) {
		this.geolong = geolong;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", roleID=" + roleID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", geolat=" + geolat + ", geolong=" + geolong + ", email=" + email + "]";
	}
	

}
