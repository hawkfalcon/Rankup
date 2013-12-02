package com.fvza.rankup.util;

public class Group {
	
	private String name;
	private double price;
	private boolean shouldBroadcast;
	
	public Group(String name, double price, boolean shouldBroadcast) {
		this.name = name;
		this.price = price;
		this.shouldBroadcast = shouldBroadcast;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean shouldBroadcast() {
		return shouldBroadcast;
	}
}
