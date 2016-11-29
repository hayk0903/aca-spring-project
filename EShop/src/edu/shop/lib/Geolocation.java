package edu.shop.lib;


import edu.shop.entity.Order;


public class Geolocation {
	/*
	 * Calculate distance between two points in latitude and longitude. 
	 * Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point
	 * @returns Distance in KM
	 */
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2) {

	    
	    double earthRadius = 6371000; //meters
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lon2-lon1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist =  (earthRadius * c);

	    return dist / 1000;
	}
	
	public static double distance(Order order1, Order order2){
		return distance(order1.getGeolat(), order2.getGeolat(), order1.getGeolong(), order2.getGeolong());
		
	}
	
	
}
