package com.zack.shops.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zack.shops.Exception.ShopNotFoundException;
import com.zack.shops.Exception.UserNotFoundException;
import com.zack.shops.models.Shop;
import com.zack.shops.models.User;
import com.zack.shops.repositories.ShopRepository;
import com.zack.shops.repositories.UserRepository;

@Service
public class ShopServiceImp implements ShopService {
	
	@Autowired
	private ShopRepository shopRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public ShopServiceImp() {
		
	}

	@Override
	public Set<Shop> getShops() {
		return new HashSet<>(this.shopRepo.findAll());
	}

	@Override
	public Set<Shop> getLikedShops() {
		//user = theCurrentConnectedUser
		User user = userRepo.findById((long)8).orElseThrow(()->new UserNotFoundException(8));
		return user.getLikedShops();
	}
	
	@Override
	public Set<Shop> getNotLikedShops() {
		//user = theCurrentConnectedUser
		User user = userRepo.findById((long)8).orElseThrow(()->new UserNotFoundException(8));
		Set<Shop> likedShops = user.getLikedShops();
		Set<Shop> shops = this.getShops();
		Set<Shop> notLikedShops = shops.stream().filter((Shop shop) -> !likedShops.contains(shop)).collect(Collectors.toSet());
		return notLikedShops;
	}

	@Override
	public void likeShop(int idShop) {
		Shop shop = shopRepo.findById(idShop).orElseThrow(()->new ShopNotFoundException(idShop));
		//find user by at first,for now we just use one user, supposed the authenticated one
		User user = userRepo.findById((long)8).orElseThrow(()->new UserNotFoundException(8));
		user.addLikedShop(shop);
		userRepo.save(user);
		
	}
	
	@Override
	public Shop getShopById(int idShop) {
		return this.shopRepo.findById(idShop).orElseThrow(()->new ShopNotFoundException(idShop));
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
		//find user by at first,for now we just use one user, supposed the authenticated one
		User user = userRepo.findById((long)1).orElseThrow(()->new UserNotFoundException(8));
		
		Set<Shop> shops = user.getLikedShops();
		if(shops != null) {
			user.getLikedShops().remove(this.shopRepo.findById(idShop).get());
			this.userRepo.save(user);
		}
		
		this.shopRepo.deleteById(idShop);	
	}

}
