package com.zack.shops.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.Services.ShopService;
import com.zack.shops.models.Shop;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@GetMapping("/shops")
    @RolesAllowed({"shop-user", "shop-admin"})
	public List<Shop> getShops() {
		return this.shopService.getShops();
	}
	
	@GetMapping("/{id}")
    @RolesAllowed("shop-admin")
    public Shop getShop(@PathVariable("id") String id) {
           return this.shopService.getShops().stream()
           .filter(s -> Integer.toString(s.getId()).equals(id))
           .findFirst()
           .orElse(null);
    }
}
