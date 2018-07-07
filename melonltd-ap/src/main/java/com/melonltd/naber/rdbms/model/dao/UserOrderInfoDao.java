package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.UserOrderInfo;

public interface UserOrderInfoDao extends JpaRepository<UserOrderInfo, String> {
	
	@Query("SELECT a FROM UserOrderInfo a WHERE a.accountUUID = ?1 AND a.enable = 'Y'")
	public Page<UserOrderInfo> findByAccountUUIDAndPage(String accountUUID, Pageable pageable);
	
	@Query("SELECT a FROM UserOrderInfo a WHERE a.accountUUID = ?1 AND a.enable = 'Y' AND a.status IN ('UNFINISH', 'PROCESSING','CAN_FETCH')")
	public List<UserOrderInfo> findByOrderStatusAccountUUID(String accountUUID);
	
	@Query("SELECT a FROM UserOrderInfo a WHERE a.accountUUID = ?1 AND a.enable = 'Y' AND a.status='FAIL' ")
	public List<UserOrderInfo> findByOrderFailByAccountUUID(String accountUUID);

	@Transactional
	@Modifying
	@Query("UPDATE UserOrderInfo a SET a.status = ?2 WHERE a.orderUUID = ?1")	
	public void updateOrderStatus(String orderUUID, String status);
	
	@Transactional
	@Modifying
	@Query("UPDATE UserOrderInfo a SET a.status=?2, a.updateDate=?3 WHERE a.orderUUID IN(?1)")	
	public void updateOrderStatusByorderUUIDs(List<String> orderUUIDs, String status, String now);
}
