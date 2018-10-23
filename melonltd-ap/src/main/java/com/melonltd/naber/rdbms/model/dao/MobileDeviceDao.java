package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileDeviceDao extends JpaRepository<MobileDevice, String> {
	
	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID=?1")	
	public List<MobileDevice> findByAccountUUID(String accountUUID);
	
	@Query("SELECT m FROM MobileDevice m, AccountInfo a WHERE a.restaurantUUID=?1 AND a.accountUUID=m.accountUUID")	
	public List<MobileDevice> findByRestaurantUUID(String restaurantUUID);
	
	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID=?1 AND deviceCategory=?2")
	public List<MobileDevice> findByAccountUUIDAndDeviceCategory(String restaurantUUID, String deviceCategory);
}
