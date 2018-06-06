package com.melonltd.naber.rdbms.model.dao.stored.procedure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface DeviceTokenDao extends CrudRepository<MobileDevice, String> {
	
	@Query(value = "{CALL mobile_device_limit (:in_account_uuid)}", nativeQuery = true)
	public MobileDevice findLimtOneByAccountUUID(@Param("in_account_uuid") String accountUUID) ;

}
