package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.AppVersionLog;
import com.melonltd.naber.rdbms.model.type.DeviceCategory;

public interface AppVersionLogDao extends JpaRepository<AppVersionLog, String> {

	@Query("SELECT a FROM AppVersionLog a WHERE a.category=?1 ")	
	public Page<AppVersionLog> findOneByCategory(DeviceCategory category, Pageable pageable);

}
