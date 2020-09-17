package com.zack.shops.Services;

import java.util.List;

import com.zack.shops.models.Shop;

public interface ShopService {

	public List<Shop> getShops();
	
	public List<Shop> getLikedShops();
	
	public void likeShop(String nameShop);
}
