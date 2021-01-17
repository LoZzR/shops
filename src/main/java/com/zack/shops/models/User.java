package com.zack.shops.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String mail;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	  name = "shop_like", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "shop_id"))
	private Set<Shop> likedShops;
	
	public User() {
	}

	public User(int id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.likedShops = new HashSet<Shop>();
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

	public Set<Shop> getLikedShops() {
		return likedShops;
	}

	public void setLikedShops(Set<Shop> likedShops) {
		this.likedShops = likedShops;
	}
	
	public void addLikedShop(Shop shop) {
		this.likedShops.add(shop);
	}
	
	@Override
	public String toString() {
		return "User : " + this.id + "--" + this.name + "--" + this.mail;
	}
	
}
