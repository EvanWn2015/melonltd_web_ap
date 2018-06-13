package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public interface AccountInfoDao extends JpaRepository<AccountInfo, String> {

	@Query("SELECT a FROM AccountInfo a WHERE a.accountUUID = ?1")	
	public AccountInfo findByAccountUUID(String uuid);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone = ?1")	
	public AccountInfo findByPhone(String phone);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone = ?1 AND a.password = ?2")	
	public AccountInfo findByPhoneAndPassword(String phone, String password );
	
	@Transactional
	@Modifying
	@Query("UPDATE AccountInfo a SET a.password = ?1 WHERE a.phone = ?2 AND a.accountUUID= ?3")	
	public void updatePasswordByPhoneAndUUID(String password, String phone,  String accountUUID);
	
}
