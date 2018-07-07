package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.CategoryRel;

public interface CategoryRelDao extends JpaRepository<CategoryRel, String> {
	
	
	@Query("SELECT a FROM CategoryRel a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y' AND a.status = 'OPEN'")	
	public List<CategoryRel> findByRestaurantUUID (String restaurantUUID);
	
	@Query("SELECT a FROM CategoryRel a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y' ORDER BY createDate DESC")	
	public List<CategoryRel> findAllByRestaurantUUID (String restaurantUUID);
	
	@Query("SELECT a FROM CategoryRel a WHERE a.categoryUUID IN (?1) AND a.enable = 'Y' AND a.status = 'OPEN'")	
	public List<CategoryRel> findByCategoryUUIDs (List<String> categoryUUIDs);
	
	
	@Query("SELECT a FROM CategoryRel a WHERE a.restaurantUUID = ?1 AND a.categoryUUID=?2 AND a.enable = 'Y'")	
	public CategoryRel findByRestaurantAndCategoryRelUUID (String restaurantUUID , String categoryRelUUID);
}
