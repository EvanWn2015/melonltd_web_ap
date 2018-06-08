package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.CategoryFoodRel;

public interface CategoryFoodRelDao extends JpaRepository<CategoryFoodRel, String> {

	@Query("SELECT a FROM CategoryFoodRel a WHERE a.enable='Y' AND a.status = ?1 AND a.categoryUUID = ?2")
	public List<CategoryFoodRel> findByStatus(String status, String categoryUUID);
	
	
	@Query("SELECT a FROM CategoryFoodRel a WHERE a.enable='Y' AND a.status = 'OPEN' AND a.foodUUID = ?2 LIMIT='1'")
	public CategoryFoodRel findByFoodUUID(String foodUUID);
	
}
