package com.zack.shops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.Services.ShopService;
import com.zack.shops.models.Shop;

@RestController
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/shops")
	public List<Shop> getShops() {
		return this.shopService.getShops();
	}
}
