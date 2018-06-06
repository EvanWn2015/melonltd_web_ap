package com.melonltd.naber.rdbms.model.dao.stored.procedure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public interface LoginDao extends CrudRepository<AccountInfo, String> {
	
	@Query(value = "{CALL account_login (:in_phone, :in_password, :in_date, :in_device_uuid, :in_device_token, :in_device_category)}", nativeQuery = true)
	public AccountInfo checkLogin(@Param("in_phone") String phone, @Param("in_password") String password,  @Param("in_date") String loginDate, @Param("in_device_uuid") String deviceUUID, @Param("in_device_token") String deviceToken, @Param("in_device_category") String deviceCategory) ;

}
