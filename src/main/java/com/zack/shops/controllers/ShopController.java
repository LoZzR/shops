package com.zack.shops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.Services.ShopService;
import com.zack.shops.models.Shop;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@GetMapping("/shops")
	public List<Shop> getShops() {
		return this.shopService.getShops();
	}
	
	@GetMapping("/liked-shops")
	public List<Shop> getLikedShops() {
		return this.shopService.getLikedShops();
	}
	
	@PostMapping("/shops/like")
	public void likeShop(@RequestBody String nameShop) {
		this.shopService.likeShop(nameShop);
	}
}
