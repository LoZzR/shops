package com.zack.shops.Exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long id) {
		super("Could not find User with id : " + id);
	}
}
