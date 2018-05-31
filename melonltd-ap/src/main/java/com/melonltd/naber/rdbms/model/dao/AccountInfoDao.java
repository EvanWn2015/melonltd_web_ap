package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.AccountInfo;
import com.melonltd.naber.rdbms.model.bean.AccountInfo.IDENTITY;

public interface AccountInfoDao extends JpaRepository<AccountInfo, String> {

	@Query("SELECT a FROM AccountInfo a WHERE a.identity = ?1")
	public List<AccountInfo> findByIdentity(IDENTITY identity);
	
}
