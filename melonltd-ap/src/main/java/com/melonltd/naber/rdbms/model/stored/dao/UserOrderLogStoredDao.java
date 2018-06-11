package com.melonltd.naber.rdbms.model.stored.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.stored.bean.UserOrderLogStored;

public interface UserOrderLogStoredDao extends CrudRepository<UserOrderLogStored, String> {

	@Query(value = "{CALL get_user_order_log (:in_account_uuid, :in_page)}", nativeQuery = true)
    public List<UserOrderLogStored> getRestaurantDistanceByLatlng(@Param("in_account_uuid") String account_uuid, @Param("in_page") int page);
	
	@Query(value = "{CALL get_category_food_status (:in_food_uuids)}", nativeQuery = true)
	public String[] getFoodStatusByUUIDs(@Param("in_food_uuids") String food_uuids);
	
	
	@Query(value = "{CALL subimt_ordaer (:in_order_uuid,:in_account_uuid,:in_restaurant_uuid,:in_restaurant_name,:in_restaurant_address,:in_user_massage,:in_data,:in_order_price,:in_order_bonus,:in_fetch_date,:in_order_data)}", nativeQuery = true)
	public UserOrderLogStored submitOrder (
			@Param("in_order_uuid") String order_uuid,
			@Param("in_account_uuid") String account_uuid,
			@Param("in_restaurant_uuid") String restaurant_uuid,
			@Param("in_restaurant_name") String restaurant_name,
			@Param("in_restaurant_address") String restaurant_address,
			@Param("in_user_massage") String user_massage,
			@Param("in_data") String data,
			@Param("in_order_price") String order_price,
			@Param("in_order_bonus") String order_bonus,
			@Param("in_fetch_date") String fetch_date,
			@Param("in_order_data") String order_data);
}
