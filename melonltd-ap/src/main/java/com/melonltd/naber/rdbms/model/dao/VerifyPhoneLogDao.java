package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.VerifyPhoneLog;

public interface VerifyPhoneLogDao extends JpaRepository<VerifyPhoneLog, String> {
	

	@Query("SELECT a FROM VerifyPhoneLog a WHERE a.phone = ?1")	
	public VerifyPhoneLog findByPhoneNumber(String phoneNumber);
	
	
	@Query("SELECT a FROM VerifyPhoneLog a WHERE a.phone = ?1 AND a.verifyCode = ?2")	
	public VerifyPhoneLog findByPhoneNumberAndCode(String phoneNumber, String code);

}
