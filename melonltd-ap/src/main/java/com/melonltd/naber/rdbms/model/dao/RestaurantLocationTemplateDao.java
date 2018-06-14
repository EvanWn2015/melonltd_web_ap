package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.RestaurantLocationTemplate;

public interface RestaurantLocationTemplateDao extends JpaRepository<RestaurantLocationTemplate, String> {
	
	@Query("SELECT a FROM RestaurantLocationTemplate a WHERE a.enable = 'Y'")
	public List<RestaurantLocationTemplate> findAllByEnable();

}
