package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.Advertisement;


public interface AdvertisementDao extends JpaRepository<Advertisement, String> {

	@Query("SELECT a FROM Advertisement a WHERE a.enable='Y'")
	public List<Advertisement> findAllByEnable();
}
