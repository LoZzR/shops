package com.zack.shops.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	private String name;
	private String mail;
	private List<Shop> likedShops;
	
	public User() {
	}

	public User(int id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.likedShops = new ArrayList<Shop>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Shop> getLikedShops() {
		return likedShops;
	}

	public void setLikedShops(List<Shop> likedShops) {
		this.likedShops = likedShops;
	}
	
	public void addLikedShop(Shop shop) {
		this.likedShops.add(shop);
	}
	
}
