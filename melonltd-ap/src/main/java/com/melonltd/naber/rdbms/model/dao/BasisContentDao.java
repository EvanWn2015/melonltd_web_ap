package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.BasisContent;

public interface BasisContentDao extends JpaRepository<BasisContent, String> {

	@Query("SELECT a FROM BasisContent a WHERE a.type=?1 AND a.function=?2")
	public BasisContent findByTypeAndFunction(String type, String function);
	
}
