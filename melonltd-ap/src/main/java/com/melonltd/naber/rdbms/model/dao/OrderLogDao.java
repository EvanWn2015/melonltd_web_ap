package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.OrderLog;

public interface OrderLogDao extends JpaRepository<OrderLog, String> {

	
	@Transactional
	@Modifying
	@Query("UPDATE OrderLog a SET a.status = ?2 WHERE a.orderUUID = ?1")	
	public void updateOrderStatus(String orderUUID, String status);
}
