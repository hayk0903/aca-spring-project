package edu.shop.entity;

public class Product {
	private long productID;
	private String type;
	private double price;
	private int quantity;
	private String available;
	
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", typeID=" + type + ", price=" + price + ", quantity=" + quantity
				+ ", available=" + available + "]";
	}
	
	
	
	

}
