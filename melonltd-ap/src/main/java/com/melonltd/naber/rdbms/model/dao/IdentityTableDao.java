package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.IdentityTable;

import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Status;

public interface IdentityTableDao extends JpaRepository<IdentityTable, Long> {

//	@Query("SELECT a FROM IdentityTable a WHERE a.enable=?1 AND a.status=?2")
	public List<IdentityTable> findByEnableAndStatus(Enable enable, Status status);
	
	@Query("SELECT a FROM IdentityTable a WHERE a.area IN(?1)")
	public List<IdentityTable> findByAreas(List<String> areas);
}
