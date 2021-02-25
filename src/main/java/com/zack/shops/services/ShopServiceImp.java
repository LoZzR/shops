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
import com.zack.shops.models.Shop;
import com.zack.shops.models.User;
import com.zack.shops.repositories.ShopRepository;
import com.zack.shops.repositories.UserRepository;
import com.zack.shops.security.services.IAuthenticationFacade;

@Service
public class ShopServiceImp implements ShopService {
	
	@Autowired
	private ShopRepository shopRepo;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	private UserRepository userRepo;
	
	public ShopServiceImp() {
		
	}

	@Override
	public Set<Shop> getShops() {
		return new HashSet<>(this.shopRepo.findAll());
	}
	
	@Override
	public Page<Shop> getPageShops(Pageable paging) {
		Set<Shop> notLikedShops = this.getNotLikedShops();
		List<Shop> shopsList = new ArrayList<>();
		shopsList.addAll(notLikedShops);
		Page<Shop> pageShops = new PageImpl<>(shopsList, paging, shopsList.size());
		return pageShops;
	}

	@Override
	public Set<Shop> getLikedShops() {
		User user = this.authenticationFacade.getAuthenticatedUser();
		return user.getLikedShops();
	}
	
	@Override
	public Set<Shop> getNotLikedShops() {
		User user = this.authenticationFacade.getAuthenticatedUser();
		Set<Shop> likedShops = user.getLikedShops();
		Set<Shop> shops = this.getShops();
		Set<Shop> notLikedShops = shops.stream().filter((Shop shop) -> !likedShops.contains(shop)).collect(Collectors.toSet());
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
		User user = this.authenticationFacade.getAuthenticatedUser();
		
		Set<Shop> shops = user.getLikedShops();
		if(shops != null) {
			user.getLikedShops().remove(this.shopRepo.findById(idShop).get());
			this.userRepo.save(user);
		}
		
		this.shopRepo.deleteById(idShop);	
	}

	@Override
	public void removeShopFromLikedShopList(int idShop) {
		User user  = this.authenticationFacade.getAuthenticatedUser();
		
		Set<Shop> shops = user.getLikedShops();
		if(shops != null) {
			user.getLikedShops().remove(this.shopRepo.findById(idShop).get());
			this.userRepo.save(user);
		}
		
	}

}
