package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;

public interface RestaurantLocationTemplateDao extends JpaRepository<RestaurantLocationTemplate, String> {
	
	@Query("SELECT a FROM RestaurantLocationTemplate a WHERE a.enable = 'Y'")
	public List<RestaurantLocationTemplate> findAllByEnable();

	// for Admin
	
	@Transactional
	@Modifying
	@Query("UPDATE RestaurantLocationTemplate a SET a.enable=?1 WHERE a.restaurantUUID=?2")	
	public void updateEnable(String enable, String restaurantUUID);
}
