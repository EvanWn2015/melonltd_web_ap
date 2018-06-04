package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public interface AccountInfoDao extends JpaRepository<AccountInfo, String> {

	@Query("SELECT a FROM AccountInfo a WHERE a.accountUUID = ?1")	
	public AccountInfo findByAccountUUID(String uuid);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone = ?1")	
	public AccountInfo findByPhone(String phone);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone = ?1 AND a.password = ?2")	
	public AccountInfo findByPhoneAndPassword(String phone, String password );
	
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE AccountInfo a SET a.accountUUID = ?1 WHERE a.accountUUID = ?2")	
//	public void update(String newUUID, String oldUUID );
	
}
