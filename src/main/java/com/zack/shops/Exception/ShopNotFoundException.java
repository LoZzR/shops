package com.zack.shops.Exception;

public class ShopNotFoundException extends RuntimeException {

	public ShopNotFoundException(int id) {
		super("Could not find shop with id : " + id);
	}
}
