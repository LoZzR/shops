package com.zack.shops.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
		Optional<User> user = userRepo.findById((long)8);
		User userC = user.get();
		return user.get().getLikedShops();
	}

	@Override
	public void likeShop(int idShop) {
		Optional<Shop> shop = shopRepo.findById(idShop);
		//find user by at first,for now we just use one user, supposed the authenticated one
		Optional<User> user = userRepo.findById((long)8);
		if(shop.isPresent()) {
			user.ifPresent((u)->{u.addLikedShop(shop.get());this.userRepo.save(u);});
		}
		
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
