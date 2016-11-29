package edu.shop.lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalDate tommorow = today.plusDays(1);
		System.out.println("Current Date="+today);
		System.out.println("Tomorrow Date="+tommorow);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDate ldate = LocalDate.parse("2016-12-05 14:45",formatter);
		
		System.out.println("Ldate="+ldate);
		System.out.println(today.isBefore(ldate));
		
		
		
		
	}

}
