package com.fvza.rankup.api;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRankupEvent extends Event {
	
	static HandlerList handlers = new HandlerList();
	
	private Player player;
	private String newRank;
	private double price;
	
	public PlayerRankupEvent(Player player, String newRank, double newRankPrice) {
		this.player = player;
		this.newRank = newRank;
		this.price = newRankPrice;
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	public String getNewRank() {
		return newRank;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}

}
