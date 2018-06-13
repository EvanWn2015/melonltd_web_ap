package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.UserOrderLog;

public interface UserOrderLogDao extends JpaRepository<UserOrderLog, String> {
	
	@Query("SELECT a FROM UserOrderLog a WHERE a.accountUUID = ?1 AND a.enable = 'Y' ORDER BY a.fetchDate DESC ")
	public Page<UserOrderLog> findByAccountUUIDAndPage(String accountUUID, Pageable pageable);

}
