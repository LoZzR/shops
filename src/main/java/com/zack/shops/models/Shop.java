package com.zack.shops.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "TEXT")
	private String imagePath;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "likedShops")
	List<User> likes;
	
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
	
	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Shop)) return false;
		Shop shop = (Shop) obj;
		return this.id == shop.getId();
	}
	
}
