package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;

public interface SellerOrderFinishDao extends JpaRepository<SellerOrderFinish, String> {
	
	
	@Query("SELECT o FROM SellerOrderFinish o WHERE o.restaurantUUID=?1 AND (o.updateDate BETWEEN ?2 AND ?3) AND o.enable='Y' AND o.status='FINISH' ")
	public List<SellerOrderFinish> findFinishByBetweenDate(String restaurantUUID, String startDate, String endDate);

}
