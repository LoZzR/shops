package com.zack.shops.models;

public class Shop {

	private int id;
	private String name;
	private String description;
	private String imagePath;
	
	public Shop() {
		
	}

	public Shop(String name, String description, String imagePath) {
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
	}
	
	public Shop(int id, String name, String description, String imagePath) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imagePath = imagePath;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Shop)) return false;
		Shop shop = (Shop) obj;
		return this.id == shop.getId();
	}
	
}
