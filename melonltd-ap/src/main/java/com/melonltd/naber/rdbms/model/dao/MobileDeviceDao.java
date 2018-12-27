package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileDeviceDao extends JpaRepository<MobileDevice, String> {

	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID = ?1")	
	public Page<MobileDevice> findByAccountUUID(String accountUUID,Pageable pageable);
	
	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID=?1")	
	public List<MobileDevice> findByAccountUUID(String accountUUID);
	
//	SELECT m.*  FROM  mobile_device m , account_info a WHERE a.restaurant_uuid = 'RESTAURANT_20180703_175534_327_f252aaec-8dec-48b8-826d-5197add44686' AND m.account_uuid = a.account_uuid
	@Query("SELECT m FROM MobileDevice m, AccountInfo a WHERE a.restaurantUUID=?1 AND a.accountUUID=m.accountUUID")	
	public Page<MobileDevice> findByRestaurantUUID(String restaurantUUID,Pageable pageable);
	
	
	@Query("SELECT m FROM MobileDevice m, AccountInfo a WHERE a.restaurantUUID=?1 AND a.accountUUID=m.accountUUID")	
	public List<MobileDevice> findByRestaurantUUID(String restaurantUUID);
	
	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID=?1 AND deviceCategory=?2")
	public List<MobileDevice> findByAccountUUIDAndDeviceCategory(String restaurantUUID, String deviceCategory);
}
