package edu.shop.model;

import edu.shop.entity.User;

public class SignUp {
	private String firstName;
	private String lastName;
	private double geolat;
	private double geolong;
	private String email;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User mapToUser(){
			User user = new User();
			user.setFirstName(this.getFirstName());
			user.setLastName(this.getLastName());
			user.setEmail(this.getEmail());
			user.setGeolat(this.getGeolat());
			user.setGeolong(this.getGeolong());
			return user;
	}
	
	public boolean isValide(){
		return !this.getFirstName().isEmpty() && !this.getLastName().isEmpty() &&
				!(this.getGeolat()==0.0) && !(this.getGeolat()== 0) && !this.getEmail().isEmpty() &&
				!this.getPassword().isEmpty();
	}
}
