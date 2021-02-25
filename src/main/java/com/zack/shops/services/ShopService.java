package com.zack.shops.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zack.shops.models.Shop;

public interface ShopService {

	public List<Shop> getShops();
	
	public Page<Shop> getPageShops(Pageable paging);
	
	public List<Shop> getLikedShops();
	
	public List<Shop> getNotLikedShops();
	
	public void likeShop(int idShop);
	
	public Shop getShopById(int idShop);
	
	public Shop addShop(Shop shop);
	
	public Shop editShop(Shop shop);
	
	public void removeShopFromLikedShopList(int idShop);
	
	public void deleteShop(int idShop);
}
