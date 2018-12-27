package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;

public interface RestaurantPhotoRelDao extends JpaRepository<RestaurantPhotoRel, Long> {

	@Query("SELECT a FROM RestaurantPhotoRel a WHERE a.restaurantUUID = ?1 AND a.enable='Y' AND a.status='OPEN'")	
	public List<RestaurantPhotoRel> findByRestaurantUUID(String restaurantUUID);
	
	
}
