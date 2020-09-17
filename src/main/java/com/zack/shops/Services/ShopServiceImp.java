package com.zack.shops.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.zack.shops.models.Shop;
import com.zack.shops.models.User;

@Service
public class ShopServiceImp implements ShopService {
	
	private List<Shop> shops;
	private User user;
	
	public ShopServiceImp() {
		this.shops = new ArrayList<>();
		this.shops.add(new Shop("Macdonald ","McDonalds.com is your hub for everything McDonald's. Find out more about our menu items and promotions today!","https://logos-world.net/wp-content/uploads/2020/04/McDonalds-Logo.png"));
		this.shops.add(new Shop("Adidas","Welcome to adidas Shop for adidas shoes, clothing and view new collections for adidas Originals, running, football, training and much more","https://upload.wikimedia.org/wikipedia/commons/2/20/Adidas_Logo.svg"));
		this.shops.add(new Shop("Ray-Ban","Ray-Ban® is the global leader in premium eyewear market. Discover the collections of sunglasses and eyeglasses for women, men and kids","https://upload.wikimedia.org/wikipedia/commons/d/d1/Ray-Ban_logo.svg"));
		this.shops.add(new Shop("Apple","Discover the innovative world of Apple and shop everything iPhone, iPad, Apple Watch, Mac, and Apple TV, plus explore accessories, entertainment, and expert","https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201809210816"));
		
		this.user = new User(1, "Zakariae", "zakariae@gmail.com");
		
	}

	@Override
	public List<Shop> getShops() {
		return this.shops;
	}

	@Override
	public List<Shop> getLikedShops() {
		return user.getLikedShops();
	}

	@Override
	public void likeShop(String nameShop) {
		Shop shop = null;
		for (Shop shopParam : shops) {
			if(shopParam.getName().equals(nameShop)) {
				shop = shopParam;
				break;
			}
		}
		//find user by at first,for now we just use one user, supposed the authenticated one
		if(shop != null) this.user.addLikedShop(shop);
		
	}

}
