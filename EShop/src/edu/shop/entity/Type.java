package edu.shop.entity;

public class Type {
	private long typeID;
	private String type;
	
	public long getTypeID() {
		return typeID;
	}
	public void setTypeID(long typeID) {
		this.typeID = typeID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Type [typeID=" + typeID + ", type=" + type + "]";
	}
	
	

}
