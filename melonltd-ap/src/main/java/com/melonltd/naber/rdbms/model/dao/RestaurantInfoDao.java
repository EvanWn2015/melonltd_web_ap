package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;

public interface RestaurantInfoDao extends JpaRepository<RestaurantInfo, String> {

	@Query("SELECT a FROM RestaurantInfo a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y'")
	public RestaurantInfo findUUID(String uuid);
	
	@Query("SELECT r FROM RestaurantInfo r, AccountInfo a WHERE r.restaurantUUID=a.restaurantUUID AND a.accountUUID=?1 AND a.enable='Y' ")
	public RestaurantInfo findByAccountUUID(String accountUUID);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.restaurantUUID IN (?1)")
	public List<RestaurantInfo> findUUIDs(@NotNull List<String> uuids);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.top>'0'")
	public Page<RestaurantInfo> findByTop(Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.address LIKE %?1% ")
	public Page<RestaurantInfo> findByArea(String area, Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.restaurantCategory=?1")
	public Page<RestaurantInfo> findByCategory(String category, Pageable pageable);
	
	// new in 2018/10/05 
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.restaurantCategory NOT IN (?1) ")
	public Page<RestaurantInfo> findByNotInCategory(List<String> notIn, Pageable pageable);

	// new in 2018/10/05
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.address LIKE %?1% AND a.name LIKE %?2% ")
	public Page<RestaurantInfo> findByAreaAndName(String area, String name, Pageable pageable);
	
	// TODO 預留 依照學餐且依照學校名稱查詢，名稱需對應 top值
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.restaurantCategory=?1 AND a.top=?2")
	public Page<RestaurantInfo> findByCategoryAndTop(String category, String top, Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.name LIKE %?1% ")
	public Page<RestaurantInfo> findByAdd(String name, Pageable pageable);
	
	
	
	// for Admin
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.restaurantUUID = ?1")
	public RestaurantInfo findUUIDForAdmin(String uuid);
	
	@Transactional
	@Modifying
	@Query("UPDATE RestaurantInfo a SET a.canDiscount=?1 WHERE a.restaurantUUID=?2")	
	public void updateCanDiscount(String canDiscount, String restaurantUUID);
	
	
	@Transactional
	@Modifying
	@Query("UPDATE RestaurantInfo a SET a.enable=?1 WHERE a.restaurantUUID=?2")	
	public void updateEnable(String enable, String restaurantUUID);
	
}
