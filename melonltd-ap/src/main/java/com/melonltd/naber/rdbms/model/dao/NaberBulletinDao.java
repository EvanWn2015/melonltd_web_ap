package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.NaberBulletin;

public interface NaberBulletinDao extends JpaRepository<NaberBulletin, String> {

	@Query("SELECT a FROM NaberBulletin a WHERE a.enable='Y'")
	public List<NaberBulletin> findAllByEnable();
}
