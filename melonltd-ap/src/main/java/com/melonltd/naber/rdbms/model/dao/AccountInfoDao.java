package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;

public interface AccountInfoDao extends JpaRepository<AccountInfo, String> {


	@Query("SELECT a FROM AccountInfo a WHERE a.accountUUID = ?1")	
	public List<AccountInfo> findByAccountUUID(String uuid);
	
	@Query("SELECT a FROM AccountInfo a WHERE a.phone = ?1 AND a.password = ?2")	
	public List<AccountInfo> findByPhoneAndPassword(String phone, String password );
	
}
