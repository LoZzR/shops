package com.zack.shops.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.Services.ShopService;
import com.zack.shops.models.Shop;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shops")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@GetMapping("")
	public Set<Shop> getShops() {
		return this.shopService.getShops();
	}
	
	@GetMapping("/liked")
	public Set<Shop> getLikedShops() {
		return this.shopService.getLikedShops();
	}
	
	@PostMapping("/like")
	public void likeShop(@RequestBody String nameShop) {
		this.shopService.likeShop(nameShop);
	}
	
	@PostMapping("")
	public Shop addShop(@RequestBody Shop shop) {
		return this.shopService.addShop(shop);
	}
	
	@GetMapping("/{id}")
	public Shop getShop(@PathVariable int id) {
		return this.shopService.getShopById(id);
	}
	
	@PutMapping("/{id}")
	public Shop editShop(@RequestBody Shop shop, @PathVariable int id) {
		return this.shopService.editShop(shop);
	}
	
	@DeleteMapping("/{id}")
	public void deleteShop(@PathVariable int id) {
		this.shopService.deleteShop(id);
	}
}
