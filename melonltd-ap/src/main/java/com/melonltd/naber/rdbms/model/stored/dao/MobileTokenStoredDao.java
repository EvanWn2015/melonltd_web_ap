package com.melonltd.naber.rdbms.model.stored.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MobileTokenStoredDao extends CrudRepository<MobileDeviceStord, String> {
	
	@Query(value = "{CALL get_mobile_token_by_account_uuid (:in_account_uuid)}", nativeQuery = true)
	public MobileDeviceStord findByAccountUUID(@Param("in_account_uuid") String accountUUID);
	
	@Query(value = "{CALL get_seller_mobile_token_by_restaurant_uuid (:in_restaurant_uuid)}", nativeQuery = true)
	public MobileDeviceStord findByRestaurantUUID(@Param("in_restaurant_uuid") String restaurantUUID);

}
