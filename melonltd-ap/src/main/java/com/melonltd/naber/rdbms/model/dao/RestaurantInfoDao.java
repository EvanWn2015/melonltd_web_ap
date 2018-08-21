package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.RestaurantInfo;

public interface RestaurantInfoDao extends JpaRepository<RestaurantInfo, String> {

	@Query("SELECT a FROM RestaurantInfo a WHERE a.restaurantUUID = ?1 AND a.enable = 'Y'")
	public RestaurantInfo findUUID(String uuid);
	
	@Query("SELECT r FROM RestaurantInfo r, AccountInfo a WHERE r.restaurantUUID=a.restaurantUUID AND a.accountUUID=?1 AND r.enable='Y' AND a.enable='Y' ")
	public RestaurantInfo findByAccountUUID(String accountUUID);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.restaurantUUID IN (?1)")
	public List<RestaurantInfo> findUUIDs(@NotNull List<String> uuids);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.top>'0'")
	public Page<RestaurantInfo> findByTop(Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.address LIKE %?1% ")
	public Page<RestaurantInfo> findByArea(String area, Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable = 'Y' AND a.restaurantCategory=?1")
	public Page<RestaurantInfo> findByCategory(String category, Pageable pageable);
	
	@Query("SELECT a FROM RestaurantInfo a WHERE a.enable='Y' AND a.name LIKE %?1% ")
	public Page<RestaurantInfo> findByAdd(String area, Pageable pageable);
}
