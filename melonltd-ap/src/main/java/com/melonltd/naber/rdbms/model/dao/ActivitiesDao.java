package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.Activities;

public interface ActivitiesDao extends JpaRepository<Activities, String> {

	@Query("SELECT a FROM Activities a WHERE a.actUUID=?1")
	public Activities findByActivitiesUUID(String uuid);
	
	@Query("SELECT a FROM Activities a WHERE a.actCategory=?1 AND a.restrictFunc=?2 AND a.status='OPEN' AND a.enable='Y' AND ((?2 BETWEEN a.manDate AND a.expDate) OR (a.manDate IS NULL OR a.expDate IS NULL)) ")
	public List<Activities> findByActCategoryAnfFunc(String actCategory, String func, Sort sort);

	@Query("SELECT a FROM Activities a WHERE LENGTH(a.serial)=8 AND a.serial=?1 AND a.status='OPEN' AND a.enable='Y' AND ((?2 BETWEEN a.manDate AND a.expDate) OR (a.manDate IS NULL OR a.expDate IS NULL)) ")
	public Activities findBySerial(String serial, String now);
	
}