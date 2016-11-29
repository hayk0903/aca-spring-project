package edu.shop.entity;

import java.util.Date;

public class Order {
	private long orderID;
	private long userID;
	private long productID;
	private String status;
	private double price;
	private Date delivaryDate;
	private double geolat;
	private double geolong;
	
	
	public double getGeolat() {
		return geolat;
	}
	public void setGeolat(double d) {
		this.geolat = d;
	}
	public double getGeolong() {
		return geolong;
	}
	public void setGeolong(double geolong) {
		this.geolong = geolong;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDelivaryDate() {
		return delivaryDate;
	}
	public void setDelivaryDate(Date delivaryDate) {
		this.delivaryDate = delivaryDate;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", productID=" + productID + ", status=" + status
				+ ", price=" + price + ", delivaryDate=" + delivaryDate + "]";
	}
	
	
	

}
