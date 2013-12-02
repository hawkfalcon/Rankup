package com.fvza.rankup.util;

public class Group {
	
	private String name;
	private double price;
	private boolean shouldBroadcast;
	private double pay;
	
	public Group(String name, double price, boolean shouldBroadcast, double pay) {
		this.name = name;
		this.price = price;
		this.shouldBroadcast = shouldBroadcast;
		this.pay = pay;
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
	
	public double getPay() {
		return pay;
	}
}
