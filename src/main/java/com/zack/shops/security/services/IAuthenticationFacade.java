package com.zack.shops.security.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationFacade {

	Authentication getAuthentication();
	UserDetails getUserDeyails();
}
