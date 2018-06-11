package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileDeviceDao extends JpaRepository<MobileDevice, String> {

//	@Query("SELECT a FROM MobileDevice a WHERE a.accountUUID = ?1 ORDER BY a.createDate DESC LIMIT '1' ")	
//	public MobileDevice findByAccountUUIDAndOrderDateDescLimitOne(String accountUUID);
}
