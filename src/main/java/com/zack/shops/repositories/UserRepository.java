package com.zack.shops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zack.shops.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
