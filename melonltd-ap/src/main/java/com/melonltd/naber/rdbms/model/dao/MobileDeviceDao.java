package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileDeviceDao extends JpaRepository<MobileDevice, String> {

	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID = ?1 ORDER BY a.createDate DESC")	
	public MobileDevice findByAccountUUID(String accountUUID);
	
	
	@Query("SELECT m FROM MobileDevice m, AccountInfo a WHERE a.restaurantUUID = ?1 AND a.accountUUID = m.accountUUID")	
	public Page<MobileDevice> findByRestaurantUUID(String restaurantUUID,Pageable pageable);
}
