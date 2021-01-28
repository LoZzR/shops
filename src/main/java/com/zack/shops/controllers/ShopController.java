package com.zack.shops.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.models.Shop;
import com.zack.shops.services.ShopService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/shops")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@GetMapping("")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Set<Shop> getShops() {
		return this.shopService.getShops();
	}
	
	@GetMapping("/liked")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Set<Shop> getLikedShops() {
		return this.shopService.getLikedShops();
	}
	
	@PostMapping("/like/{idShop}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void likeShop(@PathVariable int idShop) {
		this.shopService.likeShop(idShop);
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public Shop addShop(@RequestBody Shop shop) {
		return this.shopService.addShop(shop);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Shop getShop(@PathVariable int id) {
		return this.shopService.getShopById(id);
	}
	
	@PutMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public Shop editShop(@RequestBody Shop shop) {
		return this.shopService.editShop(shop);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteShop(@PathVariable int id) {
		this.shopService.deleteShop(id);
	}
}
