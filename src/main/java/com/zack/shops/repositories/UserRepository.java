package com.zack.shops.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zack.shops.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
