package com.zack.shops.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zack.shops.models.User;
import com.zack.shops.repositories.UserRepository;
import com.zack.shops.security.payload.request.SignupRequest;
import com.zack.shops.security.services.IAuthenticationFacade;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User updateUserProfile(SignupRequest signUpRequest) {
		User currentUser = this.authenticationFacade.getAuthenticatedUser();
		currentUser.setUsername(signUpRequest.getUsername());
		currentUser.setEmail(signUpRequest.getEmail());
		currentUser.setPassword(encoder.encode(signUpRequest.getPassword()));
		return this.userRepo.save(currentUser);
	}
}
