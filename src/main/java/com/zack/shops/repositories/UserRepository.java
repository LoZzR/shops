package com.zack.shops.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zack.shops.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
