package com.zack.shops.services;

import com.zack.shops.models.User;
import com.zack.shops.security.payload.request.SignupRequest;

public interface UserService {
	
	User updateUserProfile(SignupRequest signUpRequest);

}
