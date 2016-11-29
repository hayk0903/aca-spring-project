package edu.shop.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderModel {
	private String date;
	private Long productID;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	
	public LocalDate getDateformat(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDate ldate = LocalDate.parse(this.date,formatter);
		return ldate;
	}
	
	public boolean comesAfter(LocalDate locDate){
		return locDate.isBefore(this.getDateformat());
	};
}
