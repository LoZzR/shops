package com.zack.shops.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.zack.shops.Exception.ShopNotFoundException;
import com.zack.shops.models.Shop;
import com.zack.shops.models.User;

@Service
public class ShopServiceImp implements ShopService {
	
	private Set<Shop> shops;
	private User user;
	
	public ShopServiceImp() {
		this.shops = new HashSet<>();
		this.shops.add(new Shop(1,"Macdonald ","McDonalds.com is your hub for everything McDonald's. Find out more about our menu items and promotions today!","https://logos-world.net/wp-content/uploads/2020/04/McDonalds-Logo.png"));
		this.shops.add(new Shop(2,"Adidas","Welcome to adidas Shop for adidas shoes, clothing and view new collections for adidas Originals, running, football, training and much more","https://upload.wikimedia.org/wikipedia/commons/2/20/Adidas_Logo.svg"));
		this.shops.add(new Shop(3,"Ray-Ban","Ray-Ban® is the global leader in premium eyewear market. Discover the collections of sunglasses and eyeglasses for women, men and kids","https://upload.wikimedia.org/wikipedia/commons/d/d1/Ray-Ban_logo.svg"));
		this.shops.add(new Shop(4,"Apple","Discover the innovative world of Apple and shop everything iPhone, iPad, Apple Watch, Mac, and Apple TV, plus explore accessories, entertainment, and expert","https://www.apple.com/ac/structured-data/images/knowledge_graph_logo.png?201809210816"));
		
		this.user = new User(1, "Zakariae", "zakariae@gmail.com");
		
	}

	@Override
	public Set<Shop> getShops() {
		return this.shops;
	}

	@Override
	public Set<Shop> getLikedShops() {
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
	
	@Override
	public Shop getShopById(int idShop) {
		Shop shop = null;
		for (Shop shopParam : shops) {
			if(shopParam.getId() == (idShop)) {
				shop = shopParam;
				break;
			}
		}
		return shop;
	}
	
	@Override
	public Shop addShop(Shop shop) {
		this.shops.add(shop);
		
		return shop;
	}

	@Override
	public Shop editShop(Shop shop) {
		Shop shopToModify  = this.getShopById(shop.getId());
		if(shopToModify != null) {
			shopToModify.setName(shop.getName());
			shopToModify.setDescription(shop.getDescription());
			shopToModify.setImagePath(shop.getImagePath());
			this.shops.add(shopToModify);
			return shopToModify;
		}
		
		throw new ShopNotFoundException(shop.getId());
	}

	@Override
	public void deleteShop(int idShop) {
		this.shops.remove(this.getShopById(idShop));	
	}

}
