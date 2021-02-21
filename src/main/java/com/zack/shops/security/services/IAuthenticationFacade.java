package com.zack.shops.security.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.zack.shops.models.User;

public interface IAuthenticationFacade {

	Authentication getAuthentication();
	
	UserDetails getUserDeyails();
	
	Long getIdAuthenticatedUser();
	
	User getAuthenticatedUser();
}
