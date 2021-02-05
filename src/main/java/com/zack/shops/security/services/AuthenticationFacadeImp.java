package com.zack.shops.security.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacadeImp implements IAuthenticationFacade{

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public UserDetails getUserDeyails() {
		Authentication authentication = this.getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {
			return (UserDetailsImpl) authentication.getPrincipal();
		}
		return null;
	}
	
}
