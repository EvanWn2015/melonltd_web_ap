package com.melonltd.naber.rdbms.model.stored.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileTokenStoredDao extends CrudRepository<MobileDevice, String> {
	
	@Query(value = "{CALL get_mobile_token_limit_one (:in_account_uuid)}", nativeQuery = true)
	public MobileDevice findLimtOneByAccountUUID(@Param("in_account_uuid") String accountUUID) ;

}
