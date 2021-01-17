package com.zack.shops.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zack.shops.models.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer>{

}
