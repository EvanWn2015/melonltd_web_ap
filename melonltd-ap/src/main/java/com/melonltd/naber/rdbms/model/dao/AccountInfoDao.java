package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public interface AccountInfoDao extends JpaRepository<AccountInfo, String> {

	@Query("SELECT a FROM AccountInfo a WHERE a.accountUUID = ?1")	
	public AccountInfo findByAccountUUID(String uuid);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.account = ?1")	
	public AccountInfo findByAccount(String account);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.account = ?1 AND a.password = ?2")	
	public AccountInfo findByPhoneAndAccount(String account, String password );
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone=?1 AND a.email=?2 AND a.enable='Y'")	
	public AccountInfo findByPhoneAndMail(String phone, String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE AccountInfo a SET a.password = ?1 WHERE a.phone = ?2 AND a.accountUUID= ?3")	
	public void updatePasswordByPhoneAndUUID(String password, String phone,  String accountUUID);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE AccountInfo a SET a.photo = ?1 WHERE a.phone = ?2 AND a.accountUUID=?3")	
	public void updatePhoto(String photo, String phone, String accountUUID);
	
}
