package com.zack.shops.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zack.shops.client.LocationClient;
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
	public List<Shop> getShops() {
		return this.shopService.getShops();
	}
	
	@GetMapping("/not-liked")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Map<String, Object> getNotLikedShops(@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "3") int size) {
		
		Pageable paging = PageRequest.of(page, size);
		Page<Shop> pageShops = this.shopService.getPageShops(paging);
		List<Shop> shops = pageShops.getContent();
		
		Map<String, Object> response = new HashMap<>();
	    response.put("shops", shops);
	    response.put("currentPage", pageShops.getNumber());
	    response.put("totalItems", pageShops.getTotalElements());
	    response.put("totalPages", pageShops.getTotalPages());
		
		return response;
	}
	
	@GetMapping("/liked")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Shop> getLikedShops() {
		return this.shopService.getLikedShops();
	}
	
	@PostMapping("/like/{idShop}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void likeShop(@PathVariable int idShop) {
		this.shopService.likeShop(idShop);
	}
	
	@DeleteMapping("/liked/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void removeShopFromLikedShopList(@PathVariable int id) {
		this.shopService.removeShopFromLikedShopList(id);
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
