package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.RestaurantCategoryRel;

public interface RestaurantCategoryRelDao extends JpaRepository<RestaurantCategoryRel, String> {
	
	
	@Query("SELECT a FROM RestaurantCategoryRel a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y' AND a.status = 'OPEN'")	
	public List<RestaurantCategoryRel> findByRestaurantUUID (String restaurantUUID);
	
	@Query("SELECT a FROM RestaurantCategoryRel a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y'")	
	public List<RestaurantCategoryRel> findAllByRestaurantUUID (String restaurantUUID);
	
	@Query("SELECT a FROM RestaurantCategoryRel a WHERE a.categoryUUID IN (?1) AND a.enable = 'Y' AND a.status = 'OPEN'")	
	public List<RestaurantCategoryRel> findByCategoryUUIDs (List<String> categoryUUIDs);
	
	
	@Query("SELECT a FROM RestaurantCategoryRel a WHERE a.restaurantUUID = ?1 AND a.categoryUUID=?2 AND a.enable = 'Y'")	
	public RestaurantCategoryRel findByRestaurantAndCategoryRelUUID (String restaurantUUID , String categoryRelUUID);
}
