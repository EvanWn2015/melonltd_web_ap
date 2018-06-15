package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.CategoryFoodRel;

public interface CategoryFoodRelDao extends JpaRepository<CategoryFoodRel, String> {

	@Query("SELECT a FROM CategoryFoodRel a WHERE a.enable='Y' AND a.status = ?1 AND a.categoryUUID = ?2")
	public List<CategoryFoodRel> findByStatusAndCategoryUUID(String status, String categoryUUID);
	
	@Query("SELECT a FROM CategoryFoodRel a WHERE a.enable='Y' AND a.status = 'OPEN' AND a.foodUUID = ?1 ORDER BY a.createDate DESC")
	public CategoryFoodRel findByFoodUUID(String foodUUID);

	@Query("SELECT f FROM CategoryFoodRel f, RestaurantCategoryRel r WHERE f.foodUUID IN(?1) AND f.status='OPEN' AND f.enable='Y' AND r.status='OPEN' AND r.enable='Y' AND f.categoryUUID=r.categoryUUID")
	public List<CategoryFoodRel> findStatusByFoodUUIDs(List<String> foodUUIDs);
	
	@Query("SELECT a FROM CategoryFoodRel a WHERE a.categoryUUID = ?1")
	public List<CategoryFoodRel> findBycategoryUUID (String categoryUUID);
	
	@Query("SELECT f FROM CategoryFoodRel f, RestaurantCategoryRel c WHERE f.categoryUUID=?1 AND c.restaurantUUID=?2 AND c.enable='Y' AND f.enable='Y'")
	public List<CategoryFoodRel> findBycategoryUUIDAndRestaurantUUID (String categoryUUID, String restaurantUUID);
	
	@Query("SELECT f FROM CategoryFoodRel f, RestaurantCategoryRel c WHERE f.categoryUUID=c.categoryUUID AND f.foodUUID =?1 AND c.restaurantUUID=?2 AND c.enable='Y' AND f.enable='Y'")
	public CategoryFoodRel findByFoodUUIDAndRestaurantUUID(String foodUUID, String restaurantUUID);
	
}
