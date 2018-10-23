package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.ActivitiesLog;

public interface ActivitiesLogDao extends JpaRepository<ActivitiesLog, Integer> {

	
	@Query("SELECT a FROM ActivitiesLog a WHERE a.accountUUID=?1")
	public List<ActivitiesLog> findByAccountUUID(String accountUUID);
	
	@Query("SELECT a FROM ActivitiesLog a WHERE a.accountUUID=?1 AND a.serial=?2")
	public List<ActivitiesLog> findByAccountUUIDAndSerial(String accountUUID, String serial);
	
	@Query("SELECT a FROM ActivitiesLog a WHERE a.accountUUID=?1 AND a.actUUID=?2")
	public List<ActivitiesLog> findByAccountUUIDAndActUUID(String accountUUID, String actUUID);
	
	@Query("SELECT a FROM ActivitiesLog a WHERE a.actUUID=?1")
	public List<ActivitiesLog> findByActUUID(String actUUID);
	
	
}
