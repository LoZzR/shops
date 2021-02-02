package com.zack.shops.services;

import java.util.Set;

import com.zack.shops.models.Shop;

public interface ShopService {

	public Set<Shop> getShops();
	
	public Set<Shop> getLikedShops();
	
	public Set<Shop> getNotLikedShops();
	
	public void likeShop(int idShop);
	
	public Shop getShopById(int idShop);
	
	public Shop addShop(Shop shop);
	
	public Shop editShop(Shop shop);
	
	public void deleteShop(int idShop);
}
