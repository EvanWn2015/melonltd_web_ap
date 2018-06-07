package com.melonltd.naber.rdbms.model.stored.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
//public interface TestDao {
//public interface TestStored {
public interface TestDao extends JpaRepository<SellerRegistered, String> {

	
//	@QueryHint(name=QueryHints.RESULT_TYPE, value=ResultType.Map)
	
	@Query(value = "{CALL select_seller (:phone)}", nativeQuery = true)
	public List<SellerRegistered> text(@Param("phone") String phone);
//	public Map text(@Param("phone") String phone);
	
//	@Query("select firstname as firstname, lastname as lastname from User u where u.firstname = 'Oliver'")
//	 Map<String, Object> findMapWithNullValues()

}
