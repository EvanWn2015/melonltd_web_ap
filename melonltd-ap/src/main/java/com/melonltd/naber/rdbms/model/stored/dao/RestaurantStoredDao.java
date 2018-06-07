package com.melonltd.naber.rdbms.model.stored.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.stored.bean.RestaurantStored;

public interface RestaurantStoredDao extends CrudRepository<RestaurantStored, String> {
	
	/**
	 * 依照距離搜索，並限制20公里內
	 */
	@Query(value = "{CALL get_restaurant_distance_by_latlng (:in_latitude, :in_longitude, :in_page)}", nativeQuery = true)
    public List<RestaurantStored> getRestaurantDistanceByLatlng(@Param("in_latitude") String latitude , @Param("in_longitude") String longitude, @Param("in_page") int page);

	
	/**
	 * 依照推薦排名
	 */
	@Query(value = "{CALL get_restaurant_top_by_latlng (:in_latitude, :in_longitude, :in_top)}", nativeQuery = true)
	public List<RestaurantStored> getRestaurantTopByLatlng(@Param("in_latitude") String latitude , @Param("in_longitude") String longitude, @Param("in_top") int top);
	
	
	/**
	 * 依照區域
	 */
	@Query(value = "{CALL get_restaurant_by_area (:in_area, :in_page )}", nativeQuery = true)
	public List<RestaurantStored> getRestaurantByArea(@Param("in_area") String in_area ,@Param("in_page") int page );
	
	
	/**
	 * 依照餐館種類
	 */
	@Query(value = "{CALL get_restaurant_by_category (:in_category, :in_page )}", nativeQuery = true)
	public List<RestaurantStored> getRestaurantByCategory(@Param("in_category") String category ,@Param("in_page") int page );
	
}
