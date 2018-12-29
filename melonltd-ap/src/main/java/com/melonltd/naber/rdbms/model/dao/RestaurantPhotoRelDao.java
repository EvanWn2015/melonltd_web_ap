package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.RestaurantPhotoRel;
import com.melonltd.naber.rdbms.model.type.Enable;

public interface RestaurantPhotoRelDao extends JpaRepository<RestaurantPhotoRel, Long> {

	@Query("SELECT a FROM RestaurantPhotoRel a WHERE a.restaurantUUID = ?1 AND a.enable='Y' AND a.status='OPEN'")	
	public List<RestaurantPhotoRel> findByRestaurantUUID(String restaurantUUID);
	

	@Transactional
	@Modifying
	@Query("UPDATE RestaurantPhotoRel a SET a.enable=?1 WHERE a.id=?2")	
	public void enablePhoto(Enable enable, Long id);
}
