package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;

public interface RestaurantInfoDao extends JpaRepository<RestaurantInfo, String> {

	@Query("SELECT a FROM RestaurantInfo a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y'")
	public RestaurantInfo findUUID(String uuid);

}
