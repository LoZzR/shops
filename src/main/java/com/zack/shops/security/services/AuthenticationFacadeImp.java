package com.zack.shops.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zack.shops.Exception.UserNotFoundException;
import com.zack.shops.models.User;
import com.zack.shops.repositories.UserRepository;

@Service
public class AuthenticationFacadeImp implements IAuthenticationFacade{

	@Autowired
	private UserRepository userRepo;
	
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
	
	@Override
	public Long getIdAuthenticatedUser() {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) this.getUserDeyails();
		return userDetailsImpl.getId();
	}
	
	@Override
	public User getAuthenticatedUser() {
		return userRepo.findById(this.getIdAuthenticatedUser()).orElseThrow(()->new UserNotFoundException(this.getIdAuthenticatedUser()));
	}
	
}
