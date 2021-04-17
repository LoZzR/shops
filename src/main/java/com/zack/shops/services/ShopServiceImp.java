package com.zack.shops.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zack.shops.Exception.ShopNotFoundException;
import com.zack.shops.client.LocationClient;
import com.zack.shops.client.gen.Location;
import com.zack.shops.models.Shop;
import com.zack.shops.models.User;
import com.zack.shops.repositories.ShopRepository;
import com.zack.shops.repositories.UserRepository;
import com.zack.shops.security.services.IAuthenticationFacade;
import com.zack.shops.utils.ShopUtils;

import ch.qos.logback.classic.Logger;


@Service
public class ShopServiceImp implements ShopService {
	
	@Autowired
	private ShopRepository shopRepo;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private LocationClient locationClient;
	
	public ShopServiceImp() {
		
	}

	@Override
	public List<Shop> getShops() {
		return this.shopRepo.findAll();
	}
	
	@Override
	public Page<Shop> getPageShops(Pageable paging) {
		List<Shop> notLikedShops = this.getNotLikedShops();
		List<Shop> shopsList = new ArrayList<>();
		shopsList.addAll(notLikedShops);
		List<List<Shop>> lists = ShopUtils.getPages(shopsList, paging.getPageSize());
		Page<Shop> pageShops = new PageImpl<>(lists.get(paging.getPageNumber()), paging, shopsList.size());
		return pageShops;
	}

	@Override
	public List<Shop> getLikedShops() {
		User user = this.authenticationFacade.getAuthenticatedUser();
		return user.getLikedShops();
	}
	
	@Override
	public List<Shop> getNotLikedShops() {
		User user = this.authenticationFacade.getAuthenticatedUser();
		List<Shop> likedShops = user.getLikedShops();
		List<Shop> shops = this.getShops();
		List<Shop> notLikedShops = shops.stream().filter((Shop shop) -> !likedShops.contains(shop)).collect(Collectors.toList());
		return notLikedShops;
	}

	@Override
	public void likeShop(int idShop) {
		Shop shop = shopRepo.findById(idShop).orElseThrow(()->new ShopNotFoundException(idShop));
		User user = this.authenticationFacade.getAuthenticatedUser();
		user.addLikedShop(shop);
		userRepo.save(user);
		
	}
	
	@Override
	public Shop getShopById(int idShop) {
		
		//search for location
		Location location = null;
		try {
			location = locationClient.getLocation(idShop).getReturn();
		} catch(Exception e){
			System.out.println("Error while calling Location service with id shop : " + idShop);
		}
		Shop shop =  this.shopRepo.findById(idShop).orElseThrow(()->new ShopNotFoundException(idShop));
		shop.setLocation(location);
		return shop;
	}
	
	@Override
	public Shop addShop(Shop shop) {
		return this.shopRepo.save(shop);
	}

	@Override
	public Shop editShop(Shop shop) {
		if(!this.shopRepo.findById(shop.getId()).isPresent()) throw new ShopNotFoundException(shop.getId());
		return this.shopRepo.save(shop);
	}

	@Override
	public void deleteShop(int idShop) {
		User user = this.authenticationFacade.getAuthenticatedUser();
		
		List<Shop> shops = user.getLikedShops();
		if(shops != null) {
			user.getLikedShops().remove(this.shopRepo.findById(idShop).get());
			this.userRepo.save(user);
		}
		
		this.shopRepo.deleteById(idShop);	
	}

	@Override
	public void removeShopFromLikedShopList(int idShop) {
		User user  = this.authenticationFacade.getAuthenticatedUser();
		
		List<Shop> shops = user.getLikedShops();
		if(shops != null) {
			user.getLikedShops().remove(this.shopRepo.findById(idShop).get());
			this.userRepo.save(user);
		}
		
	}
	

}
