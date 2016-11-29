package edu.shop.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.shop.entity.Order;
import edu.shop.entity.Storage;

public class OrderDistribution implements Comparator<Order>{
		
	private Storage storage;
		 private double lat;
		 private double lon;
		 
		 public OrderDistribution(Storage storage) {
				this.storage = storage;
				this.lat = storage.getLat();
				this.lon = storage.getLon();
			}


	
	@Override
	public int compare(Order o1, Order o2) {
		double lat1 = o1.getGeolat();
		double lon1 = o1.getGeolong();
		double lat2 = o2.getGeolat();
		double lon2 = o2.getGeolong();
		
		double distance1 = Geolocation.distance(lat, lat1, lon, lon1);
		double distance2 = Geolocation.distance(lat, lat2, lon, lon2);
		return (int) (distance1*1000 - distance2*1000);

	}
	
	public  List<Order> sortOrders(List<Order> orderList){
		Storage storage = this.storage;
		List<Order> sorted = new ArrayList<>();
		List<Order> orders = orderList;
		while(!orders.isEmpty()){
		Collections.sort(orders, new OrderDistribution(storage));
		sorted.add(orders.get(0));
		storage.setLat(orders.get(0).getGeolat());
		storage.setLon(orders.get(0).getGeolong());
		orders.remove(0);
		}
		
		return sorted;
	}
	
	public double routeDistance(List<Order> orderList){
		double dist = 0.0;
		
		for(int i = 0; i < orderList.size()-1; i++){
			Order o1 = orderList.get(i);
			Order o2 = orderList.get(i+1);
			dist = dist + Geolocation.distance(o1, o2);
		}
		return dist;
	}

}
