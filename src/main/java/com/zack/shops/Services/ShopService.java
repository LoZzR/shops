package com.zack.shops.Services;

import java.util.Set;

import com.zack.shops.models.Shop;

public interface ShopService {

	public Set<Shop> getShops();
	
	public Set<Shop> getLikedShops();
	
	public void likeShop(String nameShop);
	
	public Shop getShopById(int idShop);
	
	public Shop addShop(Shop shop);
	
	public Shop editShop(Shop shop);
	
	public void deleteShop(int idShop);
}
